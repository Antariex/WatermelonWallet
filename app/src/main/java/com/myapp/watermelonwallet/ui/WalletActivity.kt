package com.myapp.watermelonwallet.ui

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.myapp.watermelonwallet.R

class WalletActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val currency = arrayOf("Pesos Argentinos", "Dólares estadounidenses")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wallet_activity)

        val spin = findViewById<Spinner>(R.id.spinnerCurrency)
        spin.onItemSelectedListener = this

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
        Toast.makeText(
            applicationContext,
            currency[position],
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}
