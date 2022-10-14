package com.finalyearproject.shoppingvilleapp.activities

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.databinding.ActivityProfileBinding
import com.finalyearproject.shoppingvilleapp.firestore.FireStoreClass
import com.finalyearproject.shoppingvilleapp.models.UserModel
import com.finalyearproject.shoppingvilleapp.utills.Constants
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.IOException

class ProfileActivity : BaseActivity(), View.OnClickListener {
    private val mFireStore = FirebaseFirestore.getInstance()
    private var binding: ActivityProfileBinding? = null
    private lateinit var galleryLauncher:ActivityResultLauncher<String>
    private val mStorage= FirebaseStorage.getInstance()
    private var imageUrl=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //Email ID can't be changed
        binding?.etEmailId?.isEnabled = false

        //Get current user data from Firestore
        getUserDetailsFromDatabase()

        galleryLauncher=registerForActivityResult(ActivityResultContracts.GetContent()
            , ActivityResultCallback { uri->
                binding?.profileIv?.setImageURI(uri)
                //Store user profile image to storage
                val storageReference=mStorage.reference.child("Images/")
                storageReference.child(FireStoreClass().getCurrentUserId()).putFile(uri)
                    .addOnSuccessListener {
                        storageReference.downloadUrl.addOnSuccessListener(OnSuccessListener { imageUri->
                            imageUrl=imageUri.toString()
                            showErrorSnackBar(
                                imageUrl,
                                false
                            )
                        })
                    }.addOnFailureListener{exception->

                        showErrorSnackBar(
                            exception.toString(),
                            true
                        )
                    }
            })

        binding?.btnBack?.setOnClickListener(this)
        binding?.btnUpdate?.setOnClickListener(this)
        binding?.profileIv?.setOnClickListener(this)
        binding?.btnAddShippingAddress?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                binding?.btnBack?.id -> {
                    onBackPressed()
                }
                binding?.btnUpdate?.id -> {
                    updateUserInfo()
                }
                binding?.profileIv?.id -> {

                    if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {

                        //method call to open gallery and select image
                       galleryLauncher.launch("image/*")
                    } else {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                            Constants.READ_STORAGE_PERMISSION_CODE
                        )
                    }
                }
                binding?.btnAddShippingAddress?.id->{
                    val intent=Intent(this@ProfileActivity,AddShippingAddressActivity::class.java)
                    intent.putExtra("type","profile")
                    startActivity(intent)
                }
            }
        }

    }

    private fun updateUserInfo() {

        showProgressDialog()
        val firstName = binding?.etFirstName?.text.toString().trim { it <= ' ' }
        val lastName = binding?.etLastName?.text.toString().trim { it <= ' ' }
        val phone = binding?.etPhone?.text.toString().trim { it <= ' ' }

        var gender = ""

        when (binding?.rgGender?.checkedRadioButtonId) {
            binding?.rbMale?.id -> {
                gender = "Male"

            }
            binding?.rbFemale?.id -> {
                gender = "Female"
            }
        }

        val userDetails= hashMapOf<String,Any>(
            "firstName" to firstName,
            "lastName" to lastName,
            "mobile" to phone,
            "gender" to gender,
            "image" to imageUrl
        )

        FireStoreClass().updateUserInfo(this@ProfileActivity,userDetails)

    }

    private fun getUserDetailsFromDatabase(){
        showProgressDialog()
        mFireStore.collection(Constants.USERS)
            .document(FireStoreClass().getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                hideProgressDialog()
                val userDetails = document.toObject(UserModel::class.java)!!
                binding?.etFirstName?.setText(userDetails.firstName)
                binding?.etLastName?.setText(userDetails.lastName)
                binding?.etEmailId?.setText(userDetails.email_id)
                binding?.etPhone?.setText(userDetails.mobile)

                Glide.with(this)
                    .load(userDetails.image)
                    .placeholder(R.drawable.user_placeholder)
                    .centerCrop()
                    .into(binding?.profileIv!!)

                if (userDetails.gender=="Female"){
                    binding?.rbFemale?.isChecked=true
                }else{
                    binding?.rbMale?.isChecked=true
                }

            }
            .addOnFailureListener { exception ->
                hideProgressDialog()
                showErrorSnackBar(
                    exception.toString(),
                    true
                )
            }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
                galleryLauncher.launch("image/*")
            } else {
                Toast.makeText(
                    this,
                    "You have denied Storage permission",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
