package com.myapp.watermelonwallet.ui.wallets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WalletsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is wallets Fragment"
    }
    val text: LiveData<String> = _text
}