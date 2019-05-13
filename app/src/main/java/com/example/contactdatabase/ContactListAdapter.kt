package com.example.contactdatabase

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.contact_card.view.*

class ContactListAdapter(val context:Context): RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    var images= arrayListOf<Int>(R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5,
        R.drawable.a6,R.drawable.a7,R.drawable.a8,R.drawable.a9,R.drawable.a10)
    var repo = RepositoryDataBase
    var items = repo.getAllContacts()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      return ViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_card,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        var randomImage = getRandomImage()
        holder.contactName.text = item.name
        holder.secondName.text = item.secondName
        holder.contactIcon.setImageResource(randomImage)
        holder.ClickItem(item.id,randomImage)

    }

    override fun getItemCount(): Int {
        var count = repo.getAllContacts().size
        return count
    }

    fun getRandomImage():Int {
       var index = (0..images.count()-1).random()
        return images[index]
    }
    fun listChanges(){
        notifyDataSetChanged()
    }
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val contactName = itemView.contact_name
        val secondName = itemView.contact_second_name
        val contactIcon = itemView.contact_icon

        fun ClickItem (id:Int,imageId:Int){
            view.setOnClickListener {
                val intent = Intent(view.context,ContactDetails::class.java)
                intent.putExtra("id", id)
                intent.putExtra("imageId",imageId)
        view.context.startActivity(intent) }
        }
    }
}