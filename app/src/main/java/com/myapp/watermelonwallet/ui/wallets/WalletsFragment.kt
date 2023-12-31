package com.myapp.watermelonwallet.ui.wallets

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.myapp.watermelonwallet.R
import com.myapp.watermelonwallet.databinding.FragmentWalletsBinding
import com.myapp.watermelonwallet.ui.WalletActivity

class WalletsFragment : Fragment() {

    private var _binding: FragmentWalletsBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var walletAdapter: WalletAdapter
    private var wallets: MutableList<Wallet> = mutableListOf() // Initialize an empty list

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalletsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        wallets = getWalletsFromSharedPreferences() // Load wallets from SharedPreferences

        walletAdapter = WalletAdapter(wallets)
        recyclerView.adapter = walletAdapter

        val textView: TextView = binding.textHome
        textView.text = "Mis Billeteras"

        return root
    }

    private fun getWalletsFromSharedPreferences(): MutableList<Wallet> {
        val prefs = requireContext().getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE)
        val walletsString = prefs.getString("wallets", null)

        return if (walletsString != null) {
            Gson().fromJson(walletsString, object : TypeToken<MutableList<Wallet>>() {}.type)
        } else {
            mutableListOf()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.wallets_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add_wallet -> {
                val intent = Intent(requireContext(), WalletActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
