package com.example.testeurosport.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testeurosport.R
import com.example.testeurosport.databinding.StoryItemBinding
import com.example.testeurosport.databinding.VideoItemBinding
import com.example.testeurosport.model.data.Article
import com.example.testeurosport.model.data.Story
import com.example.testeurosport.model.data.Video
import com.example.testeurosport.view.ui.HomeFragment
import com.example.testeurosport.view.ui.HomeFragmentDirections

class HomeAdapter(private val articleList: List<Article>, private val homeFragment: HomeFragment) :
    RecyclerView.Adapter<HomeAdapter.BaseViewHolder>() {

    abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(article: Article, homeFragment: HomeFragment)
    }

    class StoryViewHolder(private val binding: StoryItemBinding) : BaseViewHolder(binding.root) {
        override fun bind(article: Article, homeFragment: HomeFragment) {
            val story = article as Story
            val storyOnClickListener = StoryOnClickListener(story, homeFragment)

            Glide.with(binding.root).load(story.image).into(binding.storyImage)
            binding.sportText.text = story.sport?.name
            binding.titleText.text = story.title
            binding.authorDateText.text =
                binding.root.context.getString(R.string.author_date_placeholder_text)
                    .format(story.author, story.getTimeAgoString())
            binding.storyImage.setOnClickListener(storyOnClickListener)
            binding.titleText.setOnClickListener(storyOnClickListener)
        }
    }

    class VideoViewHolder(private val binding: VideoItemBinding) : BaseViewHolder(binding.root) {
        override fun bind(article: Article, homeFragment: HomeFragment) {
            val video = article as Video

            Glide.with(binding.root).load(video.thumb).into(binding.videoImage)
            binding.sportText.text = video.sport?.name
            binding.titleText.text = video.title
            binding.viewsText.text = binding.root.context.getString(R.string.views_placeholder_text).format(video.views)
//            binding.playButton.setOnClickListener(listener)
        }
    }

    class StoryOnClickListener(private val story: Story, private val homeFragment: HomeFragment) :
        View.OnClickListener {
        override fun onClick(v: View?) {
            val navDirection = HomeFragmentDirections.actionHomeFragmentToStoryFragment(story)
            homeFragment.findNavController().navigate(navDirection)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            0 -> StoryViewHolder(
                StoryItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> VideoViewHolder(
                VideoItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            articleList[position] is Story -> 0
            articleList[position] is Video -> 1
            else -> 2
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(articleList[position], homeFragment)
    }
}