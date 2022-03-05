package com.example.firebasetestapp.auth

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class FirebaseRepository {

    private val auth = FirebaseAuth.getInstance()

    fun getCurrentUser() = auth.currentUser

    fun registerUser(email: String, password: String) =
        auth.createUserWithEmailAndPassword(email, password)

    fun loginUser(email :String, password: String) =
        auth.signInWithEmailAndPassword(email, password)

    fun signOutUser() = auth.signOut()
}