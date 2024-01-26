package com.bmh.caretaker.utils

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthManager {
    val auth = Firebase.auth

    /**
     * Initiate Sign In
     */
    fun signIn(email: String, password: String, onSuccess: () -> Unit, onFailed: () -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess.invoke()
                } else {
                    onFailed.invoke()
                }
            }
    }

    /**
     * Initiate Sign Up / Register User
     */
    fun signUp(email: String, password: String, onSuccess: () -> Unit, onFailed: () -> Unit) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (auth.currentUser !=  null) onSuccess.invoke() else onFailed.invoke()
                } else {
                    onFailed.invoke()
                }
            }
    }

    /**
     * Forgot password
     */
    fun forgotPassword(email: String, onSuccess: () -> Unit, onFailed: () -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener {task ->
                if (task.isSuccessful) {
                    onSuccess.invoke()
                } else {
                    onFailed.invoke()
                }
            }
    }

    /**
     * Check if user currently login
     */
    fun isCurrentUser(onSuccess: () -> Unit, onFailed: () -> Unit) {
        if (auth.currentUser != null) {
            onSuccess.invoke()
        } else {
            onFailed.invoke()
        }
    }

    /**
     * Logout
     */
    fun logout(onSuccess: () -> Unit) {
        auth.signOut().let { onSuccess.invoke() }
    }

}