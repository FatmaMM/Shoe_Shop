package com.example.shoeshop.ui.login

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shoeshop.R
import com.example.shoeshop.databinding.FragmentLoginBinding
import com.example.shoeshop.viewmodel.LoginViewModel
import com.example.shoeshop.viewmodel.LoginViewModelFactory

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        val username = binding.username
        val password = binding.password
        val login = binding.loginButton
        val welcomeButton = binding.loginWithExistingAccountButton

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]

        loginViewModel.loginResult.observe(viewLifecycleOwner, Observer {
            val loginState = it ?: return@Observer
            if (!loginState) {
                showLoginFailed()
            } else {
                updateUiWithUser()
                navigateToWelcomeScreen()
            }
        })

        login.setOnClickListener {
            loginViewModel.login(username.text.toString(), password.text.toString())
        }
        welcomeButton.setOnClickListener { navigateToShoeListScreen() }

        return binding.root
    }

    private fun navigateToWelcomeScreen() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
    }

    private fun navigateToShoeListScreen() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToShoeListFragment())
    }


    private fun updateUiWithUser() {
        val welcome = getString(R.string.welcome_message)
        Toast.makeText(
            context,
            "$welcome",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed() {
        Toast.makeText(context, R.string.invalid_username, Toast.LENGTH_SHORT).show()
    }
}