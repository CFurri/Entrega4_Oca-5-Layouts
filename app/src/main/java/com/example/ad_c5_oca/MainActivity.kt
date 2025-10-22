package com.example.ad_c5_oca

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ad_c5_oca.maintest.Joc
import com.example.ad_c5_oca.maintest.Jugador
import com.example.ad_c5_oca.maintest.ResultatTorn


class MainActivity : AppCompatActivity() {

    private lateinit var joc: Joc

    // --- Declaració de totes les vistes ---
    private lateinit var etJugador1: EditText
    private lateinit var etJugador2: EditText
    private lateinit var btnComençar: Button
    private lateinit var enunciatGuanyador: TextView
    private lateinit var laOca: ImageView
    private lateinit var avisFaltaNom: TextView
    private lateinit var daus: ImageView
    private lateinit var btnTirarDau: Button
    private lateinit var tvTornJugador: TextView
    private lateinit var tvPuntuacioJ1: TextView
    private lateinit var tvPuntuacioJ2: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- Inicialització de les vistes (findViewById) ---
        etJugador1 = findViewById(R.id.jugador1)
        etJugador2 = findViewById(R.id.jugador2)
        laOca = findViewById(R.id.laOca)
        enunciatGuanyador = findViewById(R.id.enunciatGuanyador)
        avisFaltaNom = findViewById(R.id.avisFaltaNom)
        btnTirarDau = findViewById(R.id.btnTirarDau)
        tvTornJugador = findViewById(R.id.tvTornJugador)
        daus = findViewById(R.id.daus)
        btnComençar = findViewById(R.id.btnComençar)
        tvPuntuacioJ1 = findViewById(R.id.tvPuntuacioJ1)
        tvPuntuacioJ2 = findViewById(R.id.tvPuntuacioJ2)

        // --- Estat inicial de la UI ---
        btnComençar.isEnabled = false
        daus.visibility = View.GONE
        btnTirarDau.visibility = View.GONE
        tvTornJugador.visibility = View.GONE
        enunciatGuanyador.visibility = View.GONE
        avisFaltaNom.visibility = View.VISIBLE

        tvPuntuacioJ1.visibility = View.GONE
        tvPuntuacioJ2.visibility = View.GONE


        // Escoltador per activar el botó de començar partida
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val nom1 = etJugador1.text.trim().toString()
                val nom2 = etJugador2.text.trim().toString()
                btnComençar.isEnabled = nom1.isNotEmpty() && nom2.isNotEmpty()
            }
        }
        etJugador1.addTextChangedListener(textWatcher)
        etJugador2.addTextChangedListener(textWatcher)


        // Listener per començar la partida
        btnComençar.setOnClickListener {
            inicialitzarJoc()
        }

        // Listener per tirar el dau
        btnTirarDau.setOnClickListener {
            jugarUnTorn()
        }
    }

    private fun inicialitzarJoc() {
        // Amaguem les vistes d'inici
        // etJugador1.visibility = View.GONE
        // etJugador2.visibility = View.GONE
        btnComençar.visibility = View.GONE
        laOca.visibility = View.GONE
        avisFaltaNom.visibility = View.GONE

        // Mostrem les vistes del joc
        daus.visibility = View.VISIBLE
        btnTirarDau.visibility = View.VISIBLE
        tvTornJugador.visibility = View.VISIBLE
        tvPuntuacioJ1.visibility = View.VISIBLE
        tvPuntuacioJ2.visibility = View.VISIBLE

        // Creem els jugadors i el joc
        val nom1 = etJugador1.text.toString()
        val nom2 = etJugador2.text.toString()
        val jugador1 = Jugador(nom1)
        val jugador2 = Jugador(nom2)
        val llistaJugadors = arrayListOf(jugador1, jugador2)
        joc = Joc(llistaJugadors)


        tvTornJugador.text = "Torn de: ${joc.getJugadorActual().getNom()}"
    }

    private fun jugarUnTorn() {
        val resultatDau = joc.tirarDau()

        actualitzarImatgeDau(resultatDau)

        val resultatTorn = joc.jugarTorn(resultatDau)

        actualitzarUI(resultatTorn)
    }

    private fun actualitzarUI(resultat: ResultatTorn) {
        tvPuntuacioJ1.text = joc.getJugador(0).getPosicio().toString()
        tvPuntuacioJ2.text = joc.getJugador(1).getPosicio().toString()

        if (resultat.haGuanyat) {
            enunciatGuanyador.text = "El guanyador és ${resultat.nomJugador}!"
            enunciatGuanyador.visibility = View.VISIBLE
            btnTirarDau.isEnabled = false
            laOca.visibility = View.GONE
            return
        }

        if (resultat.esOca) {
            tvTornJugador.text = "Oca! Torna a tirar, ${resultat.nomJugador}"
            laOca.visibility = View.VISIBLE // <-- 1. MOSTREM LA IMATGE
        } else {
            tvTornJugador.text = "Torn de: ${joc.getNomProximJugador()}"
            laOca.visibility = View.GONE // <-- 2. AMAGUEM LA IMATGE
        }
    }

    private fun actualitzarImatgeDau(resultatDelDau: Int) {
        val recursImatge = when (resultatDelDau) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        daus.setImageResource(recursImatge)
    }

}