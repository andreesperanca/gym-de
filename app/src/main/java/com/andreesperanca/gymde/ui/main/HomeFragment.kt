package com.andreesperanca.gymde.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.adapters.HealthArticlesAdapter
import com.andreesperanca.gymde.adapters.TodayWorkoutAdapter
import com.andreesperanca.gymde.databinding.FragmentHomeBinding
import com.andreesperanca.gymde.ui.main.viewmodels.HomeViewModel
import com.andreesperanca.gymde.utils.EmptyDataObserver
import com.andreesperanca.gymde.utils.Resource
import com.andreesperanca.gymde.utils.extensions.isVisible
import com.andreesperanca.gymde.utils.extensions.snackBarCreator
import com.andreesperanca.gymde.utils.generics.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<
        FragmentHomeBinding, HomeViewModel>(
    R.layout.fragment_home
) {
    /** UI COMPONENTS **/
    private lateinit var _rvTodayWorkouts: RecyclerView
    private lateinit var _rvHealthArticles: RecyclerView
    private lateinit var _pgHomeScreen: ProgressBar

    private val todayWorkoutsAdapter: TodayWorkoutAdapter by inject()
    private val healthArticlesAdapter: HealthArticlesAdapter by inject()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchWorkouts(requireContext())

        setupRecyclerViewHealthArticles()
        setupTodayWorkoutRecyclerView()
    }
    private fun setupRecyclerViewHealthArticles() {
        _rvHealthArticles.adapter = healthArticlesAdapter
        _rvHealthArticles.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }
    private fun setupTodayWorkoutRecyclerView() {
        _rvTodayWorkouts.adapter = todayWorkoutsAdapter
        _rvTodayWorkouts.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false)
        val emptyDataView = binding.emptyDataParent.root
        val emptyDataObserver = EmptyDataObserver(binding.rvWorkouts, emptyDataView)
        todayWorkoutsAdapter.registerAdapterDataObserver(emptyDataObserver)
    }
    override fun setupToolbar() { /**  NO HAVE TOOLBAR **/}
    override fun setupViewModel() {
        val viewModel: HomeViewModel by viewModel()
        this.viewModel = viewModel
    }
    override fun setupObservers() {
        viewModel.todayWorkouts.observe(viewLifecycleOwner) { todayWorkouts ->
            when (todayWorkouts) {
                is Resource.Success -> {
                    todayWorkoutsAdapter.updateData(todayWorkouts.data)
                    _pgHomeScreen.isVisible(false)
                }
                is Resource.Loading -> {
                    _pgHomeScreen.isVisible(true)
                }
                is Resource.Error -> {
                    _pgHomeScreen.isVisible(false)
                    snackBarCreator(todayWorkouts.message.toString())
                }
            }
        }
    }
    override fun initComponents() {
        _rvTodayWorkouts = binding.rvWorkouts
        _rvHealthArticles = binding.rvHealthArticles
        _pgHomeScreen = binding.pgProgressBarHome
    }
}