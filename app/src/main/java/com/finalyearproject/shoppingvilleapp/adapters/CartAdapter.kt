package com.finalyearproject.shoppingvilleapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.models.ProductModel

class CartAdapter(private val productList: List<ProductModel>) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sample_product_cart, parent, false)
        context=parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val productModel = productList[position]

        holder.productImage.setImageResource(productModel.image)
        holder.productName.text = productModel.name
        holder.productPrice.text = productModel.price.toString()

        //onItemClickListener

    }

    override fun getItemCount(): Int {

        return productList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView
        val productName: TextView
        val productPrice: TextView
        val quantity:TextView

        init {
            productImage = itemView.findViewById(R.id.iv_product_cart)
            productName = itemView.findViewById(R.id.tv_product_name_cart)
            productPrice = itemView.findViewById(R.id.tv_price_cart)
            quantity = itemView.findViewById(R.id.tv_quantity)
        }

    }
}