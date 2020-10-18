package ru.elron.examplerecycleradapter.ui.medium

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.databinding.ActivityMediumBinding

class MediumActivity : AppCompatActivity() {
    lateinit var binding: ActivityMediumBinding
    val viewModel: MediumViewModel by viewModels {
        MediumViewModelFactory(application, this)
    }

    val onLongClickObserver = object : Observer<Pair<Int, MediumObservable>> {
        override fun onChanged(p: Pair<Int, MediumObservable>?) {
            p?.let {
                AlertDialog.Builder(this@MediumActivity)
                    .setTitle(p.second.name)
                    .setItems(R.array.menu_medium) {dialog, which -> when(which) {
                        0 -> viewModel.addItem(p.first)
                        1 -> viewModel.deleteItem(p.first)
                    } }
                    .create().show()
                viewModel.onLongClickLiveData.postValue(null)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = viewModel.adapter

        viewModel.onLongClickLiveData.observe(this, onLongClickObserver)
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
