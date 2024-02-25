package Principal;

import java.util.Scanner;

import Juegos.Brisca;
import Juegos.SieteYMedio;
import Juegos.Tute;

public class Principal {

	public static void menu () {

		System.out.println("*************************************");
		System.out.println("         JUEGOS DE CARTAS          ");
		System.out.println("*************************************");
		System.out.println(" 1. 7 y medio");
		System.out.println(" 2. La Brisca");
		System.out.println(" 3. Tute");
		System.out.println();
		System.out.println(" 0. Salir");
		System.out.println();
		System.out.print("Opción: ");
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (true) {

			menu();

			int opcion = sc.nextInt();
			sc.nextLine();

			switch (opcion) {
			// Siete Y medio
			case 1:
				
				SieteYMedio juegoSieteYMedio = new SieteYMedio("", "");
				juegoSieteYMedio.jugar();
				
				break;
			// Brisca	
			case 2:
				
				Brisca juegoBrisca = new Brisca("", "");
				juegoBrisca.jugar();
				
				break;
			// Tute	
			case 3:
				Tute juegoTute = new Tute();
				juegoTute.jugar();
				break;
			// Idioma
			case 8:
				//
				break;
			// Salir
			case 9:
				System.exit(0);
				break;

			default:
				break;
			}
		}
	}
}