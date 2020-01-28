package ru.elron.examplerecycleradapter.ui.medium

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.elron.examplerecycleradapter.R
import ru.elron.examplerecycleradapter.databinding.ActivityMediumBinding

class MediumActivity : AppCompatActivity() {
    lateinit var binding: ActivityMediumBinding
    lateinit var viewModel: MediumViewModel

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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_medium)
        viewModel = ViewModelProvider(this).get(MediumViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = viewModel.adapter

        viewModel.onLongClickLiveData.observe(this, onLongClickObserver)
    }
}
