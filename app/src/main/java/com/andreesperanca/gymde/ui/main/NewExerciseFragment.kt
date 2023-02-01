package com.andreesperanca.gymde.ui.main

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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.databinding.FragmentNewExerciseBinding
import com.andreesperanca.gymde.models.Exercise
import com.andreesperanca.gymde.ui.main.viewmodels.NewExerciseViewModel
import com.andreesperanca.gymde.utils.Resource
import com.andreesperanca.gymde.utils.extensions.isValidName
import com.andreesperanca.gymde.utils.extensions.toastCreator
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewExerciseFragment() : Fragment() {

    private val binding by lazy {
        FragmentNewExerciseBinding.inflate(layoutInflater)
    }

    val args : NewExerciseFragmentArgs by navArgs()

    private lateinit var cUri: Uri

    val viewModel: NewExerciseViewModel by viewModel()

    private val PHOTO_PICKER_REQUEST_CODE = 1231

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivExercisePhoto.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
            intent.type = "image/*"
            startActivityForResult(intent, PHOTO_PICKER_REQUEST_CODE)
        }

        binding.btnCreateExercise.setOnClickListener {
            val name = binding.tilNewExerciseName
            val description = binding.tilNewExerciseDescription
            val seriesQuantity = binding.tilNewExerciseSeries

            if (name.isValidName()) {
                viewModel.uploadPhoto(uri = cUri)
            }

        }

        viewModel.uploadPhoto.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Success -> {
                    /** CREATE EXERCISE **/
                    val name = binding.tilNewExerciseName
                    val description = binding.tilNewExerciseDescription
                    val quantitySeries = binding.tilNewExerciseSeries
                    val newExercise = Exercise(
                        workoutId = args.workout.uid,
                        name = name.editText?.text.toString(),
                        description = description.editText?.text.toString(),
                        image = it.data.toString(),
                        series = quantitySeries.editText?.text.toString()
                    )
                    viewModel.createExercise(newExercise)
                }
                is Resource.Loading -> {
                    binding.pgProgressBarNewExercise.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    toastCreator(it.message.toString())
                }
            }
        }

        viewModel.createExercise.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Success -> {
                    binding.pgProgressBarNewExercise.visibility = View.INVISIBLE
                    val action = NewExerciseFragmentDirections.actionNewExerciseFragmentToWorkoutDetailsFragment(args.workout)
                    findNavController().navigate(action)
                }
                is Resource.Loading -> {
                }
                is Resource.Error -> {
                    toastCreator(it.message.toString())
                    binding.pgProgressBarNewExercise.visibility = View.INVISIBLE
                }
            }
        }

    }

    // onActivityResult() handles callbacks from the photo picker.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            // Handle error
            return
        }
        when (requestCode) {
            PHOTO_PICKER_REQUEST_CODE -> {
                // Get photo picker response for single select.
                val currentUri: Uri = data?.data!!

                cUri = currentUri

                val bitmap: Bitmap = if (Build.VERSION.SDK_INT < 28) {
                    MediaStore.Images.Media.getBitmap(
                        requireContext().contentResolver,
                        currentUri
                    )
                } else {
                    val source = ImageDecoder.createSource(
                        requireContext().contentResolver,
                        currentUri
                    )
                    ImageDecoder.decodeBitmap(source)
                }

                binding.ivExercisePhoto.setImageBitmap(bitmap)
                // Do stuff with the photo/video URI.
                return
            }

        }
    }
}