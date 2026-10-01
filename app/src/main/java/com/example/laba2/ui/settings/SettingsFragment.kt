package com.example.laba2.ui.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.laba2.R
import com.example.laba2.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)

        binding.btnBack.setOnClickListener { findNavController().navigateUp() }

        binding.rowShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, getString(R.string.share_link))
            }
            startActivity(Intent.createChooser(intent, getString(R.string.settings_share)))
        }

        binding.rowSupport.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.support_email)))
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.support_subject))
                putExtra(Intent.EXTRA_TEXT, getString(R.string.support_body))
            }
            startActivity(intent)
        }

        binding.rowAgreement.setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_agreement)
        }

        binding.switchDark.setOnCheckedChangeListener { _, _ -> /* опционально */ }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}