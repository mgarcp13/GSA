package gsa.gsa;

/**
 * Created by mario on 10/06/16.
 */
public class Contrato {

    public int getID() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getServicio() {
        return servicio;
    }

    public String getTrabajador() {
        return trabajador;
    }

    public int getHoras() {
        return horas;
    }

    public int getCoste() {
        return coste;
    }

    private String cliente, servicio, trabajador;
    private int id, horas, coste;

    public Contrato(int id, String cliente, String trabajador, String servicio, int horas, int coste) {
        this.id = id;
        this.cliente = cliente;
        this.trabajador = trabajador;
        this.servicio = servicio;
        this.horas = horas;
        this.coste = coste;
    }
}

