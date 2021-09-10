package com.sachin.funfacts.catfunfacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.sachin.central.datasource.ResourceState
import com.sachin.central.utils.CoreUtility
import com.sachin.funfacts.catfunfacts.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_cat_fun_facts.*

/**
 * created by Sachin Rajput
 * https://droid-lover.medium.com/
 */
@AndroidEntryPoint
class CatFunFactsFragment : Fragment() {

    private lateinit var viewModel: CatFactsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this)[CatFactsViewModel::class.java]
        return inflater.inflate(R.layout.fragment_cat_fun_facts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDogFunFacts()
        setUpObservers()
    }

    private fun getDogFunFacts() {
        viewModel.getCatFunFacts("1")
    }

    private fun setUpObservers() {


        viewModel.catFunFactsResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResourceState.Success -> {
                    CoreUtility.printLog(TAG, "Inside_catFunFactsResponse_success ${Gson().toJson(it)}")
                    viewModel.loadingVisibility.value = View.GONE
                    it.data?.also { facts->
                        setUpCatFactsRecyclerView(facts)
                    }
                }
                is ResourceState.Error -> {
                    viewModel.loadingVisibility.value = View.GONE
                    CoreUtility.printLog(TAG, "Inside_catFunFactsResponse_error ${it.message}")
                    Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
                }
                is ResourceState.Loading -> {
                    viewModel.loadingVisibility.value = View.VISIBLE
                }
            }
        })


        viewModel.loadingVisibility.observe(viewLifecycleOwner, Observer {
            if (it == View.VISIBLE)
                catLoaderView.visibility = View.VISIBLE
            else
                catLoaderView.visibility = View.GONE
        })


    }

    private fun setUpCatFactsRecyclerView(facts: ArrayList<CatFactsViewModel>) {
        catLoaderView.visibility = View.GONE
        rvCatFunFacts.apply {
            visibility = View.VISIBLE
            adapter = CatFunFactsAdapter(facts)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

    }

    companion object{
        private const val TAG = "CatFunFactsFragment"
    }
}