package com.chernybro.wb1

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chernybro.wb1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var receiver: BroadcastReceiverImpl = BroadcastReceiverImpl()

    private lateinit var binding: ActivityMainBinding

    companion object {
        const val MESSAGE = "com.chernybro.action.message"
    }

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
                registerReceiver(receiver, IntentFilter(MESSAGE))
                val intent = Intent(MESSAGE)
                intent.putExtra("message", "Receiver")
                sendBroadcast(intent)
            }
        }
    }
}