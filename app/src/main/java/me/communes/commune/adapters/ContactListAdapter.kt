
import android.content.ContentUris
import android.database.Cursor
import android.provider.ContactsContract
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyclerview_contact_row.view.*
import me.communes.commune.R
import me.communes.commune.utilities.RecyclerViewCursorAdapter

class ContactListAdapter(cursor: Cursor?) : RecyclerViewCursorAdapter<ContactListAdapter.ContactViewHolder>(cursor) {

    override fun onBindViewHolder(holder: ContactViewHolder, cursor: Cursor) {
        holder.bindCursor(cursor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_contact_row, parent, false)
        return ContactViewHolder(v)
    }

    class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
//            TODO("Fake implementation") //To change body of created functions use File | Settings | File Templates.
            Log.d("RECYCLERVIEW", "${v?.textView_displayName?.text} clicked!")
        }

        fun bindCursor(cursor: Cursor) = with(itemView) {
            //I put code here to load item data into the various views in my ViewHolder.
            textView_displayName.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)))
            Picasso.get().load(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI))).into(imageView_picture)
        }
    }
}

