package com.andreesperanca.gymde.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.repositories.interfaces.MyWorkoutRepository
import com.andreesperanca.gymde.utils.Resource
import kotlinx.coroutines.launch

class MyWorkoutsViewModel(
    private val repository: MyWorkoutRepository
) : ViewModel() {


    private val _workouts = MutableLiveData<Resource<List<Workout>>>()
    val workouts: LiveData<Resource<List<Workout>>> = _workouts

    private val _updateWorkouts = MutableLiveData<Resource<Unit>>()
    val updateWorkouts: LiveData<Resource<Unit>> = _updateWorkouts



    fun fetchWorkouts() {
        _workouts.value = Resource.Loading()
        viewModelScope.launch {
            val workouts = repository.fetchWorkouts()
            _workouts.value = workouts
        }
    }

    fun updateWorkout(workout: Workout, newName: String, newDescription: String) {
        _updateWorkouts.value = Resource.Loading()
        viewModelScope.launch {
            repository.updateWorkout(workout, newName, newDescription)
            _updateWorkouts.value = Resource.Success(Unit)
        }
    }
}