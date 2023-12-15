package com.myapp.watermelonwallet.ui.wallets

data class Wallet(
    val id: Long = 0,
    val walletName: String,
    val walletAmount: Float,
    val walletCurrency: String
)
