package com.alex.pruebatecnicaredsinergia.domain.uses_cases.get_num_letters

import com.alex.pruebatecnicaredsinergia.core.StorageResult
import com.alex.pruebatecnicaredsinergia.data.local.model.Location
import com.alex.pruebatecnicaredsinergia.data.repository.StorageRepository
import javax.inject.Inject

class GetNumLettersLocationsUseCase @Inject constructor(private val repository: StorageRepository) {

    suspend operator fun invoke(): StorageResult<ArrayList<Location>>{
        val locationList = repository.getLocations() as ArrayList<Location>

        for (location in locationList) {
            val numLettersName = location.name.filter { !it.isWhitespace() }.length
            val numLettersType = location.type.filter { !it.isWhitespace() }.length


            location.numLettersNameLocation = numLettersName
            location.numLettersTypeLocation = numLettersType
        }

        return StorageResult.Success(locationList)
    }
}