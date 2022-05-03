package com.chernybro.wb1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chernybro.wb1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        binding.apply {
            btnService.setOnClickListener {
                startService(Intent(this@MainActivity, ServiceImpl::class.java))
            }
            btnProvider.setOnClickListener {

            }
            btnReceiver.setOnClickListener {

            }
        }
    }
}