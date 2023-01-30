package com.andreesperanca.gymde

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.andreesperanca.gymde.databinding.FragmentWorkoutDetailsBinding
import com.andreesperanca.gymde.utils.dialogs.NewExerciseDialog
import com.andreesperanca.gymde.utils.extensions.toastCreator

class WorkoutDetailsFragment : Fragment() {


    val args: WorkoutDetailsFragmentArgs by navArgs()

    private val binding by lazy {
        FragmentWorkoutDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbWorkoutToolbar.apply {
            title = args.workout.name
            setNavigationOnClickListener { it.findNavController().popBackStack() }
            inflateMenu(R.menu.workout_menu).apply {
                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.excludeWorkout -> {
                            toastCreator("Exclude workout")
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
            NewExerciseDialog().show(this.parentFragmentManager, it.transitionName)
        }
    }
}