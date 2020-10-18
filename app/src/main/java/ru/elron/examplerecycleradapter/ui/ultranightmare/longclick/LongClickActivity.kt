package ru.elron.examplerecycleradapter.ui.ultranightmare.longclick

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.elron.examplerecycleradapter.databinding.ActivityUltraNightmareMoveBinding

/**
 * drag & drop
 */
class LongClickActivity : AppCompatActivity() {
    companion object {
        fun start(activity: FragmentActivity) {
            activity.startActivity(Intent(activity, LongClickActivity::class.java))
        }
    }

    lateinit var binding: ActivityUltraNightmareMoveBinding
    val viewModel: LongClickViewModel by viewModels {
        LongClickViewModelFactory(application, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUltraNightmareMoveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = viewModel.adapter

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        viewModel.setupDragAndDrop(binding.recyclerView)
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
