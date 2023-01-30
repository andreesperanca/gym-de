package com.andreesperanca.gymde.utils.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.databinding.NewExerciseDialogBinding

class NewExerciseDialog : DialogFragment() {

    private val binding by lazy { NewExerciseDialogBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
//            setOnClickListener { dialog?.dismiss() }
//
//            btnCreateWorkout.setOnClickListener {
//                Toast.makeText(context, "Treino criado", Toast.LENGTH_SHORT).show()
//                dialog?.dismiss()
//            }

        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NO_FRAME, R.style.Theme_GymDe_FullScreen)
        return super.onCreateDialog(savedInstanceState)
    }

}