package com.example.loaddanhba

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.loader.content.CursorLoader
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.view.*


class MainActivity : AppCompatActivity() {
    private val PERMISSIONS_REQUEST_READ_CONTACTS = 100
    private var list  = ArrayList<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getdatafromDanhBa()
        recyckerView.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recyckerView.setHasFixedSize(true)
        recyckerView.itemAnimator = DefaultItemAnimator()
        recyckerView.adapter = Adapter(applicationContext, list)
        Log.d("@@@@@@@" ,"list --- ${list.size}")
    }

    private fun getdatafromDanhBa() {
        val projectFile =
            arrayOf(
                ContactsContract.CommonDataKinds.Phone._ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            )
        val curorLoader: CursorLoader = CursorLoader(
            applicationContext, ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projectFile, null, null, null
        )
        val curor: Cursor? = curorLoader.loadInBackground()

        if (curor?.moveToFirst()!!) {
            val indexID = curor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID)
            val indexName =
                curor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val indexPhone = curor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            do {
                val id = curor.getString(indexID)
                val name = curor.getString(indexName)
                val phone = curor.getString(indexPhone)
                list.add(Data(id, name, phone))
                Log.d("@@@@@@", "$id/$name/$phone/  ::  ")
            } while (curor.moveToNext())

        }
    }

    private fun showContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                PERMISSIONS_REQUEST_READ_CONTACTS
            )
        } else {

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showContacts()
            }

        }
    }
}
class Data(id :String,name : String, phone: String){
    var id: String? = null
    var name: String? = null
    var phone: String? = null
    init {
        this.id = id
        this.name = name
        this.phone = phone
    }
}
class Adapter(context: Context, list: ArrayList<Data>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var context : Context? = null
    var list : ArrayList<Data>? = null

    init {
        this.context = context
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return Viewholder(view)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Viewholder).onbind(position)
    }
    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onbind(position: Int) {
            itemView.name.text = list?.get(position)?.name
            itemView.detail.text = list?.get(position)?.phone
        }

    }

}