package com.cj3dreams.justnotefy.view.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cj3dreams.justnotefy.R
import com.cj3dreams.justnotefy.view.adapter.NotesAdapter
import com.cj3dreams.justnotefy.vm.NoteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class NotesFragment : Fragment(), View.OnClickListener {
    private val noteViewModel: NoteViewModel by viewModel()

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
        noteViewModel.getAllNotes()
        noteViewModel.newsResponse.observe(viewLifecycleOwner, Observer {
            Log.e("Nen", it.toString())
        })
    }

    override fun onClick(v: View?) {

    }

}