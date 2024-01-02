package com.myapp.watermelonwallet.ui.wallets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.myapp.watermelonwallet.R

class WalletAdapter(private val wallets: List<Wallet>) :
    RecyclerView.Adapter<WalletAdapter.WalletViewHolder>() {

    class WalletViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val walletName: TextView = itemView.findViewById(R.id.walletNameTextView)
        val walletAmount: TextView = itemView.findViewById(R.id.walletAmountTextView)
        val walletCurrency: TextView = itemView.findViewById(R.id.walletCurrencyTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wallet, parent, false)
        return WalletViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        val wallet = wallets[position]

        holder.walletName.text = wallet.walletName
        holder.walletAmount.text = wallet.walletAmount.toString()
        holder.walletCurrency.text = wallet.walletCurrency

        // Cambiar el color de fondo para wallets impares
        if (position % 2 != 0) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.colorOddWalletBackground))
        } else {
            // Restablecer el fondo predeterminado para wallets pares
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, android.R.color.white))
        }
    }

    override fun getItemCount(): Int {
        return wallets.size
    }
}
