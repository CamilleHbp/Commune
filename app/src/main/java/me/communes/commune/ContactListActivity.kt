package me.communes.commune

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import ContactListAdapter
import kotlinx.android.synthetic.main.activity_contact_list.*

class ContactListActivity : AppCompatActivity() {
    private lateinit var contactList: RecyclerView
    private lateinit var contactListAdapter: RecyclerView.Adapter<*>
    private lateinit var contactListManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        contactListManager = LinearLayoutManager(this)
        contactListAdapter = ContactListAdapter(FakeData.contactList)
        contactList = recyclerView_contactList
        contactList.layoutManager = contactListManager
        contactList.adapter = contactListAdapter
    }
}
