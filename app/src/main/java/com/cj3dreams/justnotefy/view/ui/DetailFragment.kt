package com.cj3dreams.justnotefy.view.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.whenCreated
import com.cj3dreams.justnotefy.MainActivity
import com.cj3dreams.justnotefy.R
import com.cj3dreams.justnotefy.model.DeleteResponse
import com.cj3dreams.justnotefy.model.NoteEntity
import com.cj3dreams.justnotefy.model.NotesResponse
import com.cj3dreams.justnotefy.source.remote.Resource
import com.cj3dreams.justnotefy.vm.NotesViewModel
import com.cj3dreams.justnotefy.vm.RoomViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import petrov.kristiyan.colorpicker.ColorPicker
import java.util.*
import kotlin.properties.Delegates

class DetailFragment : Fragment() {
    private val notesViewModel: NotesViewModel by viewModel()
    private val roomViewModel: RoomViewModel by viewModel()
    private lateinit var colorPicker: ColorPicker

    private lateinit var currentNoteEntity: NoteEntity
    var currentColor by Delegates.notNull<Int>()
    private lateinit var editText: EditText
    private lateinit var btnSave: Button
    private lateinit var btnColor: Button
    private lateinit var btnDelete: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val act = activity as MainActivity
        act.addFab.visibility = View.INVISIBLE
        act.bckBtn.visibility = View.VISIBLE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        editText = view.findViewById(R.id.noteFrgEditText)
        btnSave = view.findViewById(R.id.btnSave)
        btnColor = view.findViewById(R.id.btnColor)
        btnDelete = view.findViewById(R.id.btnDelete)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnColor.setOnClickListener{
            colorPicker = ColorPicker(requireActivity())
            colorPicker.setTitle("Выберите цвет")
            colorPicker.setRoundColorButton(true)

            colorPicker.setOnChooseColorListener(object: ColorPicker.OnChooseColorListener{
                override fun onChooseColor(position: Int, color: Int) {
                    if (::currentNoteEntity.isInitialized) currentNoteEntity.colorOfNote = color
                    else currentColor = color
                    btnColor.background.setTint(color)
                }

                override fun onCancel() {
                    colorPicker.dismissDialog()
                }
            }).show()
        }


        if(::currentNoteEntity.isInitialized) {
            btnColor.background.setTint(currentNoteEntity.colorOfNote)
            editText.setText(currentNoteEntity.note)
        }
        else currentColor = resources.getColor(R.color.black)

        btnSave.setOnClickListener{

            if (!::currentNoteEntity.isInitialized && editText.text.isNotEmpty()) {

                notesViewModel.insertNote(editText.text.toString(), "0", currentColor)
                notesViewModel.postResponse.observe(viewLifecycleOwner, { post ->
                    when (post) {
                        is Resource.Success -> {
                            val act = activity as MainActivity
                            act.onBackPressed()
                        }
                        else -> Toast.makeText(requireContext(), "Ошибка сети", Toast.LENGTH_SHORT).show()
                    }
                })
            }
            if (::currentNoteEntity.isInitialized && editText.text.isNotEmpty()) {

                notesViewModel.updateNote(currentNoteEntity.objectId!!, editText.text.toString(), currentNoteEntity.colorOfNote)
                notesViewModel.putResponse.observe(viewLifecycleOwner, { put ->
                    when(put){
                        is Resource.Success -> {
                            val act = activity as MainActivity
                            act.onBackPressed()
                            Log.e("POST", "SUCCESS")
                        }
                        else ->  Toast.makeText(requireContext(), "Ошибка сети", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
        if (::currentNoteEntity.isInitialized){
        btnDelete.setOnClickListener {

            notesViewModel.deleteNote(currentNoteEntity.objectId)
            notesViewModel.deleteResponse.observe(viewLifecycleOwner,{ delete ->

                when(delete){
                    is Resource.Success -> {
                        roomViewModel.deleteNote(currentNoteEntity)

                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(R.id.frgView, NotesFragment())
                            ?.commit()
                    }
                    else ->  Toast.makeText(requireContext(), "Ошибка сети", Toast.LENGTH_SHORT).show()
                }
            })

            }
        }
        else btnDelete.background.setTint(Color.parseColor("#2B2A2A"))
    }

    override fun onDestroyView() {
        val act = activity as MainActivity
        act.addFab.visibility = View.VISIBLE
        act.bckBtn.visibility = View.GONE
        super.onDestroyView()
    }

    companion object{
        fun getNoteEntity(noteEntity: NoteEntity) =
            DetailFragment().apply { currentNoteEntity = noteEntity }
    }
}
