package com.example.laba2.ui.playlist

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.laba2.R
import com.example.laba2.databinding.FragmentNewPlaylistBinding

class NewPlaylistFragment : Fragment(R.layout.fragment_new_playlist) {

    private var _binding: FragmentNewPlaylistBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewPlaylistBinding.bind(view)

        binding.btnBack.setOnClickListener { findNavController().navigateUp() }

        binding.inputName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, st: Int, c: Int, a: Int) {}
            override fun onTextChanged(s: CharSequence?, st: Int, b: Int, c: Int) {}
            override fun afterTextChanged(s: Editable?) {
                updateCreateButton(s?.isNotBlank() == true)
            }
        })

        binding.btnCreate.setOnClickListener {
            // создание плейлиста — задел на будущее
            findNavController().navigateUp()
        }

        updateCreateButton(false)
    }

    private fun updateCreateButton(enabled: Boolean) {
        binding.btnCreate.isEnabled = enabled
        val color = ContextCompat.getColor(
            requireContext(),
            if (enabled) R.color.blue else R.color.gray
        )
        binding.btnCreate.backgroundTintList = ColorStateList.valueOf(color)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}