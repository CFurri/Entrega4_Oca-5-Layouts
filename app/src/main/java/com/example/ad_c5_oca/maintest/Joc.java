package com.example.ad_c5_oca.maintest;

import java.util.ArrayList;
import java.util.Random;

public class Joc {
    private ArrayList<Jugador> jugadors;
    private int tornActual;
    private final int[] casellesOca;
    private final Random random;
    private static final int casellaFinal = 64; // La meta


    public Joc(ArrayList<Jugador> jugadors) {
        this.jugadors = jugadors;
        this.random = new Random();
        this.tornActual = 0;
        this.casellesOca = new int[]{1, 5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59};
    }


    public int tirarDau() {
        return random.nextInt(6) + 1;
    }


    public ResultatTorn jugarTorn(int resultatDau) {
        Jugador jugadorActual = getJugadorActual();
        int novaPosicio = jugadorActual.getPosicio() + resultatDau;
        boolean haGuanyat = false;

        if (novaPosicio >= casellaFinal) {
            novaPosicio = casellaFinal;
            haGuanyat = true;
        }

        jugadorActual.setPosicio(novaPosicio);

        boolean esCasellaOca = esOca(novaPosicio);

        if (!esCasellaOca && !haGuanyat) {
            canviarTorn();
        }

        return new ResultatTorn(jugadorActual.getNom(), resultatDau, novaPosicio, esCasellaOca, haGuanyat);
    }


    private void canviarTorn() {
        tornActual++;
        if (tornActual >= jugadors.size()) {
            tornActual = 0;
        }
    }


    public boolean esOca(int casella) {
        for (int oca : casellesOca) {
            if (casella == oca) {
                return true;
            }
        }
        return false;
    }




    public Jugador getJugadorActual() {
        return jugadors.get(tornActual);
    }


    public Jugador getJugador(int index) {
        return jugadors.get(index);
    }


    public String getNomProximJugador() {
        int proximTorn = tornActual;
        proximTorn++;
        if (proximTorn >= jugadors.size()){
            proximTorn = 0;
        }
        return jugadors.get(proximTorn).getNom();
    }
}