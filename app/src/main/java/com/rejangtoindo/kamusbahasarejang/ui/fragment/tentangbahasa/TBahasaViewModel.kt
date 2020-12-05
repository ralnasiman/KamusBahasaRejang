package com.rejangtoindo.kamusbahasarejang.ui.fragment.tentangbahasa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TBahasaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is tentang bahasa Fragment"
    }
    val text: LiveData<String> = _text
}