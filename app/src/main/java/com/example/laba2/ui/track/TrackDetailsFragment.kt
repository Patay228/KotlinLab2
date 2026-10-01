package com.example.laba2.ui.track

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.laba2.R
import com.example.laba2.data.MockData
import com.example.laba2.databinding.FragmentTrackDetailsBinding
import com.example.laba2.util.formatDuration

class TrackDetailsFragment : Fragment(R.layout.fragment_track_details) {

    private var _binding: FragmentTrackDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTrackDetailsBinding.bind(view)

        val trackId = arguments?.getLong("trackId", -1L) ?: -1L
        val track = MockData.tracks.firstOrNull { it.id == trackId }
        if (track == null) {
            findNavController().navigateUp()
            return
        }

        binding.btnBack.setOnClickListener { findNavController().navigateUp() }
        binding.artwork.load(track.artworkUrl)
        binding.trackName.text = track.trackName
        binding.artistName.text = track.artistName
        binding.duration.text = formatDuration(track.trackTimeMillis)

        binding.btnAddToPlaylist.setOnClickListener {
            findNavController().navigate(
                R.id.action_track_to_add_playlist,
                Bundle().apply { putLong("trackId", track.id) }
            )
        }

        binding.btnFavorite.setOnClickListener {
            if (MockData.favorites.contains(track)) {
                MockData.favorites.remove(track)
            } else {
                MockData.favorites.add(track)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}