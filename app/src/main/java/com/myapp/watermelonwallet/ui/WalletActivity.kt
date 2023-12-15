package com.myapp.watermelonwallet.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.myapp.watermelonwallet.R
import com.myapp.watermelonwallet.ui.wallets.Wallet

class WalletActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val currency = arrayOf("Pesos Argentinos", "Dólares estadounidenses", "BTC")

    // SharedPreferences
    private val PREFS_NAME = "MyPrefsFile"
    private val WALLET_KEY = "wallets"

    private lateinit var wallets: MutableList<Wallet>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wallet_activity)

        val spin = findViewById<Spinner>(R.id.spinnerCurrency)
        spin.onItemSelectedListener = this

        wallets = getWallets()

        val ad: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            currency
        )

        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spin.adapter = ad
    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        // ...
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    fun saveWallet(view: View) {
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val walletName = editTextName.text.toString()

        // Crear una nueva wallet con id, walletName, walletAmount y walletCurrency
        val newWallet = Wallet(0, walletName, 0.0f, currency[0]) // Aquí puedes elegir la moneda predeterminada

        wallets.add(newWallet)

        saveWallets(wallets)


        Toast.makeText(
            applicationContext,
            "Nueva wallet creada con éxito",
            Toast.LENGTH_LONG
        ).show()

        finish()
    }

    private fun getWallets(): MutableList<Wallet> {
        val prefs: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val walletsString: String? = prefs.getString(WALLET_KEY, null)

        return if (walletsString != null) {
            Gson().fromJson(walletsString, object : TypeToken<MutableList<Wallet>>() {}.type)
        } else {
            mutableListOf()
        }
    }

    private fun saveWallets(wallets: MutableList<Wallet>) {
        val prefs: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()

        val walletsString: String = Gson().toJson(wallets)

        editor.putString(WALLET_KEY, walletsString)
        editor.apply()
    }
}
