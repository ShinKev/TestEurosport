package com.example.testeurosport.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.testeurosport.databinding.FragmentStoryBinding

class StoryFragment : Fragment() {

    private var _binding: FragmentStoryBinding? = null
    private val binding get() = _binding!!
    private val args: StoryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val story = args.story

        Glide.with(binding.root).load(story.image).into(binding.storyDetailImage)
        binding.sportDetailText.text = story.sport?.name
        binding.titleDetailText.text = story.title
        binding.authorDetailText.text = story.author
        binding.dateDetailText.text = story.getTimeAgoString()
        binding.storyDetailText.text = story.teaser
        binding.backButton.setOnClickListener {
            this.requireActivity().onBackPressed()
        }
    }
}