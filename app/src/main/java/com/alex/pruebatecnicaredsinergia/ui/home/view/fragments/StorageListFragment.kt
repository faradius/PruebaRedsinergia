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
    private lateinit var productList: ArrayList<Product>

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

    //Este metodo configura el recyclerView
    private fun setupRecyclerView(){
        binding.rvLocations.layoutManager = LinearLayoutManager(requireContext())
        storageAdapter.submit(matchLocationWithProduct())
        binding.rvLocations.adapter = storageAdapter

    }

    //Este metodo asigna a cada locación un ID
    private fun assignLocationId(): ArrayList<Location> {
        locationList = getLocationsFromJson()

        for (i in locationList.indices) {
            locationList[i].id = i + 1
        }

        return locationList
    }

    //Este metodo asigna a cada producto un ID
    private fun assignProductId(): ArrayList<Product> {
        productList = getProductsFromJson()

        for (i in productList.indices) {
            productList[i].id = i + 1
        }

        return productList
    }

    //Este metodo calcula el numero de letras con respecto al nombre y tipo de locación
    private fun getNumLettersLocations(): ArrayList<Location> {
        val locationList = assignLocationId()

        for (location in locationList) {
            val numLettersName = location.name.filter { !it.isWhitespace() }.length
            val numLettersType = location.type.filter { !it.isWhitespace() }.length


            location.numLettersNameLocation = numLettersName
            location.numLettersTypeLocation = numLettersType
        }

        return locationList
    }

    private fun calculateBaseAptitude() {
        for (location in locationList) {
            val ssNumName = location.numLettersNameLocation * 1.5
            location.ssN = ssNumName

            val ssNumeType = location.numLettersTypeLocation * 1.0
            location.ssT = ssNumeType
        }
    }

    private fun calculateBaseAptitudeBonus(
        locationList: ArrayList<Location>,
        productList: ArrayList<Product>
    ) {
        for (product in productList) {
            for (location in locationList) {

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

    private fun matchLocationWithProductBonus(
        locationList: ArrayList<Location>,
        productList: ArrayList<Product>
    ) {
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

    private fun matchLocationWithProductUnassigned(
        locationList: ArrayList<Location>,
        productList: ArrayList<Product>
    ) {
        for (product in productList) {
            for (location in locationList) {
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
    }

    private fun matchLocationWithProduct(): ArrayList<Location>{
        val locationList = getNumLettersLocations()
        val productList = assignProductId()

        //Se calculca la aptitud base de todas las locaciones con respecto al nombre y tipo
        calculateBaseAptitude()

        //Se calcula la aptitud base con el aumento del 50% con respecto si es Par o Impar obteniendo su SSMAX
        calculateBaseAptitudeBonus(locationList, productList)

        //Se localiza las ubicaciones con bonus y evalua si existe uno o mas locaciones y lo almacena en la mejor locacion
        matchLocationWithProductBonus(locationList, productList)

        //Se asigna los productos faltantes a una locación y se le asigna el valor SSMax dependiendo si es PAR o IMPAR
        matchLocationWithProductUnassigned(locationList, productList)

        return locationList
    }

}