package com.andreesperanca.gymde.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreesperanca.gymde.adapters.MyWorkoutsAdapter
import com.andreesperanca.gymde.databinding.FragmentMyWorkoutsBinding
import com.andreesperanca.gymde.utils.dialogs.NewWorkoutDialog
import com.google.android.material.divider.MaterialDividerItemDecoration

class MyWorkoutsFragment : Fragment() {

    private val binding by lazy {
        FragmentMyWorkoutsBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val divider =
            MaterialDividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        val recyclerView = binding.rvWorkout
        recyclerView.adapter = MyWorkoutsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(divider)

        binding.extendedFab.setOnClickListener {
            NewWorkoutDialog().show(this.parentFragmentManager, it.transitionName)
        }
    }
}