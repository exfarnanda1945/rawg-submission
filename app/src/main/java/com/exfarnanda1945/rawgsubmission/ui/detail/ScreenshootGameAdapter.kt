package com.exfarnanda1945.rawgsubmission.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exfarnanda1945.rawgsubmission.databinding.RvScreenshootGameBinding

class ScreenshootGameAdapter : RecyclerView.Adapter<ScreenshootGameAdapter.MainViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    inner class MainViewHolder(itemBinding: RvScreenshootGameBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val screenShootImage: ImageView = itemBinding.itemScreenshootGame
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
            Glide.with(itemView.context).load(differ.currentList[position]).into(screenShootImage)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(list: List<String?>?) {
        differ.submitList(list)
    }
}