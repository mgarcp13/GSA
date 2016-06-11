package gsa.gsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import gsa.database.GSAQuerys;

public class AgregarContrato extends AppCompatActivity {

    private Spinner spinner_cliente, spinner_trabajador, spinner_servicio;
    private EditText et_horas;
    private Button btn_aceptar;
    private ArrayList<String> nombreClientes, nombreTrabajadores, nombreServicios;
    private ArrayList<Integer> idClientes, idTrabajadores, idServicios;
    private String nombre, trabajador, servicio;
    private int idCliente, idTrabajador, idServicio, horas, coste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contrato);

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

        spinner_trabajador.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        trabajador = parent.getItemAtPosition(position).toString();
                        idTrabajador = idTrabajadores.get(position);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        spinner_servicio.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        servicio = parent.getItemAtPosition(position).toString();
                        idServicio = idServicios.get(position);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
        Button btn_aceptar = (Button) findViewById(R.id.btn_agregar_contrato);
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContrato();
            }
        });


    }

    private void addContrato() {
        GSAQuerys query = new GSAQuerys(getApplicationContext());
        horas = Integer.parseInt(et_horas.getText().toString());
        int precio = query.getServicioCoste(idServicio);
        coste = precio*horas;
        query.addContrato(idCliente, idTrabajador, idServicio, horas, coste);
        Intent intent = new Intent(AgregarContrato.this, Contratos.class);
        startActivity(intent);

    }

    private void inicializarRecursos() {
        spinner_cliente = (Spinner) findViewById(R.id.spinner_contrato_nombre_cliente);
        spinner_trabajador = (Spinner) findViewById(R.id.spinner_contrato_nombre_trabajador);
        spinner_servicio = (Spinner) findViewById(R.id.spinner_contrato_nombre_servicio);
        et_horas = (EditText) findViewById(R.id.et_contrato_horas);

        GSAQuerys query = new GSAQuerys(getApplicationContext());
        nombreClientes = query.getNombreClientes();
        idClientes = query.getClientesIds();
        nombreTrabajadores = query.getNombreTrabajadores();
        idTrabajadores = query.getTrabajadoresIds();
        nombreServicios = query.getNombreServicios();
        idServicios = query.getServiciosIds();

        agregarAccionesSpinner();


    }

    private void agregarAccionesSpinner() {
        ArrayAdapter<String> adaptadorNombreClientes =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, nombreClientes);
        adaptadorNombreClientes.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adaptadorNombreTrabajadores =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, nombreTrabajadores);
        adaptadorNombreTrabajadores.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adaptadorNombreServicios =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, nombreServicios);
        adaptadorNombreServicios.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinner_cliente.setAdapter(adaptadorNombreClientes);
        spinner_trabajador.setAdapter(adaptadorNombreTrabajadores);
        spinner_servicio.setAdapter(adaptadorNombreServicios);
    }
}
