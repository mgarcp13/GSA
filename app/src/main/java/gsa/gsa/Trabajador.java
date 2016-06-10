package gsa.gsa;

/**
 * Created by mario on 8/06/16.
 */
public class Trabajador {

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    private String nombre, apellidos, especialidad, vehiculo, dni;

    public Trabajador(String nombre, String apellidos, String especialidad, String vehiculo, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.vehiculo = vehiculo;
        this.especialidad = especialidad;
    }
}
