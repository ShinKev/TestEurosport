package com.example.testeurosport.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.testeurosport.model.data.Article
import com.example.testeurosport.view.ui.HomeFragment

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(article: Article, homeFragment: HomeFragment)
}