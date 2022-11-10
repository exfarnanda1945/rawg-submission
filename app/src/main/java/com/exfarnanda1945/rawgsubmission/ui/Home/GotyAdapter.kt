package com.exfarnanda1945.rawgsubmission.ui.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.exfarnanda1945.rawgsubmission.databinding.RvBestGotyBinding
import com.exfarnanda1945.rawgsubmission.model.ResultsItem

class GotyAdapter : RecyclerView.Adapter<GotyAdapter.MainViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    private lateinit var onItemCallBack:IOnItemCallBack

    inner class MainViewHolder(itemBinding: RvBestGotyBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val itemImg: ImageView = itemBinding.imgItemGoty
        val itemTvPlatform: TextView = itemBinding.platformItemGoty
        val itemTvName: TextView = itemBinding.nameItemGoty
        val itemTvRate: TextView = itemBinding.rateItemGoty
        val itemWrapper:RelativeLayout = itemBinding.rvGotyWrapper
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            RvBestGotyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.apply {
            Glide.with(itemView.context).load(item.backgroundImage).into(itemImg)
            itemTvPlatform.text = item.parentPlatforms?.map {
                it?.platform?.name
            }?.joinToString()
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

    fun setData(list: List<ResultsItem?>?) {
        differ.submitList(list)
    }

    fun setOnItemClickCallback(action:IOnItemCallBack){
        this.onItemCallBack = action
    }

    interface IOnItemCallBack{
        fun onItemClickCallback(data:ResultsItem)
    }
}