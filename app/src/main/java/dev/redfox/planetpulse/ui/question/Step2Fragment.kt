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
import dev.redfox.planetpulse.databinding.FragmentStep2Binding


class Step2Fragment : Fragment() {

    private var _binding: FragmentStep2Binding? = null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStep2Binding.inflate(inflater, container, false)

        binding.btnForward2.setOnClickListener {

            binding.radioGroupOptions2.setOnCheckedChangeListener{  group, checkedId ->
                when(checkedId){
                    R.id.card_radio_button_21 -> putData(10)
                    R.id.card_radio_button_22 -> putData(7)
                    R.id.card_radio_button_23 -> putData(4)
                    R.id.card_radio_button_24 -> putData(2)
                    else -> {
                        Toast.makeText(context, "Select an option please", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            findNavController().navigate(R.id.action_step2Fragment_to_step3Fragment)
        }
        return binding.root
    }

    fun putData(points: Int){
        val firebaseDatabase: FirebaseDatabase
        firebaseDatabase = FirebaseDatabase.getInstance();

        val sharedPreference = activity?.getSharedPreferences("EMAIL", Context.MODE_PRIVATE)
        val email = sharedPreference?.getString("email", null)

        database =  firebaseDatabase.getReference("users").child(EncodeString(email!!).toString())

        database.child(EncodeString(email).toString()).child("Questions").child("q2").setValue(points)
    }

    fun EncodeString(string: String): String? {
        return string.replace(".", ",")
    }


}