package ru.elron.examplerecycleradapter.ui.easy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.databinding.ActivityEasyBinding

class EasyActivity : AppCompatActivity() {
    lateinit var binding: ActivityEasyBinding
    lateinit var viewModel: EasyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_easy)
        viewModel = ViewModelProvider(this).get(EasyViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = viewModel.adapter

    }
}
