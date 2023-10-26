package wrt.app.fllmasterpiecepoints

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.punktezaehler_auswertung.R
import com.example.punktezaehler_auswertung.databinding.ActivityMainBinding
import com.google.gson.Gson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Variable Gesamtpunkte
    var points = 50

    //Variablen zur Überprüfung der Spinner-Punkte
    var temp10 = 0
    var temp141 = 0
    var temp142 = 0
    var temp15 = 0
    var temp16 = 0

    //Variablen zur Statuserkennung der Bilder
    var aufg1 = false
    var aufg2 = false
    var aufg3 = false
    var aufg4 = false
    var aufg5 = false
    var aufg6 = false
    var aufg7 = false
    var aufg8 = false
    var aufg9 = false
    var aufg10 = false
    var aufg11 = false
    var aufg12 = false
    var aufg13 = false
    var aufg14 = false
    var aufg15 = false

    //Variable zum Speichern der Sprachauswahl
    var language = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.title ="$points pts"

        //Deaktivierung Aufgabe 02 gegnerische Szenen
        binding.checkBox22b.isEnabled = false
        binding.checkBox22b.setTextColor(Color.parseColor("#636363"))
        binding.checkBox22b.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))
        binding.checkBox22p.isEnabled = false
        binding.checkBox22p.setTextColor(Color.parseColor("#636363"))
        binding.checkBox22p.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))
        binding.checkBox22o.isEnabled = false
        binding.checkBox22o.setTextColor(Color.parseColor("#636363"))
        binding.checkBox22o.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))

        //Deaktivierung Aufgabe 04 Bonus
        binding.checkBox42.isEnabled = false
        binding.checkBox42.setTextColor(Color.parseColor("#636363"))
        binding.checkBox42.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))

        //Deaktivierung Aufgabe 12 Bonus
        binding.checkBox122.isEnabled = false
        binding.checkBox122.setTextColor(Color.parseColor("#636363"))
        binding.checkBox122.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))



        //###PUNKTE BERECHNEN###


        //Aufgabe 00
        binding.checkBox0.setOnClickListener {
            if (binding.checkBox0.isChecked) {
                points += 20
            } else {
                points -= 20
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 01
        binding.checkBox1.setOnClickListener {
            if (binding.checkBox1.isChecked) {
                points += 20
            } else {
                points -= 20
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 02 - blau eigene
        binding.checkBox21b.setOnClickListener {
            if (binding.checkBox21b.isChecked) {
                if (binding.checkBox21p.isChecked) {
                    points -= 20
                    if (binding.checkBox22p.isChecked) {
                        points -= 30
                    }
                }
                if (binding.checkBox21o.isChecked) {
                    points -= 30
                    if (binding.checkBox22o.isChecked) {
                        points -= 10
                    }
                }
                binding.checkBox21p.isChecked = false
                binding.checkBox21o.isChecked = false

                points += 10
                if (binding.checkBox22b.isChecked) {
                    points += 20
                }
                binding.checkBox22b.isEnabled = true
                binding.checkBox22b.setTextColor(Color.parseColor("#FFFFFF"))
                binding.checkBox22b.buttonTintList = ColorStateList.valueOf(getColor(R.color.orange))
                binding.checkBox22p.isEnabled = true
                binding.checkBox22p.setTextColor(Color.parseColor("#FFFFFF"))
                binding.checkBox22p.buttonTintList = ColorStateList.valueOf(getColor(R.color.orange))
                binding.checkBox22o.isEnabled = true
                binding.checkBox22o.setTextColor(Color.parseColor("#FFFFFF"))
                binding.checkBox22o.buttonTintList = ColorStateList.valueOf(getColor(R.color.orange))
            } else {
                points -= 10

                if (binding.checkBox22b.isChecked) {
                    points -= 20
                }
                binding.checkBox22b.isChecked = false
                binding.checkBox22p.isChecked = false
                binding.checkBox22o.isChecked = false

                binding.checkBox22b.isEnabled = false
                binding.checkBox22b.setTextColor(Color.parseColor("#636363"))
                binding.checkBox22b.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))
                binding.checkBox22p.isEnabled = false
                binding.checkBox22p.setTextColor(Color.parseColor("#636363"))
                binding.checkBox22p.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))
                binding.checkBox22o.isEnabled = false
                binding.checkBox22o.setTextColor(Color.parseColor("#636363"))
                binding.checkBox22o.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 02 - pink eigene
        binding.checkBox21p.setOnClickListener {
            if (binding.checkBox21p.isChecked) {
                if (binding.checkBox21b.isChecked) {
                    points -= 10
                    if (binding.checkBox22b.isChecked) {
                        points -= 20
                    }
                }
                if (binding.checkBox21o.isChecked) {
                    points -= 30
                    if (binding.checkBox22o.isChecked) {
                        points -= 10
                    }
                }
                binding.checkBox21b.isChecked = false
                binding.checkBox21o.isChecked = false

                points += 20
                if (binding.checkBox22p.isChecked) {
                    points += 30
                }
                binding.checkBox22b.isEnabled = true
                binding.checkBox22b.setTextColor(Color.parseColor("#FFFFFF"))
                binding.checkBox22b.buttonTintList = ColorStateList.valueOf(getColor(R.color.orange))
                binding.checkBox22p.isEnabled = true
                binding.checkBox22p.setTextColor(Color.parseColor("#FFFFFF"))
                binding.checkBox22p.buttonTintList = ColorStateList.valueOf(getColor(R.color.orange))
                binding.checkBox22o.isEnabled = true
                binding.checkBox22o.setTextColor(Color.parseColor("#FFFFFF"))
                binding.checkBox22o.buttonTintList = ColorStateList.valueOf(getColor(R.color.orange))
            } else {
                points -= 20

                if (binding.checkBox22p.isChecked) {
                    points -= 30
                }
                binding.checkBox22b.isChecked = false
                binding.checkBox22p.isChecked = false
                binding.checkBox22o.isChecked = false

                binding.checkBox22b.isEnabled = false
                binding.checkBox22b.setTextColor(Color.parseColor("#636363"))
                binding.checkBox22b.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))
                binding.checkBox22p.isEnabled = false
                binding.checkBox22p.setTextColor(Color.parseColor("#636363"))
                binding.checkBox22p.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))
                binding.checkBox22o.isEnabled = false
                binding.checkBox22o.setTextColor(Color.parseColor("#636363"))
                binding.checkBox22o.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 02 - orange eigene
        binding.checkBox21o.setOnClickListener {
            if (binding.checkBox21o.isChecked) {
                if (binding.checkBox21b.isChecked) {
                    points -= 10
                    if (binding.checkBox22b.isChecked) {
                        points -= 20
                    }
                }
                if (binding.checkBox21p.isChecked) {
                    points -= 20
                    if (binding.checkBox22p.isChecked) {
                        points -= 30
                    }
                }
                binding.checkBox21b.isChecked = false
                binding.checkBox21p.isChecked = false

                points += 30
                if (binding.checkBox22o.isChecked) {
                    points += 10
                }
                binding.checkBox22b.isEnabled = true
                binding.checkBox22b.setTextColor(Color.parseColor("#FFFFFF"))
                binding.checkBox22b.buttonTintList = ColorStateList.valueOf(getColor(R.color.orange))
                binding.checkBox22p.isEnabled = true
                binding.checkBox22p.setTextColor(Color.parseColor("#FFFFFF"))
                binding.checkBox22p.buttonTintList = ColorStateList.valueOf(getColor(R.color.orange))
                binding.checkBox22o.isEnabled = true
                binding.checkBox22o.setTextColor(Color.parseColor("#FFFFFF"))
                binding.checkBox22o.buttonTintList = ColorStateList.valueOf(getColor(R.color.orange))
            } else {
                points -= 30

                if (binding.checkBox22o.isChecked) {
                    points -= 10
                }
                binding.checkBox22b.isChecked = false
                binding.checkBox22p.isChecked = false
                binding.checkBox22o.isChecked = false

                binding.checkBox22b.isEnabled = false
                binding.checkBox22b.setTextColor(Color.parseColor("#636363"))
                binding.checkBox22b.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))
                binding.checkBox22p.isEnabled = false
                binding.checkBox22p.setTextColor(Color.parseColor("#636363"))
                binding.checkBox22p.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))
                binding.checkBox22o.isEnabled = false
                binding.checkBox22o.setTextColor(Color.parseColor("#636363"))
                binding.checkBox22o.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 02 - blau gegnerische
        binding.checkBox22b.setOnClickListener {
            if (binding.checkBox22b.isChecked) {
                if (binding.checkBox22p.isChecked && binding.checkBox21p.isChecked) {
                    points -= 30
                }
                if (binding.checkBox22o.isChecked && binding.checkBox21o.isChecked) {
                    points -= 10
                }
                binding.checkBox22p.isChecked = false
                binding.checkBox22o.isChecked = false
                if (binding.checkBox21b.isChecked) {
                    points += 20
                }
            } else {
                if (binding.checkBox21b.isChecked) {
                    points -= 20
                }
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 02 - pink gegnerische
        binding.checkBox22p.setOnClickListener {
            if (binding.checkBox22p.isChecked) {
                if (binding.checkBox22b.isChecked && binding.checkBox21b.isChecked) {
                    points -= 20
                }
                if (binding.checkBox22o.isChecked && binding.checkBox21o.isChecked) {
                    points -= 10
                }
                binding.checkBox22b.isChecked = false
                binding.checkBox22o.isChecked = false
                if (binding.checkBox21p.isChecked) {
                    points += 30
                }
            } else {
                if (binding.checkBox21p.isChecked) {
                    points -= 30
                }
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 02 - orange gegnerische
        binding.checkBox22o.setOnClickListener {
            if (binding.checkBox22o.isChecked) {
                if (binding.checkBox22b.isChecked && binding.checkBox21b.isChecked) {
                    points -= 20
                }
                if (binding.checkBox22p.isChecked && binding.checkBox21p.isChecked) {
                    points -= 30
                }
                binding.checkBox22b.isChecked = false
                binding.checkBox22p.isChecked = false
                if (binding.checkBox21o.isChecked) {
                    points += 10
                }
            } else {
                if (binding.checkBox21o.isChecked) {
                    points -= 10
                }
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 03
        binding.checkBox3.setOnClickListener {
            if (binding.checkBox3.isChecked) {
                points += 20
            } else {
                points -= 20
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 04 - haupt
        binding.checkBox41.setOnClickListener {
            if (binding.checkBox41.isChecked) {
                points += 10

                binding.checkBox42.isEnabled = true
                binding.checkBox42.setTextColor(Color.parseColor("#FFFFFF"))
                binding.checkBox42.buttonTintList = ColorStateList.valueOf(getColor(R.color.orange))
            } else {
                points -= 10

                if (binding.checkBox42.isChecked) {
                    points -= 20
                }
                binding.checkBox42.isChecked = false
                binding.checkBox42.isEnabled = false
                binding.checkBox42.setTextColor(Color.parseColor("#636363"))
                binding.checkBox42.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 04 - boni
        binding.checkBox42.setOnClickListener {
            if (binding.checkBox42.isChecked) {
                points += 20
            } else {
                points -= 20
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 05
        binding.checkBox5.setOnClickListener {
            if (binding.checkBox5.isChecked) {
                points += 30
            } else {
                points -= 30
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 06 - Licht
        binding.checkBox61.setOnClickListener {
            if (binding.checkBox61.isChecked) {
                points += 10
            } else {
                points -= 10
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 06 - Lautsprecher
        binding.checkBox62.setOnClickListener {
            if (binding.checkBox62.isChecked) {
                points += 10
            } else {
                points -= 10
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 07
        binding.checkBox7.setOnClickListener {
            if (binding.checkBox7.isChecked) {
                points += 20
            } else {
                points -= 20
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 08 - rechts
        binding.checkBox81.setOnClickListener {
            if (binding.checkBox81.isChecked) {
                if (binding.checkBox82.isChecked) {
                    points -= 20
                }
                if (binding.checkBox83.isChecked) {
                    points -= 30
                }
                binding.checkBox82.isChecked = false
                binding.checkBox83.isChecked = false

                points += 10
            } else {
                points -= 10
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 08 - mitte
        binding.checkBox82.setOnClickListener {
            if (binding.checkBox82.isChecked) {
                if (binding.checkBox81.isChecked) {
                    points -= 10
                }
                if (binding.checkBox83.isChecked) {
                    points -= 30
                }
                binding.checkBox81.isChecked = false
                binding.checkBox83.isChecked = false

                points += 20
            } else {
                points -= 20
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 08 - links
        binding.checkBox83.setOnClickListener {
            if (binding.checkBox83.isChecked) {
                if (binding.checkBox81.isChecked) {
                    points -= 10
                }
                if (binding.checkBox82.isChecked) {
                    points -= 20
                }
                binding.checkBox81.isChecked = false
                binding.checkBox82.isChecked = false

                points += 30
            } else {
                points -= 30
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 09 - Boot
        binding.checkBox91.setOnClickListener {
            if (binding.checkBox91.isChecked) {
                points += 10
            } else {
                points -= 10
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 09 - Kamera
        binding.checkBox92.setOnClickListener {
            if (binding.checkBox92.isChecked) {
                points += 10
            } else {
                points -= 10
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 10
        binding.spinner10.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                if (temp10 != 0) {
                    points -= temp10
                }
                points += position * 10
                temp10 = position * 10
                supportActionBar?.title ="$points pts"
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        //Aufgabe 11 - gelb
        binding.checkBox111.setOnClickListener {
            if (binding.checkBox111.isChecked) {
                if (binding.checkBox112.isChecked) {
                    points -= 20
                }
                if (binding.checkBox113.isChecked) {
                    points -= 30
                }
                binding.checkBox112.isChecked = false
                binding.checkBox113.isChecked = false

                points += 10
            } else {
                points -= 10
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 11 - grün
        binding.checkBox112.setOnClickListener {
            if (binding.checkBox112.isChecked) {
                if (binding.checkBox111.isChecked) {
                    points -= 10
                }
                if (binding.checkBox113.isChecked) {
                    points -= 30
                }
                binding.checkBox111.isChecked = false
                binding.checkBox113.isChecked = false

                points += 20
            } else {
                points -= 20
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 11 - blau
        binding.checkBox113.setOnClickListener {
            if (binding.checkBox113.isChecked) {
                if (binding.checkBox111.isChecked) {
                    points -= 10
                }
                if (binding.checkBox112.isChecked) {
                    points -= 20
                }
                binding.checkBox111.isChecked = false
                binding.checkBox112.isChecked = false

                points += 30
            } else {
                points -= 30
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 12 - haupt
        binding.checkBox121.setOnClickListener {
            if (binding.checkBox121.isChecked) {
                points += 10

                binding.checkBox122.isEnabled = true
                binding.checkBox122.setTextColor(Color.parseColor("#FFFFFF"))
                binding.checkBox122.buttonTintList = ColorStateList.valueOf(getColor(R.color.orange))
            } else {
                points -= 10

                if (binding.checkBox122.isChecked) {
                    points -= 20
                }
                binding.checkBox122.isChecked = false
                binding.checkBox122.isEnabled = false
                binding.checkBox122.setTextColor(Color.parseColor("#636363"))
                binding.checkBox122.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 12 - boni
        binding.checkBox122.setOnClickListener {
            if (binding.checkBox122.isChecked) {
                points += 20
            } else {
                points -= 20
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 13 - Deckel
        binding.checkBox131.setOnClickListener {
            if (binding.checkBox131.isChecked) {
                points += 10
            } else {
                points -= 10
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 13 - Riegel
        binding.checkBox132.setOnClickListener {
            if (binding.checkBox132.isChecked) {
                points += 20
            } else {
                points -= 20
            }
            supportActionBar?.title ="$points pts"
        }

        //Aufgabe 14 - Zuschauer
        binding.spinner141.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                if (temp141 != 0) {
                    points -= temp141
                }
                points += position * 5
                temp141 = position * 5
                supportActionBar?.title ="$points pts"
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        //Aufgabe 14 - Zielgebiete
        binding.spinner142.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                if (temp142 != 0) {
                    points -= temp142
                }
                points += position * 5
                temp142 = position * 5
                supportActionBar?.title ="$points pts"
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        //Aufgabe 15
        binding.spinner15.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                if (temp15 != 0) {
                    points -= temp15
                }
                points += position * 10
                temp15 = position * 10
                supportActionBar?.title ="$points pts"
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        //Aufgabe 16
        binding.spinner16.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                if (temp16 != 0) {
                    points += temp16
                }

                temp16 = when (position) {
                    0 -> 0
                    1 -> 0
                    2 -> 15
                    3 -> 25
                    4 -> 35
                    5 -> 40
                    else -> 50
                }

                points -= temp16
                supportActionBar?.title ="$points pts"
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }



        //###BILDER ANZEIGEN###

        //Aufgabe 01
        binding.button1.setOnClickListener {
            if (!aufg1) {
                binding.imageView1.visibility = View.VISIBLE
                binding.imageView1.bringToFront()
                binding.button1.text = getString(R.string.pfeil_hoch)
                binding.button2.visibility = View.INVISIBLE
                aufg1 = true
            } else {
                binding.imageView1.visibility = View.INVISIBLE
                binding.button1.text = getString(R.string.pfeil_runter)
                binding.button2.visibility = View.VISIBLE
                aufg1 = false
            }
        }

        //Aufgabe 02
        binding.button2.setOnClickListener {
            if (!aufg2) {
                binding.imageView2.visibility = View.VISIBLE
                binding.imageView2.bringToFront()
                binding.button2.text = getString(R.string.pfeil_hoch)
                aufg2 = true
            } else {
                binding.imageView2.visibility = View.INVISIBLE
                binding.button2.text = getString(R.string.pfeil_runter)
                aufg2 = false
            }
        }

        //Aufgabe 03
        binding.button3.setOnClickListener {
            if (!aufg3) {
                binding.imageView3.visibility = View.VISIBLE
                binding.imageView3.bringToFront()
                binding.button3.text = getString(R.string.pfeil_hoch)
                binding.button4.visibility = View.INVISIBLE
                aufg3 = true
            } else {
                binding.imageView3.visibility = View.INVISIBLE
                binding.button3.text = getString(R.string.pfeil_runter)
                binding.button4.visibility = View.VISIBLE
                aufg3 = false
            }
        }

        //Aufgabe 04
        binding.button4.setOnClickListener {
            if (!aufg4) {
                binding.imageView4.visibility = View.VISIBLE
                binding.imageView4.bringToFront()
                binding.button4.text = getString(R.string.pfeil_hoch)
                binding.button5.visibility = View.INVISIBLE
                aufg4 = true
            } else {
                binding.imageView4.visibility = View.INVISIBLE
                binding.button4.text = getString(R.string.pfeil_runter)
                binding.button5.visibility = View.VISIBLE
                aufg4 = false
            }
        }

        //Aufgabe 05
        binding.button5.setOnClickListener {
            if (!aufg5) {
                binding.imageView5.visibility = View.VISIBLE
                binding.imageView5.bringToFront()
                binding.button5.text = getString(R.string.pfeil_hoch)
                binding.button6.visibility = View.INVISIBLE
                aufg5 = true
            } else {
                binding.imageView5.visibility = View.INVISIBLE
                binding.button5.text = getString(R.string.pfeil_runter)
                binding.button6.visibility = View.VISIBLE
                aufg5 = false
            }
        }

        //Aufgabe 06
        binding.button6.setOnClickListener {
            if (!aufg6) {
                binding.imageView6.visibility = View.VISIBLE
                binding.imageView6.bringToFront()
                binding.button6.text = getString(R.string.pfeil_hoch)
                binding.button7.visibility = View.INVISIBLE
                aufg6 = true
            } else {
                binding.imageView6.visibility = View.INVISIBLE
                binding.button6.text = getString(R.string.pfeil_runter)
                binding.button7.visibility = View.VISIBLE
                aufg6 = false
            }
        }

        //Aufgabe 07
        binding.button7.setOnClickListener {
            if (!aufg7) {
                binding.imageView7.visibility = View.VISIBLE
                binding.imageView7.bringToFront()
                binding.button7.text = getString(R.string.pfeil_hoch)
                binding.button8.visibility = View.INVISIBLE
                aufg7 = true
            } else {
                binding.imageView7.visibility = View.INVISIBLE
                binding.button7.text = getString(R.string.pfeil_runter)
                binding.button8.visibility = View.VISIBLE
                aufg7 = false
            }
        }

        //Aufgabe 08
        binding.button8.setOnClickListener {
            if (!aufg8) {
                binding.imageView8.visibility = View.VISIBLE
                binding.imageView8.bringToFront()
                binding.button8.text = getString(R.string.pfeil_hoch)
                aufg8 = true
            } else {
                binding.imageView8.visibility = View.INVISIBLE
                binding.button8.text = getString(R.string.pfeil_runter)
                aufg8 = false
            }
        }

        //Aufgabe 09
        binding.button9.setOnClickListener {
            if (!aufg9) {
                binding.imageView9.visibility = View.VISIBLE
                binding.imageView9.bringToFront()
                binding.button9.text = getString(R.string.pfeil_hoch)
                binding.button10.visibility = View.INVISIBLE
                aufg9 = true
            } else {
                binding.imageView9.visibility = View.INVISIBLE
                binding.button9.text = getString(R.string.pfeil_runter)
                binding.button10.visibility = View.VISIBLE
                aufg9 = false
            }
        }

        //Aufgabe 10
        binding.button10.setOnClickListener {
            if (!aufg10) {
                binding.imageView10.visibility = View.VISIBLE
                binding.imageView10.bringToFront()
                binding.button10.text = getString(R.string.pfeil_hoch)
                binding.button11.visibility = View.INVISIBLE
                aufg10 = true
            } else {
                binding.imageView10.visibility = View.INVISIBLE
                binding.button10.text = getString(R.string.pfeil_runter)
                binding.button11.visibility = View.VISIBLE
                aufg10 = false
            }
        }

        //Aufgabe 11
        binding.button11.setOnClickListener {
            if (!aufg11) {
                binding.imageView11.visibility = View.VISIBLE
                binding.imageView11.bringToFront()
                binding.button11.text = getString(R.string.pfeil_hoch)
                aufg11 = true
            } else {
                binding.imageView11.visibility = View.INVISIBLE
                binding.button11.text = getString(R.string.pfeil_runter)
                aufg11 = false
            }
        }

        //Aufgabe 12
        binding.button12.setOnClickListener {
            if (!aufg12) {
                binding.imageView12.visibility = View.VISIBLE
                binding.imageView12.bringToFront()
                binding.button12.text = getString(R.string.pfeil_hoch)
                binding.button13.visibility = View.INVISIBLE
                aufg12 = true
            } else {
                binding.imageView12.visibility = View.INVISIBLE
                binding.button12.text = getString(R.string.pfeil_runter)
                binding.button13.visibility = View.VISIBLE
                aufg12 = false
            }
        }

        //Aufgabe 13
        binding.button13.setOnClickListener {
            if (!aufg13) {
                binding.imageView13.visibility = View.VISIBLE
                binding.imageView13.bringToFront()
                binding.button13.text = getString(R.string.pfeil_hoch)
                binding.button14.visibility = View.INVISIBLE
                aufg13 = true
            } else {
                binding.imageView13.visibility = View.INVISIBLE
                binding.button13.text = getString(R.string.pfeil_runter)
                binding.button14.visibility = View.VISIBLE
                aufg13 = false
            }
        }

        //Aufgabe 14
        binding.button14.setOnClickListener {
            if (!aufg14) {
                binding.imageView14.visibility = View.VISIBLE
                binding.imageView14.bringToFront()
                binding.button14.text = getString(R.string.pfeil_hoch)
                aufg14 = true
            } else {
                binding.imageView14.visibility = View.INVISIBLE
                binding.button14.text = getString(R.string.pfeil_runter)
                aufg14 = false
            }
        }

        //Aufgabe 15
        binding.button15.setOnClickListener {
            if (!aufg15) {
                binding.imageView15.visibility = View.VISIBLE
                binding.imageView15.bringToFront()
                binding.button15.text = getString(R.string.pfeil_hoch)
                aufg15 = true
            } else {
                binding.imageView15.visibility = View.INVISIBLE
                binding.button15.text = getString(R.string.pfeil_runter)
                aufg15 = false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val clearWarningDialog = AlertDialog.Builder(this, R.style.AlertDialogItemStyle)
            .setTitle(R.string.titleWarningAktuell)
            .setMessage(R.string.messageWarningAktuell)
            .setIcon(R.drawable.ic_warning_delete)
            .setPositiveButton(R.string.positiveWarning) { _, _ ->
                delete()
            }
            .setNegativeButton(R.string.negativeWarning) { _, _ ->}.create()

        when (item.itemId) {
            R.id.mi_delete -> clearWarningDialog.show()
            R.id.mi_save -> save()
            R.id.mi_statistics -> statistics()
        }
        return true
    }

    private fun delete() {
        binding.checkBox0.isChecked = false
        binding.checkBox1.isChecked = false
        binding.checkBox21b.isChecked = false
        binding.checkBox21p.isChecked = false
        binding.checkBox21o.isChecked = false
        binding.checkBox22b.isChecked = false
        binding.checkBox22b.isEnabled = false
        binding.checkBox22b.setTextColor(Color.parseColor("#636363"))
        binding.checkBox22b.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))
        binding.checkBox22p.isChecked = false
        binding.checkBox22p.isEnabled = false
        binding.checkBox22p.setTextColor(Color.parseColor("#636363"))
        binding.checkBox22p.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))
        binding.checkBox22o.isChecked = false
        binding.checkBox22o.isEnabled = false
        binding.checkBox22o.setTextColor(Color.parseColor("#636363"))
        binding.checkBox22o.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))
        binding.checkBox3.isChecked = false
        binding.checkBox41.isChecked = false
        binding.checkBox42.isChecked = false
        binding.checkBox42.isEnabled = false
        binding.checkBox42.setTextColor(Color.parseColor("#636363"))
        binding.checkBox42.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))
        binding.checkBox5.isChecked = false
        binding.checkBox61.isChecked = false
        binding.checkBox62.isChecked = false
        binding.checkBox7.isChecked = false
        binding.checkBox81.isChecked = false
        binding.checkBox82.isChecked = false
        binding.checkBox83.isChecked = false
        binding.checkBox91.isChecked = false
        binding.checkBox92.isChecked = false
        binding.checkBox111.isChecked = false
        binding.checkBox112.isChecked = false
        binding.checkBox113.isChecked = false
        binding.checkBox121.isChecked = false
        binding.checkBox122.isChecked = false
        binding.checkBox122.isEnabled = false
        binding.checkBox122.setTextColor(Color.parseColor("#636363"))
        binding.checkBox122.buttonTintList = ColorStateList.valueOf(getColor(R.color.darkgrey))
        binding.checkBox131.isChecked = false
        binding.checkBox132.isChecked = false

        temp10 = 0
        temp141 = 0
        temp142 = 0
        temp15 = 0
        temp16 = 0

        binding.spinner10.setSelection(0)
        binding.spinner141.setSelection(0)
        binding.spinner142.setSelection(0)
        binding.spinner15.setSelection(0)
        binding.spinner16.setSelection(0)

        binding.imageView1.visibility = View.INVISIBLE
        binding.button1.text = getString(R.string.pfeil_runter)
        binding.button2.visibility = View.VISIBLE
        aufg1 = false

        binding.imageView2.visibility = View.INVISIBLE
        binding.button2.text = getString(R.string.pfeil_runter)
        aufg2 = false

        binding.imageView3.visibility = View.INVISIBLE
        binding.button3.text = getString(R.string.pfeil_runter)
        binding.button4.visibility = View.VISIBLE
        aufg3 = false

        binding.imageView4.visibility = View.INVISIBLE
        binding.button4.text = getString(R.string.pfeil_runter)
        binding.button5.visibility = View.VISIBLE
        aufg4 = false

        binding.imageView5.visibility = View.INVISIBLE
        binding.button5.text = getString(R.string.pfeil_runter)
        binding.button6.visibility = View.VISIBLE
        aufg5 = false

        binding.imageView6.visibility = View.INVISIBLE
        binding.button6.text = getString(R.string.pfeil_runter)
        binding.button7.visibility = View.VISIBLE
        aufg6 = false

        binding.imageView7.visibility = View.INVISIBLE
        binding.button7.text = getString(R.string.pfeil_runter)
        binding.button8.visibility = View.VISIBLE
        aufg7 = false

        binding.imageView8.visibility = View.INVISIBLE
        binding.button8.text = getString(R.string.pfeil_runter)
        aufg8 = false

        binding.imageView9.visibility = View.INVISIBLE
        binding.button9.text = getString(R.string.pfeil_runter)
        binding.button10.visibility = View.VISIBLE
        aufg9 = false

        binding.imageView10.visibility = View.INVISIBLE
        binding.button10.text = getString(R.string.pfeil_runter)
        binding.button11.visibility = View.VISIBLE
        aufg10 = false

        binding.imageView11.visibility = View.INVISIBLE
        binding.button11.text = getString(R.string.pfeil_runter)
        aufg11 = false

        binding.imageView12.visibility = View.INVISIBLE
        binding.button12.text = getString(R.string.pfeil_runter)
        binding.button13.visibility = View.VISIBLE
        aufg12 = false

        binding.imageView13.visibility = View.INVISIBLE
        binding.button13.text = getString(R.string.pfeil_runter)
        binding.button14.visibility = View.VISIBLE
        aufg13 = false

        binding.imageView14.visibility = View.INVISIBLE
        binding.button14.text = getString(R.string.pfeil_runter)
        aufg14 = false

        binding.imageView15.visibility = View.INVISIBLE
        binding.button15.text = getString(R.string.pfeil_runter)
        aufg15 = false

        points = 50
        supportActionBar?.title ="$points pts"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun save() {
        val sharedPref = getSharedPreferences("saved_runs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        var i = 0

        while (sharedPref.contains("run$i")) {
            i ++
        }

        editor.apply{
            val gson = Gson()

            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
            val timestamp = LocalDateTime.now().format(formatter)

            var e2 = 0
            if (binding.checkBox21b.isChecked) {
                e2 = 1
            } else if (binding.checkBox21p.isChecked) {
                e2 = 2
            } else if (binding.checkBox21o.isChecked) {
                e2 = 3
            }

            var g2 = 0
            if (binding.checkBox22b.isChecked) {
                g2 = 1
            } else if (binding.checkBox22p.isChecked) {
                g2 = 2
            } else if (binding.checkBox22o.isChecked) {
                g2 = 3
            }

            var a8 = 0
            if (binding.checkBox81.isChecked) {
                a8 = 1
            } else if (binding.checkBox82.isChecked) {
                a8 = 2
            } else if (binding.checkBox83.isChecked) {
                a8 = 3
            }

            var a11 = 0
            if (binding.checkBox111.isChecked) {
                a11 = 1
            } else if (binding.checkBox112.isChecked) {
                a11 = 2
            } else if (binding.checkBox113.isChecked) {
                a11 = 3
            }

            var a16 = 0
            a16 = when (binding.spinner16.selectedItemPosition) {
                0 -> 6
                1 -> 5
                2 -> 4
                3 -> 3
                4 -> 2
                5 -> 1
                else -> 0
            }

            val runObj = run(
                points,
                timestamp,
                binding.checkBox0.isChecked,
                binding.checkBox1.isChecked,
                e2,
                g2,
                binding.checkBox3.isChecked,
                binding.checkBox41.isChecked,
                binding.checkBox42.isChecked,
                binding.checkBox5.isChecked,
                binding.checkBox61.isChecked,
                binding.checkBox62.isChecked,
                binding.checkBox7.isChecked,
                a8,
                binding.checkBox91.isChecked,
                binding.checkBox92.isChecked,
                binding.spinner10.selectedItemPosition,
                a11,
                binding.checkBox121.isChecked,
                binding.checkBox122.isChecked,
                binding.checkBox131.isChecked,
                binding.checkBox132.isChecked,
                binding.spinner141.selectedItemPosition,
                binding.spinner142.selectedItemPosition,
                binding.spinner15.selectedItemPosition,
                a16)

            val str = gson.toJson(runObj)
            editor.putString("run$i", str)
            apply()
        }
        Toast.makeText(this, R.string.ToastSaved, Toast.LENGTH_SHORT).show()
    }

    private fun statistics() {
        Intent(this, MainActivity2::class.java).also {
            startActivity(it)
        }
    }
}