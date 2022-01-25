package com.mutualmobile.praxis.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Displaying edge-to-edge
    // Turn off the decor fitting system windows, which allows us to handle insets, including IME animations
    // This app draws behind the system bars, so we want to handle fitting system windows
    WindowCompat.setDecorFitsSystemWindows(window, false)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
  }
}