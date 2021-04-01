package com.example.testeurosport.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testeurosport.R
import com.example.testeurosport.databinding.FragmentHomeBinding
import com.example.testeurosport.view.adapter.HomeAdapter
import com.example.testeurosport.viewmodel.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.homeRecyclerview

        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.addItemDecoration(
            MarginItemDecoration(resources.getDimension(R.dimen.recycler_item_padding).toInt())
        )

        homeViewModel.getArticleList().observe(
            viewLifecycleOwner,
            { recyclerView.adapter = HomeAdapter(it, this@HomeFragment) }
        )
    }
}