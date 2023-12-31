package com.myapp.watermelonwallet.ui.wallets

data class Wallet(
    var id: Int = 0,
    val walletName: String,
    val walletAmount: Float,
    val walletCurrency: String
) {
    constructor(walletName: String, walletAmount: Float, walletCurrency: String) : this(
        id = generateUniqueId(),
        walletName = walletName,
        walletAmount = walletAmount,
        walletCurrency = walletCurrency
    )

    companion object {
        private var uniqueIdCounter = 0

        private fun generateUniqueId(): Int {
            return uniqueIdCounter++
        }
    }
}
