
//package Juegos;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Scanner;
//
//public class Tute {
//    private static final int PUNTOS_MAXIMOS = 120;
//    private static final String[] PALOS = { "oros", "copas", "espadas", "bastos" };
//
//    private ArrayList<Carta> baraja;
//    private ArrayList<Carta> manoJugador1;
//    private ArrayList<Carta> manoJugador2;
//    private String paloTriunfo;
//    private int puntosJugador1;
//    private int puntosJugador2;
//    private int nivelTruco;
//
//    public Tute() {
//        this.baraja = new ArrayList<>();
//        this.manoJugador1 = new ArrayList<>();
//        this.manoJugador2 = new ArrayList<>();
//        this.puntosJugador1 = 0;
//        this.puntosJugador2 = 0;
//        this.nivelTruco = 1;
//
//        // Inicializar baraja
//        for (String palo : PALOS) {
//            for (int valor = 1; valor <= 12; valor++) {
//                baraja.add(new Carta(valor, palo));
//            }
//        }
//
//        // Barajar las cartas
//        Collections.shuffle(baraja);
//
//        // Repartir cartas a los jugadores
//        for (int i = 0; i < 8; i++) {
//            manoJugador1.add(baraja.remove(0));
//            manoJugador2.add(baraja.remove(0));
//        }
//
//        // Elegir palo de triunfo
//        paloTriunfo = manoJugador1.get(0).getPalo();
//    }
//
//    public void jugar() {
//        Scanner sc = new Scanner(System.in);
//
//        while (puntosJugador1 < PUNTOS_MAXIMOS && puntosJugador2 < PUNTOS_MAXIMOS) {
//            System.out.println("Puntos Jugador 1: " + puntosJugador1);
//            System.out.println("Puntos Jugador 2: " + puntosJugador2);
//
//            // Turno del Jugador 1
//            System.out.println("\nTurno de Jugador 1");
//            jugarTurno(manoJugador1, manoJugador2, paloTriunfo, sc);
//
//            // Turno del Jugador 2
//            System.out.println("\nTurno de Jugador 2");
//            jugarTurno(manoJugador2, manoJugador1, paloTriunfo, sc);
//
//            // Actualizar puntos y barajar de nuevo
//            calcularPuntos();
//            Collections.shuffle(baraja);
//        }
//
//        if (puntosJugador1 >= PUNTOS_MAXIMOS) {
//            System.out.println("¡Jugador 1 ha ganado!");
//        } else {
//            System.out.println("¡Jugador 2 ha ganado!");
//        }
//    }
//
//    private void jugarTurno(ArrayList<Carta> mano, ArrayList<Carta> otraMano, String paloTriunfo, Scanner sc) {
//        // Mostrar cartas al jugador
//        System.out.println("Tus cartas:");
//        for (int i = 0; i < mano.size(); i++) {
//            System.out.println(i + ": " + mano.get(i));
//        }
//
//        // Elegir carta a jugar
//        System.out.print("Elige una carta: ");
//        int cartaElegida = sc.nextInt();
//        Carta cartaJugador = mano.get(cartaElegida);
//        mano.remove(cartaElegida);
//
//        // Mostrar carta jugada
//        System.out.println("Has jugado: " + cartaJugador);
//
//        // Opciones adicionales (puedes implementar la lógica del truco aquí)
//
//        // Retirar una carta de la otra mano
//        otraMano.remove(0);
//        // Añadir carta a la otra mano
//        otraMano.add(cartaJugador);
//    }
//
//    private void calcularPuntos() {
//        // Implementa la lógica para calcular los puntos de cada jugador
//        // según las reglas del Tute que has proporcionado
//        // Por ejemplo, puedes usar nivelTruco para determinar la puntuación de la baza
//    }
//
//    public static void main(String[] args) {
//        Tute juego = new Tute();
//        juego.jugar();
//    }
//}
//
//class Carta {
//    private int valor;
//    private String palo;
//
//    public Carta(int valor, String palo) {
//        this.valor = valor;
//        this.palo = palo;
//    }
//
//    public int getValor() {
//        return valor;
//    }
//
//    public String getPalo() {
//        return palo;
//    }
//
//    @Override
//    public String toString() {
//        return valor + " de " + palo;
//    }
//}

package Juegos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Tute {
    private static final int PUNTOS_MAXIMOS = 40; // Ajustado según las reglas del Tute
    private static final String[] PALOS = { "oros", "copas", "espadas", "bastos" };

    private ArrayList<Carta> baraja;
    private ArrayList<Carta> manoJugador1;
    private ArrayList<Carta> manoJugador2;
    private Carta cartaCentro;
    private String paloTriunfo;
    private int puntosJugador1;
    private int puntosJugador2;
    private int nivelTruco;

    public Tute() {
        this.baraja = new ArrayList<>();
        this.manoJugador1 = new ArrayList<>();
        this.manoJugador2 = new ArrayList<>();
        this.puntosJugador1 = 0;
        this.puntosJugador2 = 0;
        this.nivelTruco = 1;

        // Inicializar baraja
        for (String palo : PALOS) {
            for (int valor = 1; valor <= 12; valor++) {
                baraja.add(new Carta(valor, palo));
            }
        }

        // Barajar las cartas
        Collections.shuffle(baraja);

        // Repartir cartas a los jugadores
        for (int i = 0; i < 3; i++) {
            manoJugador1.add(baraja.remove(0));
            manoJugador2.add(baraja.remove(0));
        }

        // Colocar carta en el centro
        cartaCentro = baraja.remove(0);
        paloTriunfo = cartaCentro.getPalo();
    }

    public void jugar() {
        Scanner sc = new Scanner(System.in);

        while (puntosJugador1 < PUNTOS_MAXIMOS && puntosJugador2 < PUNTOS_MAXIMOS) {
            System.out.println("Puntos Jugador 1: " + puntosJugador1);
            System.out.println("Puntos Jugador 2: " + puntosJugador2);

            // Turno del Jugador 1
            System.out.println("\nTurno de Jugador 1");
            jugarTurno(manoJugador1, manoJugador2, paloTriunfo, sc);

            // Turno del Jugador 2
            System.out.println("\nTurno de Jugador 2");
            jugarTurno(manoJugador2, manoJugador1, paloTriunfo, sc);

            // Actualizar puntos y barajar de nuevo
            calcularPuntos();
            Collections.shuffle(baraja);
        }

        if (puntosJugador1 >= PUNTOS_MAXIMOS) {
            System.out.println("¡Jugador 1 ha ganado!");
        } else {
            System.out.println("¡Jugador 2 ha ganado!");
        }
    }

    private void jugarTurno(ArrayList<Carta> mano, ArrayList<Carta> otraMano, String paloTriunfo, Scanner sc) {
        // Mostrar cartas al jugador
        System.out.println("Tus cartas:");
        for (int i = 0; i < mano.size(); i++) {
            System.out.println(i + ": " + mano.get(i));
        }

        // Elegir carta a jugar
        System.out.print("Elige una carta: ");
        int cartaElegida = sc.nextInt();
        Carta cartaJugador = mano.get(cartaElegida);
        mano.remove(cartaElegida);

        // Mostrar carta jugada
        System.out.println("Has jugado: " + cartaJugador);

        // Opciones adicionales (puedes implementar la lógica del truco aquí)

        // Retirar una carta de la otra mano (si hay)
        if (!otraMano.isEmpty()) {
            otraMano.remove(0);
        }

        // Añadir carta a la otra mano
        otraMano.add(cartaJugador);

        // Robar una nueva carta
        if (!baraja.isEmpty()) {
            mano.add(baraja.remove(0));
        }
    }



    private void calcularPuntos() {
        // Implementa la lógica para calcular los puntos de cada jugador
        // según las reglas del Tute que has proporcionado

        // Verificar si la mano del Jugador 2 no está vacía
        if (!manoJugador2.isEmpty()) {
            int valorJugador1 = cartaCentro.getValor();
            int valorJugador2 = manoJugador2.get(0).getValor();

            if (valorJugador1 > valorJugador2) {
                puntosJugador1++;
            } else if (valorJugador2 > valorJugador1) {
                puntosJugador2++;
            } else {
                // En caso de empate, el jugador 1 gana
                puntosJugador1++;
            }
        }

        // Limpiar manos y colocar nueva carta en el centro
        manoJugador1.clear();
        manoJugador2.clear();

        // Verificar si quedan cartas en la baraja antes de intentar remover
        if (!baraja.isEmpty()) {
            cartaCentro = baraja.remove(0);
            paloTriunfo = cartaCentro.getPalo();

            // Repartir nuevas cartas a los jugadores
            for (int i = 0; i < 3; i++) {
                if (!baraja.isEmpty()) {
                    manoJugador1.add(baraja.remove(0));
                    manoJugador2.add(baraja.remove(0));
                }
            }
        }
    }

    public static void main(String[] args) {
        Tute juego = new Tute();
        juego.jugar();
    }
}

class Carta {
    private int valor;
    private String palo;

    public Carta(int valor, String palo) {
        this.valor = valor;
        this.palo = palo;
    }

    public int getValor() {
        return valor;
    }

    public String getPalo() {
        return palo;
    }

    @Override
    public String toString() {
        return valor + " de " + palo;
    }
}
