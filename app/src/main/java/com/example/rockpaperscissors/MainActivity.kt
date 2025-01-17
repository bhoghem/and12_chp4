package com.example.rockpaperscissors

import android.app.Dialog
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
import com.example.rockpaperscissors.databinding.ActivityMainBinding
import com.example.rockpaperscissors.fragment.WinLoseDialogFragment


private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val rock = binding.rock
        val paper = binding.paper
        val scissors = binding.scissors
        val refresh = binding.refresh
        val rockCom = binding.rockCom
        val paperCom = binding.paperCom
        val scissorsCom = binding.scissorsCom
        val versus = binding.versus
        val win = binding.win
        val lose = binding.lose
        val draw = binding.draw
        val close = binding.close
        val pemain1 = binding.pemain1

        var allChoices = listOf<CardView>(rock, paper, scissors, rockCom, paperCom, scissorsCom)
        var comChoices = listOf<CardView>(rockCom, paperCom, scissorsCom)
        val nameData = intent.getStringExtra("NAME_DATA").toString()
        pemain1.text = nameData
        win.text = nameData + "\nMenang"




        fun reset() {
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
                    bundle.putString("NAME_DATA", "CPU")
                }
                else -> {
                    draw.setVisibility(View.VISIBLE)
                }
            }
        }

        fun playerClick(choice: CardView? = null) {
            reset()
            choice?.let{
                activateColor(choice)
                val suitUser: Suit = when (choice) {
                    rock -> BatuAction()
                    paper -> KertasAction()
                    else -> GuntingAction()
                }
                val suitCom = DataSources.getRandomSuit()
                handler.removeCallbacksAndMessages(null); //remove postdelayed animation
                var delay : Long = 0
                for (i in 0..2) {
                    comChoices.map {
                        handler.postDelayed({
                            activateColor(it)
                        }, delay)
                        delay = delay + 500
                        handler.postDelayed({
                            disableColor(it)
                        }, delay)
                    }
                }
                handler.postDelayed({
                    when (suitCom.name) {
                        "batu" -> activateColor(rockCom)
                        "kertas" -> activateColor(paperCom)
                        else -> activateColor(scissorsCom)
                    }
                    val toastMessage = "CPU Memilih " + suitCom.name.capitalize()
                    Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
                    var result = suitUser.action(suitCom.name)
                    versus.setVisibility(View.INVISIBLE)
                    showMessage(result)

                    bundle.putString("RESULT", result)
                    dialogWinLoseFragment.setArguments(bundle)
                    dialogWinLoseFragment.show(supportFragmentManager, null)
                }, delay)

//                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
            }
        }





        rock.setOnClickListener{
            playerClick(rock)
            Toast.makeText(this, nameData + " Memilih Batu", Toast.LENGTH_SHORT).show()
        }
        paper.setOnClickListener{
            playerClick(paper)
            Toast.makeText(this, nameData + " Memilih Kertas", Toast.LENGTH_SHORT).show()
        }
        scissors.setOnClickListener{
           playerClick(scissors)
           Toast.makeText(this, nameData + " Memilih Gunting", Toast.LENGTH_SHORT).show()
        }
        refresh.setOnClickListener{
            playerClick()
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