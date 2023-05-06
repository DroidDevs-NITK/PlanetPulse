package dev.redfox.planetpulse.ui.question

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dev.redfox.planetpulse.R
import dev.redfox.planetpulse.databinding.FragmentStep1Binding
import dev.redfox.planetpulse.databinding.FragmentStep3Binding


class Step3Fragment : Fragment() {

    private var _binding: FragmentStep3Binding? = null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStep3Binding.inflate(inflater, container, false)

        binding.btnForward3.setOnClickListener {

            binding.radioGroupOptions3.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.card_radio_button_31 -> putData(10)
                    R.id.card_radio_button_32 -> putData(4)
                    R.id.card_radio_button_33 -> putData(2)
                    else -> {
                        Toast.makeText(context, "Select an option please", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            findNavController().navigate(R.id.action_step3Fragment_to_step4Fragment)
        }

        return binding.root
    }

    fun putData(points: Int) {
        val firebaseDatabase: FirebaseDatabase
        firebaseDatabase = FirebaseDatabase.getInstance();

        val sharedPreference = activity?.getSharedPreferences("EMAIL", Context.MODE_PRIVATE)
        val email = sharedPreference?.getString("email", null)

        database =
            firebaseDatabase.getReference("users").child(EncodeString(email!!).toString())

        database.child(EncodeString(email).toString()).child("Questions").child("q3")
            .setValue(points)
    }

    fun EncodeString(string: String): String? {
        return string.replace(".", ",")
    }

}