package com.alex.pruebatecnicaredsinergia.ui.home.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.pruebatecnicaredsinergia.core.StorageResult
import com.alex.pruebatecnicaredsinergia.data.local.model.Location
import com.alex.pruebatecnicaredsinergia.data.repository.StorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val repository: StorageRepository
): ViewModel() {
    private val _locations: MutableLiveData<ArrayList<Location>?> = MutableLiveData()
    val locations: MutableLiveData<ArrayList<Location>?> = _locations

    fun getLocations(){
        viewModelScope.launch {
            val result = repository.getLocations()

            when(result){
                is StorageResult.Success ->{
                    _locations.value = result.data
                }

                is StorageResult.Error -> {
                    Log.e("ERROR", result.exception?.message ?: "Error desconocido")
                }
            }
        }
    }


}