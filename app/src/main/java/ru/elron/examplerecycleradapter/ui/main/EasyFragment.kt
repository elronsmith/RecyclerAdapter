package ru.elron.examplerecycleradapter.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.elron.examplerecycleradapter.databinding.FragmentMainEasyBinding
import ru.elron.examplerecycleradapter.ui.easy.EasyActivity

class EasyFragment : Fragment() {
    lateinit var binding: FragmentMainEasyBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainEasyBinding.inflate(inflater)

        binding.openButton.setOnClickListener { activity?.startActivity(Intent(activity, EasyActivity::class.java)) }

        return binding.root
    }
}