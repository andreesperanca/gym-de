package com.andreesperanca.gymde

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.andreesperanca.gymde.adapters.HealthArticlesAdapter
import com.andreesperanca.gymde.adapters.TodayWorkoutAdapter
import com.andreesperanca.gymde.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


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
        recyclerViewWorkouts.layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL, false)

        val recyclerViewHealthArticles = binding.rvHealthArticles
        recyclerViewHealthArticles.adapter = this.hAdapter
        recyclerViewHealthArticles.layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL, false)


    }

}