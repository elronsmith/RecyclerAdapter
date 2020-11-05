package ru.elron.examplerecycleradapter.ui.medium.diffutils

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.elron.examplerecycleradapter.databinding.ActivityMediumDiffutilBinding

class DiffutilsActivity : AppCompatActivity() {
    lateinit var binding: ActivityMediumDiffutilBinding
    val viewModel: DiffutilsViewModel by viewModels {
        DiffutilsViewModelFactory(application, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediumDiffutilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.setup(viewModel.adapter, binding.emptyTextView)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.stateLiveData.observe(this) {state ->
            when(state) {
                DiffutilsViewModel.STATE_LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                DiffutilsViewModel.STATE_COMPLETED -> {
                    binding.progressBar.visibility = View.GONE
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        viewModel.onClickLiveData.observe(this) {name ->
            name?.let {
                Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
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