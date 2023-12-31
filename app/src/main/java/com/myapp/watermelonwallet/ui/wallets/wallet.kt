package com.myapp.watermelonwallet.ui.wallets

data class Wallet(
    var id: Long = 0,
    val walletName: String,
    val walletAmount: Float,
    val walletCurrency: String
) {
    companion object {
        private var nextId: Long = 1

        fun getNextId(): Long {
            return nextId++
        }
    }

    constructor(walletName: String, walletAmount: Float, walletCurrency: String) : this(
        id = getNextId(),
        walletName = walletName,
        walletAmount = walletAmount,
        walletCurrency = walletCurrency
    )
}
