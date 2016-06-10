package gsa.gsa;

/**
 * Created by mario on 6/06/16.
 */
public class Cliente {

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    private String nombre, apellidos, dni;

    public Cliente(String nombre, String apellidos, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }
}
