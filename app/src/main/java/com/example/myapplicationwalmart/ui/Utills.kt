package com.example.myapplicationwalmart.ui

import android.view.View
import androidx.fragment.app.Fragment
import com.example.myapplicationwalmart.R
import com.example.myapplicationwalmart.network.Resource
import com.google.android.material.snackbar.Snackbar


fun View.visible(isVisible: Boolean) {

    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.snackbar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let { snackbar.setAction("Retry") { it() } }
    snackbar.show()
}

fun Fragment.handleApiError(
    failure: Resource.Failure,
    retry: (() -> Unit)? = null
) {
    when {
        failure.isNetworkError -> requireView().snackbar(
            getString(R.string.network_error_msg),
            retry
        )
        failure.errorCode == 401 -> {
        }
        else -> {
            val error = failure.errorBody?.string().toString()
            requireView().snackbar(error)
        }
    }

}