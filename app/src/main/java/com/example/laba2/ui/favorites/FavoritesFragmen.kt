package com.example.laba2.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laba2.R
import com.example.laba2.data.MockData
import com.example.laba2.databinding.FragmentFavoritesBinding
import com.example.laba2.ui.search.TrackAdapter

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoritesBinding.bind(view)

        binding.btnBack.setOnClickListener { findNavController().navigateUp() }

        val adapter = TrackAdapter { track ->
            findNavController().navigate(
                R.id.action_favorites_to_track,
                bundleOf("trackId" to track.id)
            )
        }
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

        val favs = MockData.favorites
        if (favs.isEmpty()) {
            binding.recycler.visibility = View.GONE
            binding.emptyGroup.visibility = View.VISIBLE
        } else {
            binding.recycler.visibility = View.VISIBLE
            binding.emptyGroup.visibility = View.GONE
            adapter.submit(favs)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}