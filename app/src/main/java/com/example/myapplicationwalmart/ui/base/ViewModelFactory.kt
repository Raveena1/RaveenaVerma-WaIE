package com.example.myapplicationwalmart.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationwalmart.repository.ApodRepository
import com.example.myapplicationwalmart.repository.BaseRepository
import com.example.myapplicationwalmart.ui.apod.ApodViewModel

class ViewModelFactory(
    private val repository: BaseRepository

) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ApodViewModel::class.java) -> ApodViewModel(repository as ApodRepository) as T
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
