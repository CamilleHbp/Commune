package me.communes.commune

import ContactListAdapter
import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import kotlinx.android.synthetic.main.activity_contact_list.*

private const val LOADER_ID = 0
private const val SELECTION = "${ContactsContract.Contacts.DISPLAY_NAME_PRIMARY} LIKE ?"

class ContactListActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {
    private lateinit var contactList: RecyclerView
    private lateinit var contactListAdapter: ContactListAdapter
    private lateinit var contactListManager: RecyclerView.LayoutManager
    private var searchString: String? = null
    private var selectionArgs = arrayOf("") // TODO("Fake implementation") // Replace the wildcard by a proper search string")
    private var sortOrder = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " COLLATE NOCASE ASC";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        if( applicationContext.checkSelfPermission( Manifest.permission.READ_CONTACTS ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 1);
        }
        supportLoaderManager.initLoader(LOADER_ID, null, this)
        contactListAdapter = ContactListAdapter(null)
        contactListManager = LinearLayoutManager(this)
        contactList = recyclerView_contactList
        contactList.adapter = contactListAdapter
        contactList.layoutManager = contactListManager
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        val loaderUri = ContactsContract.Contacts.CONTENT_URI
        val projection = arrayOf<String?>(
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
                ContactsContract.Contacts.PHOTO_THUMBNAIL_URI
        )
        selectionArgs[0] = searchString?:"%"


        return CursorLoader(
                this,       // Activity context
                loaderUri,         // Contacts db table
                projection,        // Columns to return
                SELECTION,         // Query to select data in table
                selectionArgs,     // Arguments to the selection query
                sortOrder     // Sort order
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        var searchItem = menu?.findItem(R.id.item_searchView)
        var searchView= searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchString = "%$query%"
                selectionArgs = arrayOf(searchString?:"")
                searchView.clearFocus();
                supportLoaderManager.restartLoader(LOADER_ID, null, this@ContactListActivity)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchString = "%$newText%"
                selectionArgs = arrayOf(searchString?:"")
                supportLoaderManager.restartLoader(LOADER_ID, null, this@ContactListActivity)
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onLoadFinished(loader: Loader<Cursor>, c: Cursor) = contactListAdapter.swapCursor(c)

    override fun onLoaderReset(loader: Loader<Cursor>) = contactListAdapter.swapCursor(null)
}
