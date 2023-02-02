package com.andreesperanca.gymde.ui.main.viewmodels

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreesperanca.gymde.databinding.FragmentNewExerciseBinding
import com.andreesperanca.gymde.models.Exercise
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.repositories.CreateExerciseRepository
import com.andreesperanca.gymde.repositories.CreateWorkoutRepository
import com.andreesperanca.gymde.utils.Resource
import kotlinx.coroutines.launch
import java.net.URI

class NewExerciseViewModel(
    private val repository: CreateExerciseRepository
) : ViewModel() {


    private val _createExercise = MutableLiveData<Resource<Unit>>()
    val createExercise: LiveData<Resource<Unit>> = _createExercise

    private val _uploadPhoto = MutableLiveData<Resource<Uri>>()
    val uploadPhoto: LiveData<Resource<Uri>> = _uploadPhoto

    fun createExercise(newExercise: Exercise) {
        _createExercise.value = Resource.Loading()
        viewModelScope.launch {
            repository.createExercise(newExercise)
            _createExercise.value = Resource.Success(Unit)
        }
    }

    fun uploadPhoto(uri: Uri) {
        _uploadPhoto.value = Resource.Loading()
        viewModelScope.launch {
            val result = repository.uploadPhoto(uri)
            _uploadPhoto.value = result
        }
    }
}