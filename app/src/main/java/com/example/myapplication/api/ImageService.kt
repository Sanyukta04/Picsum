package com.example.myapplication.api

import com.example.myapplication.db.entity.ImageEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ImageService {


    @GET("list")
    suspend fun getAuthor(): Response<List<ImageEntity>>

    @GET("{atr1}/{atr2}/{id}")
    suspend fun getImage(@Path("atr1") atr1: String,@Path("atr2") atr2:String,@Path("id") id:Long):Response<List<String>>

   /// @GET("{api}/{dataReg}/{awdId}/{authorizationToken}/{date}")
  //  suspend fun getWeatherByDate(@Path("api") api: String,@Path("dataReg") dataReg: String,@Path("awdId") awsId: String, @Path("authorizationToken") authorizationToken: String, @Path("date") date: String): Response<List<WeatherEntity>>

/*
    @GET("{api}/{Dashboard}/{awdId}/{authorizationToken}/{date}/{ForSingleDay}")
    suspend fun getWeatherByDate(@Path("awdId") awsId: String, @Path("authorizationToken") authorizationToken: String, @Path("date") date: String): Response<List<ImageEntity>>
*/
}

