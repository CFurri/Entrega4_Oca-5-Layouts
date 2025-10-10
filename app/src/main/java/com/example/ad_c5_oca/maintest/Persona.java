package com.example.ad_c5_oca.maintest;

public class Persona {
    private String nom;
    private String color;
    private int edat;

    //Per inicialitzar - CONSTRUCTOR
    public Persona(String nom, String color, int edat) {
        this.nom = nom;
        this.color = color;
        this.edat = edat;
    }
    //Getters i Setters
    public String getNom() {return nom;}

    public void setNom(String nom) {this.nom = nom;}

    public String getColor() {return color;}

    public void setColor(String color) {this.color = color;}

    public int getEdat() {return edat;}

    public void setEdat(int edat) {this.edat = edat;}

    @Override
    public String toString() {
        return "Persona{" +
                "nom='" + nom + '\'' +
                ", color='" + color + '\'' +
                ", edat=" + edat +
                '}';
    }
}
