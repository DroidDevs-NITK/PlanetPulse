package dev.redfox.planetpulse.ui.question

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dev.redfox.planetpulse.R
import dev.redfox.planetpulse.databinding.FragmentDashboardBinding
import dev.redfox.planetpulse.databinding.FragmentStep1Binding


class Step1Fragment : Fragment() {

    private var _binding: FragmentStep1Binding? = null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStep1Binding.inflate(inflater, container, false)

        val firebaseDatabase: FirebaseDatabase
        firebaseDatabase = FirebaseDatabase.getInstance();

        val sharedPreference = activity?.getSharedPreferences("EMAIL", Context.MODE_PRIVATE)
        val email = sharedPreference?.getString("email", null)
        Log.v("email", email.toString())

        database = Firebase.database.reference


        binding.btnForward1.setOnClickListener {

            binding.radioGroupOptions1.setOnCheckedChangeListener{  group, checkedId ->

                val selectedRadioButton = view?.findViewById<RadioButton>(checkedId)
                val selectedOption = selectedRadioButton?.text

                if(selectedOption == "1") {
                    database.child("users").child(EncodeString(email!!)!!).child("Questions").child("q1").setValue(12)
                } else if(selectedOption == "2"){
                    putData(10)
                } else if(selectedOption == "3"){
                    putData(8)
                } else {
                    putData(6)
                }
            }

            findNavController().navigate(R.id.action_step1Fragment_to_step2Fragment)
        }


        return binding.root
    }
    
    fun putData(points: Int){

    }

    fun EncodeString(string: String): String? {
        return string.replace(".", ",")
    }

}