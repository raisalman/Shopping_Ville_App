package com.finalyearproject.shoppingvilleapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.adapters.ProductAdapter
import com.finalyearproject.shoppingvilleapp.databinding.FragmentFavouriteBinding
import com.finalyearproject.shoppingvilleapp.databinding.FragmentHomeBinding
import com.finalyearproject.shoppingvilleapp.models.CategoryModel
import com.finalyearproject.shoppingvilleapp.models.ProductModel

class FavouriteFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var products : ArrayList<ProductModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=FragmentFavouriteBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        products = ArrayList()

        initializeData()

        val productAdapter = ProductAdapter(products)
        val productLayoutManager = GridLayoutManager(activity?.applicationContext, 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = productLayoutManager
        binding.recyclerView.adapter = productAdapter
    }

    private fun initializeData() {

        //Add products
        products.add(
            ProductModel("", R.drawable.img17,
            "Klein Brand watch in low price", "Klein", 1299f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
        products.add(
            ProductModel("", R.drawable.img13,
            "Sablon Hair spray best for hair styling", "Sablon", 349f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
        products.add(
            ProductModel("", R.drawable.img15,
            "Hair Styling wax", "SportsStar", 299f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
        products.add(
            ProductModel("", R.drawable.img16,
            "Set Wet Hair styling Wax ", "Set Wet", 320f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
        products.add(
            ProductModel("", R.drawable.img7,
            "Ponds Facial beauty face wash", "Ponds", 249f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
        products.add(
            ProductModel("", R.drawable.img8,
            "Garnier Best face wash for dark spots", "Garnier", 499f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
        products.add(
            ProductModel("", R.drawable.img15,
            "Hair Styling wax", "SportsStar", 299f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
        products.add(
            ProductModel("", R.drawable.img8,
            "Garnier Best face wash for dark spots", "Garnier", 499f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
        products.add(
            ProductModel("", R.drawable.img13,
            "Sablon Hair spray best for hair styling", "Sablon", 349f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
        products.add(
            ProductModel("", R.drawable.img17,
            "Klein Brand watch in low price", "Klein", 1299f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
    }
}