package com.arbaelbarca.trainingclassintermediate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arbaelbarca.trainingclassintermediate.db.entity.EntityMahasiswa

class AdapterListRoom(
    val listEntityMahasiswa: MutableList<EntityMahasiswa>,
    val onClickItem: OnClickItem
) :
    RecyclerView.Adapter<AdapterListRoom.MyHolderRoom>() {

    class MyHolderRoom(view: View) : RecyclerView.ViewHolder(view) {
        val tvUsername = view.findViewById<TextView>(R.id.tvItemUsernameRoom)
        val tvEmail = view.findViewById<TextView>(R.id.tvItemEmailRoom)
        val tvName = view.findViewById<TextView>(R.id.tvItemNameRoom)
        val imgDelete = view.findViewById<ImageView>(R.id.imgDeleteRoom)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderRoom {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_mahasiswa, parent, false)
        return MyHolderRoom(view)
    }

    override fun onBindViewHolder(holder: MyHolderRoom, position: Int) {
        val dataMahasiswa = listEntityMahasiswa[position]
        holder.tvUsername.text = dataMahasiswa.username
        holder.tvEmail.text = dataMahasiswa.email
        holder.tvName.text = dataMahasiswa.name

        holder.itemView.setOnClickListener {
            onClickItem.clickItem(dataMahasiswa, position)
        }

        holder.imgDelete.setOnClickListener {
            onClickItem.clickItemDelete(dataMahasiswa, position)
        }
    }

    override fun getItemCount(): Int {
        return listEntityMahasiswa.size
    }
}