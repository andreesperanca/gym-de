package com.andreesperanca.gymde

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.andreesperanca.gymde.databinding.FragmentWorkoutDetailsBinding
import com.andreesperanca.gymde.utils.extensions.toastCreator

class WorkoutDetailsFragment : Fragment() {

    val args: WorkoutDetailsFragmentArgs by navArgs()
    val PHOTO_PICKER_REQUEST_CODE = 2783

    private val binding by lazy {
        FragmentWorkoutDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.extendedFab.setOnClickListener {
            findNavController().navigate(R.id.action_workoutDetailsFragment_to_newExerciseFragment)
            onDestroy()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            // Handle error
            return
        }
        when (requestCode) {
            PHOTO_PICKER_REQUEST_CODE -> {
                // Get photo picker response for single select.
                val currentUri: Uri? = data?.data

                return
            }
        }
    }

}