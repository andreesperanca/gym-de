package com.andreesperanca.gymde.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.databinding.FragmentCreateWorkoutBinding
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.ui.main.viewmodels.CreateWorkoutViewModel
import com.andreesperanca.gymde.utils.Resource
import com.andreesperanca.gymde.utils.extensions.isValidName
import com.andreesperanca.gymde.utils.extensions.toastCreator
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.sql.Timestamp
import kotlin.math.absoluteValue


class CreateWorkoutFragment : Fragment() {

    private val binding by lazy {
        FragmentCreateWorkoutBinding.inflate(layoutInflater)
    }

    private val viewModel: CreateWorkoutViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCancel.setOnClickListener { findNavController().popBackStack() }

        binding.btnCreateWorkout.setOnClickListener {
            val name = binding.tilNewWorkoutName.editText?.text.toString()
            val description = binding.tilNewWorkoutDescription.editText?.text.toString()

            val daysOfWeek = mutableListOf<String>()
            if (binding.cbMonday.isChecked) {
                daysOfWeek.add(this.getString(R.string.monday))
            }
            if (binding.cbTuesday.isChecked) {
                daysOfWeek.add(this.getString(R.string.tuesday))
            }
            if (binding.cbWednesday.isChecked) {
                daysOfWeek.add(this.getString(R.string.wednesday))
            }
            if (binding.cbThursday.isChecked) {
                daysOfWeek.add(this.getString(R.string.thursday))
            }
            if (binding.cbFriday.isChecked) {
                daysOfWeek.add(this.getString(R.string.friday))
            }
            if (binding.cbSaturday.isChecked) {
                daysOfWeek.add(this.getString(R.string.saturday))
            }
            if (binding.cbSunday.isChecked) {
                daysOfWeek.add(this.getString(R.string.sunday))
            }

            if (binding.tilNewWorkoutName.isValidName()) {

                val newWorkout = Workout(
                    name = name,
                    description = description,
                    date = Timestamp(System.currentTimeMillis()),
                    dayOfWeek = daysOfWeek,
                )


                viewModel.createWorkout(newWorkout)


            }
        }

        viewModel.createWorkout.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    toastCreator(getString(R.string.create_success))
                    findNavController().popBackStack()
                }
                is Resource.Error -> {
                    toastCreator(it.message.toString())
                }
                is Resource.Loading -> {
                    binding.tilNewWorkoutName.isEnabled = false
                    binding.tilNewWorkoutDescription.isEnabled = false
                    binding.cbMonday.isEnabled = false
                    binding.cbTuesday.isEnabled = false
                    binding.cbThursday.isEnabled = false
                    binding.cbWednesday.isEnabled = false
                    binding.cbFriday.isEnabled = false
                    binding.cbSaturday.isEnabled = false
                    binding.cbSunday.isEnabled = false
                    binding.pgNewWorkoutDialog.visibility = View.VISIBLE
                }
            }
        }
    }
}