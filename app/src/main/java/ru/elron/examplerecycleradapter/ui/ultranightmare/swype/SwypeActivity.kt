package ru.elron.examplerecycleradapter.ui.ultranightmare.swype

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.elron.examplerecycleradapter.databinding.ActivityUltraNightmareSwypeBinding

/**
 * swipe-to-dismiss
 */
class SwypeActivity : AppCompatActivity() {
    companion object {
        fun start(activity: FragmentActivity) {
            activity.startActivity(Intent(activity, SwypeActivity::class.java))
        }
    }

    lateinit var binding: ActivityUltraNightmareSwypeBinding
    val viewModel: SwypeViewModel by viewModels {
        SwypeViewModelFactory(application, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUltraNightmareSwypeBinding.inflate(layoutInflater)
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
