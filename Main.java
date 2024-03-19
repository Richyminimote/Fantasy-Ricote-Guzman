package pk1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;


public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";

    static Scanner scanner = new Scanner(System.in);
    static int menu = 0;
    static float monedasJugador = 500;

    static ArrayList<Double> precios;
    static ArrayList<String> nombres;
    static ArrayList<String> posiciones;
    static ArrayList<String> miEquipo;
    static Set<String> jugadoresComprados;

    public static void main(String[] args) {
        boolean flag = true;
        precios = new ArrayList<>();
        nombres = new ArrayList<>();
        posiciones = new ArrayList<>();
        miEquipo = new ArrayList<>();
        jugadoresComprados = new HashSet<>();

        jugadoresTienda();
        while (flag) {
            mostrarMenu();
            procesarOpcion();
        }
    }

    static void mostrarMenu() {
        System.out.println("");
        System.out.println("|-----------" + ANSI_CYAN + "MENU" + ANSI_RESET + "-------------|");
        System.out.println("|1. " + ANSI_GREEN + "Mi Equipo" + ANSI_RESET + "                |");
        System.out.println("|2. " + ANSI_GREEN + "Ver Tienda" + ANSI_RESET + "             |");
        System.out.println("|3. " + ANSI_GREEN + "Vender Jugadores" + ANSI_RESET + "              |");
        System.out.println("|4. " + ANSI_GREEN + "Comprar Jugaodres" + ANSI_RESET + "                   |");
        System.out.println("|8. " + ANSI_GREEN + "Mostrar saldo" + ANSI_RESET + "                   |");
        System.out.println("|9. Tienes: $" + ANSI_YELLOW + monedasJugador + ANSI_RESET + "         |");
        System.out.print("|------------(1-3)-----------|");
    }

    static void procesarOpcion() {
        if (scanner.hasNextInt()) {
            menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    mostrarAlineacion();
                    break;

                case 2:
                    VerTienda();
                    break;

                case 3:
                    VenderoJugadores();
                    break;

                case 4:
                    ComprarJugadores();
                    break;





                case 8:
                    mostrarSaldo();
                    break;
                case 9:
                    guardarResultadoEnArchivo();
                    break;
                default:
                    System.out.println("Introduce un número entre (1-4)");
                    break;
            }
        } else {
            System.out.println("Número irreconocible, por favor introduce un número (1,2,3,4).");
            scanner.next();
        }
    }



    static void mostrarAlineacion() {
        System.out.println("");
        System.out.println("");
        System.out.println("|---------------" + ANSI_YELLOW + "Mi alineación" + ANSI_RESET + "---------------|" + ANSI_RESET);
        if (miEquipo.isEmpty()) {
            System.out.println(ANSI_RED + "No tienes jugadores en tu equipo." + ANSI_RESET);
        } else {
            for (String jugador : miEquipo) {
                System.out.println(jugador);
            }

            contarJugadoresPorPosicion();
        }
        System.out.println("");
        System.out.println("");
    }


    static void VerTienda() {
        nombres.add("Nani");
        precios.add(25.0);
        posiciones.add("A");
    
        nombres.add("Pepe");
        precios.add(30.0);
        posiciones.add("B");
    
        nombres.add("Jose");
        precios.add(20.0);
        posiciones.add("C");
    
        nombres.add("Stephen Curry");
        precios.add(35.0);
        posiciones.add("B");
    
        nombres.add("Lebron James");
        precios.add(40.0);
        posiciones.add("A");
    
        nombres.add("Patata");
        precios.add(15.0);
        posiciones.add("C");
        
        nombres.add("Lorem");
        precios.add(90.0);
        posiciones.add("C");
    
        nombres.add("Tifany Curry");
        precios.add(135.0);
        posiciones.add("B");
    
        nombres.add("Hit Modrich");
        precios.add(140.0);
        posiciones.add("A");
    
        nombres.add("La Chula");
        precios.add(5.0);
        posiciones.add("C");
        
        nombres.add("Mercadona");
        precios.add(80.0);
        posiciones.add("C");
    
        nombres.add("Qu Erre");
        precios.add(35.0);
        posiciones.add("B");
    
        nombres.add("Aston Martin");
        precios.add(45.0);
        posiciones.add("A");
    
        nombres.add("Papa?");
        precios.add(180.0);
        posiciones.add("C");
    
        nombres.add("chun li");
        precios.add(5.0);
        posiciones.add("B");
    
        nombres.add("Paquita chocolatera");
        precios.add(15.0);
        posiciones.add("A");
    
        nombres.add("Yados fitnes");
        precios.add(10.0);
        posiciones.add("C");
    }
    
    

    
    static void mostrarSaldo() {
        System.out.println("Saldo:" + ANSI_GREEN + monedasJugador + ANSI_RESET + "$");
    }
 
static void guardarResultadoEnArchivo() {
    try {
        
        FileOutputStream fileOut = new FileOutputStream("resultado.txt");

        ObjectOutputStream out = new ObjectOutputStream(fileOut);

        float saldo = monedasJugador;
        out.writeFloat(saldo);


        


        out.close();
        fileOut.close();
        System.out.println("Guardando...");
        System.out.println("El resultado ha sido guardado en el archivo correctamente.");
    } catch (IOException e) {
        e.printStackTrace();
    }
    System.out.println("¡Hasta pronto!");
}


/*---------------------------------------------------------------------- */


static void mostrarTienda() {
    System.out.println(ANSI_YELLOW + "|TIENDA|" + ANSI_RESET);
    System.out.println("Tienes:" + ANSI_GREEN + monedasJugador + ANSI_RESET + "$");

    for (int i = 0; i < precios.size(); i++) {
        System.out.println(ANSI_CYAN + "-----------------------" + ANSI_RESET);
        System.out.println("Jugador número " + (i + 1));
        System.out.println("Nombre:" + nombres.get(i));
        System.out.println("Precio " + precios.get(i));
        System.out.println("Posición " + posiciones.get(i));
    }

    System.out.println("Introduce el número del jugador a comprar (0 para salir): ");
    int nleido = scanner.nextInt();

    if (nleido == 0) {
        return;
    }

    comprarJugador(nleido - 1);
}

static void venderJugador() {
    System.out.println("");
    System.out.println("|----------------" + ANSI_YELLOW + "Mi alineación - Jugadores Disponibles para Venta" + ANSI_RESET + "-------------|" + ANSI_RESET);
    if (miEquipo.isEmpty()) {
        System.out.println(ANSI_RED + "No tienes jugadores para vender." + ANSI_RESET);
    } else {
        for (int i = 0; i < miEquipo.size(); i++) {
            System.out.println("Jugador número " + (i + 1) + ": " + miEquipo.get(i));
        }

        System.out.println("");
    

        System.out.println("Introduce el número del jugador a vender (0 para salir): ");
        int nleido = scanner.nextInt();

        if (nleido == 0) {
            return;
        }

        venderJugador(nleido - 1);
    }
}

static void comprarJugador(int indice) {
    if (indice >= 0 && indice < precios.size()) {
        String nombreJugador = nombres.get(indice);
        if (!jugadoresComprados.contains(nombreJugador)) {
            if (monedasJugador >= precios.get(indice)) {
                monedasJugador -= precios.get(indice);
                miEquipo.add(nombreJugador);
                jugadoresComprados.add(nombreJugador);
                System.out.println(ANSI_GREEN + "¡Jugador comprado con éxito y añadido a Mi Equipo!" + ANSI_RESET);
            } else {
                System.out.println(ANSI_RED +"No tienes suficientes monedas para comprar este jugador." + ANSI_RESET);
            }
        } else {
            System.out.println("Ya has comprado a este jugador anteriormente.");
        }
    } else {
        System.out.println(ANSI_RED +"Número de jugador no válido." + ANSI_RESET);
    }
}

static void venderJugador(int indice) {
    if (indice >= 0 && indice < miEquipo.size()) {
        String nombreJugador = miEquipo.get(indice);
        monedasJugador += precios.get(indice); 
        System.out.println("¡Jugador vendido con éxito!");
        miEquipo.remove(indice);
        jugadoresComprados.remove(nombreJugador);
    } else {
        System.out.println("Número de jugador no válido.");
    }
}



