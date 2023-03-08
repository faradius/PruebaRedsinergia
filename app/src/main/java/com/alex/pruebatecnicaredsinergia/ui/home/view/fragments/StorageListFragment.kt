package com.alex.pruebatecnicaredsinergia.ui.home.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alex.pruebatecnicaredsinergia.R
import com.alex.pruebatecnicaredsinergia.databinding.FragmentStorageBinding
import com.alex.pruebatecnicaredsinergia.ui.home.view.adapters.StorageAdapter
import com.alex.pruebatecnicaredsinergia.ui.home.viewmodel.StorageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StorageListFragment : Fragment() {

    private val viewModel by viewModels<StorageViewModel>()
    private lateinit var binding: FragmentStorageBinding

    private val storageAdapter: StorageAdapter = StorageAdapter {
        findNavController().navigate(
            R.id.action_storageListFragment_to_productDetailFragment,
            bundleOf(
                "id" to it
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        subscribeLocations()
        return inflater.inflate(R.layout.fragment_storage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStorageBinding.bind(view)
        viewModel.getLocations()
        setupRecyclerView()
    }

    fun setupRecyclerView(){
        binding.rvLocations.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLocations.adapter = storageAdapter

    }

    private fun subscribeLocations(){
        viewModel.locations.observe(viewLifecycleOwner){ locations ->
            storageAdapter.submit(locations!!)
        }
    }

}