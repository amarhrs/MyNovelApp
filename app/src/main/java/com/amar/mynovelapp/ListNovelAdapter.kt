package com.amar.mynovelapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ListNovelAdapter(private val listNovel: ArrayList<Novel>) :
    RecyclerView.Adapter<ListNovelAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_novel, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listNovel.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listNovel[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("extra_novel", listNovel[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
            Toast.makeText(
                holder.itemView.context,
                "Kamu memilih " + listNovel[holder.adapterPosition].name,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}