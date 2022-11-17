package com.exfarnanda1945.rawgsubmission.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.exfarnanda1945.rawgsubmission.databinding.RvScreenshootGameBinding
import com.exfarnanda1945.rawgsubmission.model.game_screenshots.GameScreenshotsResultsItem
import com.exfarnanda1945.rawgsubmission.utils.loadImage

class ScreenshotGameAdapter : RecyclerView.Adapter<ScreenshotGameAdapter.MainViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<GameScreenshotsResultsItem>() {
        override fun areItemsTheSame(oldItem: GameScreenshotsResultsItem, newItem: GameScreenshotsResultsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GameScreenshotsResultsItem, newItem: GameScreenshotsResultsItem): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    inner class MainViewHolder(itemBinding: RvScreenshootGameBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val screenShootImage: ImageView = itemBinding.itemScreenshotGame
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            RvScreenshootGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.apply {
            loadImage(itemView.context,differ.currentList[position].image,screenShootImage)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(list: List<GameScreenshotsResultsItem?>?) {
        differ.submitList(list)
    }
}