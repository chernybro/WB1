package com.chernybro.wb1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chernybro.wb1.databinding.ActivityMainBinding
import com.chernybro.wb1.provider.ProviderActivity
import com.chernybro.wb1.receiver.ReceiverActivity
import com.chernybro.wb1.service.ServiceActivity

// Наш первый компонент
// Здесь будут вызываться остальные компоненты через соответствующие кнопки
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)



        binding.apply {
            btnService.setOnClickListener {
                startActivity(Intent(this@MainActivity, ServiceActivity::class.java))
            }
            btnProvider.setOnClickListener {
                startActivity(Intent(this@MainActivity, ProviderActivity::class.java))
            }
            btnReceiver.setOnClickListener {
                startActivity(Intent(this@MainActivity, ReceiverActivity::class.java))
            }
        }
    }
}