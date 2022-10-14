package com.finalyearproject.shoppingvilleapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.databinding.ActivityFrorgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : BaseActivity(), View.OnClickListener {

    private var binding: ActivityFrorgotPasswordBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrorgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnSubmit?.setOnClickListener(this)
        binding?.btnBack?.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        if (view != null)
            when (view.id) {
                binding?.btnSubmit?.id -> {
                    val email: String = binding?.etEmail?.text.toString().trim { it <= ' ' }
                   sendPasswordResetEmail(email)
                }
                binding?.btnBack?.id -> {
                    onBackPressed()
                }
            }
    }


    private fun sendPasswordResetEmail(email:String)
    {
        if (email.isEmpty()){
            showErrorSnackBar("Please Enter email first",true)
        }else{
            showProgressDialog()
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        Toast.makeText(
                            this@ForgotPasswordActivity,
                            "Email sent Success",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }else{
                        showErrorSnackBar(task.exception.toString(),true)
                    }
                }
        }
    }
}