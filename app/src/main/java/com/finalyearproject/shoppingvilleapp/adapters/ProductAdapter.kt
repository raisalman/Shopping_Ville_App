package com.finalyearproject.shoppingvilleapp.adapters

import android.content.Context
import android.content.Intent
import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.activities.ProductDescriptionActivity
import com.finalyearproject.shoppingvilleapp.models.CategoryModel
import com.finalyearproject.shoppingvilleapp.models.ProductModel
import com.finalyearproject.shoppingvilleapp.utills.Constants

class ProductAdapter(private var productList: List<ProductModel>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_sample_layout, parent, false)
        context=parent.context
        return ViewHolder(view)
    }

    // method for filtering our recyclerview items.
    fun filterList(filterList: ArrayList<ProductModel>) {

        productList = filterList

        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val productModel = productList[position]

        holder.productImage.setImageResource(productModel.image)
        holder.productName.text = productModel.name
        holder.productPrice.text = productModel.price.toString()

        //onItemClickListener
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDescriptionActivity::class.java)
            intent.putExtra(Constants.PRODUCT_DETAILS, productModel)
            context.startActivity(intent)
        }

        holder.productImage.setOnClickListener{
            val intent = Intent(context, ProductDescriptionActivity::class.java)
            intent.putExtra(Constants.PRODUCT_DETAILS, productModel)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {

        return productList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView
        val productName: TextView
        val productPrice: TextView

        init {
            productImage = itemView.findViewById(R.id.product_sample_imageView)
            productName = itemView.findViewById(R.id.product_sample_name)
            productPrice = itemView.findViewById(R.id.product_sample_price)
        }

    }
}