package Barajas;

import Interfaces.Relacionable;

/**
 * Clase Carta que construye y gestiona cartas, estable los valores de las
 * cartas en cada juego e implementa la interfaz Relacionable, ya que cualquier
 * carta puede ser mayor, menor o igual que otra.
 */
public class Carta implements Interfaces.Relacionable {
	private int numero;
	private Palo palo;

	public Carta(int numero, Palo palo) {
		this.numero = numero;
		this.palo = palo;
	}

	public Carta(int id) {
		this.numero = id % 10 + 1;

		switch (id / 10) {
		case 1:
			this.palo = Palo.OROS;
			break;
		case 2:
			this.palo = Palo.COPAS;
			break;
		case 3:
			this.palo = Palo.ESPADAS;
			break;
		case 4:
			this.palo = Palo.BASTOS;
			break;
		}
	}

	public final int getNumero() {
		return numero;
	}

	public final Palo getPalo() {
		return palo;
	}

	public String getNombreNumero() {
		String nombreNumero = "";

		switch (this.numero) {
		case 1:
			nombreNumero = "As";
			break;
		case 2:
			nombreNumero = "Dos";
			break;
		case 3:
			nombreNumero = "Tres";
			break;
		case 4:
			nombreNumero = "Cuatro";
			break;
		case 5:
			nombreNumero = "Cinco";
			break;
		case 6:
			nombreNumero = "Seis";
			break;
		case 7:
			nombreNumero = "Siete";
			break;
		case 8:
			nombreNumero = "Sota";
			break;
		case 9:
			nombreNumero = "Caballo";
			break;
		case 10:
			nombreNumero = "Rey";
			break;
		default:
			nombreNumero = "desconocido";
		}

		return nombreNumero;
	}

	public String nombrePalo() {
		String nombrePalo = "";

		switch (this.palo) {
		case OROS:
			nombrePalo = "oros";
			break;
		case COPAS:
			nombrePalo = "copas";
			break;
		case ESPADAS:
			nombrePalo = "espadas";
			break;
		case BASTOS:
			nombrePalo = "bastos";
			break;
		default:
			nombrePalo = "desconocido";
		}

		return nombrePalo;
	}

	public String getNombreCarta() {
		String nombreCarta = "";

		switch (this.palo) {
		case OROS:
			for (int i = 0; i < 10; i++)
				nombreCarta = getNombreNumero() + " de oros";
			break;
		case COPAS:
			for (int i = 0; i < 10; i++)
				nombreCarta = getNombreNumero() + " de copas";
			break;
		case ESPADAS:
			for (int i = 0; i < 10; i++)
				nombreCarta = getNombreNumero() + " de espadas";
			break;
		case BASTOS:
			for (int i = 0; i < 10; i++)
				nombreCarta = getNombreNumero() + " de bastos";
			break;
		default:
			nombreCarta = "desconocido";
		}

		return nombreCarta;
	}

	public int getValorTute() {
		int valor = 0;

		if (this.numero == 1)
			valor = 11;
		else if (this.numero == 3)
			valor = 10;
		else if (this.numero == 8)
			valor = 2;
		else if (this.numero == 9)
			valor = 3;
		else if (this.numero == 10)
			valor = 4;

		return valor;
	}

	public double getValorSieteyMedio() {
		double valor = 0;

		if (this.numero >= 8 && this.numero <= 10)
			valor = 0.5f;
		else
			valor = this.numero;

		return valor;
	}

	public int getValorBrisca() {
		// El valor de las cartas en la Brisca es el mismo que en el Tute
		return getValorTute();
	}

	@Override
	public boolean esMenorQue(Interfaces.Relacionable r) {
		Carta otraCarta = (Carta) r;
		return this.numero < otraCarta.getNumero();
	}

	@Override
	public boolean esIgualQue(Interfaces.Relacionable r) {
		Carta otraCarta = (Carta) r;
		return this.numero == otraCarta.getNumero() && this.palo == otraCarta.getPalo();
	}

	@Override
	public boolean esMayorQue(Interfaces.Relacionable r) {
		Carta otraCarta = (Carta) r;
		return this.numero > otraCarta.getNumero();
	}
}
