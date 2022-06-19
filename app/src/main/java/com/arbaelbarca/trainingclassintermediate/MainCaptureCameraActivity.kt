package com.arbaelbarca.trainingclassintermediate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainCaptureCameraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_capture_camera)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, CaptureCameraFragment())
            .commit()
    }
}