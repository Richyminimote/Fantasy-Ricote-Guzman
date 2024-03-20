/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pk3;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashSet;

public class FantasyArchivos {  


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    
    static final int PRESUPUESTO_MAX=100;
    static int dineroGastado=0;
    static File ficheroJug= new File("jugadores.obj");
    static ArrayList<Jugador> jugadores=new ArrayList<>();
    
    public static void main(String[] args) {
        int opcion=0;
        
        if (!ficheroJug.exists()) {
            Instalacion.añadirJugadoresArrayInicial();
            Instalacion.guardarJugadores();
        }
        leerArchivos();
        dineroGastado=calcularGastado();
        do {
            opcion=mostrarMenu();  
            switch (opcion) {
                case 1:
                    imprimirJugadores();
                    break;
                case 2:
                    añadirJugadores();
                    break;
                case 3:
                    borrarJugador();
                    break;
                case 4:
                    ficharJugador();
                    break;
                case 5:
                    mostrarFichados();
                    break;
                case 9:
                    guardar();
                    break;
            }
        } while (opcion!=6);
    } 

    private static void leerArchivos() {
        Jugador ju;
        if (ficheroJug != null) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroJug));
                ju = (Jugador) ois.readObject();
                jugadores.add(ju);
                while (ju != null) {
                    ju = (Jugador) ois.readObject();
                    jugadores.add(ju);
                }
                ois.close();
            } catch (EOFException e1) {
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static void imprimirJugadores() {
        for (Jugador j: jugadores) {
            j.imprimir();
        }
    }

    private static int mostrarMenu() {
        int opcion; 
        Scanner sc= new Scanner (System.in);
        System.out.println("");
        System.out.println("|-----------" + ANSI_CYAN + "MENU" + ANSI_RESET + "------------|");
        System.out.println("|1. " + ANSI_GREEN + "Ver Tienda" + ANSI_RESET + "              |");
        System.out.println("|2. " + ANSI_GREEN + "Agregar Jugador" + ANSI_RESET + "         |");
        System.out.println("|3. " + ANSI_GREEN + "Vender Jugador" + ANSI_RESET + "          |");
        System.out.println("|4. " + ANSI_GREEN + "Comprar Jugaodor" + ANSI_RESET + "        |");
        System.out.println("|5. " + ANSI_GREEN + "Mostrar Fichados" + ANSI_RESET + "        |");
        System.out.println("|8. Tienes: $" + ANSI_YELLOW+ "NULL" + ANSI_RESET + "           |");
        System.out.println("|9. " + ANSI_GREEN + "Guardar" + ANSI_RESET + "           |");
        System.out.print("|------------(1-9)----------|");
        opcion=sc.nextInt();
        sc.nextLine();
        return opcion;
    }

    private static void añadirJugadores() {
        String nombre;
        int precio;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce nombre: ");
        nombre=sc.nextLine();
        System.out.println("Introduce el precio: ");
        precio=sc.nextInt();
        sc.nextLine();
        Jugador j=new Jugador(nombre,precio);
        jugadores.add(j);
    }

    private static void guardar() {
        try (FileOutputStream ff = new FileOutputStream(ficheroJug)) {
            ObjectOutputStream oos = new ObjectOutputStream(ff);
            for (Object ju : jugadores) {
                oos.writeObject(ju);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FantasyArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FantasyArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static void borrarJugador() {
        String nombre;
        Scanner sc= new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println( ANSI_CYAN +"Introduce el nombre del jugador a borrar: "+ ANSI_CYAN);
        System.out.println("");
        nombre=sc.nextLine();
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getNombre().equals(nombre))
                   jugadores.remove(i);
        }
    }

    private static void ficharJugador() {
        String nombre;
        int dineroDisponible=PRESUPUESTO_MAX-dineroGastado;
        Scanner sc= new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println( ANSI_CYAN + "Introduce el nombre del jugador a fichar: "+ ANSI_CYAN );
        System.out.println("");
        nombre=sc.nextLine();
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getNombre().equals(nombre)) {
                    if (jugadores.get(i).getPrecio()<=dineroDisponible) {
                        jugadores.get(i).setFichado(true);
                        dineroGastado+=jugadores.get(i).getPrecio();
                    } else {
                        System.out.println("|------------------------------------------");
                        System.out.println("|"+ANSI_RED +"No tienes pasta para fichar a ese tio"+ ANSI_RED);
                        System.out.println("|------------------------------------------");
                    }
            }
        }
    }

    private static void mostrarFichados() {
        for (Jugador j: jugadores) {
            if (j.isFichado())
                j.imprimir();
        }
    }

    private static int calcularGastado() {
        for (Jugador j : jugadores) {
            if (j.isFichado()) {
                dineroGastado+=j.getPrecio();
            }
        }
        return dineroGastado;
    }

}
