package dev.redfox.planetpulse.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dev.redfox.planetpulse.R
import dev.redfox.planetpulse.databinding.FragmentGptBinding
import dev.redfox.planetpulse.utils.Constants.Companion.API_KEY
import dev.redfox.planetpulse.utils.Constants.Companion.BASE_URL
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException


class GptFragment : Fragment() {

    private var _binding: FragmentGptBinding? = null
    private val binding get() = _binding!!

    val client = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGptBinding.inflate(inflater, container, false)



        binding.btnSend.setOnClickListener {
            if (binding.searchEditText.text!!.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter message", Toast.LENGTH_SHORT).show()
            } else {
                getResponse(binding.searchEditText.text.toString()) {
                    activity?.runOnUiThread {
                        binding.tvResponse.text = it
                    }
                }
            }
        }



        return binding.root
    }

    override fun onResume() {
        super.onResume()

        var uri: Uri =
            Uri.parse("android.resource://" + "dev.redfox.planetpulse" + "/" + R.raw.gptvideo)
        binding.videoView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        binding.videoView.setVideoURI(uri)
        binding.videoView.start()
        binding.videoView.setOnPreparedListener { it.isLooping = true }
    }


    fun getResponse(question: String, callback: (String) -> Unit) {
        val requestBody = """
            {
            "model": "text-davinci-003",
            "prompt": "$question",
            "max_tokens": 250,
            "temperature": 0
            }
        """.trimIndent()

        val request = Request.Builder()
            .url(BASE_URL)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $API_KEY")
            .post(requestBody.toRequestBody("application/json".toMediaTypeOrNull()))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("error", e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                if (body != null) {
                    Log.v("data", body)
                } else {
                    Log.v("data", "empty")
                }
                val jsonObject = JSONObject(body)
                val jsonArray: JSONArray = jsonObject.getJSONArray("choices")
                val textResult = jsonArray.getJSONObject(0).getString("text")
                callback(textResult)
            }

        })

    }

}