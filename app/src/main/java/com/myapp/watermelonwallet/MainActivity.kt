package com.myapp.watermelonwallet

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editUsername: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var logo: ImageView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editUsername = findViewById(R.id.editUsername)
        editPassword = findViewById(R.id.editPassword)
        btnLogin = findViewById(R.id.btnLogin)
        logo = findViewById(R.id.logo)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        loadSavedCredentials() // Autollenado al inicio de la aplicación

        btnLogin.setOnClickListener {
            val username = editUsername.text.toString()
            val password = editPassword.text.toString()

            CredentialsValidator.validateCredentials(username, password, object : CredentialsValidator.Callback {
                override fun onResult(success: Boolean) {
                    if (success) {
                        showToast("Login exitoso")

                        saveCredentials(username, password)

                        val intent = Intent(this@MainActivity, MainActivity2::class.java)
                        startActivity(intent)
                    } else {
                        showToast("Credenciales incorrectas")
                    }
                }
            })
        }
    }

    private fun loadSavedCredentials() {
        val savedUsername = sharedPreferences.getString("username", null)
        val savedPassword = sharedPreferences.getString("password", null)

        savedUsername?.let {
            editUsername.setText(it)
        }

        savedPassword?.let {
            editPassword.setText(it)
        }
    }

    private fun saveCredentials(username: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.apply()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
