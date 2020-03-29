package com.worldsnas.daggerandviewmodel.first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.worldsnas.daggerandviewmodel.R
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment: ViewModelFragment<FirstViewModel>(R.layout.fragment_first) {

  lateinit var component: FirstComponent

  val secondViewModel: FirstViewModel by componentViewModel {
    component.firstViewModel()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    component = DaggerFirstComponent.create()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    component.injectRestOfDependencies(this)

    view.findViewById<Button>(R.id.button_first).setOnClickListener {
      findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }
    view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
      viewModel.increment()
    }

    val counterTextView = view.findViewById<TextView>(R.id.textview_first)

    viewModel.observeNumber().observe(this, Observer {
      counterTextView.text = "counted: $it"
    })
  }

  override fun provideViewModel(): FirstViewModel =
    component.firstViewModel()
}
