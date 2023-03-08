package com.alex.pruebatecnicaredsinergia.ui.detail.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alex.pruebatecnicaredsinergia.R
import com.alex.pruebatecnicaredsinergia.data.local.model.Product
import com.alex.pruebatecnicaredsinergia.databinding.FragmentProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private var id:Int? = null
    private lateinit var productList: ArrayList<Product>
    private lateinit var binding: FragmentProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            id = bundle.getInt("id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductDetailBinding.bind(view)
//        getProduct(id ?: 1)
    }

//    fun asignIdProduct(): List<Product>{
//        productList = getProductsFromJson()
//
//        for (i in productList.indices){
//            productList[i].id = i + 1
//        }
//
//        return productList
//    }
//
//    fun getProduct(id : Int){
//        val newProductList = asignIdProduct()
//
//        for (product in newProductList){
//            if (product.id == id){
//                binding.ivProduct.setImageResource(R.drawable.ic_launcher_background)
//                binding.tvProductName.text = product.name
//                binding.tvWeightProduct.text = product.weight.toString()
//                binding.tvVolumeProduct.text = product.volume.toString()
//
//                Log.d("TAG", "getProduct: $product")
//                break
//            }
//
//            Log.d("TAG", "getProduct: $product")
//        }
//    }

}