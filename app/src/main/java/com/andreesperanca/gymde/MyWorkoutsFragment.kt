package com.andreesperanca.gymde

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreesperanca.gymde.adapters.MyWorkoutsAdapter
import com.andreesperanca.gymde.databinding.FragmentMyWorkoutsBinding
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

        val divider = MaterialDividerItemDecoration(requireContext(), VERTICAL)
        val recyclerView = binding.rvWorkout
        recyclerView.adapter = MyWorkoutsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(divider)
    }
}