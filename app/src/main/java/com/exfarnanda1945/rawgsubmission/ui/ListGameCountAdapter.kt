package com.exfarnanda1945.rawgsubmission.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.exfarnanda1945.rawgsubmission.databinding.RvGameCountListItemBinding
import com.exfarnanda1945.rawgsubmission.model.base_game_count.BaseGameCountResultsItem
import com.exfarnanda1945.rawgsubmission.utils.loadImage

open class ListGameCountAdapter :
    RecyclerView.Adapter<ListGameCountAdapter.MainViewHolder>() {
    inner class MainViewHolder(itemBinding: RvGameCountListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val img = itemBinding.imgGenreItem
        val tvName = itemBinding.nameGenreItem
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<BaseGameCountResultsItem>() {
        override fun areItemsTheSame(
            oldItem: BaseGameCountResultsItem,
            newItem: BaseGameCountResultsItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BaseGameCountResultsItem,
            newItem: BaseGameCountResultsItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            RvGameCountListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val result = differ.currentList[position]
        holder.apply {
            loadImage(itemView.context, result.imageBackground, img)
            tvName.text = "${result.name}\n${result.gamesCount} Games"
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(data: List<BaseGameCountResultsItem?>?) {
        differ.submitList(data)
    }
}