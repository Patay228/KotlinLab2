package com.example.laba2.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laba2.R
import com.example.laba2.data.MockData
import com.example.laba2.data.Track
import com.example.laba2.databinding.FragmentSearchBinding

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: TrackAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

        adapter = TrackAdapter { track -> openTrack(track) }
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

        binding.btnBack.setOnClickListener { findNavController().navigateUp() }

        binding.searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, st: Int, c: Int, a: Int) {}
            override fun onTextChanged(s: CharSequence?, st: Int, b: Int, c: Int) {}
            override fun afterTextChanged(s: Editable?) {
                onQueryChanged(s?.toString().orEmpty())
            }
        })

        binding.btnClear.setOnClickListener {
            binding.searchInput.text.clear()
            onQueryChanged("")
        }

        binding.btnClearHistory.setOnClickListener {
            MockData.searchHistory.clear()
            onQueryChanged("")
        }

        onQueryChanged("")
    }

    private fun onQueryChanged(query: String) {
        val trimmed = query.trim()
        binding.btnClear.visibility = if (query.isEmpty()) View.GONE else View.VISIBLE

        when {
            trimmed.isBlank() -> {
                val history = MockData.searchHistory
                if (history.isEmpty()) {
                    binding.historyHeader.visibility = View.GONE
                    binding.recycler.visibility = View.GONE
                    binding.emptyText.visibility = View.GONE
                } else {
                    binding.historyHeader.visibility = View.VISIBLE
                    binding.recycler.visibility = View.VISIBLE
                    binding.emptyText.visibility = View.GONE
                    adapter.submit(history)
                }
            }
            else -> {
                val results = MockData.tracks.filter {
                    it.trackName.contains(trimmed, true) ||
                            it.artistName.contains(trimmed, true)
                }
                binding.historyHeader.visibility = View.GONE
                if (results.isEmpty()) {
                    binding.recycler.visibility = View.GONE
                    binding.emptyText.visibility = View.VISIBLE
                } else {
                    binding.recycler.visibility = View.VISIBLE
                    binding.emptyText.visibility = View.GONE
                    adapter.submit(results)
                }
            }
        }
    }

    private fun openTrack(track: Track) {
        // добавляем в историю
        MockData.searchHistory.remove(track)
        MockData.searchHistory.add(0, track)
        if (MockData.searchHistory.size > 10) {
            MockData.searchHistory.removeAt(MockData.searchHistory.lastIndex)
        }
        findNavController().navigate(
            R.id.action_search_to_track,
            bundleOf("trackId" to track.id)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}