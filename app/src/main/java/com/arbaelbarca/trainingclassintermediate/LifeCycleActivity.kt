package com.arbaelbarca.trainingclassintermediate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class LifeCycleActivity : AppCompatActivity() {

    var getName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life_cycle)

        getName = intent.getStringExtra("name").toString()

        Toast.makeText(this, "Ini getData Lifecyele $getName", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Ini ON BackPresed", Toast.LENGTH_SHORT).show()
    }
}