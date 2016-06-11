package gsa.gsa;

/**
 * Created by mario on 11/06/16.
 */
public class Factura {

    public int getId() {
        return id;
    }

    public int getImporte() {
        return importe;
    }

    public String getCliente() {
        return cliente;
    }

    public String getPagado() {
        return pagado;
    }

    private int id, importe;
    private String cliente, pagado;

    public Factura(int id, String cliente, int importe, String pagado) {
        this.id = id;
        this.cliente = cliente;
        this.importe = importe;
        this.pagado = pagado;
    }
}
