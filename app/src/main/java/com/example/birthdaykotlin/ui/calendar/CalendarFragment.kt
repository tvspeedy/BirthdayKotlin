package com.example.birthdaykotlin.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.birthdaykotlin.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var listView: ListView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val homeViewModel =
        //    ViewModelProvider(this)[CalendarViewModel::class.java]

        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*Text view
        val textView: TextView = binding.textHome

        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        */
        val data = ArrayList<String>()
        data.add("PLACEHOLDER STRING1")
        data.add("PLACEHOLDER STRING2")

        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, data)
        listView = binding.listView
        listView!!.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}