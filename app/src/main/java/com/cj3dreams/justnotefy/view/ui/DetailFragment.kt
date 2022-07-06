package com.cj3dreams.justnotefy.view.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.cj3dreams.justnotefy.MainActivity
import com.cj3dreams.justnotefy.R
import com.cj3dreams.justnotefy.model.NoteEntity

class DetailFragment : Fragment() {
    private lateinit var currentNoteEntity: NoteEntity
    private lateinit var editText: EditText

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val act = activity as MainActivity
        act.addFab.visibility = View.INVISIBLE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        editText = view.findViewById(R.id.noteFrgEditText)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(::currentNoteEntity.isInitialized) editText.setText(currentNoteEntity.note)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val act = activity as MainActivity
        act.addFab.visibility = View.VISIBLE
    }

    companion object{
        fun getNoteEntity(noteEntity: NoteEntity) =
            DetailFragment().apply { currentNoteEntity = noteEntity }
    }
}