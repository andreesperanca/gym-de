package com.andreesperanca.gymde.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreesperanca.gymde.data.mockWorkouts
import com.andreesperanca.gymde.databinding.MyWorkoutItemBinding
import com.andreesperanca.gymde.models.Workouts

class MyWorkoutsAdapter() : RecyclerView.Adapter<MyWorkoutsAdapter.WorkoutsViewHolder>() {

    val workoutsList: List<Workouts> = mockWorkouts

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutsViewHolder {
        val binding =
            MyWorkoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkoutsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkoutsViewHolder, position: Int) {
        holder.bind(workoutsList[position])
    }

    override fun getItemCount(): Int = workoutsList.size

    inner class WorkoutsViewHolder(private val binding: MyWorkoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(workouts: Workouts) {


            binding.tvWorkoutTitle.text = workouts.name
            binding.tvWorkoutDescription.text = workouts.description
        }
    }
}