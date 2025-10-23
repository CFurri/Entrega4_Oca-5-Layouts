package com.example.ad_c5_oca

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.content.Intent
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

    private lateinit var enunciatGuanyador: TextView
    private lateinit var laOca: ImageView
    private lateinit var daus: ImageView
    private lateinit var btnTirarDau: Button
    private lateinit var tvTornJugador: TextView
    private lateinit var tvPuntuacioJ1: TextView
    private lateinit var tvPuntuacioJ2: TextView
    private lateinit var btnReiniciar: Button
    private lateinit var btnAbandonar: Button
    private lateinit var nomJugador1: String // Per guardar els noms
    private lateinit var nomJugador2: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
            //Per rebre els noms!
        nomJugador1 = intent.getStringExtra("NOM_JUGADOR_1") ?: "Jugador 1"
        nomJugador2 = intent.getStringExtra("NOM_JUGADOR_2") ?: "Jugador 2"


        // --- Inicialització de les vistes (findViewById) ---

        laOca = findViewById(R.id.laOca)
        enunciatGuanyador = findViewById(R.id.enunciatGuanyador)
        btnTirarDau = findViewById(R.id.btnTirarDau)
        tvTornJugador = findViewById(R.id.tvTornJugador)
        daus = findViewById(R.id.daus)
        tvPuntuacioJ1 = findViewById(R.id.tvPuntuacioJ1)
        tvPuntuacioJ2 = findViewById(R.id.tvPuntuacioJ2)

        btnReiniciar = findViewById(R.id.btnReiniciar)
        btnAbandonar = findViewById(R.id.btnAbandonar)


        // --- Estat inicial de la UI ---
        enunciatGuanyador.visibility = View.GONE


        // REINICIAR: Crida a inicialitzarJoc (Pantalla 4)
        btnReiniciar.setOnClickListener {
            inicialitzarJoc()
        }

        // ABANDONAR: Torna a la pantalla 1
        btnAbandonar.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP // Neteja la pila d'activitats
            startActivity(intent)
        }


        // Listener per tirar el dau
        btnTirarDau.setOnClickListener {
            jugarUnTorn()
        }
        inicialitzarJoc()
    }

    private fun inicialitzarJoc() {

        // Mostrem les vistes del joc
        daus.visibility = View.VISIBLE
        btnTirarDau.visibility = View.VISIBLE
        tvTornJugador.visibility = View.VISIBLE
        tvPuntuacioJ1.visibility = View.VISIBLE
        tvPuntuacioJ2.visibility = View.VISIBLE

        // Amaguem coses de "final de partida"
        enunciatGuanyador.visibility = View.GONE
        laOca.visibility = View.GONE // Amaguem l'oca de "Tira una altra vegada"
        btnTirarDau.isEnabled = true // Reactivem el botó per si s'ha reiniciat

        // Creem els jugadors i el joc
        val jugador1 = Jugador(nomJugador1)
        val jugador2 = Jugador(nomJugador2)
        val llistaJugadors = arrayListOf(jugador1, jugador2)
        joc = Joc(llistaJugadors)

        // Resetejem puntuacions al reiniciar
        tvPuntuacioJ1.text = "0"
        tvPuntuacioJ2.text = "0"
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

            // Creem l'Intent per a la pantalla final
            val intentFi = Intent(this, GameOverActivity::class.java)
            intentFi.putExtra("GUANYADOR", resultat.nomJugador)
            intentFi.putExtra("NOM_JUGADOR_1", nomJugador1) // Passem els noms
            intentFi.putExtra("NOM_JUGADOR_2", nomJugador2) // per poder REPETIR
            startActivity(intentFi)
            finish() // Tanquem aquesta activitat de joc
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