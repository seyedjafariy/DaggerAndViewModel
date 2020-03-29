package com.worldsnas.daggerandviewmodel.second

import dagger.Component

@Component
@FeatureScope
interface SecondComponent {

  fun inject(fragment: SecondFragment)
}