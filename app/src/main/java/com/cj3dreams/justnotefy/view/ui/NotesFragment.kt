package com.cj3dreams.justnotefy.view.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cj3dreams.justnotefy.R
import com.cj3dreams.justnotefy.model.NoteEntity
import com.cj3dreams.justnotefy.view.adapter.NotesAdapter
import com.cj3dreams.justnotefy.vm.NotesViewModel
import com.cj3dreams.justnotefy.vm.RoomViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class NotesFragment : Fragment(), View.OnClickListener {
    private val notesViewModel: NotesViewModel by viewModel()
    private val roomViewModel: RoomViewModel by viewModel()

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_notes, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = NotesAdapter(requireContext(), (1..20).toList(), this)
        notesViewModel.getAllNotes()
        notesViewModel.newsResponse.observe(viewLifecycleOwner, Observer {
            Log.e("Nen", it.toString())
        })
        roomViewModel.insertNote(NoteEntity(0,"dsfsdfsvfggdfbkukjf",455445,"dfgdfdgdgdfg", "gdfgdfg"))
        roomViewModel.getAllNews()
        roomViewModel.notesData.observe(viewLifecycleOwner, Observer {
            Log.e("252626", it.toString())
        })
    }

    override fun onClick(v: View?) {

    }

}