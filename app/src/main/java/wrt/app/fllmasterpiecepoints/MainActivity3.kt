package wrt.app.fllmasterpiecepoints

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.punktezaehler_auswertung.R
import com.example.punktezaehler_auswertung.databinding.ActivityMain3Binding
import com.google.gson.Gson

private lateinit var binding: ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPref = getSharedPreferences("saved_runs", Context.MODE_PRIVATE)
        val position = intent.getIntExtra("EXTRA_POSITION", -1)
        val pos = index(position, sharedPref.all.size)


        if (position != -1 && sharedPref.contains("run$pos")) {

            val gson = Gson()
            val json: String? = sharedPref.getString("run$pos", "")
            val runObj: run = gson.fromJson(json, run::class.java)

            supportActionBar?.title = runObj.points.toString() + " pts   -   " + runObj.time

            binding.checkBox0.isChecked = runObj.a00

            binding.checkBox1.isChecked = runObj.a01

            when (runObj.a02e) {
                1 -> binding.checkBox21b.isChecked = true
                2 -> binding.checkBox21p.isChecked = true
                3 -> binding.checkBox21o.isChecked = true
            }
            when (runObj.a02g) {
                1 -> binding.checkBox22b.isChecked = true
                2 -> binding.checkBox22p.isChecked = true
                3 -> binding.checkBox22o.isChecked = true
            }

            binding.checkBox3.isChecked = runObj.a03

            binding.checkBox41.isChecked = runObj.a041
            binding.checkBox42.isChecked = runObj.a042

            binding.checkBox5.isChecked = runObj.a05

            binding.checkBox61.isChecked = runObj.a061
            binding.checkBox62.isChecked = runObj.a062

            binding.checkBox7.isChecked = runObj.a07

            when (runObj.a08) {
                1 -> binding.checkBox81.isChecked = true
                2 -> binding.checkBox82.isChecked = true
                3 -> binding.checkBox83.isChecked = true
            }

            binding.checkBox91.isChecked = runObj.a091
            binding.checkBox92.isChecked = runObj.a092

            binding.textView10erg.text = runObj.a10.toString()

            when (runObj.a11) {
                1 -> binding.checkBox111.isChecked = true
                2 -> binding.checkBox112.isChecked = true
                3 -> binding.checkBox113.isChecked = true
            }

            binding.checkBox121.isChecked = runObj.a121
            binding.checkBox122.isChecked = runObj.a122

            binding.checkBox131.isChecked = runObj.a131
            binding.checkBox132.isChecked = runObj.a132

            binding.textView141erg.text = runObj.a141.toString()
            binding.textView142erg.text = runObj.a141.toString()

            binding.textView15erg.text = runObj.a15.toString()

            binding.textView16erg.text = runObj.a16.toString()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu3, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mi_goBack -> finish()
        }
        return true
    }

    private fun index (pos: Int, maxpos: Int): Int {
        val sharedPref = getSharedPreferences("saved_runs", Context.MODE_PRIVATE)
        var counter = 0
        var element = 0

        while (element < maxpos - pos) {
            if (sharedPref.contains("run$counter")) {
                element ++
            }
            if (element < maxpos - pos) {
                counter ++
            }
        }
        return counter
    }
}