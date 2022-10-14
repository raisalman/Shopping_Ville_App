package com.finalyearproject.shoppingvilleapp.activities

import android.content.Intent
import android.os.Bundle
import android.provider.Telephony
import android.text.TextUtils
import android.view.View
import com.finalyearproject.shoppingvilleapp.databinding.ActivityAddShippingAddressBinding
import com.finalyearproject.shoppingvilleapp.firestore.FireStoreClass
import com.finalyearproject.shoppingvilleapp.models.AddressModel
import com.finalyearproject.shoppingvilleapp.utills.Constants

class AddShippingAddressActivity : BaseActivity(), View.OnClickListener {

    private var binding: ActivityAddShippingAddressBinding? = null
    private lateinit var addressDetails: AddressModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddShippingAddressBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        val type = intent.getStringExtra("type")
        addressDetails = AddressModel()

        // Change button text to update
        if (type == "update") {
            addressDetails = intent.getParcelableExtra(Constants.ADDRESS_DETAILS)!!
            binding?.btnSave?.text = "Update"

            binding?.etFullName?.setText(addressDetails.name)
            binding?.etPhone?.setText(addressDetails.mobile)
            binding?.etProvince?.setText(addressDetails.province)
            binding?.etCity?.setText(addressDetails.city)
            binding?.etTown?.setText(addressDetails.town)
            binding?.etAddress?.setText(addressDetails.address)

            when (addressDetails.label) {
                "Home" -> {
                    binding?.rbHome?.isChecked = true
                }
                "Office" -> {
                    binding?.rbOffice?.isChecked = true
                }
                else -> {
                    binding?.rbOthers?.isChecked = true
                }
            }

        } else {
            binding?.btnSave?.text = "Save"
        }



        binding?.btnSave?.setOnClickListener(this)
        binding?.btnBack?.setOnClickListener(this)


    }

    override fun onClick(view: View?) {

        if (view != null) {
            when (view.id) {
                binding?.btnBack?.id -> {
                    onBackPressed()
                }
                binding?.btnSave?.id -> {
                    val type = intent.getStringExtra("type")
                    if (validateAddressDetails()) {
                        if (type == "update") {
                            updateAddress()
                        } else {
                            addNewAddress()
                        }
                    }
                }
            }

        }
    }

    private fun addNewAddress() {
        val name = binding?.etFullName?.text.toString().trim { it <= ' ' }
        val phone = binding?.etPhone?.text.toString().trim { it <= ' ' }
        val province = binding?.etProvince?.text.toString().trim { it <= ' ' }
        val city = binding?.etCity?.text.toString().trim { it <= ' ' }
        val town = binding?.etTown?.text.toString().trim { it <= ' ' }
        val address = binding?.etAddress?.text.toString().trim { it <= ' ' }

        var label = ""
        when (binding?.radioGroup?.checkedRadioButtonId) {
            binding?.rbHome?.id -> {
                label = "Home"
            }
            binding?.rbOffice?.id -> {
                label = "Office"
            }
            binding?.rbOthers?.id -> {
                label = "Others"
            }
        }
        val addressDetails = AddressModel("", name, phone, province, city, label, town, address)

        FireStoreClass().addShippingAddress(this@AddShippingAddressActivity, addressDetails)
    }

    private fun updateAddress() {
        val name = binding?.etFullName?.text.toString().trim { it <= ' ' }
        val phone = binding?.etPhone?.text.toString().trim { it <= ' ' }
        val province = binding?.etProvince?.text.toString().trim { it <= ' ' }
        val city = binding?.etCity?.text.toString().trim { it <= ' ' }
        val town = binding?.etTown?.text.toString().trim { it <= ' ' }
        val address = binding?.etAddress?.text.toString().trim { it <= ' ' }

        var label = ""
        when (binding?.radioGroup?.checkedRadioButtonId) {
            binding?.rbHome?.id -> {
                label = "Home"
            }
            binding?.rbOffice?.id -> {
                label = "Office"
            }
            binding?.rbOthers?.id -> {
                label = "Others"
            }
        }

        val addressHashmap = hashMapOf<String, Any>(
            "name" to name,
            "mobile" to phone,
            "province" to province,
            "city" to city,
            "town" to town,
            "address" to address,
            "label" to label,
        )
        FireStoreClass().updateShippingAddress(
            this@AddShippingAddressActivity,
            addressHashmap,
            addressDetails.address_id
        )
    }

    private fun validateAddressDetails(): Boolean {
        return when {
            TextUtils.isEmpty(binding?.etFullName?.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter your full name", true)
                false
            }
            TextUtils.isEmpty(binding?.etPhone?.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter Mobile Number", true)
                false
            }
            TextUtils.isEmpty(binding?.etProvince?.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter Province name", true)
                false
            }
            TextUtils.isEmpty(binding?.etCity?.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter City Name", true)
                false
            }
            TextUtils.isEmpty(binding?.etTown?.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter Town Name", true)
                false
            }
            TextUtils.isEmpty(binding?.etAddress?.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter your address", true)
                false
            }
            else -> {
                true
            }
        }
    }
}