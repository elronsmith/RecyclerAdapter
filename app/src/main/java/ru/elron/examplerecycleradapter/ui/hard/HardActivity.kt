package ru.elron.examplerecycleradapter.ui.hard

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.elron.examplerecycleradapter.databinding.ActivityHardBinding

class HardActivity : AppCompatActivity() {
    lateinit var binding: ActivityHardBinding
    val viewModel: HardViewModel by viewModels {
        HardViewModelFactory(application, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recyclerView.layoutManager = LinearLayoutManager(this).apply {
            reverseLayout = true
        }
        binding.recyclerView.adapter = viewModel.adapter

        viewModel.fakeMessages.startMessaging()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
