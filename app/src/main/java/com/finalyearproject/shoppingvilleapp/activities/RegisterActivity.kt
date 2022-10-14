package com.finalyearproject.shoppingvilleapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.finalyearproject.shoppingvilleapp.databinding.ActivityRegisterBinding
import com.finalyearproject.shoppingvilleapp.firestore.FireStoreClass
import com.finalyearproject.shoppingvilleapp.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : BaseActivity(), View.OnClickListener {

    private var binding: ActivityRegisterBinding? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = FirebaseAuth.getInstance()

        binding?.btnBack?.setOnClickListener(this)
        binding?.btnRegister?.setOnClickListener(this)
        binding?.btnLogin?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {

        if (view != null) {
            when (view.id) {
                binding?.btnRegister?.id -> {
                    registerUser()
                }

                binding?.btnLogin?.id -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }

                binding?.btnBack?.id -> {
                    onBackPressed()
                }
            }
        }
    }

    private fun validateRegisterDetails(): Boolean {

        // email pattern
        val emailCheck = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")
        val email = binding!!.etEmailId.text.toString().trim { it <= ' ' }

        //Password Pattern
        val passwordCheck =
            Regex("^" + "(?=.*[0-9])" + "(?=.*[a-zA-Z])" + "(?=.*[@#$%*^&+=])" + "(?=\\S+$)" + ".{8,}" + "$")
        val password = binding?.etPassword?.text.toString().trim { it <= ' ' }
        val confirmPass=binding?.etConfirmPassword?.text.toString().trim { it <= ' ' }

        return when {
            TextUtils.isEmpty(binding?.etFirstName?.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter First name", true)
                false
            }
            TextUtils.isEmpty(binding?.etLastName?.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter Last name", true)
                false
            }
            TextUtils.isEmpty(email) && !email.matches(emailCheck) -> {
                showErrorSnackBar("Please Enter a Valid Email", true)
                false
            }
            TextUtils.isEmpty(password) && !password.matches(passwordCheck) -> {
                showErrorSnackBar(
                    "Password Should contain a Digit , Alphabet , " +
                            "Special character, No white space and minimum 8 characters", true
                )
                false
            }
            TextUtils.isEmpty(confirmPass) && confirmPass != password -> {
                showErrorSnackBar("Confirm password does not match with password", true)
                false
            }
            !binding?.termsConditionCheckbox?.isChecked!! -> {
                showErrorSnackBar("Please agree to Terms and conditions", true)
                false
            }
            else -> {
                true
            }
        }
    }

    private fun registerUser() {
        // Check entered data is valid
        if (validateRegisterDetails()) {

            showProgressDialog()
            val email: String = binding?.etEmailId?.text.toString().trim { it <= ' ' }
            val password: String = binding?.etPassword?.text.toString().trim { it <= ' ' }

            // create a new user with email and password
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //register user in Firestore Database
                        val firebaseUser: FirebaseUser = task.result!!.user!!

                        val userModel = UserModel(
                            firebaseUser.uid,
                            binding?.etFirstName?.text.toString().trim { it <= ' ' },
                            binding?.etLastName?.text.toString().trim { it <= ' ' },
                            email
                        )
                        FireStoreClass().addUser(this@RegisterActivity, userModel)
                        //navigate user Login activity
//                        FirebaseAuth.getInstance().signOut()
//                        finish()

                    } else {
                        hideProgressDialog()
                        // If sign in fails, display a failure message to the user.
                        Log.w("TAG", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            this,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }


        }
    }
}