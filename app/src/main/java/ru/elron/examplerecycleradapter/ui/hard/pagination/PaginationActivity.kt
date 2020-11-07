package ru.elron.examplerecycleradapter.ui.hard.pagination

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.elron.examplerecycleradapter.databinding.ActivityHardPaginationBinding

class PaginationActivity : AppCompatActivity() {
    lateinit var binding: ActivityHardPaginationBinding
    val viewModel: PaginationViewModel by viewModels {
        PaginationViewModelFactory(application, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHardPaginationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.setup(viewModel.adapter, binding.emptyTextView)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.stateLiveData.observe(this) {state ->
            when(state) {
                PaginationViewModel.STATE_LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
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
