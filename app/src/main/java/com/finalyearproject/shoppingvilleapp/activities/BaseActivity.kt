package com.finalyearproject.shoppingvilleapp.activities

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.utills.Constants
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {

    private lateinit var myProgressDialog: Dialog


    fun showErrorSnackBar(message: String, errorMessage: Boolean){
        val snackBar=
            Snackbar.make(findViewById(android.R.id.content),message,Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view

        if (errorMessage)
        {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.color_red
                )
            )
        }else{
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.color_green
                )
            )
        }
        snackBar.show()
    }
    fun showProgressDialog()
    {
        myProgressDialog= Dialog(this)

        myProgressDialog.setContentView(R.layout.dialog_progress)

        myProgressDialog.setCancelable(false)
        myProgressDialog.setCanceledOnTouchOutside(false)

        myProgressDialog.show()
    }

    fun hideProgressDialog()
    {
        myProgressDialog.dismiss()
    }

    fun replaceFragment(myFragment:Fragment){
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container,myFragment)
        fragmentTransaction.commit()
    }
}