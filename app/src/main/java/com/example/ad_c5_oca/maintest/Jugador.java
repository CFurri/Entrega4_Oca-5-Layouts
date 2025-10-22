package com.example.ad_c5_oca.maintest;

public class Jugador extends Persona{
    private int posicio;

    public Jugador(String nom) {
        super(nom);
        this.posicio = 0; //Defineixo posició inicial

    }
    public int getPosicio() {return posicio;}

    public void setPosicio(int posicio) {this.posicio = posicio;}

    public void moure(int caselles){
        this.posicio += caselles;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "posicio=" + posicio +
                '}';
    }
}
