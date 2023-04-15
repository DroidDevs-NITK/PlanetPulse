package dev.redfox.planetpulse

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import dev.redfox.planetpulse.databinding.ActivityGetStartedBinding
import dev.redfox.planetpulse.databinding.ActivityMainBinding

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityGetStartedBinding

class GetStartedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var uri: Uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.introvideo)
        binding.videoView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        binding.videoView.setVideoURI(uri)
        binding.videoView.start()
        binding.videoView.setOnPreparedListener { it.isLooping = true }

        binding.btnGetStarted.setOnClickListener {
            val intent = Intent(this, QuestionsActivity::class.java)
            startActivity(intent)
        }
    }
}