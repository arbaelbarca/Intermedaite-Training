package com.arbaelbarca.trainingclassintermediate

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdapterListUsers(
    val listUsers: List<ResponseUsers.ResponseUsersItem>,
    val onClickItem: OnClickItem
) : RecyclerView.Adapter<AdapterListUsers.MyHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_users, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, pos: Int) {
        val dataItemUsers = listUsers[pos]
        holder.tvNameUsers.text = dataItemUsers.name
        holder.tvEmailUsers.text = dataItemUsers.username

        holder.itemView.setOnClickListener {
            onClickItem.clickItem(dataItemUsers, pos)
        }

        holder.imgDeleteUsers.setOnClickListener {
            onClickItem.clickItemDelete(dataItemUsers, pos)
        }
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNameUsers = view.findViewById<TextView>(R.id.tvItemUsers)
        val tvEmailUsers = view.findViewById<TextView>(R.id.tvItemEmailUsers)
        val imgDeleteUsers = view.findViewById<ImageView>(R.id.imgDeleteUsers)
    }

}