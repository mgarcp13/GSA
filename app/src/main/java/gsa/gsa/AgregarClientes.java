package gsa.gsa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import gsa.database.GSAQuerys;

public class AgregarClientes extends AppCompatActivity {

    private EditText et_nombre, et_apellidos, et_dni, et_dir, et_poblacion, et_provincia, et_cpostal,
            et_email, et_telefono;
    private String nombre, apellidos, dni, dir, poblacion, provincia, cpostal, email, telefono;
    private GSAQuerys query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_clientes);

        query = new GSAQuerys(getApplicationContext());

        if(getIntent().getExtras() == null)
            addCliente();
        else{
            nombre = getIntent().getExtras().getString("nombre");
            apellidos = getIntent().getExtras().getString("apellidos");
            dni = getIntent().getExtras().getString("dni");
            dir = getIntent().getExtras().getString("direccion");
            poblacion = getIntent().getExtras().getString("poblacion");
            provincia = getIntent().getExtras().getString("provincia");
            cpostal = getIntent().getExtras().getString("codpostal");
            email = getIntent().getExtras().getString("email");
            telefono = getIntent().getExtras().getString("telefono");
            editarCliente();
        }
    }

    private void editarCliente() {
        inicializarRecursos();
        et_nombre.setText(nombre);
        et_apellidos.setText(apellidos);
        et_dni.setText(dni);
        et_dir.setText(dir);
        et_poblacion.setText(poblacion);
        et_provincia.setText(provincia);
        et_cpostal.setText(cpostal);
        et_email.setText(email);
        et_telefono.setText(telefono);

        Button editar = (Button) findViewById(R.id.btn_agregar_cliente);
        editar.setText("EDITAR");

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = et_nombre.getText().toString();
                apellidos = et_apellidos.getText().toString();
                dni = et_dni.getText().toString();
                dir = et_dir.getText().toString();
                poblacion = et_poblacion.getText().toString();
                provincia = et_provincia.getText().toString();
                cpostal = et_cpostal.getText().toString();
                email = et_email.getText().toString();
                telefono = et_telefono.getText().toString();

                int id = query.getClienteID(dni);
                query.editarCliente(nombre, apellidos, dni, dir, poblacion, provincia, cpostal, email, telefono, id);

                Intent intent = new Intent(AgregarClientes.this, Clientes.class);
                startActivity(intent);
            }
        });

    }

    private void inicializarRecursos() {
        et_nombre = (EditText) findViewById(R.id.et_nombre_cliente);
        et_apellidos = (EditText) findViewById(R.id.et_apellidos_cliente);
        et_dni = (EditText) findViewById(R.id.et_dni_cliente);
        et_dir = (EditText) findViewById(R.id.et_dir_cliente);
        et_poblacion = (EditText) findViewById(R.id.et_poblacion_cliente);
        et_provincia = (EditText) findViewById(R.id.et_provincia_cliente);
        et_cpostal = (EditText) findViewById(R.id.et_cpostal_cliente);
        et_email = (EditText) findViewById(R.id.et_email_cliente);
        et_telefono = (EditText) findViewById(R.id.et_telefono_cliente);
    }

    private void addCliente() {
        Button aceptar = (Button) findViewById(R.id.btn_agregar_cliente);
        if (aceptar != null) {
            aceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    et_nombre = (EditText) findViewById(R.id.et_nombre_cliente);
                    nombre = et_nombre.getText().toString();
                    et_apellidos = (EditText) findViewById(R.id.et_apellidos_cliente);
                    apellidos = et_apellidos.getText().toString();
                    et_dni = (EditText) findViewById(R.id.et_dni_cliente);
                    dni = et_dni.getText().toString();
                    et_dir = (EditText) findViewById(R.id.et_dir_cliente);
                    dir = et_dir.getText().toString();
                    et_poblacion = (EditText) findViewById(R.id.et_poblacion_cliente);
                    poblacion = et_poblacion.getText().toString();
                    et_provincia = (EditText) findViewById(R.id.et_provincia_cliente);
                    provincia = et_provincia.getText().toString();
                    et_cpostal = (EditText) findViewById(R.id.et_cpostal_cliente);
                    cpostal = et_cpostal.getText().toString();
                    et_email = (EditText) findViewById(R.id.et_email_cliente);
                    email = et_email.getText().toString();
                    et_telefono = (EditText) findViewById(R.id.et_telefono_cliente);
                    telefono = et_telefono.getText().toString();

                    query.addCliente(nombre, apellidos, dni, dir, poblacion, provincia, cpostal, email, telefono);

                    Intent intent = new Intent(AgregarClientes.this, Clientes.class);
                    startActivity(intent);
                }

            });
        }
    }

}
