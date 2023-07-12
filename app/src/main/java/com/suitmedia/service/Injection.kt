package com.suitmedia.service

import android.content.Context
import com.suitmedia.data.Repository
import com.suitmedia.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService)
    }
}