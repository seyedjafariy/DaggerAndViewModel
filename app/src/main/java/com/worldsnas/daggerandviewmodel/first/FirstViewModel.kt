package com.worldsnas.daggerandviewmodel.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.worldsnas.daggerandviewmodel.Repository
import javax.inject.Inject

class FirstViewModel @Inject constructor(
  private val repository: Repository
): ViewModel() {

  fun observeNumber(): LiveData<Int> =
    repository.numberLiveData

  fun increment() = repository.increment()
}