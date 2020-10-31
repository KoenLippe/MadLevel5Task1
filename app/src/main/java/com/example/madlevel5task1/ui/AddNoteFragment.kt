package com.example.madlevel5task1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task1.R
import kotlinx.android.synthetic.main.fragment_add_note.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddNoteFragment : Fragment() {

    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSave.setOnClickListener {
            //Save the note
            noteViewModel.updateNote(
                    tilNoteTitle.editText?.text.toString(),
                    tilNoteText.editText?.text.toString()
            )
        }

        observeNote()
    }

    private fun observeNote() {
        //Set already known text in the edit text fields
        noteViewModel.note.observe(viewLifecycleOwner, Observer {
            note  -> note?.let {
                tilNoteTitle.editText?.setText(it.title)
                tilNoteText.editText?.setText(it.text)
            }

        })

        //Give Error toast
        noteViewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
        })

        //Return to last fragment
        noteViewModel.success.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack()
        })
    }
}