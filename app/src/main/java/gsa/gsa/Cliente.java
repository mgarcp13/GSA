package gsa.gsa;

/**
 * Created by mario on 6/06/16.
 */
public class Cliente {

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    private String nombre, apellidos, email;

    public Cliente(String nombre, String apellidos, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
    }
}
