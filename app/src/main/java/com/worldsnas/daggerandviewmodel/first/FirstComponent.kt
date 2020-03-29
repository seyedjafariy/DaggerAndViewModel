package com.worldsnas.daggerandviewmodel.first

import dagger.Component

@Component
interface FirstComponent {

  fun firstViewModel() : FirstViewModel

  fun injectRestOfDependencies(fragment : FirstFragment)
}