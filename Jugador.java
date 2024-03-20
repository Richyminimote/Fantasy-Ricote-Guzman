
package pk3;

import java.io.Serializable;


public class Jugador implements Serializable {
    private String nombre;
    private int precio;
    private boolean fichado;

    public Jugador(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
        fichado=false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isFichado() {
        return fichado;
    }

    public void setFichado(boolean fichado) {
        this.fichado = fichado;
    }
    
    public void imprimir () {
        System.out.println(""+nombre+" "+precio);
    }
}
