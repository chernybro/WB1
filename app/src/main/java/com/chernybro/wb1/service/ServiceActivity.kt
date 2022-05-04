package com.chernybro.wb1.service

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chernybro.wb1.databinding.ActivityServiceBinding

// Активити под сервис, по нажатии на кнопку сервис будет запущен
class ServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)



        binding.apply {
            btnService.setOnClickListener {
                startService(Intent(this@ServiceActivity, ServiceImpl::class.java))
            }
        }
    }
}