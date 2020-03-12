package com.example.retrofitapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapp.R
import com.example.retrofitapp.adapter.RecyclerAdapter
import com.example.retrofitapp.model.CryptoModel
import com.example.retrofitapp.service.CryptoAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.security.auth.callback.Callback

//https://api.nomics.com/v1/prices?key=ee7b915a5a2444c079ed48a965bf46bb
//ee7b915a5a2444c079ed48a965bf46bb

class MainActivity : AppCompatActivity(), RecyclerAdapter.Listener {
    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this, "Clicked: ${cryptoModel.currency}",Toast.LENGTH_LONG)
    }

    private val BASE_URL = "https://api.nomics.com/v1/"

    private var cryptoModels: ArrayList<CryptoModel>? = null

    private lateinit var recyclerViewAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager (this)
        recyclerView.layoutManager = layoutManager

        loadData()
    }

    private fun loadData(){

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object: retrofit2.Callback<List<CryptoModel>> {

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>) {

                if(response.isSuccessful){
                    response.body()?.let{
                        cryptoModels = ArrayList(it)

                        recyclerViewAdapter = RecyclerAdapter(cryptoModels!!,this@MainActivity)
                        recyclerView.adapter = recyclerViewAdapter
                    }
                }


            }
        })
    }


}
