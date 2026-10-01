package com.example.laba2.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.laba2.R
import com.example.laba2.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        binding.menuSearch.setOnClickListener {
            findNavController().navigate(R.id.action_main_to_search)
        }
        binding.menuPlaylists.setOnClickListener {
            findNavController().navigate(R.id.action_main_to_playlists)
        }
        binding.menuFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_main_to_favorites)
        }
        binding.menuSettings.setOnClickListener {
            findNavController().navigate(R.id.action_main_to_settings)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}