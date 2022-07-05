package com.cj3dreams.justnotefy.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cj3dreams.justnotefy.R

class NotesAdapter(private val context: Context, private val list: List<Any>,
    private val onClickListener: View.OnClickListener)
    :RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(view: View):RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)

        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val itemData = list[position]


    }

    override fun getItemCount() = list.size
}