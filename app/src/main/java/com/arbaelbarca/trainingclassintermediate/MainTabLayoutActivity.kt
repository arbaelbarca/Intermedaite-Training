package com.arbaelbarca.trainingclassintermediate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main_tab_layout.*

class MainTabLayoutActivity : AppCompatActivity() {

    lateinit var mTabLayout: TabLayout
    lateinit var mViewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tab_layout)

        mTabLayout = findViewById(R.id.tabLayout)
        mViewPager = findViewById(R.id.viewPager)

        initTabLayout()
    }

    fun initTabLayout() {
        val arraysTab = arrayOf(
            "Home",
            "Chat",
            "Profile",
            "Chat",
            "Profile",
            "Chat",
            "Profile"
        )

        val adapterViewPager = AdapterViewPager(
            supportFragmentManager,
            lifecycle
        )

        mViewPager.adapter = adapterViewPager

        TabLayoutMediator(
            mTabLayout,
            mViewPager,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.text = arraysTab[position]
                    if (position == 0) {
                        tab.setIcon(R.drawable.ic_baseline_home_24)
                    }
                }

            }).attach()
    }
}