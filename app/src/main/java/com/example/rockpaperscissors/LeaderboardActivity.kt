package com.example.rockpaperscissors

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.databinding.ActivityLeaderboardBinding
import com.example.rockpaperscissors.model.Players

private lateinit var binding: ActivityLeaderboardBinding

class LeaderboardActivity: AppCompatActivity() {
    private val playersList = listOf<Players>(
        Players("Ucup", 31),
        Players("Anton", 21),
        Players("Naruto", 100),
        Players("Ronaldo", 34),
        Players("Eric", 20)

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val playersRecyclerView: RecyclerView = binding.leadRv
        val playersLayoutManager = LinearLayoutManager(this)
        val playersAdapter = PlayersAdapter()

        playersRecyclerView.layoutManager = playersLayoutManager
        playersRecyclerView.adapter = playersAdapter

        playersAdapter.addList(playersList)


    }
}