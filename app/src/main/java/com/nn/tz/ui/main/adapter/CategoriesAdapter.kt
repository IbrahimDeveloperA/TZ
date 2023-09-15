package com.nn.tz.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nn.tz.databinding.ItemCategoriesBinding

class CategoriesAdapter(val onClick: (String) -> Unit) :
    ListAdapter<String, CategoriesAdapter.CategoriesViewHolder>(StringDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            ItemCategoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class CategoriesViewHolder(private val binding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: String) {
                binding.btnCategories.text = category

            itemView.setOnClickListener {
                onClick(category)
            }
        }
    }

    private class StringDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
