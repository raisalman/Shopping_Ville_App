package com.finalyearproject.shoppingvilleapp.activities

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.finalyearproject.shoppingvilleapp.databinding.ActivityAddNewCardBinding
import com.finalyearproject.shoppingvilleapp.firestore.FireStoreClass
import com.finalyearproject.shoppingvilleapp.models.CardModel

class AddNewCardActivity : BaseActivity(),View.OnClickListener {
    private lateinit var binding:ActivityAddNewCardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityAddNewCardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener(this)
        binding.btnSave.setOnClickListener(this)

    }

    /*private fun uploadCard() {
        val name=binding.etCardHolderName.text.toString()
        val cardNumber=binding.etCardNumber.text.toString()
        val expiryDate=binding.etExpiryDate.text.toString()
        val securityCode=binding.etSecurityCode.text.toString()
        val saveCheckBox=binding.saveCardCheckbox.isChecked

        val cardDetails=CardModel("1",name,cardNumber,expiryDate,securityCode,saveCheckBox)

        FireStoreClass().addAtmCard(this@AddNewCardActivity,cardDetails)
    }*/

    override fun onClick(view: View?) {
       if (view!=null){
           when(view.id){
               binding.btnBack.id ->{
                   onBackPressedDispatcher.onBackPressed()
               }
               binding.btnSave.id ->{
                   uploadCard()
               }
           }
       }
    }

    private fun uploadCard(){
        if (validateCardDetails()){

            val cardHolderName=binding.etCardHolderName.text.toString().trim { it<=' ' }
            val cardNumber=binding.etCardNumber.text.toString().trim { it<=' ' }
            val expireDate=binding.etExpiryDate.text.toString().trim { it<=' ' }
            val securityCode=binding.etSecurityCode.text.toString().trim { it<=' ' }

            var saveCard = binding.saveCardCheckbox.isChecked

            val cardDetails=CardModel(
                "",
                cardHolderName,
                cardNumber,
                expireDate,
                securityCode,
                saveCard
            )
            FireStoreClass().addAtmCard(this@AddNewCardActivity,cardDetails)
        }
    }

    private fun validateCardDetails(): Boolean {
        return when {
            TextUtils.isEmpty(binding.etCardHolderName.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter Card Holder name", true)
                false
            }
            TextUtils.isEmpty(binding.etCardNumber.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter Mobile Number", true)
                false
            }
            TextUtils.isEmpty(binding.etExpiryDate.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter Province name", true)
                false
            }
            TextUtils.isEmpty(binding.etSecurityCode.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter City Name", true)
                false
            }
            else -> {
                true
            }
        }
    }
}