package com.shuike.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager2 = findViewById<ViewPager2>(R.id.viewpager)
        val bgView = findViewById<BackgroundView>(R.id.bg)
        viewPager2.apply {
            adapter = Adapter()
            offscreenPageLimit = 3

            val pageTransformer = CompositePageTransformer()
            pageTransformer.addTransformer(MarginPageTransformer(16.dp))
            setPageTransformer(pageTransformer)

            registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int,
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                    bgView.onPageScrolled(positionOffset, position)
                }
            })

            val recyclerView = getChildAt(0) as RecyclerView
            val padding = 50.dp
            if (orientation == ViewPager2.ORIENTATION_VERTICAL) {
                recyclerView.setPadding(0, padding, 0, padding)
            } else {
                recyclerView.setPadding(padding, 0, padding, 0)
            }
            recyclerView.clipToPadding = false
            recyclerView.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }

    private inner class Adapter : FragmentStateAdapter(this) {
        override fun getItemCount(): Int = 5

        override fun createFragment(position: Int): Fragment = ChildFragment()
    }

    class ChildFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
        ): View? {
            return LayoutInflater.from(requireContext())
                .inflate(R.layout.item_fragment, container, false)
        }
    }
}