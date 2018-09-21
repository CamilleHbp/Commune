package me.communes.commune.utilities

import android.database.Cursor
import android.support.v7.widget.RecyclerView

abstract class RecyclerViewCursorAdapter<VH : RecyclerView.ViewHolder>(cursor: Cursor?) : RecyclerView.Adapter<VH>() {
    private var cursor: Cursor? = null
    private var isDataValid: Boolean = false
    private var rowIDColumn: Int = 0

    abstract fun onBindViewHolder(holder: VH, cursor: Cursor)

    init {
        setHasStableIds(true)
        swapCursor(cursor)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

        if (!isDataValid) {
            throw IllegalStateException("Cannot bind viewholder when cursor is in invalid state.")
        }
        if (!cursor!!.moveToPosition(position)) {
            throw IllegalStateException("Could not move cursor to position $position when trying to bind viewholder")
        }
        onBindViewHolder(holder, cursor!!)
    }

    override fun getItemCount(): Int {
        return if (isDataValid) {
            cursor!!.count
        } else {
            0
        }
    }

    override fun getItemId(position: Int): Long {
        if (!isDataValid) {
            throw IllegalStateException("Cannot lookup item id when cursor is in invalid state.")
        }
        if (!cursor!!.moveToPosition(position)) {
            throw IllegalStateException("Could not move cursor to position $position when trying to get an item id")
        }

        return cursor!!.getLong(rowIDColumn)
    }

    open fun swapCursor(newCursor: Cursor?) {
        if (newCursor === cursor) {
            return
        }

        if (newCursor != null) {
            cursor = newCursor
            rowIDColumn = cursor!!.getColumnIndexOrThrow("_id")
            isDataValid = true
            // notify the observers about the new cursor
            notifyDataSetChanged()
        } else {
            notifyItemRangeRemoved(0, itemCount)
            cursor = null
            rowIDColumn = -1
            isDataValid = false
        }
    }
}