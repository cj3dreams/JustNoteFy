package com.cj3dreams.justnotefy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cj3dreams.justnotefy.view.ui.DetailFragment
import com.cj3dreams.justnotefy.view.ui.NotesFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var addFab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFab = findViewById(R.id.mActivityAddFab)
        addFab.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.frgView, DetailFragment())
                .addToBackStack("backToMain")
                .commit()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.frgView, NotesFragment())
            .commit()
    }
}