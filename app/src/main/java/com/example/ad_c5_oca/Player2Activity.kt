package com.example.ad_c5_oca

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Player2Activity : AppCompatActivity() {

    private lateinit var name2: EditText
    private lateinit var btnGoToGame: Button
    private var nomJugador1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_player2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.i("AD_C5_Oca", "Player2Activity")

        // Per recuperar la info que ve de Player1Activity!
        nomJugador1 = intent.getStringExtra("NOM_JUGADOR_1")

        //Initializing views needed
        var name2 = findViewById<TextView>(R.id.nameInput2)
        var btnGoToGame = findViewById<Button>(R.id.btnToGame)

        btnGoToGame.isEnabled = false // Estat inicial

        //TextWatcher perquè el Button no es pugui fer servir fins ficat el nom.
        name2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                btnGoToGame.isEnabled = s.toString().trim().isNotEmpty()
            }
        })
    }

    fun goToGame (btnGoToGame : View) {
        Log.i("AD_C5_Oca", "Player2Activity")
        var i = Intent(Player2Activity@ this, MainActivity::class.java)
        val nomJugador2 = name2.text.toString()

        i.putExtra("NOM_JUGADOR_1", nomJugador1)
        i.putExtra("NOM_JUGADOR_2", nomJugador2)

        startActivity(i)
    }
}