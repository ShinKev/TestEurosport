package com.example.testeurosport.view.viewholder

import com.bumptech.glide.Glide
import com.example.testeurosport.R
import com.example.testeurosport.databinding.VideoItemBinding
import com.example.testeurosport.model.data.Article
import com.example.testeurosport.model.data.Video
import com.example.testeurosport.view.ui.HomeFragment

class VideoViewHolder(private val binding: VideoItemBinding) : BaseViewHolder(binding.root) {
    override fun bind(article: Article, homeFragment: HomeFragment) {
        val video = article as Video

        Glide.with(binding.root).load(video.thumb).into(binding.videoImage)
        binding.sportText.text = video.sport?.name
        binding.titleText.text = video.title
        binding.viewsText.text = binding.root.context.getString(R.string.views_placeholder_text)
            .format(video.views)
//            binding.playButton.setOnClickListener(listener)
    }
}