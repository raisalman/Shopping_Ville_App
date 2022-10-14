package com.finalyearproject.shoppingvilleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.databinding.ActivityPaymentOptionsBinding
import com.finalyearproject.shoppingvilleapp.utills.Constants

class PaymentOptionsActivity :BaseActivity(),View.OnClickListener  {
    private lateinit var binding:ActivityPaymentOptionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityPaymentOptionsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener(this)
        binding.btnAddCard.setOnClickListener(this)

        var amount=""
        if(intent.hasExtra(Constants.ACTIVITY)){
            amount=intent.getStringExtra("amount")!!
        }
        val price="Rs. $amount"

        binding.tvTotalAmount.text=price

    }

    override fun onClick(view: View?) {
        if (view!=null){
            when(view.id){
                binding?.btnBack?.id->{
                    onBackPressed()
                }
                binding?.btnAddCard?.id->{
                    val intent=Intent(this@PaymentOptionsActivity,CardBookActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}