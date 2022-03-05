package com.example.firebasetestapp.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebasetestapp.auth.FirebaseRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val firebaseClient: FirebaseRepository) : ViewModel() {

    private val _currentUser : MutableLiveData<FirebaseUser?> = MutableLiveData<FirebaseUser?>()
    val currentUser : LiveData<FirebaseUser?> get() = _currentUser

    fun login(email : String, passwor: String) {
        firebaseClient.loginUser(email,passwor).addOnCompleteListener { task->
            if (task.isSuccessful){
                _currentUser.value = firebaseClient.getCurrentUser()
            }else{
                _currentUser.value = null
            }
        }

    }

    fun register(email : String, passwor: String) = firebaseClient.registerUser(email,passwor)

    fun logout() = firebaseClient.signOutUser()

}