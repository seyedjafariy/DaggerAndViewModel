package com.worldsnas.daggerandviewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.worldsnas.daggerandviewmodel.Repository

class MainViewModel(
  val repository: Repository
): ViewModel() {

  companion object {
    val factory: ViewModelProvider.Factory
      get() = object: ViewModelProvider.Factory {
        override fun <T: ViewModel?> create(modelClass: Class<T>): T =
          if (modelClass.isAssignableFrom(MainViewModel::class.java))
            MainViewModel(Repository()) as T
          else
            null as T
      }
  }
}