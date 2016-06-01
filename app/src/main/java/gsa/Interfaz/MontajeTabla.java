package gsa.Interfaz;

import android.widget.TableRow;

import java.util.ArrayList;

/**
 * Created by Mario on 20/05/2016.
 */

public interface MontajeTabla {

    /**
     * Añade la cabecera a la tabla
     * @param recursocabecera Recurso (array) donde se encuentra la cabecera de la tabla
     */
    public void agregarCabecera (int recursocabecera);

    /**
     * Agrega una fila a la tabla
     * @param entrada Elementos de la fila
     * @return fila creada
     */
    public TableRow agregarFilaTabla(String[] entrada);

    /**
     * Obtiene el ancho en píxeles de un texto en un String
     * @param texto Texto
     * @return Ancho en píxeles del texto
     */
    public int obtenerAnchoPixelesTexto(String texto);

}
