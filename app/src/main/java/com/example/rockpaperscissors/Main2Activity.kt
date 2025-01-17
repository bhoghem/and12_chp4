package com.example.rockpaperscissors

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.rockpaperscissors.action.BatuAction
import com.example.rockpaperscissors.action.GuntingAction
import com.example.rockpaperscissors.action.KertasAction
import com.example.rockpaperscissors.databinding.ActivityMain2Binding
import com.example.rockpaperscissors.fragment.WinLoseDialogFragment


private lateinit var binding: ActivityMain2Binding
class Main2Activity : AppCompatActivity() {
    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val rock = binding.rock
        val paper = binding.paper
        val scissors = binding.scissors
        val refresh = binding.refresh
        val rock2 = binding.rockCom
        val paper2 = binding.paperCom
        val scissors2 = binding.scissorsCom
        val versus = binding.versus
        val win = binding.win

        val lose = binding.lose
        val draw = binding.draw
        val close = binding.close
        val pemain1 = binding.pemain1

        var allChoices = listOf<CardView>(rock, paper, scissors, rock2, paper2, scissors2)
        var comChoices = listOf<CardView>(rock2, paper2, scissors2)
        val nameData = intent.getStringExtra("NAME_DATA").toString()
        pemain1.text = nameData
        win.text = nameData + "\nMenang"
        var picked = false
        var player1pick : Suit? = null
        var player2pick : String? = null
        var gameDone = false




        fun reset() {
            gameDone = false
            player1pick = null
            player2pick = null
            picked = false
            allChoices.map { it.setCardBackgroundColor(Color.WHITE) }
            versus.setVisibility(View.VISIBLE)
            win.setVisibility(View.INVISIBLE)
            lose.setVisibility(View.INVISIBLE)
            draw.setVisibility(View.INVISIBLE)
        }

        fun activateColor(choice: CardView) {
            choice.setCardBackgroundColor(Color.parseColor("#C3DAE9"))
        }

        fun disableColor(choice: CardView) {
            choice.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
        }



        val dialogWinLoseFragment = WinLoseDialogFragment()
        val bundle = Bundle()

        fun showMessage(condition: String) {
            when (condition) {
                "win" -> {
                    win.setVisibility(View.VISIBLE)
                    bundle.putString("NAME_DATA", nameData)

                }
                "lose" -> {
                    lose.setVisibility(View.VISIBLE)
                    bundle.putString("NAME_DATA", "Pemain 2")
                }
                else -> {
                    draw.setVisibility(View.VISIBLE)
                }
            }
        }

        fun result(suitPlayer1 : Suit, suitPlayer2 : String) {
            var result = suitPlayer1.action(DataSources.convertStringToData(suitPlayer2).name)
            versus.setVisibility(View.INVISIBLE)
            showMessage(result)
            bundle.putString("RESULT", result)
            dialogWinLoseFragment.setArguments(bundle)
            dialogWinLoseFragment.show(supportFragmentManager, null)
            gameDone = true
        }

        fun playerClick(choice: CardView) {
            if (gameDone || player1pick != null) {
                return
            }
            choice?.let{
                activateColor(choice)
                val suitPlayer1: Suit = when (choice) {
                    rock -> BatuAction()
                    paper -> KertasAction()
                    else -> GuntingAction()
                }
                if (picked) {
                    player2pick?.let { it1 -> result(suitPlayer1, it1) }
                } else {
                    player1pick = suitPlayer1
                    picked = true
                }
            }
        }

        fun player2Click(choice: CardView? = null) {
            if (gameDone || player2pick != null) {
                return
            }
            choice?.let{
                activateColor(choice)
                val suitUser: String = when (choice) {
                    rock2 -> "batu"
                    paper2 -> "kertas"
                    else -> "gunting"
                }
                if (picked) {
                    player1pick?.let { it1 -> result(it1, suitUser) }
                } else {
                    player2pick = suitUser
                    picked = true
                }
            }
        }







        rock.setOnClickListener{
            if (!gameDone) {
                if (player1pick == null) {
                    Toast.makeText(this, nameData + " Memilih Batu", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Tekan Reset Dulu!", Toast.LENGTH_SHORT).show()
            }
            playerClick(rock)

        }
        paper.setOnClickListener{
            if (!gameDone) {
                if (player1pick == null) {
                    Toast.makeText(this, nameData + " Memilih Kertas", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Tekan Reset Dulu!", Toast.LENGTH_SHORT).show()
            }
            playerClick(paper)

        }
        scissors.setOnClickListener{
            if (!gameDone) {
                if (player1pick == null) {
                    Toast.makeText(this, nameData + " Memilih Gunting", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Tekan Reset Dulu!", Toast.LENGTH_SHORT).show()
            }
            playerClick(scissors)

        }
        rock2.setOnClickListener{
            if (!gameDone) {
                if (player2pick == null) {
                    Toast.makeText(this, "Player 2 Memilih Batu", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Tekan Reset Dulu!", Toast.LENGTH_SHORT).show()
            }
            player2Click(rock2)

        }
        paper2.setOnClickListener{
            if (!gameDone) {
                if (player2pick == null) {
                    Toast.makeText(this, "Player 2 Memilih Kertas", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Tekan Reset Dulu!", Toast.LENGTH_SHORT).show()
            }
            player2Click(paper2)

        }
        scissors2.setOnClickListener{
            if (!gameDone) {
                if (player2pick == null) {
                    Toast.makeText(this, "Player 2 Memilih Gunting", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Tekan Reset Dulu!", Toast.LENGTH_SHORT).show()
            }
            player2Click(scissors2)
        }
        refresh.setOnClickListener{
            reset()
            handler.removeCallbacksAndMessages(null); //remove postdelayed animation
        }
        close.setOnClickListener{
            onBackPressed()
            handler.removeCallbacksAndMessages(null); //remove postdelayed animation
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        handler.removeCallbacksAndMessages(null); //remove postdelayed animation
    }
}