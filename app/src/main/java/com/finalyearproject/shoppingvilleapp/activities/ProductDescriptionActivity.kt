package com.finalyearproject.shoppingvilleapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.finalyearproject.shoppingvilleapp.databinding.ActivityProductDescriprionBinding
import com.finalyearproject.shoppingvilleapp.firestore.FireStoreClass
import com.finalyearproject.shoppingvilleapp.models.ProductModel
import com.finalyearproject.shoppingvilleapp.utills.Constants

class ProductDescriptionActivity : BaseActivity(), View.OnClickListener {

    private var binding: ActivityProductDescriprionBinding? = null
    private lateinit var productDetails: ProductModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDescriprionBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        productDetails = ProductModel()
        if (intent.hasExtra(Constants.PRODUCT_DETAILS)) {
            productDetails = intent.getParcelableExtra(Constants.PRODUCT_DETAILS)!!
        }

        binding?.tvProductName?.text = productDetails.name
        binding?.tvDescription?.text = productDetails.description
        binding?.ivProduct?.setImageResource(productDetails.image)
        binding?.tvDescription?.text=productDetails.description
        binding?.tvPrice?.text=productDetails.price.toString()



        binding?.btnBack?.setOnClickListener(this)
        binding?.btnAddCart?.setOnClickListener(this)
        binding?.btnBuyNow?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {

                binding?.btnBack?.id->{
                    onBackPressed()
                }
                binding?.btnBuyNow?.id -> {
                    val intent = Intent(this, CheckoutActivity::class.java)
                    intent.putExtra(Constants.PRODUCT_DETAILS, productDetails)
                    intent.putExtra(Constants.ACTIVITY,"description")
                    startActivity(intent)
                }
                binding?.btnAddCart?.id -> {
                    FireStoreClass().addToCartProducts(this@ProductDescriptionActivity,productDetails.id)
                }
            }
        }
    }
}