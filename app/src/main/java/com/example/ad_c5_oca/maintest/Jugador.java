package com.example.ad_c5_oca.maintest;

public class Jugador extends Persona{
    private int posicio;

    public Jugador(String nom, String color, int edat) {
        super(nom, color, edat);
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
                "nom='" + getNom() + '\'' +
                ", posicio=" + posicio +
                ", color='" + getColor() + '\'' +
                '}';
    }
}
