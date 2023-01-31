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
import com.andreesperanca.gymde.databinding.FragmentNewExerciseBinding

class NewExerciseFragment() : Fragment() {

    private val binding by lazy {
        FragmentNewExerciseBinding.inflate(layoutInflater)
    }

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