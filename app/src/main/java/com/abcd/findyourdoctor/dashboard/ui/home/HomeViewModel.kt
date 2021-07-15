package com.abcd.findyourdoctor.dashboard.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Click Here to go to Doctor List Screen"
    }
    val text: LiveData<String> = _text
}