package com.arbaelbarca.trainingclassintermediate

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterViewPager(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 7
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return HomeFragment()
        } else if (position == 1) {
            return ChatFragment()
        } else {
            return HomeFragment()
        }
    }
}