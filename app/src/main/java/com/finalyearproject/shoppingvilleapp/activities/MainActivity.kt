package com.finalyearproject.shoppingvilleapp.activities

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.databinding.ActivityMainBinding
import com.finalyearproject.shoppingvilleapp.databinding.NavHeaderMainBinding
import com.finalyearproject.shoppingvilleapp.firestore.FireStoreClass
import com.finalyearproject.shoppingvilleapp.fragments.FavouriteFragment
import com.finalyearproject.shoppingvilleapp.fragments.HomeFragment
import com.finalyearproject.shoppingvilleapp.models.UserModel
import com.finalyearproject.shoppingvilleapp.utills.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.iammert.library.readablebottombar.ReadableBottomBar

class MainActivity : BaseActivity(),NavigationView.OnNavigationItemSelectedListener ,
View.OnClickListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var mFireStore:FirebaseFirestore
    private lateinit var headerMainBinding: NavHeaderMainBinding
    private lateinit var readableBottomBar:ReadableBottomBar
    private lateinit var toolbar:Toolbar

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initialize global variables
        mFireStore=FirebaseFirestore.getInstance()
        headerMainBinding=NavHeaderMainBinding.inflate(layoutInflater)

        //method call to replace fragment
        replaceFragment(HomeFragment())
        //method call to set the user info in navigation drawer header
        if (FirebaseAuth.getInstance().currentUser!=null) {
            setUserDetailsHeader()
        }

        drawerLayout = binding.drawerLayout
        navView = binding.navigationView
        readableBottomBar= binding.appBarMain.contentMain.readableBottomBar
        toolbar=binding.appBarMain.toolbar
        toolbar.title = "Home"

        binding.appBarMain.toolbarCart.setOnClickListener(this)
        setSupportActionBar(toolbar)

        readableBottomBar.setOnItemSelectListener(object :
            ReadableBottomBar.ItemSelectListener {
            override fun onItemSelected(index: Int) {
                readableBottomBar.selectItem(index)
                when(index){
                    0->{
                        replaceFragment(HomeFragment())
                    }
                    1->{
                        val intent=Intent(this@MainActivity,SearchActivity ::class.java)
                        startActivity(intent)
                    }
                    2->{
                        readableBottomBar.selectItem(index)
                        replaceFragment(FavouriteFragment())
                    }
                    3->{
                        val intent=Intent(this@MainActivity,MyCartActivity ::class.java)
                        startActivity(intent)
                    }
                    4->{
                        val intent=Intent(this@MainActivity,ProfileActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        })



        val toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer,R.string.closeDrawer)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings->{
                val intent=Intent(this@MainActivity,SettingsActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_home->{
               replaceFragment(HomeFragment())
            }
            R.id.nav_favourite->{
               BaseActivity().replaceFragment(FavouriteFragment())
            }
            R.id.nav_settings->{

                val intent=Intent(this@MainActivity,SettingsActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_categories->{

                val intent=Intent(this@MainActivity,CategoriesActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_feature_products->{

                val intent=Intent(this@MainActivity,FeatureProductActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_trending->{
                val intent=Intent(this@MainActivity,FeatureProductActivity::class.java)
                intent.putExtra(Constants.ACTIVITY,"trending")
                startActivity(intent)
            }
            R.id.nav_recent_products->{
                val intent=Intent(this@MainActivity,FeatureProductActivity::class.java)
                intent.putExtra(Constants.ACTIVITY,"recent")
                startActivity(intent)
            }
            R.id.nav_log_out->{
               FirebaseAuth.getInstance().signOut()
                val intent=Intent(this@MainActivity,LoginActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
            R.id.nav_my_cart->{
                val intent=Intent(this@MainActivity,MyCartActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_orders->{
                Toast.makeText(
                    this,
                    "Orders Navigation",Toast.LENGTH_LONG).show()
            }
            R.id.nav_help->{
                Toast.makeText(
                    this,
                    "help Navigation",Toast.LENGTH_LONG).show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onClick(view: View?) {
        if (view != null)
            when (view.id) {
                R.id.toolbar_cart -> {
                    val intent = Intent(this@MainActivity, MyCartActivity::class.java)
                    startActivity(intent)
                }
            }
    }

    private fun setUserDetailsHeader(){
        showProgressDialog()
        mFireStore.collection(Constants.USERS)
            .document(FireStoreClass().getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                hideProgressDialog()
                val userDetails = document.toObject(UserModel::class.java)!!
                val name=userDetails.firstName+" "+userDetails.lastName
                headerMainBinding.tvName.text = name
                headerMainBinding.tvEmail.text = userDetails.email_id

                Glide.with(this)
                    .load(userDetails.image)
                    .centerCrop()
                    .placeholder(R.drawable.user_placeholder)
                    .into(headerMainBinding.ivProfile)

            }
            .addOnFailureListener { exception ->
                hideProgressDialog()
                showErrorSnackBar(
                    exception.toString(),
                    true
                )
            }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }
    }

}