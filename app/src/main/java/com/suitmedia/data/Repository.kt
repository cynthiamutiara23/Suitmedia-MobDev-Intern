package com.suitmedia.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.liveData
import com.suitmedia.data.paging.UserPagingSource
import com.suitmedia.data.remote.response.User
import com.suitmedia.data.remote.retrofit.ApiService

class Repository private constructor(private val apiService: ApiService) {
    fun getUsers(): LiveData<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData.map {
            val userMap = mutableSetOf<User>()
            it.filter { user ->
                if(userMap.contains(user)) {
                    false
                } else {
                    userMap.add(user)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            apiService: ApiService,
        ): Repository = instance ?: synchronized(this){
            instance ?: Repository(apiService)
        }.also {
            instance = it
        }
    }
}