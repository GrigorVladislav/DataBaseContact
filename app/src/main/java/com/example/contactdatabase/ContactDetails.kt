package com.example.contactdatabase

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.details_activity.*

class ContactDetails:AppCompatActivity() {
    var notification = Notification
    private var entry_id = -1
    lateinit var contact : ContactListInfo
    var realmDB= RepositoryDataBase
    var imageId  = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        entry_id = intent.extras!!.get("id") as Int

        if(intent.hasExtra("imageId")) {
            imageId = intent.extras!!.get("imageId") as Int
        }else{
            imageId = R.drawable.a1
            realmDB.initRealm(this)
        }

        contact = realmDB.getContact(entry_id)!!
        clickButton()
        fillData()
    }

    private fun fillData(){

        details_image.setImageResource(imageId)
        details_name.setText(contact.name)
        details_second_name.setText(contact.secondName)
        details_phone.setText(contact.phone)
        details_email.setText(contact.email)
}
    private fun clickButton(){
        details_update.setOnClickListener {
            if(details_update.text == "Update"){
                updateInfo()
            }else{
                seaveContactInfo()
            }
        }

        details_delete.setOnClickListener {

            realmDB.deleteContact(entry_id)
           val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("listChanged",true)
            startActivity(intent)
        }
    }

    private fun seaveContactInfo() {
        var name = details_name.text.toString()
        var secondName = details_second_name.text.toString()
        var phone = details_phone.text.toString()
        var email = details_email.text.toString()
        realmDB.updateContact(name,secondName,phone,email,entry_id)
        var notificationIntent = Intent(this, ContactDetails::class.java)
        notificationIntent.putExtra("id",entry_id)
        notification.makeNotification(this,"Update contact","Successfully update",R.drawable.edit_white_24dp,notificationIntent)





    }

    private fun updateInfo() {
        details_image.isEnabled = true
        details_name.isEnabled = true
        details_second_name.isEnabled = true
        details_phone.isEnabled = true
        details_email.isEnabled = true
        details_update.text = "Save"
    }

}