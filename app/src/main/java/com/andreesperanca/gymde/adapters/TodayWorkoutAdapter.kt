package com.andreesperanca.gymde.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.andreesperanca.gymde.data.mockWorkouts
import com.andreesperanca.gymde.databinding.WorkoutsItemBinding
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.ui.login_and_register.LoginHomeScreenDirections
import com.andreesperanca.gymde.ui.main.HomeFragmentDirections
import com.google.android.material.snackbar.Snackbar

class TodayWorkoutAdapter() : RecyclerView.Adapter<TodayWorkoutAdapter.WorkoutsViewHolder>() {

    var workoutList: List<Workout> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutsViewHolder {
        val binding =
            WorkoutsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkoutsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkoutsViewHolder, position: Int) {
        holder.bind(workoutList[position])
    }

    override fun getItemCount(): Int = workoutList.size
    fun updateData(data: List<Workout>?) {
        if (data != null) {
            workoutList = data
            notifyDataSetChanged()
        }

    }

    inner class WorkoutsViewHolder(private val binding: WorkoutsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(workout: Workout) {
            binding.tvWorkoutTitle.text = workout.description
            binding.tvWorkoutDescription.text = workout.description

            binding.btnEditWorkout.setOnClickListener {
                Snackbar.make(
                    binding.root,
                    "Função implementada apenas na aba treinos.",
                    Snackbar.LENGTH_LONG
                ).show()

            }
            binding.btnStartWorkout.setOnClickListener {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToWorkoutDetailsFragment(workout)
                it.findNavController().navigate(action)
            }
        }
    }
}