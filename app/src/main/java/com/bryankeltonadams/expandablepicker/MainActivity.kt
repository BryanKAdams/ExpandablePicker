package com.bryankeltonadams.expandablepicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var button: Button? = null
    fun onClick(view: View) {
        openRecyclerActivity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.recyclerButton)
    }

    private fun openRecyclerActivity() {
        val intent = Intent(this, RecyclerActivity::class.java)
        startActivity(intent)
    }
}