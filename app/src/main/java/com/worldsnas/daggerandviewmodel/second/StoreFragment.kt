package com.worldsnas.daggerandviewmodel.second

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class StoreFragment<T: Any>: Fragment {
  //you can not provide anything related to the fragment or view
  //otherwise component will leak that reference whenever a configChange happens
  val component: T by lazy {
    val store = ViewModelProvider(
      this,
      StoreViewModel.factory
    ).get(StoreViewModel::class.java)

    if (store.component != null) {
      store.component!! as T
    } else {
      viewModelStore
      store.component = createComponent()
      store.component!! as T
    }
  }

  abstract fun createComponent(): T

  constructor(@LayoutRes contentLayoutId: Int): super(contentLayoutId)
  constructor(): super()
}

private class StoreViewModel: ViewModel() {
  var component: Any? = null

  companion object {
    val factory
      get() = object: ViewModelProvider.Factory {
        override fun <T: ViewModel?> create(modelClass: Class<T>): T =
          StoreViewModel() as T
      }
  }
}

fun <T: Any> Fragment.storeComponent(
  creator : () -> T
) = lazy {
  val store = ViewModelProvider(
    this,
    StoreViewModel.factory
  ).get(StoreViewModel::class.java)

  if (store.component != null) {
    store.component!! as T
  } else {
    viewModelStore
    store.component = creator()
    store.component!! as T
  }
}
