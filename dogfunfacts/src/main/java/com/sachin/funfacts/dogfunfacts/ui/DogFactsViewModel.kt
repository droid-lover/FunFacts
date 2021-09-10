package com.sachin.funfacts.dogfunfacts.ui

import android.content.Context
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachin.central.datasource.ResourceState
import com.sachin.central.datasource.pojos.DogFactsResModel
import com.sachin.central.utils.CoreUtility
import com.sachin.funfacts.dogfunfacts.repository.DogFactsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DogFactsViewModel @ViewModelInject constructor(
        @ApplicationContext private val context: Context,
        private val dogFactsRepository: DogFactsRepository
) : ViewModel() {


    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val successMessage: MutableLiveData<String> = MutableLiveData()
    var noInternet: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loadingVisibility.value = View.GONE
    }


    /** API call to get Education Content for user*/
    private val _dogFunFactsResponse: MutableLiveData<ResourceState<ArrayList<DogFactsResModel>>> = MutableLiveData()
    val dogFunFactsResponse: LiveData<ResourceState<ArrayList<DogFactsResModel>>> = _dogFunFactsResponse


    fun getDogFunFacts(number:String) {
        if (!CoreUtility.isInternetConnected(context)) {
            noInternet.value = true
        } else {
            noInternet.value = false
            if (CoreUtility.checkNetworkAndToast(context)) {
                loadingVisibility.value = View.VISIBLE
                viewModelScope.launch {
                    dogFactsRepository.getDogFunFacts(number).collect {
                        _dogFunFactsResponse.value = it
                    }
                }
            }
        }
    }


}