package com.andreesperanca.gymde.ui.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.databinding.FragmentWorkoutDetailsBinding
import com.andreesperanca.gymde.ui.main.viewmodels.WorkoutDetailsViewModel
import com.andreesperanca.gymde.utils.Resource
import com.andreesperanca.gymde.utils.extensions.toastCreator
import org.koin.androidx.viewmodel.ext.android.viewModel

class WorkoutDetailsFragment : Fragment() {

    val args: WorkoutDetailsFragmentArgs by navArgs()

    private val binding by lazy {
        FragmentWorkoutDetailsBinding.inflate(layoutInflater)
    }

    private val viewModel : WorkoutDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbWorkoutToolbar.apply {
            title = args.workout.name
            setNavigationOnClickListener { it.findNavController().popBackStack() }
            binding.tbWorkoutToolbar.apply {
                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.excludeWorkout -> {
                            viewModel.excludeWorkout(args.workout)
                            true
                        }
                        R.id.editWorkout -> {
                            toastCreator("Edit workout")
                            false
                        }
                        else -> {
                            false
                        }
                    }
                }
            }
        }

        binding.extendedFab.setOnClickListener {
            findNavController().navigate(R.id.action_workoutDetailsFragment_to_newExerciseFragment)
        }

        viewModel.excludeWorkout.observe(viewLifecycleOwner) {

            when(it){
                is Resource.Success -> {
                    findNavController().navigate(R.id.action_workoutDetailsFragment_to_myWorkoutsFragment)
                }
                is Resource.Loading -> {
                    binding.pgProgressBarWorkoutDetails.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    toastCreator(it.message.toString())
                    binding.pgProgressBarWorkoutDetails.visibility = View.INVISIBLE
                }
            }

        }

    }
}