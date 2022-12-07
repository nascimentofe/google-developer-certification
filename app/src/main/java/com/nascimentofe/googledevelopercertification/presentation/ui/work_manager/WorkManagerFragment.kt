package com.nascimentofe.googledevelopercertification.presentation.ui.work_manager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nascimentofe.googledevelopercertification.R
import com.nascimentofe.googledevelopercertification.databinding.FragmentWorkManagerBinding

class WorkManagerFragment : Fragment() {

    private val binding: FragmentWorkManagerBinding by lazy {
        FragmentWorkManagerBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        return binding.root
    }
}