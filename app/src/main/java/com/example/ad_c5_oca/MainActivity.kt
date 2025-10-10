package com.example.ad_c5_oca

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ad_c5_oca.maintest.Joc
import com.example.ad_c5_oca.maintest.Jugador

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun inicialitzar() {
        val joc = Joc()
        val jugador1 = Jugador("Marc", "Vermell", 17)
        val jugador2 = Jugador("Josep", "Verd", 67)
    }

    //Per omplir noms

    //Clicar 'Començar partida'
            // Si hi ha els dos noms = Començar partida. El dau ja es pot clicar
            // Si falta un nom, rectifica.

    // Jugador 1 tira el dau.
            //Surt un número. S'avança de casella
            //Si la casella és oca, el jugador 1 torna a tirar. Apareix la imatge d'una oca

    // Jugador 2 tira el dau.
            // Surt un número. S'avança de casella
            // Si la casella és oca, el jugador 2 torna a tirar. Apareix l'imatge d'una oca

    //Quan un jugador arriba a la puntuació màxima, s'enuncia guanyador.


}