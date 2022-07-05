package com.cj3dreams.justnotefy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cj3dreams.justnotefy.view.ui.NotesFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frgView, NotesFragment())
            .commit()
    }
}