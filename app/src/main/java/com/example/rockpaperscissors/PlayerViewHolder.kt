package com.example.rockpaperscissors

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.model.Players

class PlayerViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    fun bind(players: Players) {
        val tvName: TextView = itemView.findViewById(R.id.item_tv_name)
        val tvAge: TextView = itemView.findViewById(R.id.item_tv_wins)
        tvName.text = players.name
        tvAge.text = "Wins: " + players.wins.toString()



        itemView.setOnClickListener {
            Toast.makeText(itemView.context, "clicked on ${players.name}", Toast.LENGTH_SHORT).show()
        }
    }
}