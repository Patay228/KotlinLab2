package com.example.laba2.ui.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.laba2.data.Playlist
import com.example.laba2.databinding.ItemPlaylistBinding
import com.example.laba2.R
class PlaylistAdapter(
    private val onClick: (Playlist) -> Unit
) : RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    private val items = mutableListOf<Playlist>()

    fun submit(newItems: List<Playlist>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val binding = ItemPlaylistBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PlaylistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class PlaylistViewHolder(private val b: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(b.root) {
        fun bind(pl: Playlist) {
            b.cover.load(pl.coverUrl) {
                placeholder(R.color.light_gray)
                error(R.color.light_gray)
            }
            b.name.text = pl.name
            b.tracksCount.text = "${pl.tracksCount} треков"
            b.root.setOnClickListener { onClick(pl) }
        }
    }
}