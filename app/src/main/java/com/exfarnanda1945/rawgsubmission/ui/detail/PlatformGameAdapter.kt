package com.exfarnanda1945.rawgsubmission.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.exfarnanda1945.rawgsubmission.R
import com.exfarnanda1945.rawgsubmission.databinding.RvDetailPlatformsBinding
import com.exfarnanda1945.rawgsubmission.model.game_detail_response.GameDetailParentPlatformsItem
import com.exfarnanda1945.rawgsubmission.utils.loadImage

class PlatformGameAdapter : RecyclerView.Adapter<PlatformGameAdapter.MainViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<GameDetailParentPlatformsItem>() {
        override fun areItemsTheSame(
            oldItem: GameDetailParentPlatformsItem,
            newItem: GameDetailParentPlatformsItem
        ): Boolean {
            return oldItem.platform?.id == newItem.platform?.id
        }

        override fun areContentsTheSame(
            oldItem: GameDetailParentPlatformsItem,
            newItem: GameDetailParentPlatformsItem
        ): Boolean {
            return oldItem.platform == newItem.platform
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    inner class MainViewHolder(itemBinding: RvDetailPlatformsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val imgPlatform: ImageView = itemBinding.imageItemPlatform

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            RvDetailPlatformsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = differ.currentList[position]
        val iconDrawable: Int = getIconDrawable(item.platform?.id)

        holder.apply {
            loadImage(itemView.context,null,imgPlatform,iconDrawable)
        }
    }

    private fun getIconDrawable(id: Int?): Int {
        return when (id) {
            1 -> R.drawable.windows
            2 -> R.drawable.playstation
            3 -> R.drawable.xbox
            4 -> R.drawable.apple
            5 -> R.drawable.apple
            6 -> R.drawable.linux
            7 -> R.drawable.nintendo
            else -> R.drawable.joystick
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(list: List<GameDetailParentPlatformsItem?>?) {
        differ.submitList(list)
    }
}