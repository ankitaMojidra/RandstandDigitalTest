package com.example.randstanddigitaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randstanddigitaltest.data.repository.CountryRepository
import com.example.randstanddigitaltest.databinding.ActivityMainBinding
import com.example.randstanddigitaltest.domain.NetworkStatus
import com.example.randstanddigitaltest.ui.MainActivityViewModel
import com.example.randstanddigitaltest.ui.factory.CountryListAdapter
import com.example.randstanddigitaltest.ui.factory.MainActivityViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerAdapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        initViewModel()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding.rclCountries) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val dividerItemDecoration =
                DividerItemDecoration(binding.rclCountries.context, LinearLayoutManager.VERTICAL)
            binding.rclCountries.addItemDecoration(dividerItemDecoration)
            adapter = CountryListAdapter(this@MainActivity).also { recyclerAdapter = it }
        }
    }

    private fun initViewModel() {
        val repository = CountryRepository()
        val viewModelFactory = MainActivityViewModelFactory(repository)
        val viewModel: MainActivityViewModel =
            ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]

        // Observe viewModel's LiveData and update UI
        viewModel.getCountryList().observe(this) { status ->
            when (status) {
                is NetworkStatus.Loading -> {
                    // Show loading spinner
                    binding.progressBar.visibility = View.VISIBLE
                }

                is NetworkStatus.Success -> {
                    // Update UI with data
                    binding.progressBar.visibility = View.GONE
                    status.data.let { recyclerAdapter.setCountryList(it) }
                }

                is NetworkStatus.Error -> {
                    // Show error message
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        this,
                        getString(R.string.check_internet),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}