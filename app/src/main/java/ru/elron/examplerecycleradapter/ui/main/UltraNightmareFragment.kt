package ru.elron.examplerecycleradapter.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.elron.examplerecycleradapter.databinding.FragmentMainUltraNightmareBinding

/**
 * A placeholder fragment containing a simple view.
 */
class UltraNightmareFragment : Fragment() {
    lateinit var binding: FragmentMainUltraNightmareBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainUltraNightmareBinding.inflate(inflater)

        binding.swypeToDeleteButton.setOnClickListener {

        }

        binding.longClickToMoveButton.setOnClickListener {

        }

        return binding.root
    }

}