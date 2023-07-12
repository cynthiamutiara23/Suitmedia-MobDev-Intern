package com.suitmedia.data.remote.retrofit

import com.suitmedia.data.remote.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int?,
        @Query("per_page") size: Int?,
    ): Response
}