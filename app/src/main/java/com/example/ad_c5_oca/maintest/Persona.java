package com.example.ad_c5_oca.maintest;

public class Persona {
    private String nom;


    //Per inicialitzar - CONSTRUCTOR
    public Persona(String nom) {
        this.nom = nom;

    }

    //Getters i Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nom='" + nom + '\'' +
                '}';
    }
}
