package com.andreesperanca.gymde.ui.main

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.adapters.MyWorkoutsAdapter
import com.andreesperanca.gymde.databinding.FragmentMyWorkoutsBinding
import com.andreesperanca.gymde.ui.main.viewmodels.MyWorkoutsViewModel
import com.andreesperanca.gymde.utils.Resource
import com.andreesperanca.gymde.utils.dialogs.UpdateWorkoutBottomSheetDialog
import com.andreesperanca.gymde.utils.extensions.isVisible
import com.andreesperanca.gymde.utils.extensions.snackBarCreator
import com.andreesperanca.gymde.utils.generics.BaseFragment
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.progressindicator.LinearProgressIndicator
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyWorkoutsFragment : BaseFragment<
        FragmentMyWorkoutsBinding,
        MyWorkoutsViewModel>(R.layout.fragment_my_workouts) {

    /** UI COMPONENTS **/
    private lateinit var _rvMyWorkouts: RecyclerView
    private lateinit var _addWorkoutButton: ExtendedFloatingActionButton
    private lateinit var _pgMyWorkouts: LinearProgressIndicator

    private val adapter by lazy { MyWorkoutsAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMyWorkoutsRecyclerView()
        setupClickListeners()

        /** FETCH DATA **/
        viewModel.fetchWorkouts()
    }

    private fun setupClickListeners() {
        _addWorkoutButton.setOnClickListener {
            findNavController().navigate(R.id.action_myWorkoutsFragment_to_createWorkoutFragment)
        }
    }
    private fun setupMyWorkoutsRecyclerView() {
        val divider =
            MaterialDividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        adapter.updateWorkout = {
            val modalBottomSheet = UpdateWorkoutBottomSheetDialog(
                updateWorkout = { newName, newDescription ->
                    viewModel.updateWorkout(it, newName, newDescription)
                })
            modalBottomSheet.show(this.parentFragmentManager, UpdateWorkoutBottomSheetDialog.TAG)
        }
        _rvMyWorkouts.adapter = adapter
        _rvMyWorkouts.layoutManager = LinearLayoutManager(requireContext())
        _rvMyWorkouts.addItemDecoration(divider)
    }
    override fun setupToolbar() { /** NO HAVE TOOLBAR **/ }
    override fun setupViewModel() {
        val viewModel: MyWorkoutsViewModel by viewModel()
        this.viewModel = viewModel
    }

    override fun setupObservers() {
        viewModel.workouts.observe(viewLifecycleOwner) { workoutList ->
            when (workoutList) {
                is Resource.Success -> {
                    adapter.updateData(workoutList.data)
                    _pgMyWorkouts.isVisible(false)
                }
                is Resource.Loading -> {
                    _pgMyWorkouts.isVisible(true)
                }
                is Resource.Error -> {
                    snackBarCreator(workoutList.message.toString())
                    _pgMyWorkouts.isVisible(false)
                }
            }
        }
        viewModel.updateWorkouts.observe(viewLifecycleOwner) { update ->
            when (update) {
                is Resource.Success -> {
                    viewModel.fetchWorkouts()
                    _pgMyWorkouts.isVisible(false)
                }
                is Resource.Loading -> {
                    _pgMyWorkouts.isVisible(true)
                }
                is Resource.Error -> { snackBarCreator(update.message.toString()) }
            }

        }
    }

    override fun initComponents() {
        _rvMyWorkouts = binding.rvWorkout
        _addWorkoutButton = binding.extendedFab
        _pgMyWorkouts = binding.pgProgressBarMyWorkouts
    }
}