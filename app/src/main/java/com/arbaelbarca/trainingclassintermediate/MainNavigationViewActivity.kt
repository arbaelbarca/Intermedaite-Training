package com.arbaelbarca.trainingclassintermediate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainNavigationViewActivity : AppCompatActivity() {

    lateinit var btmNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_navigation_view)

        btmNav = findViewById(R.id.bottomNav)

        btmNav.setOnItemSelectedListener {
            if (it.itemId == R.id.menu_nav_home) {
                loadFragment(HomeFragment())
            } else if (it.itemId == R.id.menu_nav_chat) {
                loadFragment(ChatFragment())
            }

            true
        }
    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }
}