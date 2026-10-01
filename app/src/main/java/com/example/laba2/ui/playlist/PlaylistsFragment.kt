package com.example.laba2.ui.playlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laba2.R
import com.example.laba2.data.MockData
import com.example.laba2.databinding.FragmentPlaylistsBinding

class PlaylistsFragment : Fragment(R.layout.fragment_playlists) {

    private var _binding: FragmentPlaylistsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPlaylistsBinding.bind(view)

        binding.btnBack.setOnClickListener { findNavController().navigateUp() }
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_playlists_to_new)
        }

        val adapter = PlaylistAdapter { /* открытие плейлиста — задел на будущее */ }
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter
        adapter.submit(MockData.playlists)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}