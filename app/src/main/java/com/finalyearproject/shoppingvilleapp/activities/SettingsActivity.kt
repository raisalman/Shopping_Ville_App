package com.finalyearproject.shoppingvilleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.databinding.ActivitySettingsBinding
import com.finalyearproject.shoppingvilleapp.firestore.FireStoreClass
import com.finalyearproject.shoppingvilleapp.models.AddressModel
import com.finalyearproject.shoppingvilleapp.models.UserModel
import com.finalyearproject.shoppingvilleapp.utills.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.User

class SettingsActivity : AppCompatActivity(), View.OnClickListener {
    private var binding: ActivitySettingsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnAccountDetails?.setOnClickListener(this)
        binding?.btnAddressBook?.setOnClickListener(this)
        binding?.btnLogout?.setOnClickListener(this)
        binding?.btnBack?.setOnClickListener(this)
        binding?.btnPolicy?.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        if (view != null)
            when (view.id) {
                binding?.btnAccountDetails?.id -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                }
                binding?.btnAddressBook?.id -> {
                    val intent = Intent(this, AddressBookActivity::class.java)
                    startActivity(intent)
                }
                binding?.btnBack?.id -> {
                    onBackPressed()
                }
                binding?.btnLogout?.id -> {
                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finishAffinity()
                }
                binding?.btnPolicy?.id -> {

                    val intent = Intent(this, PrivacyPolicyActivity::class.java)
                    startActivity(intent)
                }
            }
    }
}