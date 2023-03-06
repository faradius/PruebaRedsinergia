package com.alex.pruebatecnicaredsinergia.ui.home.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alex.pruebatecnicaredsinergia.R
import com.alex.pruebatecnicaredsinergia.data.local.model.Location
import com.alex.pruebatecnicaredsinergia.data.local.model.Product
import com.alex.pruebatecnicaredsinergia.data.local.provider.DataProvider.Companion.getLocationsFromJson
import com.alex.pruebatecnicaredsinergia.data.local.provider.DataProvider.Companion.getProductsFromJson
import com.alex.pruebatecnicaredsinergia.databinding.FragmentStorageBinding
import com.alex.pruebatecnicaredsinergia.ui.home.view.adapters.StorageAdapter


class StorageListFragment : Fragment() {

    private lateinit var binding: FragmentStorageBinding
    private lateinit var locationList: ArrayList<Location>

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
        return inflater.inflate(R.layout.fragment_storage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStorageBinding.bind(view)
        setupRecyclerView()
    }

    fun setupRecyclerView(){
        binding.rvLocations.layoutManager = LinearLayoutManager(requireContext())
        storageAdapter.submit(assignId())
        binding.rvLocations.adapter = storageAdapter

    }

    private fun assignId(): List<Location>{
        locationList = getLocationsFromJson()

        for (i in locationList.indices){
            locationList[i].id = i + 1
            locationList[i].idProduct = i + 1
        }

        Log.d("TAG", "assignId: $locationList")
        return locationList
    }

}