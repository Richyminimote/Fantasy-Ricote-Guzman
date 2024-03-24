
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
import java.util.logging.Level;
import java.util.logging.Logger;


public class Instalacion {

    static File ficheroJug = new File("jugadores.obj");
    static ArrayList<Jugador> jugadores=new ArrayList<>();

    static void añadirJugadoresArrayInicial() {
        String[] nombres = {"Messi", "Ronaldo", "Neymar", "Mbappé", "Salah", "Lewandowski", "De Bruyne", "Kane", "Suárez", "Hazard", "Mané", "Sterling", "De Jong", "Lloris", "Alisson", "Van Dijk", "Ramos", "Piqué", "Kroos", "Benzema"};
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int precio = 10 + random.nextInt(91); // Precio aleatorio entre 10 y 100
            jugadores.add(new Jugador(nombres[i], precio));
        }
    }

    static void guardarJugadores() {
        FileOutputStream ff;
        try {
            ff = new FileOutputStream(ficheroJug);
            ObjectOutputStream oos= new ObjectOutputStream(ff);
            for (Object jug:jugadores) {
                oos.writeObject(jug); 
            }
            ff.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FantasyArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FantasyArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}