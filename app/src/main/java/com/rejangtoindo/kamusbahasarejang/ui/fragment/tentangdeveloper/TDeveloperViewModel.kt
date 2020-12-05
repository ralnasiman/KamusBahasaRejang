package com.rejangtoindo.kamusbahasarejang.ui.tentangbahasa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TDeveloperViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Developer Fragment"
    }
    val text: LiveData<String> = _text
}