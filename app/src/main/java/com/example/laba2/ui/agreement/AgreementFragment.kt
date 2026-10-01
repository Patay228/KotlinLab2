package com.example.laba2.ui.agreement

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.laba2.R
import com.example.laba2.databinding.FragmentAgreementBinding

class AgreementFragment : Fragment(R.layout.fragment_agreement) {

    private var _binding: FragmentAgreementBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAgreementBinding.bind(view)
        binding.btnBack.setOnClickListener { findNavController().navigateUp() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}