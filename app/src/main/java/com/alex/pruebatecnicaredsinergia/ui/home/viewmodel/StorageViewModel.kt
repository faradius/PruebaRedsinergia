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
    private val _locations: MutableLiveData<List<Location>?> = MutableLiveData()
    val locations: MutableLiveData<List<Location>?> = _locations

    fun getLocations(){
        viewModelScope.launch {
            val result = repository.getLocations()

            when(result){
                is StorageResult.Success ->{
                    _locations.value = getNumLettersLocations(result.data)

                    Log.d("TAG", "getLocations: ${result.data}")
                }

                is StorageResult.Error -> {
                    Log.e("ERROR", result.exception?.message ?: "Error desconocido")
                }
            }
        }
    }

    private suspend fun getNumLettersLocations(lista: List<Location>): List<Location> {


        for (location in lista) {
            val numLettersName = location.name.filter { !it.isWhitespace() }.length
            val numLettersType = location.type.filter { !it.isWhitespace() }.length


            location.numLettersNameLocation = numLettersName
            location.numLettersTypeLocation = numLettersType
        }

        return lista
    }


}