package dev.redfox.planetpulse.ui.question

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dev.redfox.planetpulse.MainActivity
import dev.redfox.planetpulse.R
import dev.redfox.planetpulse.databinding.FragmentStep1Binding
import dev.redfox.planetpulse.databinding.FragmentStep5Binding


class Step5Fragment : Fragment() {


    private var _binding: FragmentStep5Binding? = null
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStep5Binding.inflate(inflater, container, false)

        var q1 = 0;
        var q2 = 0;
        var q3 = 0;
        var q4 = 0;
        var q5 = 0;
        var total = 0;

        binding.btnForward5.setOnClickListener {

            binding.radioGroupOptions5.setOnCheckedChangeListener{  group, checkedId ->
                when(checkedId){
                    R.id.card_radio_button_51 -> putData(12)
                    R.id.card_radio_button_52 -> putData(10)
                    R.id.card_radio_button_53 -> putData(8)
                    R.id.card_radio_button_54 -> putData(6)
                    else -> {
                        Toast.makeText(context, "Select an option please", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            val sharedPreference = activity?.getSharedPreferences("EMAIL", Context.MODE_PRIVATE)
            val email = sharedPreference?.getString("email", null)

            val firebaseDatabase: FirebaseDatabase
            val databaseReference: DatabaseReference
            firebaseDatabase = FirebaseDatabase.getInstance();

            databaseReference =
                firebaseDatabase.getReference("users").child(EncodeString(email!!).toString())

            databaseReference.child("Questions").child("q1").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        q1 = snapshot.getValue(Int::class.java)!!
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

            databaseReference.child("Questions").child("q2").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        q2 = snapshot.getValue(Int::class.java)!!
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

            databaseReference.child("Questions").child("q3").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        q3 = snapshot.getValue(Int::class.java)!!
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

            databaseReference.child("Questions").child("q4").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        q4 = snapshot.getValue(Int::class.java)!!
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

            databaseReference.child("Questions").child("q5").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        q5 = snapshot.getValue(Int::class.java)!!
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

            databaseReference.child("Total").setValue(q1+q2+q3+q4+q5)

            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    fun putData(points: Int){
        val firebaseDatabase: FirebaseDatabase
        firebaseDatabase = FirebaseDatabase.getInstance();

        val sharedPreference = activity?.getSharedPreferences("EMAIL", Context.MODE_PRIVATE)
        val email = sharedPreference?.getString("email", null)

        database =  firebaseDatabase.getReference("users").child(EncodeString(email!!).toString())

        database.child(EncodeString(email).toString()).child("Questions").child("q5").setValue(points)
    }

    fun EncodeString(string: String): String? {
        return string.replace(".", ",")
    }


}