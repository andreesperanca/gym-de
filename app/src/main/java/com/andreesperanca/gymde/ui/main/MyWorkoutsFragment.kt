package com.andreesperanca.gymde.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreesperanca.gymde.R
import com.andreesperanca.gymde.adapters.MyWorkoutsAdapter
import com.andreesperanca.gymde.databinding.FragmentMyWorkoutsBinding
import com.andreesperanca.gymde.ui.main.viewmodels.MyWorkoutsViewModel
import com.andreesperanca.gymde.utils.Resource
import com.andreesperanca.gymde.utils.extensions.toastCreator
import com.google.android.material.divider.MaterialDividerItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyWorkoutsFragment : Fragment() {

    private val binding by lazy {
        FragmentMyWorkoutsBinding.inflate(layoutInflater)
    }

    private val adapter by lazy {
        MyWorkoutsAdapter()
    }

    private val viewModel : MyWorkoutsViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val divider =
            MaterialDividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        val recyclerView = binding.rvWorkout
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(divider)

        binding.extendedFab.setOnClickListener {
            findNavController().navigate(R.id.action_myWorkoutsFragment_to_createWorkoutFragment)
        }

        viewModel.fetchWorkouts()

        viewModel.workouts.observe(viewLifecycleOwner) {

            when(it){
                is Resource.Success -> {
                    adapter.updateData(it.data)
                    binding.pgProgressBarMyWorkouts.visibility = View.INVISIBLE
                }
                is Resource.Loading -> {
                    binding.pgProgressBarMyWorkouts.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.pgProgressBarMyWorkouts.visibility = View.INVISIBLE
                    Log.i("iError", it.message.toString() )
                }
            }
        }
    }
}