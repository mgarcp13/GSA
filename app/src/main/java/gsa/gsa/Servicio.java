package gsa.gsa;

/**
 * Created by mario on 10/06/16.
 */
public class Servicio {

    public String getNombre() {
        return nombre;
    }

    public int getCoste() {
        return coste;
    }

    private String nombre;
    private int coste;

    public Servicio(String nombre, int coste) {
        this.nombre = nombre;
        this.coste = coste;
    }
}
