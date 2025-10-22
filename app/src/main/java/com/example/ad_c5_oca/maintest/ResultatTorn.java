
package com.example.ad_c5_oca.maintest;

public class ResultatTorn {
    public final String nomJugador;
    public final int resultatDau;
    public final int novaPosicio;
    public final boolean esOca;
    public final boolean haGuanyat;

    public ResultatTorn(String nom, int dau, int pos, boolean oca, boolean guanyat) {
        this.nomJugador = nom;
        this.resultatDau = dau;
        this.novaPosicio = pos;
        this.esOca = oca;
        this.haGuanyat = guanyat;
    }
}
