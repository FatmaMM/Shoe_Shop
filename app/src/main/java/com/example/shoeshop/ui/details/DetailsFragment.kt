package com.example.shoeshop.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.shoeshop.R
import com.example.shoeshop.data.model.Shoe
import com.example.shoeshop.databinding.FragmentDetailsBinding
import com.example.shoeshop.viewmodel.MainViewModel

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: MainViewModel by activityViewModels()
    private val shoe = Shoe(name = "", size = "0", company = "", description = "")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.viewModel = viewModel
        binding.shoeData = shoe

        binding.cancelButton.setOnClickListener {
            navigateToShoeList()
        }

        viewModel.isSaveBTClicked.observe(this as LifecycleOwner, Observer {
            if (it)
                navigateToShoeList()
        })

        return binding.root
    }

    private fun navigateToShoeList() {
        val action = DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment()
        findNavController().navigate(action)
    }

}