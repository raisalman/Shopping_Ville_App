package com.finalyearproject.shoppingvilleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.databinding.ActivityCardBookBinding

class CardBookActivity : BaseActivity(),View.OnClickListener {

    private lateinit var binding:ActivityCardBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCardBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddNewCard.setOnClickListener(this)
        binding.btnBack.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
       if (view!=null) {
           when(view.id){
               binding.btnAddNewCard.id->{
                   val intent=Intent(this@CardBookActivity,AddNewCardActivity::class.java)
                   startActivity(intent)
               }
               binding.btnBack.id->{
                   onBackPressedDispatcher.onBackPressed()
               }
           }
       }
    }
}