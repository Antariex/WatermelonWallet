package com.myapp.watermelonwallet.ui.info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.myapp.watermelonwallet.databinding.FragmentInfoBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private lateinit var tvDollarOfficialToPeso: TextView
    private lateinit var tvBitcoinToDollar: TextView
    private lateinit var tvDollarBlueToPeso: TextView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        tvDollarOfficialToPeso = binding.amountOficial
        tvBitcoinToDollar = binding.amountBitcoin
        tvDollarBlueToPeso = binding.amountBlue

        fetchDollarOfficialToPeso()
        fetchDollarBlueToPeso()
        fetchBitcoinToDollar()

        return view
    }

    private fun fetchDollarOfficialToPeso() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.bluelytics.com.ar/v2/latest")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                val json = responseBody?.let { JSONObject(it) }
                Log.d("JSON_RESPONSE", json.toString())
                val oficialObject = json?.optJSONObject("oficial")
                if (oficialObject != null && oficialObject.has("value_avg")) {
                    val dollarToPeso = oficialObject.getDouble("value_avg")

                    activity?.runOnUiThread {
                        tvDollarOfficialToPeso.text = String.format("%.2f", dollarToPeso)
                    }
                } else {
                    Log.e("JSON_PARSING_ERROR", "No se encontró el valor 'value_avg' en el objeto 'oficial'")
                }
            }
        })
    }

    private fun fetchDollarBlueToPeso() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.bluelytics.com.ar/v2/latest")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                val json = responseBody?.let { JSONObject(it) }
                Log.d("JSON_RESPONSE", json.toString())
                val blueObject = json?.optJSONObject("blue")
                if (blueObject != null && blueObject.has("value_avg")) {
                    val dollarBlueToPeso = blueObject.getDouble("value_avg")

                    activity?.runOnUiThread {
                        tvDollarBlueToPeso.text = String.format("%.2f", dollarBlueToPeso)
                    }
                } else {
                    Log.e("JSON_PARSING_ERROR", "No se encontró el valor 'value_avg' en el objeto 'blue'")
                }
            }
        })
    }

    private fun fetchBitcoinToDollar() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://cex.io/api/tickers/BTC/USD")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                val json = responseBody?.let { JSONObject(it) }
                val dataArray = json?.optJSONArray("data")
                if (dataArray != null && dataArray.length() > 0) {
                    val dataObject = dataArray.getJSONObject(0)
                    val bitcoinToDollar = dataObject.getString("last").toDouble()

                    activity?.runOnUiThread {
                        tvBitcoinToDollar.text = String.format("%.2f (USD)", bitcoinToDollar)
                    }
                } else {
                    Log.e("JSON_PARSING_ERROR", "No se encontró el array 'data' o estaba vacío")
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
