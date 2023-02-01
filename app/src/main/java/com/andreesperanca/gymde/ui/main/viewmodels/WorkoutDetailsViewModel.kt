package com.andreesperanca.gymde.ui.main.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.repositories.WorkoutDetailsRepository
import com.andreesperanca.gymde.utils.Resource
import kotlinx.coroutines.launch

class WorkoutDetailsViewModel(
    private val repository: WorkoutDetailsRepository

) : ViewModel() {

    private val _excludeWorkout = MutableLiveData<Resource<Unit>>()
    val excludeWorkout: LiveData<Resource<Unit>> = _excludeWorkout


    fun excludeWorkout(workout: Workout) {
        _excludeWorkout.value = Resource.Loading()
        viewModelScope.launch {
            repository.excludeWorkout(workout)
            _excludeWorkout.value = Resource.Success(Unit)
        }
    }
}