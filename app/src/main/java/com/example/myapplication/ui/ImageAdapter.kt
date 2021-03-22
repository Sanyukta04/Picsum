package com.example.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RvListItemBinding
import com.example.myapplication.db.entity.ImageEntity

class ImageAdapter: ListAdapter<ImageEntity, RecyclerView.ViewHolder>(ImageDiffCallback()) {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = getItem(position).id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageViewHolder(
            RvListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return (holder as ImageViewHolder).bind(getItem(position))
    }


    class ImageViewHolder(
        private val binding: RvListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: ImageEntity
        ) {
            binding.apply {
                image = item
                executePendingBindings()
            }
        }
    }


}

class ImageDiffCallback : DiffUtil.ItemCallback<ImageEntity>() {

    override fun areItemsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageEntity, newItem: ImageEntity): Boolean {
        return oldItem == newItem
    }
}