package ru.elron.examplerecycleradapter.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.elron.examplerecycleradapter.databinding.FragmentMainHardBinding

/**
 * A placeholder fragment containing a simple view.
 */
class HardFragment : Fragment() {
    lateinit var binding: FragmentMainHardBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainHardBinding.inflate(inflater)

        binding.openButton.setOnClickListener {  }

        return binding.root
    }

}