package com.compscieddy.customtextselection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val text = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)
    Log.d("eddie", "text: $text")
    Toast.makeText(this@MainActivity, "Text: $text", Toast.LENGTH_SHORT).show()
  }
}