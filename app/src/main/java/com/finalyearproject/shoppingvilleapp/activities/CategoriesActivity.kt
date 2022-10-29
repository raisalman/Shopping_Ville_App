package com.finalyearproject.shoppingvilleapp.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.adapters.CategoryAdapter
import com.finalyearproject.shoppingvilleapp.databinding.ActivityCategoriesBinding
import com.finalyearproject.shoppingvilleapp.models.CategoryModel
import com.finalyearproject.shoppingvilleapp.models.ProductModel

class CategoriesActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityCategoriesBinding
    private lateinit var categories: ArrayList<CategoryModel>
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categories = ArrayList()
        toolbar = binding.toolbar
        toolbar.title = "Categories"


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        initializeData()

        categoryAdapter = CategoryAdapter(categories)
        val categoryLayoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = categoryLayoutManager
        binding.recyclerView.adapter = categoryAdapter

//        binding.btnBack.setOnClickListener(this)
    }

    private fun initializeData() {
        //Add categories
        categories.add(
            CategoryModel(
                "",
                R.drawable.img1, "Watches"
            )
        )
        categories.add(
            CategoryModel(
                "",
                R.drawable.img_1, "VegiTables"
            )
        )
        categories.add(
            CategoryModel(
                "",
                R.drawable.img10, "Beauty Products"
            )
        )
        categories.add(
            CategoryModel(
                "",
                R.drawable.img12, "Perfumes"
            )
        )
        categories.add(
            CategoryModel(
                "",
                R.drawable.img8, "Face Washes"
            )
        )
        categories.add(
            CategoryModel(
                "",
                R.drawable.img13, "Hair styling"
            )
        )
        categories.add(
            CategoryModel(
                "",
                R.drawable.img12, "Perfumes"
            )
        )
        categories.add(
            CategoryModel(
                "",
                R.drawable.img1, "Watches"
            )
        )
        categories.add(
            CategoryModel(
                "",
                R.drawable.img10, "Beauty Products"
            )
        )

    }

    override fun onClick(view: View?) {
        /* when(view?.id){
             binding.btnBack.id->{
                 onBackPressed()
             }
         }*/
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu?.findItem(R.id.search)

        val searchView: SearchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO()
            }

            override fun onQueryTextChange(searchText: String?): Boolean {

                filter(searchText)
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun filter(text: String?) {
        // creating a new array list to filter our data.
        val filteredList: ArrayList<CategoryModel> = ArrayList()

        // running a for loop to compare elements.
        for (item in categories) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.Category_name.lowercase().contains(text?.lowercase()!!)) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredList.add(item)
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
            categoryAdapter.filterList(filteredList)
        } else {

            categoryAdapter.filterList(filteredList)
        }
    }


}