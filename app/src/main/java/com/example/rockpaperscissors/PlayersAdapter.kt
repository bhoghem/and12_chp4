package com.example.rockpaperscissors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.model.Players

class PlayersAdapter : RecyclerView.Adapter<PlayerViewHolder>() {
    private val dataPlayers: MutableList<Players> = mutableListOf()

    //add function to add item list
    fun addList(data: List<Players>) {
        dataPlayers.addAll(data)
        sort()

        notifyDataSetChanged() //kasi tau ke adapter kalo ada data yang diubah supaya dia retrieve data baru
    }

    //add function to add item single
    fun addItem(dataSingle: Players) {
        dataPlayers.add(dataSingle)
        sort()

        notifyDataSetChanged()
    }

    fun sort() {
        dataPlayers.sortByDescending { it.wins }
//        dataPeople.sortByDescending {it.age}
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_players, parent, false)
        val viewHolder = PlayerViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val people = dataPlayers.get(position) //ini data 1 per 1
        holder.bind(people)
    }

    override fun getItemCount(): Int {
        return dataPlayers.size
    }
}