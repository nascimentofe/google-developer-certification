package com.nascimentofe.googledevelopercertification.presentation.ui.toast_snake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.nascimentofe.googledevelopercertification.databinding.FragmentToastSnakeBinding
import com.nascimentofe.googledevelopercertification.util.toast

class ToastSnakeFragment : Fragment() {

    private val binding: FragmentToastSnakeBinding by lazy {
        FragmentToastSnakeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // TOAST
        binding.toast.setOnClickListener {
            val msg = "Uma mensagem pra você!"
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        }

        // SIMPLE SNAKE
        binding.snake.setOnClickListener {
            Snackbar.make(requireView(), "Hello Snake", Snackbar.LENGTH_SHORT).show()
        }

        // SNAKE WITH ACTION
        binding.snakeAction.setOnClickListener {
            Snackbar
                .make(requireView(), "Hello Snake with Action", Snackbar.LENGTH_SHORT)
                .setAction("See the Toast") {
                    toast("I'm from Snake.")
                }
                .show()
        }

        return binding.root
    }
}