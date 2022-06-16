package com.example.rockpaperscissors

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.rockpaperscissors.fragment.Slide1Fragment
import com.example.rockpaperscissors.fragment.Slide2Fragment
import com.example.rockpaperscissors.fragment.Slide3Fragment
import com.example.rockpaperscissors.fragment.Slide4Fragment
import com.github.appintro.AppIntro

class RPSIntro : AppIntro() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        addSlide(Slide1Fragment())
        addSlide(Slide2Fragment())
        addSlide(Slide3Fragment())
        addSlide(Slide4Fragment())
        setIndicatorColor(Color.parseColor("#0070C0"), Color.parseColor("#C3DAE9"))
        setColorSkipButton(Color.parseColor("#0070C0"))
        setColorDoneText(Color.parseColor("#0070C0"))
        var seperator : View = findViewById(com.github.appintro.R.id.bottom_separator)
        seperator.setBackgroundColor(Color.parseColor("#ffffff"))
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        var viewPager : ViewPager = findViewById(com.github.appintro.R.id.view_pager)
        viewPager.setCurrentItem(2)
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        launchToMenu()
    }

    private fun launchToMenu() {
        val menuIntent = Intent(this, MenuActivity::class.java)
        var name = findViewById<EditText>(R.id.edt_name_player2).getText().toString().trim()
        if (name == "" || name == null) {
            Toast.makeText(this, "Masukkan Nama", Toast.LENGTH_SHORT).show()
        } else {
            menuIntent.putExtra("NAME_DATA", name);
            startActivity(menuIntent)
            finish() //supaya pas diback dari main activity, ga ke intro lagi
        }
    }

}