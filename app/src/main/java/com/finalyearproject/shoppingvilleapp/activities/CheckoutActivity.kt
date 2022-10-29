package com.finalyearproject.shoppingvilleapp.activities

import android.app.TaskStackBuilder
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.finalyearproject.shoppingvilleapp.databinding.ActivityCheckoutBinding
import com.finalyearproject.shoppingvilleapp.models.AddressModel
import com.finalyearproject.shoppingvilleapp.models.ProductModel
import com.finalyearproject.shoppingvilleapp.utills.Constants
import java.time.temporal.TemporalAmount


class CheckoutActivity : BaseActivity(),View.OnClickListener {

    private lateinit var productDetails:ProductModel
    private lateinit var addressDetails:AddressModel
    private lateinit var totalAmount: String

    private lateinit var binding: ActivityCheckoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        productDetails = ProductModel()
        addressDetails = AddressModel()
        if (intent.hasExtra(Constants.PRODUCT_DETAILS)) {
            productDetails = intent.getParcelableExtra(Constants.PRODUCT_DETAILS)!!
        }else if (intent.hasExtra(Constants.ADDRESS_DETAILS)){
            addressDetails = intent.getParcelableExtra(Constants.ADDRESS_DETAILS)!!
        }
        val activity=intent.getStringExtra("activity")


        totalAmount=productDetails.price.toString()
        val address=addressDetails.town+" "+addressDetails.address+", "+addressDetails.city
        binding.tvAddressCheckout.text=address

        if (activity=="description") {
            if (productDetails.shipping_fee.toString()!="") {
                val shipFee = "Rs. " + productDetails.shipping_fee.toString()
                binding.tvShippingFee.text=shipFee
            }
            binding.tvTotalAmount.text=productDetails.price.toString()
        }

        binding.btnBack.setOnClickListener(this)
        binding.btnPlaceOrder.setOnClickListener(this)
        binding.btnChangeAddress.setOnClickListener(this)
        binding.btnChangePaymentMethod.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        if (view!=null){
            when(view.id){
                binding.btnBack.id->{
                    onBackPressedDispatcher.onBackPressed()
                }
                binding.btnPlaceOrder.id->{
                    val intent=Intent(this@CheckoutActivity,PaymentOptionsActivity::class.java)
                    startActivity(intent)
                }
                binding.btnChangeAddress.id->{
                    val intent=Intent(this@CheckoutActivity,AddressBookActivity::class.java)
                    intent.putExtra(Constants.ACTIVITY,"checkout")
                    startActivity(intent)
                }
                binding.btnChangePaymentMethod.id->{
                    val intent=Intent(this@CheckoutActivity,PaymentOptionsActivity::class.java)
                    intent.putExtra("amount",totalAmount)
                    intent.putExtra(Constants.ACTIVITY,"checkout")
                    startActivity(intent)
                }
            }
        }

    }
}