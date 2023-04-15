package dev.redfox.planetpulse.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.redfox.planetpulse.R
import dev.redfox.planetpulse.databinding.FragmentDashboardBinding
import dev.redfox.planetpulse.databinding.FragmentTipsBinding

class TipsFragment : Fragment() {

    private var _binding: FragmentTipsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTipsBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onResume() {
        super.onResume()

        var uri: Uri = Uri.parse("android.resource://" + "dev.redfox.planetpulse" + "/" + R.raw.tips)
      val params = binding.videoView.layoutParams
        params.height = resources.displayMetrics.heightPixels / 2
        binding.videoView.layoutParams = params
       binding.videoView.setVideoURI(uri)
       binding.videoView.start()
        binding.videoView.setOnPreparedListener { it.isLooping = true }
    }

}