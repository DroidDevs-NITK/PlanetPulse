package dev.redfox.planetpulse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import dev.redfox.planetpulse.databinding.ActivityMainBinding
import dev.redfox.planetpulse.ui.DashboardFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        replaceFragment(DashboardFragment())

        window.setStatusBarColor(this.getResources().getColor(R.color.color_appBar))
        binding.bottomNav.setOnItemSelectedListener {

            when(it.itemId) {
                R.id.dashboard -> replaceFragment(DashboardFragment())
                else -> {}

            }
            true
        }
        setContentView(binding.root)
    }

    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

}