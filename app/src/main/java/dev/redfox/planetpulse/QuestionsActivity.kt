package dev.redfox.planetpulse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dev.redfox.planetpulse.databinding.ActivityQuestionsBinding
import dev.redfox.planetpulse.ui.question.Step1Fragment

class QuestionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionsBinding.inflate(layoutInflater)
//        replaceFragment(Step1Fragment())
        setContentView(binding.root)
    }

//    private fun replaceFragment(fragment: Fragment){
//
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.frameLayout, fragment)
//        fragmentTransaction.commit()
//    }
}