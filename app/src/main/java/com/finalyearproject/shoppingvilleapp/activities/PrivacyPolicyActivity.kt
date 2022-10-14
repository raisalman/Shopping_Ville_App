package com.finalyearproject.shoppingvilleapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.databinding.ActivityPrivacyPolicyBinding

class PrivacyPolicyActivity : AppCompatActivity() {
    private var binding:ActivityPrivacyPolicyBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityPrivacyPolicyBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)


        binding?.webView?.webViewClient= WebViewClient()
        binding?.webView?.loadUrl("https://pages.flycricket.io/shopping-app-2/privacy.html")

        binding?.webView?.settings?.javaScriptEnabled=true
        binding?.webView?.settings?.setSupportZoom(true)

        binding?.btnBack?.setOnClickListener{
            onBackPressed()
        }

    }


    override fun onBackPressed() {

        if (binding?.webView?.canGoBack()!!){
            binding?.webView?.goBack()
        }
        else

        super.onBackPressed()
    }
}