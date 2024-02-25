package Juegos;

import java.util.Scanner;

import Barajas.Baraja;
import Barajas.Carta;

public class Brisca extends Juego {

    // ATRIBUTOS
    private static final String nombreJuego = "Brisca";
    private Baraja manoJ1;
    private Baraja manoJ2;
    private int cartaElegida;
    private Carta cartaJugador1;
    private Carta cartaJugador2;
    private Carta pinta;
    private int aQuienLeToca = 0;
    private int ganadorRonda;
    private int puntosJugador1 = 0;
    private int puntosJugador2 = 0;

    // CONSTRUCTORES
    public Brisca(String nombreJugador1, String nombreJugador2) {
        super(nombreJuego);
        this.baraja = new Baraja();
        this.manoJ1 = new Baraja();
        this.manoJ2 = new Baraja();
        this.nombreJugador1 = nombreJugador1;
        this.nombreJugador2 = nombreJugador2;
    }

    // METODOS
    @Override
    public void jugar() {
        Scanner sc = new Scanner(System.in);

        // Nombre jugadores
        this.nombreJugador1 = "Player 1";
        this.nombreJugador2 = "Player 2";

        // Crear la baraja
        this.baraja = new Baraja();  // Asegúrate de que se cree una nueva baraja al inicio de la partida

        // Repartir cartas
        for (int i = 0; i < 3; i++) {
            this.manoJ1.insertaCartaFinal(this.baraja.robar());
            this.manoJ2.insertaCartaFinal(this.baraja.robar());
        }

        // Carta que pinta
        pinta = this.baraja.robar();
        this.baraja.insertaCartaPrincipio(pinta);

        // Bucle de juego mientras los jugadores tengan cartas en la mano
        while (!this.manoJ1.laBarajaEstaVacia()) {
            // Dos pasadas, una por cada jugador
            for (int i = 0; i < 2; i++) {
                // Quien juega
                switch (aQuienLeToca) {
                    case 0:
                        turnoJugador(this.manoJ1, this.nombreJugador1, this.manoJ2, this.nombreJugador2, pinta, sc);
                        aQuienLeToca = 1;
                        break;
                    case 1:
                        turnoJugador(this.manoJ2, this.nombreJugador2, this.manoJ1, this.nombreJugador1, pinta, sc);
                        aQuienLeToca = 0;
                        break;
                }
            }

            // Ganador mano
            ganadorRonda = elegirGanador();
            // Sumar Puntos al ganador
            if (ganadorRonda == 0) {
                puntosJugador1++;
            } else {
                puntosJugador2++;
            }
            System.out.println("Puntos " + this.nombreJugador1 + ": " + puntosJugador1);
            System.out.println("Puntos " + this.nombreJugador2 + ": " + puntosJugador2);
        }

        // Determinar ganador final
        if (puntosJugador1 > puntosJugador2) {
            System.out.println("¡" + this.nombreJugador1 + " ha ganado!");
        } else if (puntosJugador2 > puntosJugador1) {
            System.out.println("¡" + this.nombreJugador2 + " ha ganado!");
        } else {
            System.out.println("¡Empate!");
        }
    }

    private void turnoJugador(Baraja manoJugador, String nombreJugador, Baraja otraMano, String nombreOtroJugador,
                              Carta pinta, Scanner sc) {
        // Imprimir pinta
        System.out.println("Pinta: " + pinta.getNombreCarta());
        // Mostrar cartas al jugador
        for (int j = 0; j < manoJugador.getNumeroDeCartas(); j++) {
            Carta carta = manoJugador.getCarta(j);
            System.out.println(j + ": " + carta.getNombreCarta());
        }
        // Elegir carta a jugar
        System.out.print("Carta: ");
        int cartaElegida = sc.nextInt();
        cartaJugador1 = manoJugador.getCarta(cartaElegida);
        manoJugador.removerCarta(cartaElegida);

        // Mostrar cartas al otro jugador
        System.out.println("Cartas de " + nombreOtroJugador);
        for (int j = 0; j < otraMano.getNumeroDeCartas(); j++) {
            Carta carta = otraMano.getCarta(j);
            System.out.println(j + ": " + carta.getNombreCarta());
        }
        // Elegir carta para el otro jugador
        System.out.print("Carta para " + nombreOtroJugador + ": ");
        int cartaElegidaOtroJugador = sc.nextInt();
        cartaJugador2 = otraMano.getCarta(cartaElegidaOtroJugador);
        otraMano.removerCarta(cartaElegidaOtroJugador);

        // Imprimimos las cartas jugadas
        System.out.println(nombreJugador + " ha jugado el " + cartaJugador1.getNombreCarta());
        System.out.println(nombreOtroJugador + " ha jugado el " + cartaJugador2.getNombreCarta());
    }

    private int elegirGanador() {
        int ganador = 0;

        // Obtiene el valor de las cartas jugadas por cada jugador
        int valorJugador1 = cartaJugador1.getValorTute(); // Puedes ajustar según el juego
        int valorJugador2 = cartaJugador2.getValorTute(); // Puedes ajustar según el juego

        // Lógica para determinar el ganador de la mano
        if (valorJugador1 > valorJugador2) {
            ganador = 0;
        } else if (valorJugador2 > valorJugador1) {
            ganador = 1;
        } else {
            // En caso de empate, puedes agregar más lógica según las reglas del juego
            // Por ejemplo, puedes comparar los palos
            if (cartaJugador1.getPalo().ordinal() > cartaJugador2.getPalo().ordinal()) {
                ganador = 0;
            } else {
                ganador = 1;
            }
        }

        return ganador;
    }
}

