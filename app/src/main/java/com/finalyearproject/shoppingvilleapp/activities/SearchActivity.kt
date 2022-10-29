package com.finalyearproject.shoppingvilleapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.marginEnd
import androidx.recyclerview.widget.GridLayoutManager
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.adapters.ProductAdapter
import com.finalyearproject.shoppingvilleapp.databinding.ActivitySearchBinding
import com.finalyearproject.shoppingvilleapp.models.ProductModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySearchBinding
    private lateinit var products:ArrayList<ProductModel>
    private lateinit var adapter:ProductAdapter
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        products = ArrayList()
        toolbar=binding.toolbar

        toolbar.title = "Search"

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initializeData()

        adapter = ProductAdapter(products)
        val layoutManager = GridLayoutManager(this,2, GridLayoutManager.VERTICAL,false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

    }

    private fun initializeData() {
        //Add categories
        products.add(
            ProductModel("", R.drawable.img17,
                "Klein Brand watch in low price", "Klein", 1299f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
        products.add(
            ProductModel("", R.drawable.img13,
                "Sablon Hair spray best for hair styling", "Sablon", 349f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
        products.add(
            ProductModel("", R.drawable.img15,
                "Hair Styling wax", "SportsStar", 299f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
        products.add(
            ProductModel("", R.drawable.img16,
                "Set Wet Hair styling Wax ", "Set Wet", 320f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
        products.add(
            ProductModel("", R.drawable.img7,
                "Ponds Facial beauty face wash", "Ponds", 249f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
        products.add(
            ProductModel("", R.drawable.img8,
                "Garnier Best face wash for dark spots", "Garnier", 499f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
        products.add(
            ProductModel("", R.drawable.img15,
                "Hair Styling wax", "SportsStar", 299f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
        products.add(
            ProductModel("", R.drawable.img8,
                "Garnier Best face wash for dark spots", "Garnier", 499f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
        products.add(
            ProductModel("", R.drawable.img13,
                "Sablon Hair spray best for hair styling", "Sablon", 349f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
        products.add(
            ProductModel("", R.drawable.img17,
                "Klein Brand watch in low price", "Klein", 1299f,"It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here")
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)

        val searchItem =menu?.findItem(R.id.search)

        val searchView: SearchView =searchItem?.actionView as SearchView
        searchView.setIconifiedByDefault(true)
        searchView.queryHint="Search"
        searchView.maxWidth=700

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(searchText: String?): Boolean {

                filter(searchText)
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun filter(text: String?) {
        // creating a new array list to filter our data.
        val filteredList: ArrayList<ProductModel> = ArrayList()

        // running a for loop to compare elements.
        for (item in products) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.name.lowercase().contains(text?.lowercase()!!)) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredList.add(item)
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
            adapter.filterList(filteredList)
        } else {

            adapter.filterList(filteredList)
        }
    }
}