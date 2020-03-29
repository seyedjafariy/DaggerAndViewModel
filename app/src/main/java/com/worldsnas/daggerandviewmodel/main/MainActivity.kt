package com.worldsnas.daggerandviewmodel.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import com.worldsnas.daggerandviewmodel.R

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

//  val viewModel : MainViewModel by viewModels(MainViewModel.factory)

  lateinit var mainViewModel: MainViewModel

//  override fun onRetainNonConfigurationInstance(): Any {
//    return super.onRetainNonConfigurationInstance()
//  }
//
//  override fun getLastNonConfigurationInstance(): Any? {
//    return super.getLastNonConfigurationInstance()
//  }

  override fun onRetainCustomNonConfigurationInstance(): Any? {
    return super.onRetainCustomNonConfigurationInstance()
  }


  override fun getLastCustomNonConfigurationInstance(): Any? {
    return super.getLastCustomNonConfigurationInstance()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mainViewModel = ViewModelProvider(this, MainViewModel.factory).get(MainViewModel::class.java)

    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    return when (item.itemId) {
      R.id.action_settings -> true
      else                 -> super.onOptionsItemSelected(item)
    }
  }
}
