package com.worldsnas.daggerandviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class Repository @Inject constructor(
) {

  var number = 0

  private val _numberLiveData = MutableLiveData<Int>()
  val numberLiveData: LiveData<Int>
    get() = _numberLiveData

  fun increment() {
    _numberLiveData.postValue(++number)
  }
}
