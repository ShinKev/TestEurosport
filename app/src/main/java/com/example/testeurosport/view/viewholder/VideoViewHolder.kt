package com.example.testeurosport.view.viewholder

import android.view.View
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.testeurosport.R
import com.example.testeurosport.databinding.VideoItemBinding
import com.example.testeurosport.model.data.Article
import com.example.testeurosport.model.data.Story
import com.example.testeurosport.model.data.Video
import com.example.testeurosport.view.ui.HomeFragment
import com.example.testeurosport.view.ui.HomeFragmentDirections

class VideoViewHolder(private val binding: VideoItemBinding) : BaseViewHolder(binding.root) {

    class VideoOnClickListener(private val urlVideo: String, private val homeFragment: HomeFragment) :
        View.OnClickListener {
        override fun onClick(v: View?) {
            val navDirection = HomeFragmentDirections.actionHomeFragmentToVideoFragment(urlVideo)
            homeFragment.findNavController().navigate(navDirection)
        }
    }

    override fun bind(article: Article, homeFragment: HomeFragment) {
        val video = article as Video
        val videoOnClickListener = VideoViewHolder.VideoOnClickListener(video.url, homeFragment)

        Glide.with(binding.root).load(video.thumb).into(binding.videoImage)
        binding.sportVideoText.text = video.sport?.name
        binding.titleVideoText.text = video.title
        binding.viewsText.text = binding.root.context.getString(R.string.views_placeholder_text)
            .format(video.views)
        binding.playButton.setOnClickListener(videoOnClickListener)
    }
}