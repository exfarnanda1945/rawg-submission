package com.exfarnanda1945.rawgsubmission.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.exfarnanda1945.rawgsubmission.databinding.RvDetailDeveloperBinding
import com.exfarnanda1945.rawgsubmission.model.game_detail_response.GameDetailDevelopersItem

class DeveloperGameDetailAdapter :
    RecyclerView.Adapter<DeveloperGameDetailAdapter.MainViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<GameDetailDevelopersItem>() {

        override fun areItemsTheSame(
            oldItem: GameDetailDevelopersItem,
            newItem: GameDetailDevelopersItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GameDetailDevelopersItem,
            newItem: GameDetailDevelopersItem
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        return MainViewHolder(
            RvDetailDeveloperBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.tvDeveloper.text = item.name
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(list: List<GameDetailDevelopersItem?>?) {
        differ.submitList(list)
    }

    class MainViewHolder
    constructor(
        itemBinding: RvDetailDeveloperBinding,
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        val tvDeveloper = itemBinding.itemGenreName
    }
}

