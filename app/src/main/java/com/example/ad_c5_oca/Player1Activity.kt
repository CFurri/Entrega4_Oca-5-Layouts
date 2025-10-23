package com.example.ad_c5_oca

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Player1Activity : AppCompatActivity() {

    private lateinit var name1: EditText
    private lateinit var btnGoTo2nPlayer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_player1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Initializing views needed
        var name1 = findViewById<EditText>(R.id.nameInput1)
        var btnGoTo2nPlayer = findViewById<Button>(R.id.btnTo2nNom)

        //TextWatcher típic per fer funcional el botó Següent
        name1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            // Després que el text hagi canviat...
            override fun afterTextChanged(s: Editable?) {
                // Activa el botó NOMÉS si el text (sense espais) NO està buit
                btnGoTo2nPlayer.isEnabled = s.toString().trim().isNotEmpty()
            }
        })


    }

    fun goTo2nPlayer (btnGoTo2nPlayer : View){
        Log.i("AD_C5_Oca","Player1Activity")
        var i = Intent(Player1Activity@ this, Player2Activity::class.java)
        val nomJugador1 = name1.text.toString()
        i.putExtra("NOM_JUGADOR_1", nomJugador1)

        startActivity(i)

    }
}