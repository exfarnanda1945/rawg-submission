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
import com.exfarnanda1945.rawgsubmission.databinding.RvListGameBinding
import com.exfarnanda1945.rawgsubmission.model.game_response.GameResponseResultsItem
import com.exfarnanda1945.rawgsubmission.utils.loadImage

open class ListGameAdapter:RecyclerView.Adapter<ListGameAdapter.MainViewHolder>() {
    private val diffCallBack = object : DiffUtil.ItemCallback<GameResponseResultsItem>() {
        override fun areItemsTheSame(oldItem: GameResponseResultsItem, newItem: GameResponseResultsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GameResponseResultsItem, newItem: GameResponseResultsItem): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    private lateinit var onItemCallBack: IOnItemCallBack

    inner class MainViewHolder(itemBinding: RvListGameBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val itemImg: ImageView = itemBinding.itemImg
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
            loadImage(itemView.context,item.backgroundImage,itemImg)
            itemRelease.text = item.released
            itemTvName.text = item.name
            itemTvRate.text = item.rating.toString()
            itemWrapper.setOnClickListener {
                onItemCallBack.onItemClickCallback(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(list: List<GameResponseResultsItem?>?) {
        differ.submitList(list)
    }
    fun setOnItemClickCallback(action: IOnItemCallBack){
        this.onItemCallBack = action
    }

   interface IOnItemCallBack{
        fun onItemClickCallback(data: GameResponseResultsItem)
    }
}