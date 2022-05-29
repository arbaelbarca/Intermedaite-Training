package com.arbaelbarca.trainingclassintermediate

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LocalChaceActivity : AppCompatActivity() {

    lateinit var getEdName: EditText
    lateinit var getEdEmail: EditText
    lateinit var btnSave: Button

    var sharePreferences: SharedPreferences? = null
    var editorPreferences: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_chace)

        getEdName = findViewById(R.id.edNameLocalChace)
        getEdEmail = findViewById(R.id.edEmailLocalChace)
        btnSave = findViewById(R.id.btnSaveLocalChace)

        initialSharePreferences()
        initOnClick()
    }

    private fun initOnClick() {
        btnSave.setOnClickListener {
            val getName = getEdName.text.toString()
            val getEmail = getEdEmail.text.toString()

            savingData(getName, getEmail)
        }
    }

    fun savingData(name: String, email: String) {
        editorPreferences?.putString("name", name)
        editorPreferences?.putString("email",email)
        editorPreferences?.apply()

        val getDataNameLocal = sharePreferences?.getString("name","")
        Toast.makeText(this, "Get data local $getDataNameLocal", Toast.LENGTH_SHORT).show()

    }

    fun initialSharePreferences() {
        sharePreferences = getSharedPreferences("biodata", MODE_PRIVATE)
        editorPreferences = sharePreferences?.edit()
    }
}