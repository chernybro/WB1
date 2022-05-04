package com.chernybro.wb1.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import kotlin.random.Random

// В сервисе выполняется сортировка пузырьком на 30000 элементов,
// этот долгий процесс запускаем в отдельном треде

// Пример использования - музыкальные плееры
class ServiceImpl : Service() {

    companion object {
        private const val TAG = "SERVICE"
    }

    override fun onCreate() {
        Log.d(TAG, "Служба создана")
    }

    private fun startLongProcess() {
        Handler().post(
            Runnable {
                bubbleSort(
                    IntArray(
                        size = 30000,
                        init = { Random.nextInt() }
                    ))
                Toast.makeText(this,
                    "Сервис закончил работу, сортировка закончена",
                    Toast.LENGTH_SHORT)
                    .show()
                stopSelf()
            }
        )
    }

    private fun bubbleSort(arr: IntArray): IntArray {
        var swap = true
        while (swap) {
            swap = false
            for (i in 0 until arr.size - 1) {
                if (arr[i] > arr[i + 1]) {
                    val temp = arr[i]
                    arr[i] = arr[i + 1]
                    arr[i + 1] = temp
                    swap = true
                }
            }
        }
        return arr
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Сервис начал работу", Toast.LENGTH_SHORT)
            .show()
        startLongProcess()
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        Log.d(TAG, "Служба уничтожена")
    }
}