package com.finalyearproject.shoppingvilleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.finalyearproject.shoppingvilleapp.adapters.AddressAdapter
import com.finalyearproject.shoppingvilleapp.databinding.ActivityAddressBookBinding
import com.finalyearproject.shoppingvilleapp.firestore.FireStoreClass
import com.finalyearproject.shoppingvilleapp.models.AddressModel
import com.finalyearproject.shoppingvilleapp.utills.Constants
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObject
import kotlin.collections.ArrayList

class AddressBookActivity : AppCompatActivity(), View.OnClickListener {
    private var binding: ActivityAddressBookBinding? = null
    private lateinit var database: FirebaseFirestore
    private lateinit var addressList: ArrayList<AddressModel>
    private lateinit var addressAdapter: AddressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddressBookBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)
        var navActivity=""
        if (intent.hasExtra(Constants.ACTIVITY)) {
            navActivity = intent.getStringExtra(Constants.ACTIVITY)!!
        }

        binding?.btnAddAddress?.setOnClickListener(this)
        binding?.btnBack?.setOnClickListener(this)

        addressList = arrayListOf()
        database = FirebaseFirestore.getInstance()

        database.collection(Constants.USERS)
            .document(FireStoreClass().getCurrentUserId())
            .collection(Constants.SHIPPING_ADDRESSES)
            .addSnapshotListener { value, error ->

                if (error != null) {
                    Log.e("FireStore Error", error.toString())
                    return@addSnapshotListener
                }

                if (value != null) {
                    for (dc: DocumentChange in value.documentChanges) {

                        if (dc.type == DocumentChange.Type.ADDED) {

                            addressList.add(dc.document.toObject())

                        }
                    }

                    addressAdapter.notifyDataSetChanged()
                }
            }

        addressAdapter = AddressAdapter(this@AddressBookActivity, addressList,navActivity)
        addressAdapter.notifyDataSetChanged()

        val addressLayoutManager = LinearLayoutManager(
            this@AddressBookActivity,
            LinearLayoutManager.VERTICAL, false
        )

        binding?.addressRecyclerview?.layoutManager = addressLayoutManager
        binding?.addressRecyclerview?.adapter = addressAdapter

    }

    override fun onClick(view: View?) {
        if (view != null)
            when (view.id) {
                binding?.btnAddAddress?.id -> {

                    val intent = Intent(this, AddShippingAddressActivity::class.java)
                    intent.putExtra("type","add")
                    startActivity(intent)
                    finish()
                }
                binding?.btnBack?.id -> {
                    onBackPressed()
                }

            }
    }
}
