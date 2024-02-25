package Juegos;

import java.util.Scanner;

import Barajas.Baraja;
import Barajas.Carta;
import Barajas.TipoBaraja;

/**
 * Clase SiesteYMedio que hereda de la clase Juego, implementa el método jugar
 * 
 * Modos de juego a implementar: Player 1, Player1 vs Player2, Player1 vs CPU
 */
public class SieteYMedio extends Juego {

	private String nombreJugador;

	public SieteYMedio(String nombreJugador1, String nombreJugador2) {
		super("Siete y medio");
		// Crear una baraja barajada
		baraja = new Baraja(TipoBaraja.NORMAL, true);

	}

	public void jugar() {
		Scanner sc = new Scanner(System.in);

		int opcion = -1;

		while (opcion != 0) {
			System.out.println("1. Player 1");
			System.out.println("2. Player 1 VS Player 2");
			System.out.println("3. Player 1 VS CPU");
			System.out.println("0. Salir");
			System.out.print("\nOpción: ");

			opcion = sc.nextInt();

			System.out.println("");
			switch (opcion) {
			case 1:
				jugarModoPlayer1();
				break;
			case 2:
				jugarModoPlayer1VSPlayer2();
				break;
			case 3:
				jugarModoPlayer1VSCPU();
				break;
			case 4:
				break;
			case 0:
				break;
			default:
				System.out.println("Opción inválida. Por favor elige una opción del menú.");
			}
		}
	}

	private void jugarModoPlayer1() {
		this.puntosJugador1 = 0;
		this.puntosJugador2 = 0;
		this.nombreJugador1 = "Player 1";
		this.nombreJugador2 = null;

		// Robamos carta y asignamos puntos
		Carta c1 = this.baraja.robar();
		this.sumarPuntosJugador(c1.getValorSieteyMedio(), 0, 1);
		imprimirCartaJugada(c1, this.nombreJugador1);

		// Maxima puntuación?
		while (this.puntosJugador1 < 7.5) {
			this.imprimirPuntuacion();

			// Otra carta?
			System.out.print(this.nombreJugador1 + ": ¿Continuar (c) o plantarse (p)?");
			Scanner sc = new Scanner(System.in);
			String otraCarta = sc.nextLine();

			// Si
			if (otraCarta.equals("c")) {
				c1 = this.baraja.robar(); // Robamos una nueva carta
				this.sumarPuntosJugador(c1.getValorSieteyMedio(), 0, 1);
				imprimirCartaJugada(c1, this.nombreJugador1);
			}
			// No
			else {
				this.imprimirPuntuacion();
				break;
			}
		}

		this.imprimirPuntuacion();

		// Ganas o pierdes?
		if (this.puntosJugador1 >= 6 && this.puntosJugador1 <= 7.5) {
			this.imprimirFinJuego(this.nombreJugador1, "ganado");
		} else {
			this.imprimirFinJuego(this.nombreJugador1, "perdido");
		}
	}

	private void jugarModoPlayer1VSPlayer2() {
		this.puntosJugador1 = 0;
		this.puntosJugador2 = 0;
		this.nombreJugador1 = "Player 1";
		this.nombreJugador2 = "Player 2";

		for (int i = 0; i < 2; i++) {
			String nombreJugador = (i == 0) ? nombreJugador1 : nombreJugador2;

			// Robamos carta y asignamos puntos
			Carta c = this.baraja.robar();
			this.sumarPuntosJugador(c.getValorSieteyMedio(), 0, i + 1);
			imprimirCartaJugada(c, nombreJugador);

			// Máxima puntuación?
			while (this.puntosJugador1 <= 7.5 && this.puntosJugador2 <= 7.5) {
				this.imprimirPuntuacion();

				// Otra carta?
				System.out.print(nombreJugador + ": ¿Continuar (c) o plantarse (p)?");
				Scanner sc = new Scanner(System.in);
				String otraCarta = sc.nextLine();

				// Si
				if (otraCarta.equals("c")) {
					c = this.baraja.robar();
					this.sumarPuntosJugador(c.getValorSieteyMedio(), 0, i + 1);
					imprimirCartaJugada(c, nombreJugador);
				}
				// No
				else {
					break;
				}
			}
		}

		// Imprimir puntuación global después de que ambos jugadores terminen su turno
		imprimirPuntuacion();

		// Evaluar resultados
		if (this.puntosJugador1 > 7.5 && this.puntosJugador2 > 7.5) {
			// Ambos jugadores se pasaron, derrota
			this.imprimirFinJuego("DERROTA", "");
		} else if (this.puntosJugador1 > 7.5) {
			// Jugador 1 se pasó, pierde
			this.imprimirFinJuego("Player 1", "PIERDE");
		} else if (this.puntosJugador2 > 7.5) {
			// Jugador 2 se pasó, pierde
			this.imprimirFinJuego("Player 2", "PIERDE");
		} else {
			// Ninguno se pasó, gana el que tiene más puntos
			if (this.puntosJugador1 > this.puntosJugador2) {
				this.imprimirFinJuego("Player 1", "GANA");
			} else if (this.puntosJugador2 > this.puntosJugador1) {
				this.imprimirFinJuego("Player 2", "GANA");
			} else {
				// Empate en puntos
				this.imprimirFinJuego("Empate", "");
			}
		}
	}

	private void jugarModoPlayer1VSCPU() {
		this.puntosJugador1 = 0;
		this.puntosJugador2 = 0;
		this.nombreJugador1 = "Player 1";
		this.nombreJugador2 = "CPU";
		Scanner sc = new Scanner(System.in);

		Carta c1;
		Carta c2;

		String respuesta = null;

		boolean continuarJugador1 = true;
		boolean continuarCPU = true;
		boolean compararPuntos = true;

		// Repetir hasta que los 2 jugadores terminen
		while (continuarJugador1 || continuarCPU) {
			// 1.- Juega JUGADOR 1
			if (continuarJugador1) {
				c1 = baraja.robar();
				sumarPuntosJugador(c1.getValorSieteyMedio(), 0, 1);
				imprimirCartaJugada(c1, nombreJugador1);
			}

			// Impimir puntuación de los 2 jugadores
			imprimirPuntuacion();

			// Comprobar si GANA el jugador 1
			if (puntosJugador1 == 7.5 && continuarJugador1) {
				// Gana
				imprimirFinJuego(nombreJugador1, "Gana");
				continuarJugador1 = false;
				compararPuntos = false;
			} else {
				// Comprobar si PIERDE el jugador 1
				if (puntosJugador1 > 7.5 && continuarJugador1) {
					// Pierde
					imprimirFinJuego(nombreJugador1, "Pierde");
					continuarJugador1 = false;
					compararPuntos = false;
					continuarCPU = false;
				} else {
					// Si gana y no se pasa...
					if (continuarJugador1) {
						// Continua o se planta el JUGADOR 1 ???
						System.out.printf("%s: %s", nombreJugador1, "¿Continuar (c) o Plantarse (p)? ");
						respuesta = sc.nextLine();
						continuarJugador1 = respuesta.equals("c");
					}

					// Juega CPU solo si el jugador 1 decide plantarse o se pasa
					if (continuarCPU && (!continuarJugador1 || !respuesta.equals("c"))) {
						c2 = baraja.robar();
						sumarPuntosJugador(c2.getValorSieteyMedio(), 0, 2);

						imprimirCartaJugada(c2, nombreJugador2);

						System.out.println("");
						imprimirPuntuacion();
						if (puntosJugador2 == 7.5 && continuarCPU) {
							// Gana CPU
							imprimirFinJuego(nombreJugador2, "Gana");
							continuarCPU = false;
							compararPuntos = false;
						} else {
							if (puntosJugador2 > 7.5 && continuarCPU) {
								// Pierde CPU
								imprimirFinJuego(nombreJugador2, "Pierde");
								continuarCPU = false;
								compararPuntos = false;
							}
						}
					}
				}
			}

			// Decisión de la CPU
			if (continuarCPU) {
				if (puntosJugador2 >= 6)
					continuarCPU = false;
			}
		}

		// Imprimir quien ha ganado
		if (compararPuntos) {
			imprimirGanador();
		}
	}

	private void imprimirCartaJugada(Carta c, String jugador) {
		System.out.println();
		System.out.println(jugador + ": " + c.getNombreCarta() + "=> " + c.getValorSieteyMedio() + " " + " puntos");
	}
}