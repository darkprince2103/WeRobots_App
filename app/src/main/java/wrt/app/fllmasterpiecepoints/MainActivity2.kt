package wrt.app.fllmasterpiecepoints

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.punktezaehler_auswertung.R
import com.example.punktezaehler_auswertung.databinding.ActivityMain2Binding
import com.google.gson.Gson

private lateinit var binding: ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    var saveRunList = mutableListOf<SavedRun>()

    val adapter = SavedRunsAdapter(saveRunList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.title = getString(R.string.menu2title)

        binding.rvSavedRuns.adapter = adapter
        binding.rvSavedRuns.layoutManager = LinearLayoutManager(this)

        val sharedPref = getSharedPreferences("saved_runs", Context.MODE_PRIVATE)

        var i = 0
        var a = 0

        while (a < sharedPref.all.size) {
            if (sharedPref.contains("run$i")) {
                val gson = Gson()
                val json: String? = sharedPref.getString("run$i", "")
                val runObj: run = gson.fromJson(json, run::class.java)
                saveRunList.add(0, SavedRun(runObj.points, runObj.time))
                adapter.notifyItemInserted(0)
                a ++
            }
            i ++
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val clearWarningDialog = AlertDialog.Builder(this, R.style.AlertDialogItemStyle)
            .setTitle(R.string.titleWarning)
            .setMessage(R.string.messageWarning)
            .setIcon(R.drawable.ic_warning_delete)
            .setPositiveButton(R.string.positiveWarning) { _, _ ->
                clearall()
            }
            .setNegativeButton(R.string.negativeWarning) { _, _ ->}.create()

        when (item.itemId) {
            R.id.mi_counter -> finish()
            R.id.mi_delete2 -> clearWarningDialog.show()
        }
        return true
    }



    private fun clearall() {
        val sharedPref = getSharedPreferences("saved_runs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.apply{
            clear()
            apply()
        }
        saveRunList.clear()
        adapter.notifyDataSetChanged()
    }

    fun deleteItem(pos: Int) {
        val sharedPref = getSharedPreferences("saved_runs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val position = index(pos, saveRunList.size)
        editor.apply{
            Log.v("DEBUG",position.toString())
            remove("run$position")
            apply()
        }
        saveRunList.removeAt(pos)

        adapter.notifyDataSetChanged()
        Log.v("DEBUG", sharedPref.all.toString())
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