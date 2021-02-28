package com.example.randopic.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.randopic.databinding.FragmentDetailsBinding

class DetailsFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentDetailsBinding.inflate(inflater)
        val picture = DetailsFragmentArgs.fromBundle(requireArguments()).pictureData
        val viewModelFactory = DetailsViewModelFactory(picture, application)
        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)
        binding.lifecycleOwner = this
        return binding.root
    }
}