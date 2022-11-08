package com.example.paging3.data.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UnsplashApiImg {

/*
    @GET("/photo-1661347333298-26846cec680b?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwzNzY4ODF8MXwxfGFsbHwxfHx8fHx8Mnx8MTY2Nzg4ODE1NQ&ixlib=rb-4.0.3&q=80&w=1080")
*/
    @GET("{url}")
    suspend fun loadImage(
        @Path("url") url: String
    ): Response<ResponseBody>

}