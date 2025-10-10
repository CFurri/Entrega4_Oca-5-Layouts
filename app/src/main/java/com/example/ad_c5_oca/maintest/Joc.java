package com.example.ad_c5_oca.maintest;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Joc implements Resultat {
    private ArrayList<Jugador> jugadors;
    private int[] casellesOca; //Posicions on hi ha Oca
    private Random random;
    private Scanner scanner;
    private static final int casellaFinal = 64; //Es comença des de la casella 0.

    public Joc() {
        jugadors = new ArrayList<>();
        random = new Random();
        scanner = new Scanner(System.in);
        inicialitzarCasellesOca();
    }
    public void afegirJugador(Jugador jugador) {
        jugadors.add(jugador);
    }
    public void inicialitzarCasellesOca() {
        casellesOca = new int[]{1, 5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59};
    }
    private boolean esOca(int casella) {
        for (int oca : casellesOca) {
            if (casella == oca){
                return true;
            }
        } return false;
    }
    private int tirarDau(){
        return random.nextInt(6)+1;
    }

    public void inicialitzarJoc() {
        boolean partidaAcabada = false;
        while (!partidaAcabada) {
            for (Jugador jugador : jugadors) {
                System.out.println("Tira el jugador " + jugador.getNom());
                System.out.println("Clica la tecla Enter");
                scanner.nextLine();

                int dau= tirarDau();
                System.out.println();
                int novaPosicio = jugador.getPosicio() + dau;

                if (novaPosicio >= casellaFinal) {
                    novaPosicio = casellaFinal;
                    jugador.setPosicio(novaPosicio);
                    System.out.println("Jugador:  " + jugador.getNom() + " has guanyat.");
                    partidaAcabada = true;
                    break;
                }

                jugador.setPosicio(novaPosicio);
                System.out.println("Estàs a la casella " + novaPosicio);

                while (esOca(jugador.getPosicio()) && jugador.getPosicio() < casellaFinal) {
                    System.out.println("D'oca a oca i tires perquè et toca");
                    System.out.println("Torna a clicar la tecla Enter");
                    scanner.nextLine();


                    dau = tirarDau();
                    System.out.println("Has tret un " + dau);
                    novaPosicio = jugador.getPosicio() + dau;

                    if (novaPosicio >= casellaFinal) {
                        novaPosicio = casellaFinal;
                        jugador.setPosicio(novaPosicio);
                        System.out.println("Jugador: " + jugador.getNom() + " has guanyat.");
                        partidaAcabada = true;
                        break;
                    }
                    jugador.setPosicio(novaPosicio);
                    System.out.println("Estàs a la casella " + novaPosicio);
                }
                if (partidaAcabada) break;
                System.out.println("Fi del teu torn. \n");
                System.out.println("------------");
            }
        }
    }


    @Override
    public String getGuanyador() {
        for (Jugador jugador : jugadors) {
            if (jugador.getPosicio() >= casellaFinal) {
                return jugador.getNom();
            }
        }
        return "";
    }
}