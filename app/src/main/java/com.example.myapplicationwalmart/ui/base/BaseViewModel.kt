
package com.example.myapplicationwalmart.ui.base

import androidx.lifecycle.ViewModel
import com.example.myapplicationwalmart.repository.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository
) : ViewModel() {

}
