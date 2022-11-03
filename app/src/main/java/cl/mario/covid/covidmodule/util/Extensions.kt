package cl.mario.covid.covidmodule.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> T?.orElse(value: T) = this ?: value

fun <T> MutableLiveData<T>.toLiveData() = this as LiveData<T>