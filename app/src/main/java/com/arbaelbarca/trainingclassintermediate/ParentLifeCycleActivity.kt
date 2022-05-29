package com.arbaelbarca.trainingclassintermediate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ParentLifeCycleActivity : AppCompatActivity() {

    var getName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_life_cycle)

        getName = intent.getStringExtra("name").toString()

        initSetFragment()
    }

    fun initSetFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayoutFragment, LifeCycleFragment(getName))
            .commit()
    }
}