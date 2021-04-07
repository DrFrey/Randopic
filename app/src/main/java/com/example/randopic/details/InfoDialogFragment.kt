package com.example.randopic.details

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.randopic.databinding.FragmentInfoDialogBinding

class InfoDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bundle = InfoDialogFragmentArgs.fromBundle(requireArguments())
        val viewModelFactory = InfoDialogViewModelFactory(bundle.createdAt, bundle.views,
            bundle.downloads, bundle.make, bundle.focalLength,
            bundle.aperture, bundle.exposureTime, bundle.iso, bundle.dimensions)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(InfoDialogViewModel::class.java)
        val binding = FragmentInfoDialogBinding.inflate(LayoutInflater.from(context))
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}