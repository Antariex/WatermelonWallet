package com.myapp.watermelonwallet

import android.os.Handler
import android.os.Looper

class CredentialsValidator {

    interface Callback {
        fun onResult(success: Boolean)
    }

    companion object {
        fun validateCredentials(username: String, password: String, callback: Callback) {
            Handler(Looper.getMainLooper()).postDelayed({
                // Compare password as a string
                val isValid = "admin" == username && "123456" == password
                callback.onResult(isValid)
            }, 2000) // Simulate a 2-second delay
        }
    }
}
