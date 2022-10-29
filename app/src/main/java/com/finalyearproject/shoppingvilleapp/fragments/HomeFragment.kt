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
import com.finalyearproject.shoppingvilleapp.activities.*
import com.finalyearproject.shoppingvilleapp.adapters.CategoryAdapter
import com.finalyearproject.shoppingvilleapp.adapters.ProductAdapter
import com.finalyearproject.shoppingvilleapp.adapters.SliderAdapter
import com.finalyearproject.shoppingvilleapp.databinding.FragmentHomeBinding
import com.finalyearproject.shoppingvilleapp.models.CategoryModel
import com.finalyearproject.shoppingvilleapp.models.ProductModel
import com.finalyearproject.shoppingvilleapp.models.ImageSliderModel
import com.finalyearproject.shoppingvilleapp.utills.Constants
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class HomeFragment : Fragment(),View.OnClickListener{

    private lateinit var binding: FragmentHomeBinding
    private lateinit var categories:ArrayList<CategoryModel>
    private lateinit var products : ArrayList<ProductModel>

    private lateinit var sliderImages: ArrayList<ImageSliderModel>
    lateinit var sliderAdapter: SliderAdapter


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

        categories = ArrayList()
        products = ArrayList()
        sliderImages = ArrayList()



        //Add sample data
        initializeData()


        // Auto Image Slider
        sliderAdapter = SliderAdapter(sliderImages)
       binding.imageSlider.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
        binding.imageSlider.setSliderAdapter(sliderAdapter)
        binding.imageSlider.scrollTimeInSec = 3
        binding.imageSlider.isAutoCycle = true
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        binding.imageSlider.startAutoCycle()

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

        //Trending Product recyclerView
        val trendingProductLayoutManager = GridLayoutManager(activity?.applicationContext, 2, GridLayoutManager.HORIZONTAL, false)
        binding.trendingProductsRecyclerview.layoutManager = trendingProductLayoutManager
        binding.trendingProductsRecyclerview.adapter = productAdapter

        //Recent Product recyclerView
        val recentProductLayoutManager = GridLayoutManager(activity?.applicationContext, 2, GridLayoutManager.HORIZONTAL, false)
        binding.recentProductsRecyclerview.layoutManager = recentProductLayoutManager
        binding.recentProductsRecyclerview.adapter = productAdapter


        binding.homeSearch.setOnClickListener(this)
        binding.btnAllCategory.setOnClickListener(this)
        binding.btnAllFeatureProducts.setOnClickListener(this)
        binding.btnAllTrendingProducts.setOnClickListener(this)
        binding.btnAllRecentProducts.setOnClickListener(this)

    }

    private fun initializeData() {
        //Add categories
        categories.add(CategoryModel("1",
            R.drawable.img1, "Watches"))
        categories.add(CategoryModel("2",
            R.drawable.img_1, "Vegetables"))
        categories.add(CategoryModel("3",
            R.drawable.img10, "Beauty Products"))
        categories.add(CategoryModel("4",
            R.drawable.img12, "Perfumes"))
        categories.add(CategoryModel("5",
            R.drawable.img8, "Face Washes"))
        categories.add(CategoryModel("6",
            R.drawable.img13, "Hair styling"))
        categories.add(CategoryModel("7",
            R.drawable.img12, "Perfumes"))
        categories.add(CategoryModel("8",
            R.drawable.img1, "Watches"))
        categories.add(CategoryModel("9",
            R.drawable.img10, "Beauty Products"))


        //Add Slider Images
        sliderImages.add(ImageSliderModel("Flat 70% Sale ",R.drawable.img8))
        sliderImages.add(ImageSliderModel("Upto 60% Sale on Vegetables",R.drawable.img))
        sliderImages.add(ImageSliderModel("Flat 70% Sale ",R.drawable.img13))
        sliderImages.add(ImageSliderModel("Flat 70% Sale ",R.drawable.img16))
        sliderImages.add(ImageSliderModel("Flat 70% Sale ",R.drawable.img1))
        sliderImages.add(ImageSliderModel("Flat 70% Sale ",R.drawable.img15))
        sliderImages.add(ImageSliderModel("Flat 70% Sale ",R.drawable.img12))

        //Add products
        products.add(ProductModel("1", R.drawable.img17,
            "Klein Brand watch in low price", "Klein", 1299f,
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"
        ,"4 Days","Watches",0.0f,"New",4.5f,34))
        products.add(ProductModel("2", R.drawable.img13,
            "Sablon Hair spray best for hair styling", "Sablon", 349f,
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"
        ,"5 Days","Beauty Products",200.0f,"New",4.5f,55))
        products.add(ProductModel("3", R.drawable.img15,
            "Hair Styling wax", "SportsStar", 299f,
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"
            ,"4 Days","Watches",0.0f,"New",4.5f,34))
        products.add(ProductModel("4", R.drawable.img16,
            "Set Wet Hair styling Wax ", "Set Wet", 320f,
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"
            ,"4 Days","Beauty Products",0.0f,"New",4.9f,49))
        products.add(ProductModel("5", R.drawable.img7,
            "Ponds Facial beauty face wash", "Ponds", 249f,
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"
            ,"4 Days","Beauty Products",0.0f,"New",4.5f,25))
        products.add(ProductModel("6", R.drawable.img8,
            "Garnier Best face wash for dark spots", "Garnier", 499f,
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"
            ,"4 Days","Beauty Products",0.0f,"New",4.5f,63))
        products.add(ProductModel("7", R.drawable.img15,
            "Hair Styling wax", "SportsStar", 299f,
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"
            ,"4 Days","Beauty Products",0.0f,"New",4.5f,34))
        products.add(ProductModel("8", R.drawable.img8,
            "Garnier Best face wash for dark spots", "Garnier", 499f,
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"
            ,"2 Days","Beauty Products",0.0f,"New",4.7f,384))
        products.add(ProductModel("9", R.drawable.img13,
            "Sablon Hair spray best for hair styling", "Sablon", 349f,
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"
            ,"3 Days","Beauty Products",0.0f,"New",4.5f,34))
        products.add(ProductModel("10", R.drawable.img17,
            "Klein Brand watch in low price", "Klein", 1299f,
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here"
            ,"4 Days","Watches",0.0f,"New",5.0f,466))
    }

    override fun onClick(view: View?) {
        when(view?.id){
            binding.btnAllCategory.id->{
                val intent=Intent(activity?.applicationContext,CategoriesActivity::class.java)
                startActivity(intent)
            }
            binding.btnAllFeatureProducts.id->{
                val intent=Intent(activity?.applicationContext,FeatureProductActivity::class.java)
                startActivity(intent)
            }
            binding.btnAllTrendingProducts.id->{
                val intent=Intent(activity?.applicationContext,FeatureProductActivity::class.java)
                intent.putExtra(Constants.ACTIVITY,"trending")
                startActivity(intent)
            }
            binding.btnAllRecentProducts.id->{
                val intent=Intent(activity?.applicationContext,FeatureProductActivity::class.java)
                intent.putExtra(Constants.ACTIVITY,"recent")
                startActivity(intent)
            }
            binding.homeSearch.id->{
                val intent=Intent(activity?.applicationContext,SearchActivity::class.java)
                startActivity(intent)
            }
        }
    }
}