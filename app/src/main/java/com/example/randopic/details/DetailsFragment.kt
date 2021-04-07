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
        val detailsViewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)
        binding.viewModel = detailsViewModel
        binding.lifecycleOwner = this


        detailsViewModel.navigateToOverview.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    DetailsFragmentDirections.actionDetailsFragmentToOverviewFragment()
                )
                detailsViewModel.goBackComplete()
            }
        })

        detailsViewModel.displayInfoDialog.observe(viewLifecycleOwner, {
            if (it == true) {
                //val infoDialogFragment = InfoDialogFragment()
                //infoDialogFragment.show(parentFragmentManager, "info")
                val pic = detailsViewModel.picture.value
                this.findNavController().navigate(
                    DetailsFragmentDirections.actionDetailsFragmentToInfoDialogFragment(
                        pic?.createdAt.toString(),
                        pic?.views.toString(),
                        pic?.downloads.toString(),
                        pic?.exif?.make.toString(),
                        pic?.exif?.focalLength.toString(),
                        pic?.exif?.aperture.toString(),
                        pic?.exif?.exposureTime.toString(),
                        pic?.exif?.iso.toString(),
                        pic?.height.toString() + "x" + pic?.width.toString()
                    )
                )
                detailsViewModel.displayInfoComplete()
            }
        })

        return binding.root
    }
}