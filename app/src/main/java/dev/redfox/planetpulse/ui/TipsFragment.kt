package dev.redfox.planetpulse.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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


        val sharedPreference = activity?.getSharedPreferences("EMAIL", Context.MODE_PRIVATE)
        val email = sharedPreference?.getString("email", null)

        val firebaseDatabase: FirebaseDatabase
        val databaseReference: DatabaseReference
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference =
            firebaseDatabase.getReference("users").child(EncodeString(email!!).toString())

        databaseReference.child("Name").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val name = snapshot.getValue(String::class.java)!!
                    binding.tvName.text = name
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

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

    fun EncodeString(string: String): String? {
        return string.replace(".", ",")
    }

}