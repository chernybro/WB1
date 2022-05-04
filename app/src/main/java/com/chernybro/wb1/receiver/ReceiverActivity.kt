package com.chernybro.wb1.receiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chernybro.wb1.databinding.ActivityReceiverBinding


// Активити под ресивер, по нажатии на кнопку будет отправлено широковещательное сообщение
// Наш ресивер получит его и отобразит на экране

// Пример - приложения, которым нужно знать
// об изменении состояния подключения к сети, о заряд устройства, смене конфигурации
class ReceiverActivity : AppCompatActivity() {

    private var receiver: BroadcastReceiverImpl = BroadcastReceiverImpl()

    private lateinit var binding: ActivityReceiverBinding

    companion object {
        const val MESSAGE = "com.chernybro.action.message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiverBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)


        binding.apply {
            btnReceiver.setOnClickListener {
                registerReceiver(receiver, IntentFilter(MESSAGE))
                val intent = Intent(MESSAGE)
                intent.putExtra("message", "Receiver")
                sendBroadcast(intent)
            }
        }
    }
}