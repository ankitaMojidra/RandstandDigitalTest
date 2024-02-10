package com.example.randstanddigitaltest.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randstanddigitaltest.data.repository.CountryRepository

class MainActivityViewModelFactory(private val repository: CountryRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(CountryRepository::class.java).newInstance(repository)
    }
}