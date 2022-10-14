package com.finalyearproject.shoppingvilleapp.activities

import android.content.Intent
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.databinding.ActivityLoginBinding
import com.finalyearproject.shoppingvilleapp.models.UserModel
import com.finalyearproject.shoppingvilleapp.utills.Constants
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : BaseActivity(), View.OnClickListener {

    private var binding: ActivityLoginBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var database:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = FirebaseAuth.getInstance()
        database=FirebaseFirestore.getInstance()

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding?.btnLogin?.setOnClickListener(this)
        binding?.btnRegister?.setOnClickListener(this)
        binding?.btnForgotPassword?.setOnClickListener(this)
        binding?.btnGoogleSignIn?.setOnClickListener(this)

        if(auth.currentUser!=null)
        {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                binding?.btnLogin?.id -> {
                  loginRegisteredUser()
                }
                binding?.btnRegister?.id -> {
                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                }
                binding?.btnForgotPassword?.id -> {
                    val intent = Intent(this, ForgotPasswordActivity::class.java)
                    startActivity(intent)
                }
                binding?.btnGoogleSignIn?.id->{
                    googleSignIn()
                }
            }
        }
    }

    private fun loginRegisteredUser() {
        if (validateLoginDetails()) {

            showProgressDialog()
            val email: String = binding?.etEmail?.text.toString().trim { it <= ' ' }
            val password: String = binding?.etPassword?.text.toString().trim { it <= ' ' }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->

                    hideProgressDialog()
                    if (task.isSuccessful) {

                        // Sign in success, show a success message to user
                        Toast.makeText(
                            this,
                            "Login Success",
                            Toast.LENGTH_SHORT
                        ).show()
                        // Navigate to MainActivity
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {

                        // If sign in fails, display a failure message to the user.
                        Log.w("TAG", "createUserWithEmail:failure", task.exception)
                        showErrorSnackBar(
                            task.exception.toString(),
                            true
                        )
                    }
                }
        }
    }

    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(binding?.etEmail?.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter your Email", true)
                false
            }
            TextUtils.isEmpty(binding?.etPassword?.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please Enter your password", true)
                false
            }
            else -> {
                true
            }
        }

    }

    // [START onactivityresult]
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == Constants.RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }
    // [END onactivityresult]

    // [START auth_with_google]
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    val userDetails = UserModel()
                    if(user!=null) {
                        userDetails.user_id = user.uid
                        userDetails.email_id= user.email.toString()
                        userDetails.firstName= user.displayName.toString()
                        userDetails.image=user.photoUrl.toString()
                    }

                    database.collection(Constants.USERS)
                        .document(user?.uid.toString()).set(userDetails)

                    val intent=Intent(this,MainActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)

                }
            }
    }
    // [END auth_with_google]

    // [START signin]
    private fun googleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        @Suppress("DEPRECATION", "DEPRECATION", "DEPRECATION", "DEPRECATION")
        startActivityForResult(signInIntent, Constants.RC_SIGN_IN)
    }
}