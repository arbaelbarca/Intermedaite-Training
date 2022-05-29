package com.arbaelbarca.trainingclassintermediate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_all_menu_button.*

class AllMenuButtonActivity : AppCompatActivity() {

    lateinit var btnNavPage: Button
    lateinit var btnTabLayout: Button
    lateinit var btnScroolView: Button
    lateinit var btnNavDrawer: Button
    lateinit var btnLifeCycleActivity: Button
    lateinit var btnLifeCycleFragment: Button
    lateinit var btnToLocalChace: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_menu_button)

        btnNavPage = findViewById(R.id.btnNavView)
        btnTabLayout = findViewById(R.id.btnTanLayoutView)
        btnScroolView = findViewById(R.id.btnScroolView)
        btnNavDrawer = findViewById(R.id.btnNavDrawer)
        btnLifeCycleActivity = findViewById(R.id.btnActionActivity)
        btnLifeCycleFragment = findViewById(R.id.btnActionFragmnet)
        btnToLocalChace = findViewById(R.id.btnActivityLocalChace)

        initOnClick()
    }

    fun initOnClick() {
        btnNavPage.setOnClickListener {
            val intent = Intent(this, MainNavigationViewActivity::class.java)
            startActivity(intent)
        }

        btnTabLayout.setOnClickListener {
            val intent = Intent(this, MainTabLayoutActivity::class.java)
            startActivity(intent)
        }

        btnScroolView.setOnClickListener {
            val intent = Intent(this, ScrolllViewActivity::class.java)
            startActivity(intent)
        }

        btnNavDrawer.setOnClickListener {
            val intent = Intent(this, MainNavigationDrawerActivity::class.java)
            startActivity(intent)
        }

        btnLifeCycleActivity.setOnClickListener {
            val intent = Intent(this, LifeCycleActivity::class.java)
            startActivity(intent)
        }

        btnLifeCycleFragment.setOnClickListener {
            val intent = Intent(this, ParentLifeCycleActivity::class.java)
            intent.putExtra("name", "arba")
            startActivity(intent)
        }

        btnToLocalChace.setOnClickListener {
            val intent = Intent(this, LocalChaceActivity::class.java)
            startActivity(intent)
        }
    }
}