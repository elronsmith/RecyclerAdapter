package ru.elron.examplerecycleradapter.ui.hard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.databinding.ActivityHardBinding

class HardActivity : AppCompatActivity() {
    lateinit var binding: ActivityHardBinding
    lateinit var viewModel: HardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hard)
        viewModel = ViewModelProvider(this).get(HardViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this).apply {
            reverseLayout = true
        }
        binding.recyclerView.adapter = viewModel.adapter

        viewModel.fakeMessages.startMessaging()
    }
}
