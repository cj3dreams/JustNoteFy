package com.cj3dreams.justnotefy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.cj3dreams.justnotefy.view.ui.DetailFragment
import com.cj3dreams.justnotefy.view.ui.NotesFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var addFab: FloatingActionButton
    lateinit var bckBtn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bckBtn = findViewById(R.id.backBtn)
        addFab = findViewById(R.id.mActivityAddFab)
        addFab.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.frgView, DetailFragment())
                .addToBackStack("backToMain")
                .commit()
        }
        bckBtn.setOnClickListener {
            onBackPressed()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.frgView, NotesFragment())
            .commit()
    }
}