package com.exfarnanda1945.rawgsubmission.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exfarnanda1945.rawgsubmission.databinding.RvListGameBinding
import com.exfarnanda1945.rawgsubmission.model.ResultsItem

open class ListGameAdapter:RecyclerView.Adapter<ListGameAdapter.MainViewHolder>() {
    private val diffCallBack = object : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    private lateinit var onItemCallBack: IOnItemCallBack

    inner class MainViewHolder(itemBinding: RvListGameBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val itemImg: ImageView = itemBinding.itemImg
        val itemTvPlatform: TextView = itemBinding.itemPlatform
        val itemRelease: TextView = itemBinding.itemRelease
        val itemTvName: TextView = itemBinding.itemName
        val itemTvRate: TextView = itemBinding.itemRating
        val itemWrapper: CardView = itemBinding.latestGameItemWrapper
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            RvListGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.apply {
            Glide.with(itemView.context).load(item.backgroundImage).into(itemImg)
            itemRelease.text = "Release: ${item.released}"
            itemTvPlatform.text = "Platform: ${
                item.parentPlatforms?.map {
                    it?.platform?.name
                }?.joinToString()
            }"
            itemTvName.text = item.name
            itemTvRate.text = "${item.rating.toString()}/5"
            itemWrapper.setOnClickListener {
                onItemCallBack.onItemClickCallback(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(list: List<ResultsItem?>?) {
        differ.submitList(list)
    }
    fun setOnItemClickCallback(action: IOnItemCallBack){
        this.onItemCallBack = action
    }

   interface IOnItemCallBack{
        fun onItemClickCallback(data: ResultsItem)
    }
}