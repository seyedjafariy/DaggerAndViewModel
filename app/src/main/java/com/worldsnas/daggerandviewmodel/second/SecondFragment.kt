package com.worldsnas.daggerandviewmodel.second

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.worldsnas.daggerandviewmodel.R
import javax.inject.Inject

class SecondFragment: StoreFragment<SecondComponent>(R.layout.fragment_second) {

  @Inject
  lateinit var viewModel: SecondViewModel

  private val extComponent: SecondComponent by storeComponent {
    DaggerSecondComponent.create()
  }

  override fun createComponent(): SecondComponent =
    DaggerSecondComponent.create()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    component.inject(this)
//    extComponent.inject(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    view.findViewById<FloatingActionButton>(R.id.fab_second)
      .setOnClickListener {
        viewModel.increment()
      }

    view.findViewById<Button>(R.id.button_second).setOnClickListener {
      findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }

    val counterTextView = view.findViewById<TextView>(R.id.textview_second)

    viewModel.observeNumber().observe(this, Observer {
      counterTextView.text = "counted: $it"
    })
  }
}
