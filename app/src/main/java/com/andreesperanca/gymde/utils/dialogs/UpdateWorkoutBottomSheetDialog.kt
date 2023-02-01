package com.andreesperanca.gymde.utils.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andreesperanca.gymde.databinding.UpdateWorkoutDialogBinding
import com.andreesperanca.gymde.utils.extensions.isValidName
import com.andreesperanca.gymde.utils.extensions.text
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UpdateWorkoutBottomSheetDialog(
    private val updateWorkout: (newName: String, newDescription: String) -> Unit

) : BottomSheetDialogFragment() {

    private val binding by lazy {
        UpdateWorkoutDialogBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCancelUpdateWorkout.setOnClickListener {
            this.dialog?.dismiss()
        }

        binding.btnSaveUpdateWorkout.setOnClickListener {
            val tilNewName = binding.tilUpdateWorkoutName
            val tilNewDescription = binding.tilUpdateWorkoutDescription

            val newName = tilNewName.text()
            val newDescription = tilNewDescription.text()

            if (tilNewName.isValidName()) {
                updateWorkout(newName, newDescription)
                this.dialog?.dismiss()
            }
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}
