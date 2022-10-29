package com.finalyearproject.shoppingvilleapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.models.RatingReviewModel

class RatingReviewAdapter(private val reviewList: ArrayList<RatingReviewModel>) :
    RecyclerView.Adapter<RatingReviewAdapter.ViewHolder>() {

    private lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.customer_review_sample_layout, parent, false)
        context=parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val reviewModel = reviewList[position]

        holder.image.setImageResource(reviewModel.image)
        holder.name.text = reviewModel.name
        holder.comment.text = reviewModel.review

        //onItemClickListener

    }

    override fun getItemCount(): Int {

        return reviewList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView
        val name: TextView
        val comment:TextView

        init {
            image = itemView.findViewById(R.id.iv_review_sample)
            name = itemView.findViewById(R.id.tv_name_review_sample)
            comment = itemView.findViewById(R.id.tv_comment_review_sample)
        }

    }
}