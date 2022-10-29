package com.finalyearproject.shoppingvilleapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.models.ImageSliderModel
import com.smarteist.autoimageslider.SliderViewAdapter


// on below line we are creating a class for slider
// adapter and passing our array list to it.
class SliderAdapter(images: ArrayList<ImageSliderModel>) :
    SliderViewAdapter<SliderAdapter.SliderViewHolder>() {

    // on below line we are creating a
    // new array list and initializing it.
    private var sliderList: ArrayList<ImageSliderModel> = images

    // on below line we are calling get method
    override fun getCount(): Int {
        // in this method we are returning
        // the size of our slider list.
        return sliderList.size
    }

    // on below line we are calling on create view holder method.
    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapter.SliderViewHolder {
        // inside this method we are inflating our layout file for our slider view.
        val inflate: View =
            LayoutInflater.from(parent!!.context).inflate(R.layout.slider_item, null)

        // on below line we are simply passing
        // the view to our slider view holder.
        return SliderViewHolder(inflate)
    }

    // on below line we are calling on bind view holder method to set the data to our image view.
    override fun onBindViewHolder(viewHolder: SliderAdapter.SliderViewHolder?, position: Int) {

        val sliderItem: ImageSliderModel = sliderList[position]

        viewHolder?.textView?.text = sliderItem.description
        viewHolder?.imageView?.setImageResource(sliderItem.imageRes)
    }

    // on below line we are creating a class for slider view holder.
    class SliderViewHolder(itemView: View?) : SliderViewAdapter.ViewHolder(itemView) {

        var imageView: ImageView
        var textView: TextView
        init {
            imageView = itemView?.findViewById(R.id.iv_auto_image_slider)!!
            textView = itemView.findViewById(R.id.tv_auto_image_slider)!!
        }
    }
}