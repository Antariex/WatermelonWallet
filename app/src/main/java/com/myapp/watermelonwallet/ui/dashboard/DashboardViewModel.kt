package com.myapp.watermelonwallet.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "El total de tus tenencias es de: $"
    }
    val text: LiveData<String> = _text
}