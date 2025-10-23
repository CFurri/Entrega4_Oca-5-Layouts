package com.example.ad_c5_oca

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameOverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game_over)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Som-hi amb les vistes:
        val tvMissatgeGuanyador: TextView = findViewById(R.id.tvMissatgeGuanyador)
        val btnRepetir: Button = findViewById(R.id.btnRepetir)
        val btnIniciar: Button = findViewById(R.id.btnIniciar)

        //Les dades que rebem:
        val guanyador = intent.getStringExtra("GUANYADOR") ?: "Algú"
        val nom1 = intent.getStringExtra("NOM_JUGADOR_1")
        val nom2 = intent.getStringExtra("NOM_JUGADOR_2")

        tvMissatgeGuanyador.text = "Enhorabona, ha guanyat $guanyador!"

        btnRepetir.setOnClickListener {
            val intentJoc = Intent(this, MainActivity::class.java)
            intentJoc.putExtra("NOM_JUGADOR_1", nom1)
            intentJoc.putExtra("NOM_JUGADOR_2", nom2)
            startActivity(intentJoc)
            finish() // Tanca aquesta pantalla
        }

        // --- 5. Configurar botó INICIAR ---
        // Torna a la Pantalla 1 (WelcomeActivity)
        btnIniciar.setOnClickListener {
            val intentInici = Intent(this, WelcomeActivity::class.java)
            // Neteja totes les activitats anteriors (Joc, Noms, etc.)
            intentInici.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intentInici)
            finish() // Tanca aquesta pantalla
        }
    }
}