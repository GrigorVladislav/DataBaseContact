package com.example.contactdatabase

object DefaultContacts {

    fun createDefaultContact(){

        val repos = RepositoryDataBase
        repos.createToDB("Gena","Ivanov","067-99-123-15","qwerty@gmail.com")
        repos.createToDB("Vasa","Ivanov","067-99-123-15","qwerty@gmail.com")
        repos.createToDB("Igor","Ivanov","067-99-123-15","qwerty@gmail.com")
        repos.createToDB("Leo","Ivanov","067-99-123-15","qwerty@gmail.com")
        repos.createToDB("Denis","Ivanov","067-99-123-15","qwerty@gmail.com")
        repos.createToDB("Ignat","Ivanov","067-99-123-15","qwerty@gmail.com")
        repos.createToDB("Rodion","Ivanov","067-99-123-15","qwerty@gmail.com")
        repos.createToDB("Ruben","Ivanov","067-99-123-15","qwerty@gmail.com")
        repos.createToDB("Izya","Ivanov","067-99-123-15","qwerty@gmail.com")
        repos.createToDB("Kartman","Ivnov","067-99-123-15","qwerty@gmail.com")

    }
}