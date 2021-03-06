package ru.elron.examplerecycleradapter.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewPager.adapter = SectionsPagerAdapter(supportFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}
