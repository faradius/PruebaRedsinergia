package com.alex.pruebatecnicaredsinergia.domain.uses_cases.match_location_product

import com.alex.pruebatecnicaredsinergia.data.local.model.Location
import com.alex.pruebatecnicaredsinergia.data.local.model.Product
import com.alex.pruebatecnicaredsinergia.data.repository.StorageRepository
import com.alex.pruebatecnicaredsinergia.domain.uses_cases.get_num_letters.GetNumLettersLocationsUseCase
import javax.inject.Inject

class MatchLocationWithProductBonusUseCase @Inject constructor(
    private val getNumLettersLocationsUseCase: GetNumLettersLocationsUseCase,
    private val repository: StorageRepository
) {
    suspend operator fun invoke() {
        val locationList = getNumLettersLocationsUseCase.invoke() as ArrayList<Location>
        val productList = repository.getProducts() as ArrayList<Product>

        var contador = 0
        var sstTemporal = 0.0
        var idlocation = 0

        for (product in productList) {
            for (location in locationList) {
                if (product.volume == location.numLettersNameLocation) {
                    contador++

                    when {
                        contador == 1 -> {
                            idlocation = location.id
                            sstTemporal = location.ssT

                            product.status = 1
                            location.apply {
                                idProduct = product.id
                                status = 1
                            }
                        }
                        contador >= 2 -> {
                            if (sstTemporal > location.ssT || sstTemporal == location.ssT) {
                                location.apply {
                                    idProduct = product.id
                                    status = 1
                                }

                                for (location in locationList) {
                                    if (location.id == idlocation) {
                                        location.apply {
                                            idProduct = 0
                                            status = 0
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            idlocation = 0
            sstTemporal = 0.0
            contador = 0
        }
    }
}