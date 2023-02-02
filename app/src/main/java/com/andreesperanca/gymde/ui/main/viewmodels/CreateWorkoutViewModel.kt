package com.andreesperanca.gymde.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.repositories.interfaces.CreateWorkoutRepository
import com.andreesperanca.gymde.utils.Resource
import kotlinx.coroutines.launch

class CreateWorkoutViewModel(
    private val repository: CreateWorkoutRepository
) : ViewModel() {


    private val _createWorkout = MutableLiveData<Resource<Unit>>()
    val createWorkout: LiveData<Resource<Unit>> = _createWorkout

    fun createWorkout(newWorkout: Workout) {
        _createWorkout.value = Resource.Loading()
        viewModelScope.launch {
            repository.createWorkout(newWorkout)
            _createWorkout.value = Resource.Success(Unit)
        }
    }
}