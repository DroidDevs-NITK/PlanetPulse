package dev.redfox.planetpulse.ui.question

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.redfox.planetpulse.R
import dev.redfox.planetpulse.databinding.FragmentDashboardBinding
import dev.redfox.planetpulse.databinding.FragmentStep1Binding


class Step1Fragment : Fragment() {

    private var _binding: FragmentStep1Binding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStep1Binding.inflate(inflater, container, false)

        binding.btnForward1.setOnClickListener {
            findNavController().navigate(R.id.action_step1Fragment_to_step2Fragment)
        }


        return binding.root
    }

}