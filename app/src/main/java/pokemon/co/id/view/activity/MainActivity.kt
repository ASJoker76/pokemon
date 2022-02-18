package pokemon.co.id.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import pokemon.co.id.R
import pokemon.co.id.databinding.ActivityMainBinding
import pokemon.co.id.view.fragment.HomeFragment


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        val fragment: Fragment? = null
        val roolUser = "inspection"
        if (roolUser == "inspection") {
            val homeFragment = HomeFragment()
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fl_frame, homeFragment)
            ft.commit()
        }
    }
}