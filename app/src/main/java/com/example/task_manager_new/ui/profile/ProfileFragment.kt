package com.example.task_manager_new.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.example.task_manager_new.R
import com.example.task_manager_new.data.Pref
import com.example.task_manager_new.databinding.FragmentProfileBinding
import com.example.task_manager_new.utils.loadImage

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref

    private val launcher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK
            && result.data != null
        ) {
            val photoUri = result.data?.data
            pref.saveImage(photoUri.toString())
            binding.avatarAccount.loadImage(photoUri.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())

        saveName()
        binding.avatarAccount.loadImage(pref.getImage())

        binding.avatarAccount.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            launcher.launch(intent)
        }
    }

    private fun saveName() {
        binding.etName.setText(pref.getName())
        binding.etName.addTextChangedListener {
            pref.saveName(binding.etName.text.toString())
        }
    }

}