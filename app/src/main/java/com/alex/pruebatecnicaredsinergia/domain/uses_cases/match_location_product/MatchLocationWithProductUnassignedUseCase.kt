package com.alex.pruebatecnicaredsinergia.domain.uses_cases.match_location_product

import com.alex.pruebatecnicaredsinergia.core.StorageResult
import com.alex.pruebatecnicaredsinergia.data.local.model.Location
import com.alex.pruebatecnicaredsinergia.data.local.model.Product
import com.alex.pruebatecnicaredsinergia.data.repository.StorageRepository
import com.alex.pruebatecnicaredsinergia.domain.uses_cases.get_num_letters.GetNumLettersLocationsUseCase
import javax.inject.Inject

class MatchLocationWithProductUnassignedUseCase @Inject constructor(
    private val getNumLettersLocationsUseCase: GetNumLettersLocationsUseCase,
    private val calculateBaseAptitudeUseCase: CalculateBaseAptitudeUseCase,
    private val calculateBaseAptitudeBonusUseCase: CalculateBaseAptitudeBonusUseCase,
    private val matchLocationWithProductBonusUseCase: MatchLocationWithProductBonusUseCase,
    private val repository: StorageRepository
) {
    suspend operator fun invoke(): StorageResult<ArrayList<Location>> {
        val locationList = getNumLettersLocationsUseCase.invoke() as ArrayList<Location>
        val productList = repository.getProducts() as ArrayList<Product>

        calculateBaseAptitudeUseCase.invoke()

        calculateBaseAptitudeBonusUseCase.invoke()

        matchLocationWithProductBonusUseCase.invoke()

        for (product in productList ) {
            for (location in locationList ) {
                if (location.status == 0 && product.status == 0) {

                    location.idProduct = product.id
                    location.status = 1
                    product.status = 1

                    if (product.weight % 2 == 0) {
                        location.ssMax = location.ssN
                    } else {
                        location.ssMax = location.ssT
                    }
                }
            }
        }

        return StorageResult.Success(locationList)

    }
}