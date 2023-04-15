package dev.redfox.planetpulse.ui.question

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.redfox.planetpulse.R
import dev.redfox.planetpulse.databinding.FragmentStep1Binding
import dev.redfox.planetpulse.databinding.FragmentStep4Binding


class Step4Fragment : Fragment() {


    private var _binding: FragmentStep4Binding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStep4Binding.inflate(inflater, container, false)

        binding.btnForward4.setOnClickListener {
            findNavController().navigate(R.id.action_step4Fragment_to_step5Fragment)
        }
        return binding.root
    }


}