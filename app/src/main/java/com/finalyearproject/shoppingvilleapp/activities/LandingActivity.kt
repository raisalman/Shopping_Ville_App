package com.finalyearproject.shoppingvilleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.finalyearproject.shoppingvilleapp.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {

    private var binding:ActivityLandingBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnNext?.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}