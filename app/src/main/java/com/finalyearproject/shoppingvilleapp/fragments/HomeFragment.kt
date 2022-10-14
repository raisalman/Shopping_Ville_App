package com.finalyearproject.shoppingvilleapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.activities.BaseActivity
import com.finalyearproject.shoppingvilleapp.activities.MyCartActivity
import com.finalyearproject.shoppingvilleapp.activities.SettingsActivity
import com.finalyearproject.shoppingvilleapp.adapters.CategoryAdapter
import com.finalyearproject.shoppingvilleapp.adapters.ProductAdapter
import com.finalyearproject.shoppingvilleapp.databinding.FragmentHomeBinding
import com.finalyearproject.shoppingvilleapp.models.CategoryModel
import com.finalyearproject.shoppingvilleapp.models.ProductModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var categories:ArrayList<CategoryModel>
    private lateinit var products : ArrayList<ProductModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categories = ArrayList<CategoryModel>()
        products = ArrayList<ProductModel>()

        //Add sample data
        initializeData()

        //Category recyclerView
        val categoryAdapter = CategoryAdapter(categories)
        val categoryLayoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL,false)
        binding.categoriesRecyclerview.layoutManager = categoryLayoutManager
        binding.categoriesRecyclerview.adapter = categoryAdapter

        //Feature Product recyclerView
        val productAdapter = ProductAdapter(products)
        val productLayoutManager = GridLayoutManager(activity?.applicationContext, 2, GridLayoutManager.HORIZONTAL, false)
        binding.featureProductRecyclerview.layoutManager = productLayoutManager
        binding.featureProductRecyclerview.adapter = productAdapter

        //Recent Product recyclerView
        val recentProductLayoutManager =
            GridLayoutManager(activity?.applicationContext, 2, GridLayoutManager.HORIZONTAL, false)
        binding.recentProductsRecyclerview.layoutManager = recentProductLayoutManager
        binding.recentProductsRecyclerview.adapter = productAdapter
    }

    private fun initializeData() {
        //Add categories
        categories.add(CategoryModel("",
            R.drawable.img1, "Watches"))
        categories.add(CategoryModel("",
            R.drawable.img_1, "VegiTables"))
        categories.add(CategoryModel("",
            R.drawable.img10, "Beauty Products"))
        categories.add(CategoryModel("",
            R.drawable.img12, "Perfumes"))
        categories.add(CategoryModel("",
            R.drawable.img8, "Face Washes"))
        categories.add(CategoryModel("",
            R.drawable.img13, "Hair styling"))
        categories.add(CategoryModel("",
            R.drawable.img12, "Perfumes"))
        categories.add(CategoryModel("",
            R.drawable.img1, "Watches"))
        categories.add(CategoryModel("",
            R.drawable.img10, "Beauty Products"))


        //Add products
        products.add(ProductModel("", R.drawable.img17,
            "Klein Brand watch in low price", "Klein", 1299f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"))
        products.add(ProductModel("", R.drawable.img13,
            "Sablon Hair spray best for hair styling", "Sablon", 349f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"))
        products.add(ProductModel("", R.drawable.img15,
            "Hair Styling wax", "SportsStar", 299f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"))
        products.add(ProductModel("", R.drawable.img16,
            "Set Wet Hair styling Wax ", "Set Wet", 320f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"))
        products.add(ProductModel("", R.drawable.img7,
            "Ponds Facial beauty face wash", "Ponds", 249f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"))
        products.add(ProductModel("", R.drawable.img8,
            "Garnier Best face wash for dark spots", "Garnier", 499f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"))
        products.add(ProductModel("", R.drawable.img15,
            "Hair Styling wax", "SportsStar", 299f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"))
        products.add(ProductModel("", R.drawable.img8,
            "Garnier Best face wash for dark spots", "Garnier", 499f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"))
        products.add(ProductModel("", R.drawable.img13,
            "Sablon Hair spray best for hair styling", "Sablon", 349f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"))
        products.add(ProductModel("", R.drawable.img17,
            "Klein Brand watch in low price", "Klein", 1299f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"))
    }
}