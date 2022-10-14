package com.finalyearproject.shoppingvilleapp.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.finalyearproject.shoppingvilleapp.R
import com.finalyearproject.shoppingvilleapp.activities.AddShippingAddressActivity
import com.finalyearproject.shoppingvilleapp.activities.CheckoutActivity
import com.finalyearproject.shoppingvilleapp.models.AddressModel
import com.finalyearproject.shoppingvilleapp.utills.Constants

class AddressAdapter(private val activity: Activity, private val addressList: List<AddressModel>,private val navActivity:String) :
    RecyclerView.Adapter<AddressAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.address_sample_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val addressDetails = addressList[position]

        holder.name.text=addressDetails.name
        holder.phone.text=addressDetails.mobile
        holder.label.text=addressDetails.label
        holder.address.text=addressDetails.address
        holder.province.text=addressDetails.province
        holder.city.text=addressDetails.city

        if (navActivity=="checkout"){
            holder.itemView.setOnClickListener{
                val intent=Intent(activity,CheckoutActivity::class.java)
                intent.putExtra(Constants.ADDRESS_DETAILS,addressDetails)
                activity.startActivity(intent)
            }
        }

        holder.btnEdit.setOnClickListener{
            val intent=Intent(activity,AddShippingAddressActivity::class.java)
            intent.putExtra(Constants.ADDRESS_DETAILS,addressDetails)
            intent.putExtra("type","update")
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return addressList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView
        val phone: TextView
        val label: TextView
        val address: TextView
        val province: TextView
        val city: TextView
        val btnEdit: TextView

        init {
            name = itemView.findViewById(R.id.tv_name)
            phone = itemView.findViewById(R.id.tv_phone)
            label = itemView.findViewById(R.id.tv_label)
            address = itemView.findViewById(R.id.tv_address)
            province = itemView.findViewById(R.id.tv_province)
            city = itemView.findViewById(R.id.tv_city)
            btnEdit = itemView.findViewById(R.id.btn_edit_address)
        }
    }
}