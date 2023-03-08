package com.alex.pruebatecnicaredsinergia.domain.uses_cases.match_location_product

import com.alex.pruebatecnicaredsinergia.data.local.model.Location
import com.alex.pruebatecnicaredsinergia.data.local.model.Product
import com.alex.pruebatecnicaredsinergia.data.repository.StorageRepository
import com.alex.pruebatecnicaredsinergia.domain.uses_cases.get_num_letters.GetNumLettersLocationsUseCase
import javax.inject.Inject

class CalculateBaseAptitudeBonusUseCase @Inject constructor(
    private val getNumLettersLocationsUseCase: GetNumLettersLocationsUseCase,
    private val repository: StorageRepository
){
    suspend operator fun invoke() {
        val locationList = getNumLettersLocationsUseCase.invoke()
        val productList = repository.getProducts()

        for (product in productList as ArrayList<Product>) {
            for (location in locationList as ArrayList<Location>) {

                if (product.volume == location.numLettersNameLocation) {
                    if (product.weight % 2 == 0) {
                        val ssMax = location.ssN * 1.5
                        location.ssMax = ssMax
                    } else {
                        val ssMax = location.ssT * 1.5
                        location.ssMax = ssMax
                    }
                }
            }
        }
    }
}