package ru.elron.examplerecycleradapter.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.elron.examplerecycleradapter.databinding.FragmentMainMediumBinding
import ru.elron.examplerecycleradapter.ui.medium.MediumActivity

/**
 * A placeholder fragment containing a simple view.
 */
class MediumFragment : Fragment() {
    lateinit var binding: FragmentMainMediumBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainMediumBinding.inflate(inflater)

        binding.openButton.setOnClickListener { activity?.startActivity(Intent(activity, MediumActivity::class.java)) }

        binding.showEmptyButton.setOnClickListener {

        }

        return binding.root
    }

}