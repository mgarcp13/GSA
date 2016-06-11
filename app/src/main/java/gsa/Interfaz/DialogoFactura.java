package gsa.Interfaz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import gsa.database.GSAQuerys;
import gsa.gsa.Facturas;

/**
 * Created by mario on 11/06/16.
 */
public class DialogoFactura extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setMessage("¿Desea pagar la factura?")
                .setTitle("Confirmacion")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Facturas facturas = new Facturas();
                        GSAQuerys query = new GSAQuerys(facturas.getApplicationContext());
                        dialog.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }
}