package com.chernybro.wb1.provider

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.chernybro.wb1.databinding.ActivityProviderBinding



// Активити под сервис, по нажатии на кнопку мы в хранилище андроида обратимся к календарю
// и выведем названия первого event в таблице
class ProviderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProviderBinding

    companion object {
        private const val REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProviderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnProvider.setOnClickListener {
                val permission = ContextCompat.checkSelfPermission(this@ProviderActivity, Manifest.permission.READ_CALENDAR)
                if (permission == PackageManager.PERMISSION_GRANTED) {
                    loadContacts()
                } else {
                    ActivityCompat.requestPermissions(
                        this@ProviderActivity,
                        arrayOf(Manifest.permission.READ_CALENDAR),
                        REQUEST_CODE
                    )
                }
            }
        }

    }


    @SuppressLint("Range")
    private fun loadContacts() {
        var eventTitle = "0"
        val uri = contentResolver.query(CalendarContract.Events.CONTENT_URI,null, null, null, null)
        if (uri != null) {
            while (uri.moveToNext()){
                eventTitle = uri.getString(uri.getColumnIndex(CalendarContract.Events.TITLE))

            }
        }
        Toast.makeText(this, "Calendar Event title = $eventTitle", Toast.LENGTH_SHORT)
            .show()
        uri?.close()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadContacts()
            } else {
                Toast.makeText(this, "Требуется установить разрешения", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Требуется установить разрешения", Toast.LENGTH_LONG).show()
        }
    }
}