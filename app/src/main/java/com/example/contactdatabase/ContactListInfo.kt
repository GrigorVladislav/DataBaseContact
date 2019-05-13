package com.example.contactdatabase

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class ContactListInfo(): RealmObject() {
    @PrimaryKey
    var id : Int = 0
    var imageId:Int? = null
    var name : String = ""
    var secondName:String = ""
    var phone : String = ""
    var email : String = ""

}