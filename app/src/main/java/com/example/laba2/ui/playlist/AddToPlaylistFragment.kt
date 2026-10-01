package com.example.laba2.ui.playlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laba2.R
import com.example.laba2.data.MockData
import com.example.laba2.databinding.FragmentAddToPlaylistBinding

class AddToPlaylistFragment : Fragment(R.layout.fragment_add_to_playlist) {

    private var _binding: FragmentAddToPlaylistBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddToPlaylistBinding.bind(view)

        binding.btnBack.setOnClickListener { findNavController().navigateUp() }

        val adapter = PlaylistAdapter {
            // «добавить трек в плейлист» — задел
            findNavController().navigateUp()
        }
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter
        adapter.submit(MockData.playlists)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}