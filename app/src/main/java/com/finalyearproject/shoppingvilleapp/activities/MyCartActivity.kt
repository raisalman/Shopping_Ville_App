package com.finalyearproject.shoppingvilleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.databinding.ActivityMyCartBinding

class MyCartActivity : BaseActivity(), View.OnClickListener {

    private var binding:ActivityMyCartBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMyCartBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        binding?.btnBack?.setOnClickListener(this)
        binding?.btnCheckout?.setOnClickListener(this)


    }

    override fun onClick(view: View?) {
        if (view!=null){
            when(view.id){
                binding?.btnBack?.id->{
                    onBackPressed()
                }
                binding?.btnCheckout?.id->{
                    val intent=Intent(this@MyCartActivity,CheckoutActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}