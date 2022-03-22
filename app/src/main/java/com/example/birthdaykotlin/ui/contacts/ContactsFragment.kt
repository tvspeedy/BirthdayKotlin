package com.example.birthdaykotlin.ui.contacts

import android.Manifest
import android.R
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.birthdaykotlin.databinding.FragmentContactsBinding
import com.google.android.material.snackbar.Snackbar

class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this)[ContactsViewModel::class.java]

        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textContacts
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        //Request Permission to read in Contacts from phone
        requestPermission()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                "Has Permission for Contacts",
                Snackbar.LENGTH_LONG
            ).show()
        } else {
            Snackbar.make(
                requireActivity().findViewById(R.id.content),
                "Does not have permission for Contacts", Snackbar.LENGTH_LONG
            ).show()
            registerForActivityResult(
                RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                    Snackbar.make(
                        requireActivity().findViewById(R.id.content),
                        "PERMISSION GRANTED", Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    Snackbar.make(
                        requireActivity().findViewById(R.id.content),
                        "PERMISSION NOT GRANTED", Snackbar.LENGTH_LONG
                    ).show()
                }
            }.launch(Manifest.permission.READ_CONTACTS)
        }

    }
}