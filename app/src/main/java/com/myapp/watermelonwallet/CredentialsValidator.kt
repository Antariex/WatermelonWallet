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

                val isValid = "admin" == username && "123456" == password
                callback.onResult(isValid)
            }, 2000)
        }
    }
}
