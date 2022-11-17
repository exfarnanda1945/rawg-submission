package com.exfarnanda1945.rawgsubmission.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.exfarnanda1945.rawgsubmission.databinding.RvDetailGenreBinding
import com.exfarnanda1945.rawgsubmission.model.game_detail_response.GameDetailGenresItem

class GenresGameDetailAdapter : RecyclerView.Adapter<GenresGameDetailAdapter.MainViewHolder>() {
    private val diffCallback = object : DiffUtil.ItemCallback<GameDetailGenresItem>() {
        override fun areItemsTheSame(
            oldItem: GameDetailGenresItem,
            newItem: GameDetailGenresItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GameDetailGenresItem,
            newItem: GameDetailGenresItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    inner class MainViewHolder(itemBinding: RvDetailGenreBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val tvGenre = itemBinding.itemGenreName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            RvDetailGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.tvGenre.text = item.name
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(list: List<GameDetailGenresItem?>?) {
        differ.submitList(list)
    }
}