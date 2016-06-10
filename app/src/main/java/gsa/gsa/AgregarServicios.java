package gsa.gsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import gsa.database.GSAQuerys;

public class AgregarServicios extends AppCompatActivity {

    private GSAQuerys query;
    private String nombre;
    private int coste;
    private EditText et_nombre, et_coste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_servicios);

        query = new GSAQuerys(getApplicationContext());

        if(getIntent().getExtras() == null)
            addServicio();
        else {
            nombre = getIntent().getExtras().getString("nombre");
            coste = getIntent().getExtras().getInt("coste");
            editarServicio();
        }
    }

    private void editarServicio() {
        inicializarRecursos();

        et_nombre.setText(nombre);
        et_coste.setText(coste+"");

        Button editar = (Button) findViewById(R.id.btn_agregar_servicio);
        editar.setText("Editar");

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = query.getServicioID(nombre);
                nombre = et_nombre.getText().toString();
                coste = Integer.parseInt(et_coste.getText().toString());
                query.editarServicio(nombre, coste, id);

                Intent intent = new Intent(AgregarServicios.this, Servicios.class);
                startActivity(intent);

            }
        });
    }

    private void addServicio() {
        inicializarRecursos();
        Button aceptar = (Button) findViewById(R.id.btn_agregar_servicio);
        if (aceptar != null) {
            aceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nombre = et_nombre.getText().toString();
                    coste = Integer.parseInt(et_coste.getText().toString());
                    query.addServicio(nombre, coste);

                    Intent intent = new Intent(AgregarServicios.this, Servicios.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void inicializarRecursos() {
        et_nombre = (EditText) findViewById(R.id.et_nombre_servicio);
        et_coste = (EditText) findViewById(R.id.et_coste_servicio);
    }
}