package com.example.myapplicationwalmart.ui.apod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplicationwalmart.network.Resource
import com.example.myapplicationwalmart.repository.ApodRepository
import com.example.myapplicationwalmart.responses.APODData
import com.example.myapplicationwalmart.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ApodViewModel(
    private val repository: ApodRepository
) : BaseViewModel(repository) {
    private val _apodResponse: MutableLiveData<Resource<APODData>> = MutableLiveData()
    val loginResponse: LiveData<Resource<APODData>>
        get() = _apodResponse

    fun loadApodData() = viewModelScope.launch {
        _apodResponse.value = Resource.Loading
        _apodResponse.value = repository.getApodData()
    }

}