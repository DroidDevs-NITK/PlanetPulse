package dev.redfox.planetpulse.ui

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.redfox.planetpulse.R
import dev.redfox.planetpulse.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.indiaProgressBar.setProgress(50, true)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        var uri: Uri =
            Uri.parse("android.resource://" + "dev.redfox.planetpulse" + "/" + R.raw.videodashboard)
        binding.dashboardVideo.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        binding.dashboardVideo.setVideoURI(uri)
        binding.dashboardVideo.start()
        binding.dashboardVideo.setOnPreparedListener { it.isLooping = true }

    }

}