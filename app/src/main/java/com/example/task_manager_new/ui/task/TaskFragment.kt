package com.example.task_manager_new.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.task_manager_new.App
import com.example.task_manager_new.R
import com.example.task_manager_new.databinding.FragmentTaskBinding
import com.example.task_manager_new.model.Task

class TaskFragment : Fragment() {

    companion object {
        const val RESULT_TASK = "result.task"
    }

    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            var data = Task(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString(),
            )

            if (data.title!!.isBlank() || data.desc!!.isBlank()) {
                Toast.makeText(requireContext(), "Title and desc cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (data != null) {
                data!!.title = data.title
                data!!.desc = data.desc
                App.db.taskDao().update(data!!)
            } else {
                data = Task(title = data.title, desc = data.desc)
                App.db.taskDao().insert(data!!)
            }

            findNavController().navigateUp()
        }
    }
}