package com.myapp.watermelonwallet

import android.content.Intent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editUsername = findViewById(R.id.editUsername)
        editPassword = findViewById(R.id.editPassword)
        btnLogin = findViewById(R.id.btnLogin)
        logo = findViewById(R.id.logo)

        autoLogin()

        btnLogin.setOnClickListener {
            val username = editUsername.text.toString()
            val password = editPassword.text.toString()

            CredentialsValidator.validateCredentials(username, password, object : CredentialsValidator.Callback {
                override fun onResult(success: Boolean) {
                    if (success) {
                        showToast("Login exitoso")

                       val intent = Intent(this@MainActivity, MainActivity2::class.java)
                        startActivity(intent)

                    } else {
                        showToast("Credenciales incorrectas")
                    }
                }
            })
        }
    }

    private fun autoLogin() {
        val defaultUsername = "admin"
        val defaultPassword = "123456"

        CredentialsValidator.validateCredentials(defaultUsername, defaultPassword, object : CredentialsValidator.Callback {
            override fun onResult(success: Boolean) {
                if (success) {

                    val intent = Intent(this@MainActivity, MainActivity2::class.java)
                    startActivity(intent)

                } else {
                    showToast("Usuario o clave incorrectas")
                }
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
