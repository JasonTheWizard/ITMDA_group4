package com.example.itmda_group4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class ProductAdapter(private var productList:MutableList<Product>):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutView:View = LayoutInflater.from(parent.context).
                inflate(R.layout.product_view, parent, false)
        return ProductViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        Picasso.get().load(productList[position].img)
            .into(holder.productImage)
        holder.productTitle.text = productList[position].title
        holder.productPrice.text = productList[position].price
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view){
        var productImage: ImageView = view.findViewById(R.id.product_img)
        var productTitle: TextView = view.findViewById(R.id.product_title)
        var productPrice: TextView = view.findViewById(R.id.product_price)

    }

}

