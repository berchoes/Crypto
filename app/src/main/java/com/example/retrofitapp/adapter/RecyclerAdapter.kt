package com.example.retrofitapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.R
import com.example.retrofitapp.model.CryptoModel
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerAdapter(private val cryptolist: ArrayList<CryptoModel>, private val listener: Listener) : RecyclerView.Adapter<RecyclerAdapter.RowHolder>() {

    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }

     val colors: Array<String> = arrayOf("#aa779a","#1b9fba ","#eeb200","#66cdaa","#fdfaf7 ","#f6dfd7","#b4eeb4","#c4b8f9")

     class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

         fun bind(cryptoModel: CryptoModel, colors: Array<String>, position: Int,listener: Listener){

             itemView.setOnClickListener{listener.onItemClick(cryptoModel)}
             itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
             itemView.text_name.text = cryptoModel.currency
             itemView.text_price.text = cryptoModel.price
        }

     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)

        return RowHolder(view)

    }

    override fun getItemCount(): Int {
        return cryptolist.count()
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptolist[position],colors,position,listener)


    }




}