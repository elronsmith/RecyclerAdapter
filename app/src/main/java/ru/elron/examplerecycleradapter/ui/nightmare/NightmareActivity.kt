package ru.elron.examplerecycleradapter.ui.nightmare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.databinding.ActivityNightmareBinding

class NightmareActivity : AppCompatActivity() {
    lateinit var binding: ActivityNightmareBinding
    lateinit var viewModel: NightmareViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nightmare)
        viewModel = ViewModelProvider(this).get(NightmareViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = viewModel.adapter
    }
}
