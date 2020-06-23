package ru.elron.examplerecycleradapter.ui.hard

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.databinding.ActivityHardBinding

class HardActivity : AppCompatActivity() {
    lateinit var binding: ActivityHardBinding
    val viewModel: HardViewModel by viewModels {
        HardViewModelFactory(application, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hard)

        binding.recyclerView.layoutManager = LinearLayoutManager(this).apply {
            reverseLayout = true
        }
        binding.recyclerView.adapter = viewModel.adapter

        viewModel.fakeMessages.startMessaging()
    }
}
