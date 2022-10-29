package com.finalyearproject.shoppingvilleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.databinding.ActivityMyCartBinding
import com.finalyearproject.shoppingvilleapp.firestore.FireStoreClass
import com.finalyearproject.shoppingvilleapp.utills.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MyCartActivity : BaseActivity(), View.OnClickListener {

    private var binding:ActivityMyCartBinding?=null
    private lateinit var mFireStore:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMyCartBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)

        if (FirebaseAuth.getInstance().currentUser==null){
            val intent=Intent(this,LoginActivity::class.java)
            intent.putExtra(Constants.ACTIVITY,"cart")
            startActivity(intent)
            finish()
        }
        mFireStore=FirebaseFirestore.getInstance()

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