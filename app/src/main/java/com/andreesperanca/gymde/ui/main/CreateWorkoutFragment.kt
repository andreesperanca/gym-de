package com.andreesperanca.gymde.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import androidx.navigation.fragment.findNavController
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.databinding.FragmentCreateWorkoutBinding
import com.andreesperanca.gymde.models.Workout
import com.andreesperanca.gymde.ui.main.viewmodels.CreateWorkoutViewModel
import com.andreesperanca.gymde.utils.Resource
import com.andreesperanca.gymde.utils.disableComponents
import com.andreesperanca.gymde.utils.extensions.isValidName
import com.andreesperanca.gymde.utils.extensions.isVisible
import com.andreesperanca.gymde.utils.extensions.text
import com.andreesperanca.gymde.utils.extensions.toastCreator
import com.andreesperanca.gymde.utils.generics.BaseFragment
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.sql.Timestamp


class CreateWorkoutFragment : BaseFragment<
        FragmentCreateWorkoutBinding,
        CreateWorkoutViewModel
        >(R.layout.fragment_create_workout) {

    /** UI COMPONENTS **/
    private lateinit var _progressBar: LinearProgressIndicator
    private lateinit var _tilName: TextInputLayout
    private lateinit var _tilDescription: TextInputLayout
    private lateinit var _checkBoxMonday: CheckBox
    private lateinit var _checkBoxTuesday: CheckBox
    private lateinit var _checkBoxWednesday: CheckBox
    private lateinit var _checkBoxThursday: CheckBox
    private lateinit var _checkBoxFriday: CheckBox
    private lateinit var _checkBoxSaturday: CheckBox
    private lateinit var _checkBoxSunday: CheckBox
    private lateinit var _btnCancel: Button
    private lateinit var _btnCreateWorkout: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
    }
    private fun setupClickListeners() {
        binding.btnCancel.setOnClickListener { findNavController().popBackStack() }

        binding.btnCreateWorkout.setOnClickListener {
            val name = _tilName.text()
            val description = _tilDescription.text()
            val daysOfWeek = mutableListOf<String>()
            if (_checkBoxMonday.isChecked) {
                daysOfWeek.add(this.getString(R.string.monday))
            }
            if (_checkBoxTuesday.isChecked) {
                daysOfWeek.add(this.getString(R.string.tuesday))
            }
            if (_checkBoxWednesday.isChecked) {
                daysOfWeek.add(this.getString(R.string.wednesday))
            }
            if (_checkBoxThursday.isChecked) {
                daysOfWeek.add(this.getString(R.string.thursday))
            }
            if (_checkBoxFriday.isChecked) {
                daysOfWeek.add(this.getString(R.string.friday))
            }
            if (_checkBoxSaturday.isChecked) {
                daysOfWeek.add(this.getString(R.string.saturday))
            }
            if (_checkBoxSunday.isChecked) {
                daysOfWeek.add(this.getString(R.string.sunday))
            }

            if (_tilName.isValidName()) {
                val newWorkout = Workout(
                    name = name,
                    description = description,
                    date = Timestamp(System.currentTimeMillis()),
                    dayOfWeek = daysOfWeek,
                )
                viewModel.createWorkout(newWorkout)
            }
        }
    }

    override fun setupToolbar() {
        /** NO HAVE TOOLBAR **/
    }

    override fun setupViewModel() {
        val viewModel: CreateWorkoutViewModel by viewModel()
        this.viewModel = viewModel
    }

    override fun setupObservers() {
        viewModel.createWorkout.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    _progressBar.isVisible(false)
                    findNavController().popBackStack()
                }
                is Resource.Error -> {
                    toastCreator(it.message.toString())
                }
                is Resource.Loading -> {
                    disableComponents(listOf(
                            _tilName,
                            _tilDescription,
                            _checkBoxMonday,
                            _checkBoxTuesday,
                            _checkBoxThursday,
                            _checkBoxWednesday,
                            _checkBoxFriday,
                            _checkBoxSaturday,
                            _checkBoxSunday
                        ))
                    _progressBar.isVisible(true)
                }
            }
        }
    }
    override fun initComponents() {
        _tilName = binding.tilNewWorkoutName
        _tilDescription = binding.tilNewWorkoutDescription
        _checkBoxSunday = binding.cbSunday
        _checkBoxMonday = binding.cbMonday
        _checkBoxTuesday = binding.cbTuesday
        _checkBoxWednesday = binding.cbWednesday
        _checkBoxFriday = binding.cbFriday
        _checkBoxSaturday = binding.cbSaturday
        _checkBoxThursday = binding.cbThursday
        _btnCancel = binding.btnCancel
        _btnCreateWorkout = binding.btnCreateWorkout
        _progressBar = binding.pgNewWorkoutDialog
    }
}