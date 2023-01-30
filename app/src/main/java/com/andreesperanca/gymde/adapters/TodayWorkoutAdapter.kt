package com.andreesperanca.gymde.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreesperanca.gymde.data.mockWorkouts
import com.andreesperanca.gymde.databinding.WorkoutsItemBinding
import com.andreesperanca.gymde.models.Workout

class TodayWorkoutAdapter() : RecyclerView.Adapter<TodayWorkoutAdapter.WorkoutsViewHolder>() {

    val workoutList: List<Workout> = mockWorkouts

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutsViewHolder {
        val binding =
            WorkoutsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkoutsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkoutsViewHolder, position: Int) {
        holder.bind(workoutList[position])
    }

    override fun getItemCount(): Int = workoutList.size

    inner class WorkoutsViewHolder(private val binding: WorkoutsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(workout: Workout) {
            binding.tvWorkoutTitle.text = workout.description
            binding.tvWorkoutDescription.text = workout.description

        }
    }
}