package Juegos;

import Barajas.*;

/**
 * Clase abstracta Juego que implementa las bases de los juegos de cartas en el
 * sistema
 */
public abstract class Juego {

	// Atributos
	protected Baraja baraja;
	protected String nombreJuego;
	protected String nombreJugador1;
	protected String nombreJugador2;
	protected double puntosJugador1;
	protected double puntosJugador2;
	protected int numeroMano;

	// Constructor
	public Juego(String nombre) {
		nombreJuego = nombre;
		puntosJugador1 = 0;
		puntosJugador2 = 0;

		imprimirCabecera();
	}

	// Métodos abstractos
	public abstract void jugar();

	// Métodos
	private void imprimirCabecera() {
		System.out.println("*****************************************");
		System.out.println("     JUEGOS DE CARTAS: " + this.nombreJuego);
		System.out.println("*****************************************");
	}

	// Métodos
	protected void imprimirFinJuego(String jugador, String ganaPierde) {

		System.out.println("");
		System.out.println("**********************************");
		System.out.println("  " + jugador + " " + ganaPierde + " a " + this.nombreJuego);
		System.out.println("**********************************");
		System.out.println("");

	}

	protected void imprimirPuntuacion() {
		System.out.println();
		System.out.printf("%1s%s: %3.1f %s", " ", nombreJugador1, puntosJugador1, " puntos");
		System.out.printf("\n");
		if (nombreJugador2 != null) {
			System.out.printf("%1s%s: %3.1f %s", " ", nombreJugador2, puntosJugador2, " puntos");
			System.out.println();
		}
		System.out.printf("\n");
	}

	protected void imprimirGanador() {

		// Comprobar quien ha ganado
		if (puntosJugador1 == puntosJugador2) {
			imprimirFinJuego(nombreJugador1 + "\n y" + nombreJugador2, "");
		} else {
			if (puntosJugador1 > puntosJugador2) {
				imprimirFinJuego(nombreJugador1, "Gana");
			} else {
				imprimirFinJuego(nombreJugador2, "Gana");
			}
		}
	}

	protected void sumarPuntosJugador(double puntosCarta1, double puntosCarta2, int numJugador) {
		// Calcular puntos a sumar...
		double puntosExtra = puntosCarta1 + puntosCarta2;

		if (numJugador == 1) {
			// ...sumar puntos al Jugador 1
			puntosJugador1 += puntosExtra;
		} else {
			// ...sumar puntos al Jugador 2
			puntosJugador2 += puntosExtra;
		}
	}
}
