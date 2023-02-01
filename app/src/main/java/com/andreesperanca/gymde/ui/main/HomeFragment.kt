package com.andreesperanca.gymde.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.adapters.HealthArticlesAdapter
import com.andreesperanca.gymde.adapters.TodayWorkoutAdapter
import com.andreesperanca.gymde.databinding.EmptyWorkoutItemBinding
import com.andreesperanca.gymde.databinding.ExerciseItemBinding
import com.andreesperanca.gymde.databinding.FragmentHomeBinding
import com.andreesperanca.gymde.ui.main.viewmodels.HomeViewModel
import com.andreesperanca.gymde.utils.EmptyDataObserver
import com.andreesperanca.gymde.utils.Resource
import com.andreesperanca.gymde.utils.extensions.toastCreator
import kotlinx.coroutines.NonDisposableHandle.parent
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.time.Duration.Companion.days

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

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchWorkouts(requireContext())

        val recyclerViewWorkouts = binding.rvWorkouts
        recyclerViewWorkouts.adapter = this.adapter
        recyclerViewWorkouts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val empty_data_parent = binding.emptyDataParent.root

        val emptyDataObserver = EmptyDataObserver(binding.rvWorkouts, empty_data_parent)
        adapter.registerAdapterDataObserver(emptyDataObserver)

        val recyclerViewHealthArticles = binding.rvHealthArticles
        recyclerViewHealthArticles.adapter = this.hAdapter
        recyclerViewHealthArticles.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        viewModel.todayWorkouts.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Success -> {
                    adapter.updateData(it.data)
                    Log.i("data",it.data?.size.toString())
                    binding.pgProgressBarHomeScreen.visibility = View.INVISIBLE
                }
                is Resource.Loading -> {
                    binding.pgProgressBarHomeScreen.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.pgProgressBarHomeScreen.visibility = View.INVISIBLE
                    toastCreator(it.message.toString())
                }
            }
        }

    }

}