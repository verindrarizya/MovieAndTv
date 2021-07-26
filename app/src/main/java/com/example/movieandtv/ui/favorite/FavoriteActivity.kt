package com.example.movieandtv.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieandtv.R
import com.example.movieandtv.databinding.ActivityHomeBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var pagerAdapter: FavSectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()
        setPageAdapter()
    }

    private fun initActionBar() {
        supportActionBar?.apply {
            title = getString(R.string.favorite_page_title)
            elevation = 0f
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setPageAdapter() {
        pagerAdapter = FavSectionsPagerAdapter(this, supportFragmentManager)
        with(binding) {
            viewPager.adapter = pagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }
}