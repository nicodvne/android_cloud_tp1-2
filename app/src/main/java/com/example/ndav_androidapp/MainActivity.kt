package com.example.ndav_androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickRedirectTP1(view: View) {
        val intentToRecycler = Intent(this, RecyclerViewActivity::class.java);
        startActivity(intentToRecycler);
    }

}