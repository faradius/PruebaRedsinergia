package com.alex.pruebatecnicaredsinergia.domain.uses_cases.match_location_product

import com.alex.pruebatecnicaredsinergia.core.StorageResult
import com.alex.pruebatecnicaredsinergia.data.local.model.Location
import com.alex.pruebatecnicaredsinergia.domain.uses_cases.get_num_letters.GetNumLettersLocationsUseCase
import javax.inject.Inject

class CalculateBaseAptitudeUseCase @Inject constructor(private val getNumLettersLocationsUseCase: GetNumLettersLocationsUseCase) {

    suspend operator fun invoke() {
        val locationList = getNumLettersLocationsUseCase.invoke()as ArrayList<Location>

        for (location in locationList) {
            val ssNumName = location.numLettersNameLocation * 1.5
            location.ssN = ssNumName

            val ssNumeType = location.numLettersTypeLocation * 1.0
            location.ssT = ssNumeType
        }
    }
}