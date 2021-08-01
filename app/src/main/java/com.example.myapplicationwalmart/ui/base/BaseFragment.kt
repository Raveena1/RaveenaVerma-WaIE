

package com.example.myapplicationwalmart.ui.apod

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.walmartproject.db.ApodDatabase
import com.example.walmartproject.db.ApodEntity
import com.example.myapplicationwalmart.R
import com.example.myapplicationwalmart.databinding.FragmentApodBinding
import com.example.myapplicationwalmart.network.ApodApi
import com.example.myapplicationwalmart.network.Resource
import com.example.myapplicationwalmart.repository.ApodRepository
import com.example.myapplicationwalmart.ui.base.BaseFragment
import com.example.myapplicationwalmart.ui.handleApiError
import com.example.myapplicationwalmart.ui.visible
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [ApodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ApodFragment : BaseFragment<ApodViewModel, FragmentApodBinding, ApodRepository>() {
    lateinit var apod_image: ImageView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        apod_image = view.findViewById(R.id.apod_image)
        Log.v("raveena", "onViewCreated")
        binding.progressbar.visible(false)
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {

                        context?.let { it1 ->
                            ApodDatabase.getInstance(it1).apodDao.insertApodData(
                                ApodEntity(
                                    it.value.date,
                                    it.value.title,
                                    it.value.explanation,
                                    it.value.hdurl
                                )
                            )
                        }
                        // Set text for APOD Title
                        binding.mainTitle.text = it.value.title
                        // Set text for APOD Explanation
                        binding.apodExpln.text = it.value.explanation
                        // Load APOD Image
                        Picasso.get().load(it.value.hdurl).resize(600,600).into(apod_image)
                    }
                }

                is Resource.Failure -> {

                    updateUI()
                    handleApiError(it) {
                        loadApodData()
                    }

                }
            }
        })

        loadApodData()

    }

    private fun updateUI() {
        context?.let {
            ApodDatabase.getInstance(it.applicationContext).apodDao.getCompleteData()
                .observe(this.viewLifecycleOwner, Observer {
                    if (it != null && it.size >0) {
                        Log.d("raveena", it.get(0).title.toString())
                        binding.apodExpln.text = it.get(0).explanation
                        binding.mainTitle.text = it.get(0).title
                        Picasso.get().load(it.get(0).imageurl).resize(600, 600).into(apod_image)
                    }

                })
        }

    }

    private fun loadApodData() {
        viewModel.loadApodData()

    }

    override fun getViewModel() = ApodViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentApodBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        ApodRepository(remoteDataSource.buildApi(ApodApi::class.java))

}
