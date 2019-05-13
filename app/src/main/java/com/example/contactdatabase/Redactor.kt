package com.example.contactdatabase

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_redactor.*

class Redactor():AppCompatActivity() {

    val realmBD = RepositoryDataBase
    var notification = Notification

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redactor)

        save_redactor_button.setOnClickListener {
            addContactToRecycler()
        }

    }


    private fun addContactToRecycler() {

        var name = add_name.text.toString()
        var secondName = add_second_name.text.toString()
        var phone = add_phone.text.toString()
        var email = add_email.text.toString()
        if(validateFields(name, secondName, phone, email)){
            add_name.setText("")
            add_second_name.setText("")
            add_phone.setText("")
            add_email.setText("")
            realmBD.createToDB(name,secondName,phone,email)
            var lastId = realmBD.getLastId()
            var notificationIntent = Intent(this, ContactDetails::class.java)
            notificationIntent.putExtra("id",lastId)
            notification.makeNotification(this,"Add contact","Successfully add",R.drawable.add_white_24dp,notificationIntent)


        }


    }

    private fun validateFields(name: String, secondName: String, phone: String, email: String):Boolean {

        if (name.length <2 ){
            showAlert("Field name must contain more then 2 letters")
        }
        else if(secondName.length<2){
            showAlert("Field Second name must contain more then 2 letters")
        }
        else if(phone.length >9||phone.length<6){
            showAlert("Field phone number must contain 6-9 digits")
        }
        else if (!email.contains('@')){
            showAlert("Field email must contain @ symbol")
        }
        else{
            return true
        }
        return false
    }

    private fun showAlert(message:String) {

        val builder = AlertDialog.Builder(this)
       builder.setTitle("Error")
        builder.setMessage(message)
        builder.show()
    }
}