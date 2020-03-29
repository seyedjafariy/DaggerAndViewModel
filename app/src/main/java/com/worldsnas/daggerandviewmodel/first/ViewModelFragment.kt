package com.worldsnas.daggerandviewmodel.first

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

abstract class ViewModelFragment<V: ViewModel>: Fragment {

  protected val viewModel: V by ViewModelLazy(
    getViewModelClass(),
    {
      viewModelStore
    },
    {
      ViewModelFactory()
    }
  )

  inner class ViewModelFactory: ViewModelProvider.Factory {
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
      return provideViewModel() as T
    }
  }

  abstract fun provideViewModel(): V

  private fun getViewModelClass(): KClass<V> {
    return this::class.java.methods.find {
      it.name == ::provideViewModel.name
    }?.returnType?.kotlin as KClass<V>
  }

  constructor(@LayoutRes contentLayoutId: Int): super(contentLayoutId)
  constructor(): super()
}

inline fun <reified V: ViewModel> Fragment.componentViewModel(crossinline provider: () -> V): ViewModelLazy<V> {
  return ViewModelLazy(V::class, { viewModelStore }) {
    object: ViewModelProvider.Factory {
      override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        return provider() as T
      }
    }
  }
}
