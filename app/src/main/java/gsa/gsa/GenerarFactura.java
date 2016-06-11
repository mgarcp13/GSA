package gsa.gsa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import gsa.database.GSAQuerys;

public class GenerarFactura extends AppCompatActivity {

    private Spinner spinner_cliente;
    private String nombre;
    private int idCliente;
    private ArrayList<String> nombreClientes;
    private ArrayList<Integer> idClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generar_factura);

        inicializarRecursos();

        spinner_cliente.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        nombre = parent.getItemAtPosition(position).toString();
                        idCliente = idClientes.get(position);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        Button aceptar = (Button) findViewById(R.id.btn_generar_factura);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generarFactura();
            }
        });

    }

    private void generarFactura() {
        GSAQuerys query = new GSAQuerys(getApplicationContext());
        ArrayList<Integer> importeContratos = query.getContratosCliente(idCliente);
        int importe = getImporteTotal(importeContratos);
        query.generarFactura(idCliente, importe, "NO");
        Intent intent = new Intent(GenerarFactura.this, Facturas.class);
        startActivity(intent);
    }

    private int getImporteTotal(ArrayList<Integer> importeContratos) {
        int importe=0;
        for(int i=0; i<importeContratos.size(); i++){
            importe+=importeContratos.get(i);
        }
        return importe;
    }

    private void inicializarRecursos() {
        spinner_cliente = (Spinner) findViewById(R.id.spinner_factura_nombre_cliente);
        GSAQuerys query = new GSAQuerys(getApplicationContext());
        nombreClientes = query.getNombreClientes();
        idClientes = query.getClientesIds();

        ArrayAdapter<String> adaptadorNombreClientes =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, nombreClientes);
        adaptadorNombreClientes.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinner_cliente.setAdapter(adaptadorNombreClientes);
    }

}
