package com.worldsnas.daggerandviewmodel.second

import androidx.lifecycle.LiveData
import com.worldsnas.daggerandviewmodel.Repository
import javax.inject.Inject

//no need to extend from arch component viewmodel here
// dagger will provide the same instance all the time
@FeatureScope
class SecondViewModel @Inject constructor(
  private val repository: Repository
) {

  fun observeNumber(): LiveData<Int> =
    repository.numberLiveData

  fun increment() = repository.increment()
}