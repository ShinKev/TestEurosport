package com.example.testeurosport.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testeurosport.databinding.StoryItemBinding
import com.example.testeurosport.databinding.VideoItemBinding
import com.example.testeurosport.model.data.Article
import com.example.testeurosport.model.data.Story
import com.example.testeurosport.model.data.Video
import com.example.testeurosport.view.ui.HomeFragment
import com.example.testeurosport.view.viewholder.BaseViewHolder
import com.example.testeurosport.view.viewholder.StoryViewHolder
import com.example.testeurosport.view.viewholder.VideoViewHolder

class HomeAdapter(private val articleList: List<Article>, private val homeFragment: HomeFragment) :
    RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        private const val TYPE_STORY = 0
        private const val TYPE_VIDEO = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TYPE_STORY -> StoryViewHolder(
                StoryItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            TYPE_VIDEO -> VideoViewHolder(
                VideoItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (articleList[position]) {
            is Story -> TYPE_STORY
            is Video -> TYPE_VIDEO
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(articleList[position], homeFragment)
    }
}