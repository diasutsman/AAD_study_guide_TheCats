package com.dias.thecats.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dias.thecats.data.Cat
import com.dias.thecats.databinding.RowItemCatBinding
import com.dias.thecats.ui.detail.DetailActivity

class CatAdapter : PagingDataAdapter<Cat, CatAdapter.CatViewHolder>(DIFF_CALLBACK) {
    class CatViewHolder(val binding: RowItemCatBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CatViewHolder(
        RowItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val ctx = holder.itemView.context
        val mCat = getItem(position)
        holder.binding.apply {
            cat = mCat
            onClick = {
                ctx.startActivity(
                    Intent(ctx, DetailActivity::class.java)
                        .putExtra(DetailActivity.URL_KEY, mCat.url)
                )
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Cat>() {
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean =
                oldItem == newItem
        }
    }

}