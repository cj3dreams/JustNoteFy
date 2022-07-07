package com.cj3dreams.justnotefy.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cj3dreams.justnotefy.R
import com.cj3dreams.justnotefy.model.NoteEntity
import com.cj3dreams.justnotefy.source.remote.Resource
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

        roomViewModel.getAllNotes()
        notesViewModel.getAllNotes()

        notesViewModel.noteResponse.observe(viewLifecycleOwner, { resource->
            when (resource){
                is Resource.Success -> {
                    roomViewModel.setNotesToDb(resource.value.results)
                    roomViewModel.notesData.observe(viewLifecycleOwner, { notesEntityList ->
                        recyclerView.adapter = NotesAdapter(requireContext(), notesEntityList, this)
                    })
                }
                else -> {
                    roomViewModel.notesData.observe(viewLifecycleOwner, { notesEntityList ->
                        recyclerView.adapter = NotesAdapter(requireContext(), notesEntityList, this)
                    })
                }
            }
        })
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.root -> {
                val tag = v.tag as NoteEntity
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.frgView, DetailFragment.getNoteEntity(tag))
//                    ?.addToBackStack("backToMain")
                    ?.commit()
            }
        }
    }
}