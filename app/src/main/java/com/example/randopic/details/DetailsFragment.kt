package com.example.randopic.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.randopic.databinding.FragmentDetailsBinding

class DetailsFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentDetailsBinding.inflate(inflater)
        val picture = DetailsFragmentArgs.fromBundle(requireArguments()).pictureDataId
        val viewModelFactory = DetailsViewModelFactory(picture, application)
        val detailsViewMode = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)
        binding.viewModel = detailsViewMode
        binding.lifecycleOwner = this


        detailsViewMode.navigateToOverview.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    DetailsFragmentDirections.actionDetailsFragmentToOverviewFragment()
                )
                detailsViewMode.goBackComplete()
            }
        })
        return binding.root
    }
}