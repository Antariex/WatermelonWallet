package com.myapp.watermelonwallet.ui.wallets

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapp.watermelonwallet.R
import com.myapp.watermelonwallet.databinding.FragmentWalletsBinding
import com.myapp.watermelonwallet.ui.WalletActivity

class WalletsFragment : Fragment() {

    private var _binding: FragmentWalletsBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var walletAdapter: WalletAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalletsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Ejemplo de billeteras (deberías obtenerlas de SharedPreferences)
        val wallets = listOf(
            Wallet(1, "Billetera 1", 100.0f, "USD"),
            Wallet(2, "Billetera 2", 50.0f, "EUR")
            // Agrega tus billeteras aquí
        )

        walletAdapter = WalletAdapter(wallets)
        recyclerView.adapter = walletAdapter

        val textView: TextView = binding.textHome
        textView.text = "Mis Billeteras"

        return root
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
