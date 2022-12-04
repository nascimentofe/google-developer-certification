package com.nascimentofe.googledevelopercertification.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.nascimentofe.googledevelopercertification.R
import com.nascimentofe.googledevelopercertification.databinding.FragmentMainBinding
import com.nascimentofe.googledevelopercertification.util.navTo
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel : MainViewModel by viewModel()
    private val binding: FragmentMainBinding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        initBinding()
        setupComponents()

        return binding.root
    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupComponents() {
        binding.btnToastSnakeFragment.setOnClickListener { navTo(R.id.main_to_toast_snake_fragment) }
        binding.btnNotification.setOnClickListener { navTo(R.id.main_to_notification_fragment) }
    }
}