package com.andreesperanca.gymde

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.andreesperanca.gymde.databinding.FragmentCreateWorkoutBinding
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.utils.extensions.isValidName
import java.sql.Timestamp


class CreateWorkoutFragment : Fragment() {

    private val binding by lazy {
        FragmentCreateWorkoutBinding.inflate(layoutInflater)
    }

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
                daysOfWeek.add(R.string.monday.toString())
            }
            if (binding.cbTuesday.isChecked) {
                daysOfWeek.add(R.string.tuesday.toString())
            }
            if (binding.cbWednesday.isChecked) {
                daysOfWeek.add(R.string.wednesday.toString())
            }
            if (binding.cbThursday.isChecked) {
                daysOfWeek.add(R.string.thursday.toString())
            }
            if (binding.cbFriday.isChecked) {
                daysOfWeek.add(R.string.friday.toString())
            }
            if (binding.cbSaturday.isChecked) {
                daysOfWeek.add(R.string.saturday.toString())
            }
            if (binding.cbSunday.isChecked) {
                daysOfWeek.add(R.string.sunday.toString())
            }

            if (binding.tilNewWorkoutName.isValidName()) {

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

                val newWorkout = Workout(
                    name = name,
                    description = description,
                    date = Timestamp(System.currentTimeMillis()),
                    dayOfWeek = daysOfWeek,
                    exercises = emptyList()
                )
                /** create new workout db **/
                Handler().postDelayed({
                    findNavController().popBackStack()
                }, 3000)
            }

        }

    }
}