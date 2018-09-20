package me.communes.commune

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import ContactListAdapter
import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.provider.ContactsContract
import android.provider.Telephony.Mms.Addr.CONTACT_ID
import android.support.v4.app.ActivityCompat
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import kotlinx.android.synthetic.main.activity_contact_list.*

private const val LOADER_ID = 0

class ContactListActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {
    private lateinit var contactList: RecyclerView
    private lateinit var contactListAdapter: ContactListAdapter
    private lateinit var contactListManager: RecyclerView.LayoutManager

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
        val projection = arrayOf<String?>(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)

        return CursorLoader(this, loaderUri, projection, null, null, null)
    }

    override fun onLoadFinished(loader: Loader<Cursor>, c: Cursor) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        if (contactListAdapter == null) {
//        } else {
            contactListAdapter.swapCursor(c)
//        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        contactListAdapter.swapCursor(null)
    }
}
