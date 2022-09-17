package com.example.shoeshop.ui.shoelist

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.shoeshop.R
import com.example.shoeshop.databinding.FragmentShoeListBinding
import com.example.shoeshop.databinding.ShoeItemBinding
import com.example.shoeshop.viewmodel.MainViewModel

class ShoeListFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding : FragmentShoeListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        viewModel.shoeList.observe(this as LifecycleOwner, Observer {

            for (shoe in it) {
                val itemBinding = ShoeItemBinding.inflate(layoutInflater)
                itemBinding.shoeData = shoe
                binding.linearLayout.addView(itemBinding.root)
            }
        })

        binding.fab.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToDetailsFragment())
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, this.findNavController())
                || super.onOptionsItemSelected(item)
    }
}