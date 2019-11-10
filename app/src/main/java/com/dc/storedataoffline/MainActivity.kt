package com.dc.storedataoffline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dc.storedataoffline.offlinecrud.OfflineCrudActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        crudButton.setOnClickListener {
            startActivity(Intent(this, OfflineCrudActivity::class.java))
        }
    }
}
