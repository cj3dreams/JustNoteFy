package com.cj3dreams.justnotefy.view.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cj3dreams.justnotefy.R

class NotesAdapter(private val context: Context, private val list: List<Any>,
    private val onClickListener: View.OnClickListener)
    :RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val item = view.findViewById(R.id.root) as CardView
        val colorItem = view.findViewById(R.id.itemColorView) as View
        val noteTx = view.findViewById(R.id.itemNoteTx) as TextView
        val randomColors = listOf(android.R.color.holo_red_dark,
            android.R.color.holo_orange_dark, android.R.color.holo_green_dark)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)

        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val itemData = list[position]
        holder.noteTx.text = itemData.toString()
        holder.colorItem.background =
            AppCompatResources.getDrawable(context,
                holder.randomColors[(holder.randomColors.indices).toList().random()])
    }

    override fun getItemCount() = list.size
}