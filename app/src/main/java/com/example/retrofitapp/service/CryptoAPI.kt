package com.example.retrofitapp.service

import com.example.retrofitapp.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {

    @GET("prices?key=ee7b915a5a2444c079ed48a965bf46bb")

    fun getData() : Call<List<CryptoModel>>
}