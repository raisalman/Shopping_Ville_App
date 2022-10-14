package com.finalyearproject.shoppingvilleapp.firestore

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.finalyearproject.shoppingvilleapp.activities.*
import com.finalyearproject.shoppingvilleapp.models.AddressModel
import com.finalyearproject.shoppingvilleapp.models.CardModel
import com.finalyearproject.shoppingvilleapp.models.UserModel
import com.finalyearproject.shoppingvilleapp.utills.Constants
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage

class FireStoreClass() {

    private val mDatabase = FirebaseFirestore.getInstance()


    fun addUser(activity: RegisterActivity, userDetails: UserModel) {

        mDatabase.collection(Constants.USERS)
            .document(userDetails.user_id)
            .set(userDetails, SetOptions.merge())
            .addOnSuccessListener {

                Toast.makeText(
                    activity,
                    "User Created Successfully",
                    Toast.LENGTH_LONG
                ).show()

                activity.hideProgressDialog()
                val intent = Intent(activity, LoginActivity::class.java)
                activity.startActivity(intent)
            }
            .addOnFailureListener { exception ->

                Toast.makeText(
                    activity,
                    exception.toString(),
                    Toast.LENGTH_LONG
                ).show()

                activity.hideProgressDialog()
            }
    }

    fun addShippingAddress(activity: AddShippingAddressActivity, addressDetails: AddressModel) {

       activity.showProgressDialog()
        mDatabase.collection(Constants.USERS)
            .document(getCurrentUserId())
            .collection(Constants.SHIPPING_ADDRESSES)
            .add(addressDetails)
            .addOnSuccessListener { document ->

                val documentId = document.id

                val addressHashMap = hashMapOf<String, Any>(
                    "address_id" to documentId
                )
                //Update address_id in database
                mDatabase.collection(Constants.USERS)
                    .document(getCurrentUserId())
                    .collection(Constants.SHIPPING_ADDRESSES)
                    .document(documentId)
                    .update(addressHashMap)

                Toast.makeText(
                    activity,
                    "Address Added Success",
                    Toast.LENGTH_LONG
                ).show()
                activity.hideProgressDialog()
                val intent = Intent(activity, AddressBookActivity::class.java)
                activity.startActivity(intent)
                activity.finish()

            }
            .addOnFailureListener { exception ->

                Toast.makeText(
                    activity,
                    exception.toString(),
                    Toast.LENGTH_LONG
                ).show()

                activity.hideProgressDialog()
            }
    }

    fun addAtmCard(activity: AddNewCardActivity, cardDetails: CardModel) {

        activity.showProgressDialog()
        mDatabase.collection(Constants.USERS)
            .document(getCurrentUserId())
            .collection(Constants.ATM_CARDS)
            .add(cardDetails)
            .addOnSuccessListener {document->

                val cardId=document.id

                val cardHashMap= hashMapOf<String,Any>(
                    "card_id" to cardId
                )

                //update card ID in database
                mDatabase.collection(Constants.USERS)
                    .document(getCurrentUserId())
                    .collection(Constants.ATM_CARDS)
                    .document(cardId)
                    .update(cardHashMap)

                Toast.makeText(
                    activity,
                    "Card Added Success",
                    Toast.LENGTH_LONG
                ).show()

                activity.hideProgressDialog()
            }
            .addOnFailureListener { exception ->

                Toast.makeText(
                    activity,
                    exception.toString(),
                    Toast.LENGTH_LONG
                ).show()
                activity.hideProgressDialog()
            }
    }

    fun updateUserInfo(activity: ProfileActivity, userHashMap: HashMap<String, Any>) {

        mDatabase.collection(Constants.USERS)
            .document(getCurrentUserId())
            .update(userHashMap)
            .addOnSuccessListener {
                Toast.makeText(
                    activity,
                    "Profile Updated",
                    Toast.LENGTH_LONG
                ).show()

                activity.hideProgressDialog()
            }
            .addOnFailureListener { exception ->

                Toast.makeText(
                    activity,
                    exception.toString(),
                    Toast.LENGTH_LONG
                ).show()

                activity.hideProgressDialog()
            }

    }

    fun updateShippingAddress(
        activity: AddShippingAddressActivity,
        addressHashMap: HashMap<String, Any>,
        documentId: String
    ) {

        activity.showProgressDialog()
        mDatabase.collection(Constants.USERS)
            .document(getCurrentUserId())
            .collection(Constants.SHIPPING_ADDRESSES)
            .document(documentId)
            .update(addressHashMap)
            .addOnSuccessListener {

                Toast.makeText(
                    activity,
                    "Address Updated Successfully",
                    Toast.LENGTH_LONG
                ).show()
                activity.hideProgressDialog()
                val intent = Intent(activity, AddressBookActivity::class.java)
                activity.startActivity(intent)
                activity.finish()

            }
            .addOnFailureListener { exception ->

                Toast.makeText(
                    activity,
                    exception.toString(),
                    Toast.LENGTH_LONG
                ).show()
                activity.hideProgressDialog()
            }
    }

    fun getCurrentUserId(): String {

        val currentUser = FirebaseAuth.getInstance().currentUser

        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }

        return currentUserID
    }

    fun addToCartProducts(context: Context,id:String){

        mDatabase.collection(Constants.USERS)
            .document(getCurrentUserId())
            .collection("cart items")
            .add(id).addOnSuccessListener {

                Toast.makeText(
                    context,
                    "Added to Cart Successfully",
                    Toast.LENGTH_LONG
                ).show()
            }.addOnFailureListener { exception->
                Log.e("Error",exception.toString())
                Toast.makeText(
                    context,
                    exception.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }

    }

}