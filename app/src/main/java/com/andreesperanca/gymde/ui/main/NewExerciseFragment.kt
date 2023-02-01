package com.andreesperanca.gymde.ui.main

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.databinding.FragmentNewExerciseBinding
import com.andreesperanca.gymde.models.Exercise
import com.andreesperanca.gymde.ui.main.viewmodels.NewExerciseViewModel
import com.andreesperanca.gymde.utils.DefaultValues
import com.andreesperanca.gymde.utils.Resource
import com.andreesperanca.gymde.utils.disableComponents
import com.andreesperanca.gymde.utils.extensions.isValidName
import com.andreesperanca.gymde.utils.extensions.isVisible
import com.andreesperanca.gymde.utils.extensions.snackBarCreator
import com.andreesperanca.gymde.utils.extensions.text
import com.andreesperanca.gymde.utils.generics.BaseFragment
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewExerciseFragment() : BaseFragment<
        FragmentNewExerciseBinding,
        NewExerciseViewModel
        >(R.layout.fragment_new_exercise) {

    private val args: NewExerciseFragmentArgs by navArgs()
    private var cUri: Uri? = null

    /** UI COMPONENTS **/
    private lateinit var _tilName: TextInputLayout
    private lateinit var _tilDescription: TextInputLayout
    private lateinit var _tilQuantitySeries: TextInputLayout
    private lateinit var ivExercisePhoto: ImageView
    private lateinit var btnCreateExercise: Button
    private lateinit var btnCancelCreate: Button
    private lateinit var progressBarNewExercise: LinearProgressIndicator
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        ivExercisePhoto.setOnClickListener {
            val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Intent(MediaStore.ACTION_PICK_IMAGES)
            } else {
                TODO("VERSION.SDK_INT < TIRAMISU")
            }
            intent.type = "image/*"
            startActivityForResult(intent, DefaultValues.PHOTO_PICKER_REQUEST_CODE)
        }

        btnCreateExercise.setOnClickListener {
            if (_tilName.isValidName()) {
                if (cUri != null) {
                    viewModel.uploadPhoto(cUri!!)
                } else {
                    viewModel.createExercise(
                        Exercise(
                            workoutId = args.workout.uid,
                            name = _tilName.text(),
                            description = _tilDescription.text(),
                            image = DefaultValues.FIREBASE_DEFAULT_EXERCISE_PHOTO,
                            series = _tilQuantitySeries.text()
                        )
                    )
                }
            }
        }

        btnCancelCreate.setOnClickListener { findNavController().popBackStack() }
    }

    override fun setupToolbar() {
        /** NO HAVE TOOLBAR **/
    }

    override fun setupViewModel() {
        val viewModel: NewExerciseViewModel by viewModel()
        this.viewModel = viewModel
    }

    override fun setupObservers() {

        viewModel.uploadPhoto.observe(viewLifecycleOwner) { uploadLink ->
            when (uploadLink) {
                is Resource.Success -> {
                    val newExercise = Exercise(
                        workoutId = args.workout.uid,
                        name = _tilName.text(),
                        description = _tilDescription.text(),
                        image = uploadLink.data.toString(),
                        series = _tilQuantitySeries.text()
                    )
                    viewModel.createExercise(newExercise)
                }
                is Resource.Loading -> {
                    disableComponents(
                        listOf(
                            _tilName,
                            _tilDescription,
                            _tilQuantitySeries,
                            ivExercisePhoto,
                            btnCreateExercise,
                            btnCancelCreate
                        )
                    )
                    progressBarNewExercise.isVisible(true)
                }
                is Resource.Error -> {
                    snackBarCreator(uploadLink.message.toString())
                    progressBarNewExercise.isVisible(false)
                }
            }
        }
        viewModel.createExercise.observe(viewLifecycleOwner) { newExercise ->
            when (newExercise) {
                is Resource.Success -> {
                    progressBarNewExercise.isVisible(false)
                    val action = NewExerciseFragmentDirections
                        .actionNewExerciseFragmentToWorkoutDetailsFragment(args.workout)
                    findNavController().navigate(action)
                }
                is Resource.Loading -> {
                    if (_tilName.isEnabled) disableComponents(
                        listOf(
                            _tilName,
                            _tilDescription,
                            _tilQuantitySeries,
                            ivExercisePhoto,
                            btnCreateExercise,
                            btnCancelCreate
                        )
                    )
                    progressBarNewExercise.isVisible(true)
                }
                is Resource.Error -> {
                    snackBarCreator(newExercise.message.toString())
                    progressBarNewExercise.isVisible(false)
                }
            }
        }
    }

    override fun initComponents() {
        _tilName = binding.tilNewExerciseName
        _tilDescription = binding.tilNewExerciseDescription
        _tilQuantitySeries = binding.tilNewExerciseSeries
        ivExercisePhoto = binding.ivExercisePhoto
        btnCreateExercise = binding.btnCreateExercise
        btnCancelCreate = binding.btnCancelCreateExercise
        progressBarNewExercise = binding.pgProgressBarNewExercise
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            snackBarCreator(getString(R.string.photo_error))
            return
        }
        when (requestCode) {
            DefaultValues.PHOTO_PICKER_REQUEST_CODE -> {
                val currentUri: Uri = data?.data!!
                cUri = currentUri
                val bitmap: Bitmap = if (Build.VERSION.SDK_INT < 28) {
                    MediaStore.Images.Media.getBitmap(
                        requireContext().contentResolver,
                        currentUri
                    )
                } else {
                    val source =
                        ImageDecoder.createSource(requireContext().contentResolver, currentUri)
                    ImageDecoder.decodeBitmap(source)
                }
                ivExercisePhoto.setImageBitmap(bitmap)
                return
            }
        }
    }
}