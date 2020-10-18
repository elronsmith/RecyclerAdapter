package ru.elron.examplerecycleradapter.ui.medium.empty

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.elron.examplerecycleradapter.databinding.ActivityMediumEmptyBinding

class EmptyActivity : AppCompatActivity() {
    lateinit var binding: ActivityMediumEmptyBinding
    val viewModel: EmptyViewModel by viewModels {
        EmptyViewModelFactory(application, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediumEmptyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.setup(viewModel.adapter, binding.emptyTextView)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.stateLiveData.observe(this) {state ->
            when(state) {
                EmptyViewModel.STATE_LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                EmptyViewModel.STATE_COMPLETED -> {
                    binding.progressBar.visibility = View.GONE
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        viewModel.loadContent()
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
