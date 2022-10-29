package com.finalyearproject.shoppingvilleapp.activities

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.adapters.RatingReviewAdapter
import com.finalyearproject.shoppingvilleapp.databinding.ActivityProductDescriprionBinding
import com.finalyearproject.shoppingvilleapp.firestore.FireStoreClass
import com.finalyearproject.shoppingvilleapp.models.ProductModel
import com.finalyearproject.shoppingvilleapp.models.RatingReviewModel
import com.finalyearproject.shoppingvilleapp.utills.Constants
import com.google.firebase.auth.FirebaseAuth

class ProductDescriptionActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityProductDescriprionBinding
    private lateinit var productDetails: ProductModel
    private lateinit var reviewsList:ArrayList<RatingReviewModel>
    private  var flag=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDescriprionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productDetails = ProductModel()
        if (intent.hasExtra(Constants.PRODUCT_DETAILS)) {
            productDetails = intent.getParcelableExtra(Constants.PRODUCT_DETAILS)!!
        }

        reviewsList= ArrayList()
        addReviews()

        val adapter=RatingReviewAdapter(reviewsList)
        val layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.reviewsRV.layoutManager=layoutManager
        binding.reviewsRV.adapter=adapter
        binding.reviewsRV.isNestedScrollingEnabled=false

        binding.tvProductName.text = productDetails.name
        binding.tvDescription.text = productDetails.description
        binding.ivProduct.setImageResource(productDetails.image)
        binding.tvDescription.text =productDetails.description
        binding.tvPrice.text =productDetails.price.toString()



        binding.btnBack.setOnClickListener(this)
        binding.btnAddCart.setOnClickListener(this)
        binding.btnBuyNow.setOnClickListener(this)
        binding.btnFavourite.setOnClickListener(this)
    }

    private fun addReviews() {
        val comment="There are many variations of passages of Lorem Ipsum available," +
                " but the majority have suffered alteration in some form, by injected humour," +
                " or randomised words which don't look even slightly believable. If you are going to use" +
                " a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in " +
                "the middle of text."

        reviewsList.add(RatingReviewModel("Rai Salman",R.drawable.user_placeholder,4.5f,comment))
        reviewsList.add(RatingReviewModel("M. Junaid Akram",R.drawable.user_placeholder,5.0f,comment))
        reviewsList.add(RatingReviewModel("M. Umer",R.drawable.user_placeholder,4.7f,comment))
        reviewsList.add(RatingReviewModel("Hammad Nasir",R.drawable.user_placeholder,4.0f,comment))
        reviewsList.add(RatingReviewModel("M. Hammd",R.drawable.user_placeholder,3.5f,comment))
        reviewsList.add(RatingReviewModel("M. Arslan Ashraf",R.drawable.user_placeholder,4.3f,comment))
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {

                binding.btnBack.id ->{
                    onBackPressed()
                }
                binding.btnBuyNow.id -> {
                    val intent = Intent(this, CheckoutActivity::class.java)
                    intent.putExtra(Constants.PRODUCT_DETAILS, productDetails)
                    intent.putExtra(Constants.ACTIVITY,"description")
                    startActivity(intent)
                }
                binding.btnAddCart.id -> {
                    if (FirebaseAuth.getInstance().currentUser!=null) {
                        FireStoreClass().addToCartProducts(
                            this@ProductDescriptionActivity,
                            productDetails.id
                        )
                    }else{
                        val intent=Intent(this,LoginActivity::class.java)
                        startActivity(intent)
                    }
                }

                binding.btnFavourite.id -> {
//                   val productId=productDetails.id
                    if(flag==0) {
                        binding.btnFavourite.setImageResource(R.drawable.ic_favorite)
                        flag=1
                    }else{
                        binding.btnFavourite.setImageResource(R.drawable.ic_outline_favorite)
                        flag=0
                    }
//                    FireStoreClass().addToFavourite(this@ProductDescriptionActivity,productId)
                }
            }
        }
    }


}