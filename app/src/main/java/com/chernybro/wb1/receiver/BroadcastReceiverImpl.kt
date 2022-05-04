package com.chernybro.wb1.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

// Получаем сообщение, по интент фильтру, который задали в ReceiverActivity
class BroadcastReceiverImpl : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("TAG", "onReceive: ")
        Toast
            .makeText(
                context,
                "Received: " + intent?.getStringExtra("message"),
                Toast.LENGTH_LONG)
            .show()
    }
}