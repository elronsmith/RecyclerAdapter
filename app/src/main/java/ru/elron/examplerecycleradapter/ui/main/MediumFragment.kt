package ru.elron.examplerecycleradapter.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.elron.examplerecycleradapter.databinding.FragmentMainMediumBinding

/**
 * A placeholder fragment containing a simple view.
 */
class MediumFragment : Fragment() {
    lateinit var binding: FragmentMainMediumBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainMediumBinding.inflate(inflater)

        binding.openButton.setOnClickListener {  }

        return binding.root
    }

}