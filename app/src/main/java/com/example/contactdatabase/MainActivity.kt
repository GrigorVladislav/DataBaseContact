package com.example.contactdatabase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var notification = Notification
    var realmDB= RepositoryDataBase
    var repository = RepositoryDataBase
    var defaultContact = DefaultContacts
    lateinit var adapter: ContactListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repository.initRealm(this)
        if (repository.getAllContacts().size == 0){
            defaultContact.createDefaultContact()
        }
        setupControls()
        clickFabButton()
    }

    private fun setupControls(){

        adapter = ContactListAdapter( this)
        recyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        registerForContextMenu(recyclerView)
    }

    private fun clickFabButton(){

        fab_add.setOnClickListener{

            val intent = Intent(this,Redactor::class.java)
            startActivity(intent)
        }

        fab_delete.setOnClickListener {
           realmDB.deleteAllContacts()
            adapter.listChanges()
            var notificationIntent =Intent(this, MainActivity::class.java)
            notification.makeNotification(this,"All contacts deleted","Successfully deleted",R.drawable.delete_white_24dp,notificationIntent)

        }
    }
}
