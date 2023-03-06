package com.alex.pruebatecnicaredsinergia.ui.home.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.pruebatecnicaredsinergia.R
import com.alex.pruebatecnicaredsinergia.data.local.model.Location
import com.alex.pruebatecnicaredsinergia.data.local.model.Storage
import com.alex.pruebatecnicaredsinergia.databinding.ItemStorageBinding

class StorageAdapter(private val storageList:List<Location>) :RecyclerView.Adapter<StorageAdapter.StorageHolder>(){

    inner class StorageHolder(private val binding: ItemStorageBinding):RecyclerView.ViewHolder(binding.root){
       fun bind(itemLocation: Location) = with(binding){
           tvNameLocation.text = itemLocation.name
           tvTypeLocation.text = itemLocation.type
           ivLocation.setImageResource(R.drawable.ic_account)

//           root.setOnClickListener { onClickListener(itemLocation.id) }
       }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageHolder {
        val itemBinding = ItemStorageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StorageHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return storageList.size
    }

    override fun onBindViewHolder(holder: StorageHolder, position: Int) {
        holder.bind(storageList[position])
    }
}