package com.finalyearproject.shoppingvilleapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.models.CategoryModel

class CategoryAdapter (private var categoryList: List<CategoryModel>)
    : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).
        inflate(R.layout.category_sample_layout,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val categoryModel = categoryList[position]

        holder.categoryImage.setImageResource(categoryModel.category_image)

        holder.categoryName.text= categoryModel.Category_name
    }

    override fun getItemCount(): Int {

        return categoryList.size
    }

    // method for filtering our recyclerview items.
    fun filterList(filterList: ArrayList<CategoryModel>) {

        categoryList = filterList

        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

        val categoryImage: ImageView
        val categoryName: TextView
        init {
            categoryImage=itemView.findViewById(R.id.category_iv)
            categoryName=itemView.findViewById(R.id.category_name_tv)
        }
    }
}