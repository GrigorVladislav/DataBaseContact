package com.example.contactdatabase

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration
import java.lang.Exception

object RepositoryDataBase {

    private var realmBD : Realm? = null

    fun initRealm(context: Context){
        if (realmBD == null) {
            Realm.init(context)
            val realmConfiguration = RealmConfiguration.Builder()
                .name("todo_db.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build()
            realmBD = Realm.getInstance(realmConfiguration)
            Realm.setDefaultConfiguration(realmConfiguration)
        }
    }

    fun createToDB(name:String,secondName:String,phone:String,email:String){

        realmBD!!.beginTransaction()
        var contactItem = realmBD!!.createObject(ContactListInfo::class.java, getID())
//        contactItem.id = getID()
        contactItem.name = name
        contactItem.secondName = secondName
        contactItem.phone = phone
        contactItem.email = email
        realmBD!!.commitTransaction()
    }
    fun updateContact(name:String,secondName:String,phone:String,email:String,id:Int){

        realmBD!!.executeTransactionAsync(Realm.Transaction { realm ->
            val contactItem = realm.where(ContactListInfo::class.java!!).equalTo("id",id).findFirst()
            if (contactItem != null) {
                contactItem.name = name
                contactItem.secondName = secondName
                contactItem.phone = phone
                contactItem.email = email
            }
        })
    }

    fun deleteContact(id:Int){
        realmBD!!.executeTransactionAsync(Realm.Transaction { realm ->
            val contactItem = realm.where(ContactListInfo::class.java!!).equalTo("id",id).findFirst()
            contactItem!!.deleteFromRealm()
        })
    }

    fun deleteAllContacts(){
        realmBD!!.executeTransactionAsync(Realm.Transaction { realm ->
            val contactItem = realm.where(ContactListInfo::class.java).findAll()
            contactItem.deleteAllFromRealm()
        })
    }

     fun getContact(id: Int):ContactListInfo? {
            var contact :ContactListInfo? = null
            var items = getAllContacts()
         for (item in items){
             if (item.id==id){
                 contact = item
             }
         }

//         realmBD.executeTransactionAsync(Realm.Transaction { realm ->
//             contact = realm.where(ContactListInfo::class.java).equalTo("id",id).findFirst()!!
//         })
         return contact
//         var contactList = ContactListInfo()
////         try {
////                 realmBD.executeTransaction {
////                    contactList = realmBD.where(ContactListInfo::class.java).equalTo("Id", id).findFirst() !!
////
////                 }
////             } catch (e:Exception) {
////
////             }
////             return contactList

     }

    fun getLastId():Int?{
        var lastId:Int? = -1
        realmBD!!.executeTransaction(Realm.Transaction { realm ->
            val contactItem = realm.where(ContactListInfo::class.java!!).max("id")
            lastId = contactItem?.toInt()
    })
        return lastId
    }

    fun getAllContacts():List<ContactListInfo> {

        var contactList = mutableListOf<ContactListInfo>()
        try {
            if (realmBD != null) {
                realmBD?.executeTransaction {
                    var realmBD = realmBD?.where(ContactListInfo::class.java)?.findAll()!!

                    for (data in realmBD) {
                        contactList.add(data)
                    }
                }
            }else{}
        }catch (e:Exception){

        }
        return contactList
    }



    fun getID(): Int{

        var currentId  = realmBD?.where(ContactListInfo::class.java)?.max("id")
        if (currentId == null){
            return 0
        }else {
            return currentId!!.toInt() + 1
        }
    }



}