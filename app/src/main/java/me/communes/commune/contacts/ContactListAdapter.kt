
import Contact
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recyclerview_contact_row.view.*
import me.communes.commune.R.layout.recyclerview_contact_row

class ContactListAdapter(private val contacts: Array<Contact>) : RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(recyclerview_contact_row, parent, false)
        return ContactViewHolder(inflatedView)
    }

    override fun getItemCount()= contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val itemContact = contacts[position]
        holder.bindContact(itemContact)
    }

    class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
//            TODO("Fake implementation") //To change body of created functions use File | Settings | File Templates.
            Log.d("RECYCLERVIEW", "${v?.textView_firstName?.text} ${v?.textView_lastName?.text} clicked!")
        }

        fun bindContact(contact: Contact) = with(itemView) {
            textView_firstName.setText(contact.firstName)
            textView_lastName.setText(contact.lastName)
            textView_phoneNumber.setText(contact.phoneNumber)
//            textView_lastName.text = contact.lastName
//            textView_phoneNumber.text = contact.phoneNumber
        }
    }
}