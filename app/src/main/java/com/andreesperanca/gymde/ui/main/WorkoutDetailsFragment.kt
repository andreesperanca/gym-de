package com.andreesperanca.gymde.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.adapters.ExerciseAdapter
import com.andreesperanca.gymde.databinding.FragmentWorkoutDetailsBinding
import com.andreesperanca.gymde.ui.main.viewmodels.WorkoutDetailsViewModel
import com.andreesperanca.gymde.utils.Resource
import com.andreesperanca.gymde.utils.extensions.isVisible
import com.andreesperanca.gymde.utils.extensions.toastCreator
import com.andreesperanca.gymde.utils.generics.BaseFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.progressindicator.LinearProgressIndicator
import org.koin.androidx.viewmodel.ext.android.viewModel

class WorkoutDetailsFragment : BaseFragment<
        FragmentWorkoutDetailsBinding,
        WorkoutDetailsViewModel
        >(R.layout.fragment_workout_details) {

    private val args: WorkoutDetailsFragmentArgs by navArgs()

    /** UI COMPONENTS **/
    private lateinit var tbWorkoutDetails: Toolbar
    private lateinit var rvExercises: RecyclerView
    private lateinit var fabCreateExercise: FloatingActionButton
    private lateinit var progressBarWorkoutDetails: LinearProgressIndicator

    private val adapter by lazy { ExerciseAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupExercisesRecyclerView()
        setupClickListeners()
        /** FETCH DATA **/
        viewModel.fetchExercises(args.workout.uid)
    }

    private fun setupClickListeners() {
        fabCreateExercise.setOnClickListener {
            val action =
                WorkoutDetailsFragmentDirections.actionWorkoutDetailsFragmentToNewExerciseFragment(
                    args.workout
                )
            findNavController().navigate(action)
        }
    }

    private fun setupExercisesRecyclerView() {
        binding.rvExercise.adapter = adapter
        binding.rvExercise.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    override fun setupToolbar() {
        tbWorkoutDetails.apply {
            title = args.workout.name
            setNavigationOnClickListener { findNavController().popBackStack() }
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.excludeWorkout -> {
                        viewModel.excludeWorkout(args.workout)
                        true
                    }
                    else -> {
                        false
                    }
                }
            }

        }
    }

    override fun setupViewModel() {
        val viewModel: WorkoutDetailsViewModel by viewModel()
        this.viewModel = viewModel
    }

    override fun setupObservers() {
        viewModel.excludeWorkout.observe(viewLifecycleOwner) { excludeWorkout ->

            when (excludeWorkout) {
                is Resource.Success -> {
                    progressBarWorkoutDetails.isVisible(false)
                    findNavController().navigate(R.id.action_workoutDetailsFragment_to_myWorkoutsFragment)
                }
                is Resource.Loading -> {
                    progressBarWorkoutDetails.isVisible(true)
                }
                is Resource.Error -> {
                    toastCreator(excludeWorkout.message.toString())
                    progressBarWorkoutDetails.isVisible(false)
                }
            }

        }
        viewModel.fetchExercises.observe(viewLifecycleOwner) { exerciseList ->
            when (exerciseList) {
                is Resource.Success -> {
                    adapter.updateData(exerciseList.data)
                    progressBarWorkoutDetails.isVisible(false)
                }
                is Resource.Loading -> {
                    progressBarWorkoutDetails.isVisible(true)
                }
                is Resource.Error -> {
                    toastCreator(exerciseList.message.toString())
                    progressBarWorkoutDetails.isVisible(false)
                }
            }
        }
    }

    override fun initComponents() {
        progressBarWorkoutDetails = binding.pgProgressBarWorkoutDetails
        rvExercises = binding.rvExercise
        fabCreateExercise = binding.fabCreateExercise
        tbWorkoutDetails = binding.tbWorkoutToolbar
    }
}