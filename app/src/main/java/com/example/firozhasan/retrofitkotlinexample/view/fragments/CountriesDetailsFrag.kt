package com.example.firozhasan.retrofitkotlinexample.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.firozhasan.retrofitkotlinexample.R
import com.example.firozhasan.retrofitkotlinexample.databinding.CountryDetailFragBinding
import com.example.firozhasan.retrofitkotlinexample.util.Coroutines
import com.example.firozhasan.retrofitkotlinexample.viewModel.CountryDetailsViewModel
import com.example.firozhasan.retrofitkotlinexample.viewModel.CountryDetailsViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class CountriesDetailsFrag: Fragment(), KodeinAware {
    private lateinit var viewModel: CountryDetailsViewModel
    override val kodein by kodein()
    private val factory: CountryDetailsViewModelFactory by instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val alphaValue = arguments!!.getString("alpha")
        Log.d("alhabibi", "yaalpha = $alphaValue")

        viewModel = ViewModelProviders.of(this, factory).get(CountryDetailsViewModel::class.java)
        val binding = DataBindingUtil.inflate<CountryDetailFragBinding>(inflater,
                R.layout.country_detail_frag, container, false).apply {
            this.lifecycleOwner = activity
            this.viewmodel = viewModel
        }
        //Toast.makeText(activity,"sfsdfsf",Toast.LENGTH_LONG).show()
        return binding.root
       // return inflater.inflate(R.layout.fragment_search_frag, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this, factory).get(CountryDetailsViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = Coroutines.main {
        //progressBar.show()
        viewModel.countryDetails.await().observe(this, Observer {
            //progressBar.hide()
            //initRecyclerView(it)
        })
    }
}