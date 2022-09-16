package com.example.shoeshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns

class LoginViewModel : ViewModel() {

    val loginResult  = MutableLiveData<Boolean>()

    fun login(username: String, password: String) {
        loginResult.value = !(!isUserNameValid(username)||!isPasswordValid(password))
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}