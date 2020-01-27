package ru.elron.examplerecycleradapter.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.elron.examplerecycleradapter.databinding.FragmentMainNightmareBinding

/**
 * A placeholder fragment containing a simple view.
 */
class NightmareFragment : Fragment() {
    lateinit var binding: FragmentMainNightmareBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainNightmareBinding.inflate(inflater)

        binding.openButton.setOnClickListener {  }

        return binding.root
    }

}