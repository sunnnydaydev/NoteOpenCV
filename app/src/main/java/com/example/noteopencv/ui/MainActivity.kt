package com.example.noteopencv.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noteopencv.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSimpleDemo.setOnClickListener {
            startActivity(Intent(this,SimpleDemoActivity::class.java))
        }
        btnColorChange.setOnClickListener {
            startActivity(Intent(this,ColorChangeActivity::class.java))
        }
    }
}