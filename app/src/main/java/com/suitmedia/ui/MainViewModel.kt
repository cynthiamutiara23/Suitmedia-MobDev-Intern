package com.suitmedia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.suitmedia.data.Repository
import com.suitmedia.data.remote.response.User

class MainViewModel(private val repository: Repository): ViewModel() {
    val users: LiveData<PagingData<User>> =
        repository.getUsers().cachedIn(viewModelScope)
}