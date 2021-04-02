package com.example.testeurosport.view.viewholder

import android.view.View
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.testeurosport.R
import com.example.testeurosport.databinding.StoryItemBinding
import com.example.testeurosport.model.data.Article
import com.example.testeurosport.model.data.Story
import com.example.testeurosport.view.ui.HomeFragment
import com.example.testeurosport.view.ui.HomeFragmentDirections

class StoryViewHolder(private val binding: StoryItemBinding) : BaseViewHolder(binding.root) {

    class StoryOnClickListener(private val story: Story, private val homeFragment: HomeFragment) :
        View.OnClickListener {
        override fun onClick(v: View?) {
            val navDirection = HomeFragmentDirections.actionHomeFragmentToStoryFragment(story)
            homeFragment.findNavController().navigate(navDirection)
        }
    }

    override fun bind(article: Article, homeFragment: HomeFragment) {
        val story = article as Story
        val storyOnClickListener = StoryOnClickListener(story, homeFragment)

        Glide.with(binding.root).load(story.image).into(binding.storyImage)
        binding.sportStoryText.text = story.sport?.name
        binding.titleStoryText.text = story.title
        binding.authorDateText.text =
            binding.root.context.getString(R.string.author_date_placeholder_text)
                .format(story.author, story.getTimeAgoString())
        binding.storyImage.setOnClickListener(storyOnClickListener)
        binding.titleStoryText.setOnClickListener(storyOnClickListener)
    }
}