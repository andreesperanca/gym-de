package com.andreesperanca.gymde.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.andreesperanca.gymde.data.mockWorkouts
import com.andreesperanca.gymde.databinding.MyWorkoutItemBinding
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.ui.main.MyWorkoutsFragmentDirections

class MyWorkoutsAdapter() : RecyclerView.Adapter<MyWorkoutsAdapter.WorkoutsViewHolder>() {

    var workoutList: List<Workout> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutsViewHolder {
        val binding =
            MyWorkoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkoutsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WorkoutsViewHolder, position: Int) {
        holder.bind(workoutList[position])
    }

    override fun getItemCount(): Int = workoutList.size
    fun updateData(data: List<Workout>?) {
        if (data != null) {
            workoutList = data
            notifyItemRangeChanged(0, data.size)
        }
    }

    inner class WorkoutsViewHolder(private val binding: MyWorkoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(workout: Workout) {

            binding.tvWorkoutTitle.text = workout.name
            binding.tvWorkoutDescription.text = workout.description

            binding.root.setOnClickListener {
                val action =
                    MyWorkoutsFragmentDirections.actionMyWorkoutsFragmentToWorkoutDetailsFragment(
                        workout
                    )
                it.findNavController().navigate(action)
            }
        }
    }
}