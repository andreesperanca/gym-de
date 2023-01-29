package com.andreesperanca.gymde.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreesperanca.gymde.adapters.HealthArticlesAdapter
import com.andreesperanca.gymde.adapters.TodayWorkoutAdapter
import com.andreesperanca.gymde.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    private val adapter by lazy {
        TodayWorkoutAdapter()
    }


    private val hAdapter by lazy {
        HealthArticlesAdapter()
    }

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewWorkouts = binding.rvWorkouts
        recyclerViewWorkouts.adapter = this.adapter
        recyclerViewWorkouts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val recyclerViewHealthArticles = binding.rvHealthArticles
        recyclerViewHealthArticles.adapter = this.hAdapter
        recyclerViewHealthArticles.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


    }

}