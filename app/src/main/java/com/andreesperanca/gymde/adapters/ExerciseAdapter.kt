package com.andreesperanca.gymde.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.adapters.ExerciseAdapter.ExerciseViewHolder
import com.andreesperanca.gymde.databinding.ExerciseItemBinding
import com.andreesperanca.gymde.models.Exercise
import com.bumptech.glide.Glide

class ExerciseAdapter() : RecyclerView.Adapter<ExerciseViewHolder>() {

    var exerciseList: List<Exercise> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding =
            ExerciseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(exerciseList[position])
    }

    override fun getItemCount(): Int = exerciseList.size

    fun updateData(data: List<Exercise>?) {
        if( data != null) {
            exerciseList = data
            notifyItemRangeInserted(0, exerciseList.size)
        }
    }


    inner class ExerciseViewHolder(private val binding: ExerciseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: Exercise) {
            binding.tvExerciseTitle.text = exercise.name
            binding.tvExerciseDescription.text = exercise.description

            Glide
                .with(binding.root)
                .load(exercise.image)
                .centerCrop()
                .placeholder(R.drawable.workout_bg)
                .into(binding.ivExercise)

        }
    }
}