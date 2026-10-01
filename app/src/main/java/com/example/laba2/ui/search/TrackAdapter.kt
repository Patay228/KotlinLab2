package com.example.laba2.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.laba2.R
import com.example.laba2.data.Track
import com.example.laba2.databinding.ItemTrackBinding
import com.example.laba2.util.formatDuration

class TrackAdapter(
    private val onClick: (Track) -> Unit
) : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    private val items = mutableListOf<Track>()

    fun submit(newItems: List<Track>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val binding = ItemTrackBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TrackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class TrackViewHolder(private val b: ItemTrackBinding) :
        RecyclerView.ViewHolder(b.root) {
        fun bind(track: Track) {
            b.artwork.load(track.artworkUrl)
            b.trackName.text = track.trackName
            b.artistName.text = track.artistName
            b.duration.text = formatDuration(track.trackTimeMillis)
            b.root.setOnClickListener { onClick(track) }
        }
    }
}