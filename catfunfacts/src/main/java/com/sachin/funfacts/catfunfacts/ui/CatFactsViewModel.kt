package com.sachin.funfacts.catfunfacts.ui

import android.content.Context
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachin.central.datasource.ResourceState
import com.sachin.central.datasource.pojos.CatFactsResModel
import com.sachin.central.utils.CoreUtility
import com.sachin.funfacts.catfunfacts.repository.CatFactsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * created by Sachin Rajput
 * https://droid-lover.medium.com/
 */
class CatFactsViewModel @ViewModelInject constructor(
        @ApplicationContext private val context: Context,
        private val catFactsRepository: CatFactsRepository
) : ViewModel() {


    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val successMessage: MutableLiveData<String> = MutableLiveData()
    var noInternet: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loadingVisibility.value = View.GONE
    }


    /** API call to get Education Content for user*/
    private val _catFunFactsResponse: MutableLiveData<ResourceState<ArrayList<CatFactsResModel>>> = MutableLiveData()
    val catFunFactsResponse: LiveData<ResourceState<ArrayList<CatFactsResModel>>> = _catFunFactsResponse


    fun getCatFunFacts(number:String) {
        if (!CoreUtility.isInternetConnected(context)) {
            noInternet.value = true
        } else {
            noInternet.value = false
            if (CoreUtility.checkNetworkAndToast(context)) {
                loadingVisibility.value = View.VISIBLE
                viewModelScope.launch {
                    catFactsRepository.getCatFunFacts(number).collect {
                        _catFunFactsResponse.value = it
                    }
                }
            }
        }
    }


}