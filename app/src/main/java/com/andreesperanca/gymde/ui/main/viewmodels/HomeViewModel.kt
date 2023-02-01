package com.andreesperanca.gymde.ui.main.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.repositories.HomeRepository
import com.andreesperanca.gymde.utils.Resource
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    private val _todayWorkouts = MutableLiveData<Resource<List<Workout>>>()
    val todayWorkouts: LiveData<Resource<List<Workout>>> = _todayWorkouts

    fun fetchWorkouts(context: Context) {
        _todayWorkouts.value = Resource.Loading()
        viewModelScope.launch {
            val workouts = repository.fetchTodayWorkouts(context)
            _todayWorkouts.value = workouts
        }
    }
}