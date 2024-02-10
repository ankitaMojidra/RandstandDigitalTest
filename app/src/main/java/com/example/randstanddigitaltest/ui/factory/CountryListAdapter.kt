package com.example.randstanddigitaltest.ui.factory

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.randstanddigitaltest.R
import com.example.randstanddigitaltest.data.repository.CountryModel
import com.example.randstanddigitaltest.databinding.ListCountriesBinding

class CountryListAdapter (private val activity: Activity) :
    ListAdapter<CountryModel, CountryListAdapter.MyViewHolder>(CountryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListCountriesBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun setCountryList(countryList: List<CountryModel>) {
        submitList(countryList)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!, activity)
    }

    class MyViewHolder(private val binding: ListCountriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CountryModel, activity: Activity) {
            val formattedString = activity.getString(R.string.name_region, data.name, data.region)
            binding.txvName.text = formattedString
            binding.txvCapital.text = data.capital
            binding.txvCode.text = data.code
        }
    }
}

class CountryDiffCallback : DiffUtil.ItemCallback<CountryModel>() {
    override fun areItemsTheSame(oldItem: CountryModel, newItem: CountryModel): Boolean {
        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(oldItem: CountryModel, newItem: CountryModel): Boolean {
        return oldItem == newItem
    }
}