package com.cj3dreams.justnotefy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import com.cj3dreams.justnotefy.view.ui.DetailFragment
import com.cj3dreams.justnotefy.view.ui.NotesFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    lateinit var addFab: FloatingActionButton
    lateinit var bckBtn: ImageView
    var switcher by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switcher = false

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
        noteFragment()

    }

    override fun onBackPressed() {
        if (switcher) noteFragment()
        else super.onBackPressed()
    }
    private fun noteFragment() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frgView, NotesFragment())
            .commit()
    }
}