package com.sachin.funfacts.dogfunfacts.ui

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
import com.sachin.central.datasource.pojos.DogFactsResModel
import com.sachin.central.utils.CoreUtility
import com.sachin.funfacts.dogfunfacts.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dog_fun_facts.*

/**
 * created by Sachin Rajput
 * https://droid-lover.medium.com/
 */
@AndroidEntryPoint
class DogFunFactsFragment : Fragment() {

    private lateinit var viewModel: DogFactsViewModel
    private lateinit var dogFunFactsAdapter: DogFunFactsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this)[DogFactsViewModel::class.java]
        return inflater.inflate(R.layout.fragment_dog_fun_facts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDogFunFacts()
        setUpObservers()
    }

    private fun getDogFunFacts() {
        viewModel.getDogFunFacts("10")
    }

    private fun setUpObservers() {


        viewModel.dogFunFactsResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResourceState.Success -> {
                    CoreUtility.printLog(TAG, "Inside_dogFunFactsResponse_success ${Gson().toJson(it)}")
                    viewModel.loadingVisibility.value = View.GONE
                    it.data?.also { facts->
                        setUpDogFactsRecyclerView(facts)
                    }
                }
                is ResourceState.Error -> {
                    viewModel.loadingVisibility.value = View.GONE
                    CoreUtility.printLog(TAG, "Inside_dogFunFactsResponse_error ${it.message}")
                    Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
                }
                is ResourceState.Loading -> {
                    viewModel.loadingVisibility.value = View.VISIBLE
                }
            }
        })


        viewModel.loadingVisibility.observe(viewLifecycleOwner, Observer {
            if (it == View.VISIBLE)
                dogLoaderView.visibility = View.VISIBLE
            else
                dogLoaderView.visibility = View.GONE
        })


    }

    private fun setUpDogFactsRecyclerView(facts: ArrayList<DogFactsResModel>) {
        dogLoaderView.visibility = View.GONE
        rvDogFunFacts.apply {
            visibility = View.VISIBLE
            adapter = DogFunFactsAdapter(facts)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

    }

    companion object{
        private const val TAG = "DogFunFactsFragment"
    }
}