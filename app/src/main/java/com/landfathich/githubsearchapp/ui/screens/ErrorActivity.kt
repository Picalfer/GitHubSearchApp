package com.landfathich.githubsearchapp.ui.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.landfathich.githubsearchapp.R
import com.landfathich.githubsearchapp.ui.screens.main.MainActivity

class ErrorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)

        val textError = findViewById<TextView>(R.id.tv_error)
        val btnReload = findViewById<Button>(R.id.btn_reload)

        val error = intent.getStringExtra("error")
        textError.text = error

        btnReload.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}