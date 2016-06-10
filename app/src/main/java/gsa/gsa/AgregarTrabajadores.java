package gsa.gsa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import gsa.database.GSAQuerys;

public class AgregarTrabajadores extends AppCompatActivity {

    private Spinner spinner_opciones;
    private String nombre, apellidos, dni, dir, poblacion, provincia, cpostal, email, telefono,
            nacionalidad, vehiculo, especialidad;
    private EditText et_nombre, et_apellidos, et_dni, et_dir, et_poblacion, et_provincia, et_cpostal,
            et_email, et_telefono, et_nacionalidad, et_especialidad;
    private GSAQuerys query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_trabajadores);

        vehiculo = "Si";

        ArrayAdapter<CharSequence> adaptador =
                ArrayAdapter.createFromResource(this, R.array.opciones, android.R.layout.simple_spinner_item);

        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinner_opciones = (Spinner) findViewById(R.id.spinner_vehiculo_trabajador);

        spinner_opciones.setAdapter(adaptador);
        spinner_opciones.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        vehiculo = parent.getItemAtPosition(position).toString();
                        Log.i("******", "vehiculo=" + vehiculo);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        query = new GSAQuerys(getApplicationContext());

        if(getIntent().getExtras() == null)
            addTrabajador();
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
            nacionalidad = getIntent().getExtras().getString("nacionalidad");
            especialidad = getIntent().getExtras().getString("especialidad");
            editarTrabajador();
        }

    }

    public void addTrabajador(){
        Button aceptar = (Button) findViewById(R.id.btn_agregar_trabajador);
        if (aceptar != null) {
            aceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    et_nombre = (EditText) findViewById(R.id.et_nombre_trabajador);
                    nombre = et_nombre.getText().toString();
                    et_apellidos = (EditText) findViewById(R.id.et_apellidos_trabajador);
                    apellidos = et_apellidos.getText().toString();
                    et_dni = (EditText) findViewById(R.id.et_dni_trabajador);
                    dni = et_dni.getText().toString();
                    et_dir = (EditText) findViewById(R.id.et_dir_trabajador);
                    dir = et_dir.getText().toString();
                    et_poblacion = (EditText) findViewById(R.id.et_poblacion_trabajador);
                    poblacion = et_poblacion.getText().toString();
                    et_provincia = (EditText) findViewById(R.id.et_provincia_trabajador);
                    provincia = et_provincia.getText().toString();
                    et_cpostal = (EditText) findViewById(R.id.et_cpostal_trabajador);
                    cpostal = et_cpostal.getText().toString();
                    et_email = (EditText) findViewById(R.id.et_email_trabajador);
                    email = et_email.getText().toString();
                    et_telefono = (EditText) findViewById(R.id.et_telefono_trabajador);
                    telefono = et_telefono.getText().toString();
                    et_nacionalidad = (EditText) findViewById(R.id.et_nacionalidad_trabajador);
                    nacionalidad = et_nacionalidad.getText().toString();
                    et_especialidad = (EditText) findViewById(R.id.et_especialidad_trabajador);
                    especialidad = et_especialidad.getText().toString();

                    query.addTrabajador(nombre, apellidos, dni, dir, poblacion, provincia, cpostal,
                            email, telefono, nacionalidad, vehiculo, especialidad);

                    Intent intent = new Intent(AgregarTrabajadores.this, Trabajadores.class);
                    startActivity(intent);
                }

            });
        }

    }

    public void editarTrabajador(){
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
        et_nacionalidad.setText(nacionalidad);
        et_especialidad.setText(especialidad);

        if(vehiculo.equals("Si"))
            spinner_opciones.setSelection(0);
        else if(vehiculo.equals("No"))
            spinner_opciones.setSelection(1);


        Button editar = (Button) findViewById(R.id.btn_agregar_trabajador);
        editar.setText("EDITAR");

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = query.getTrabajadorID(dni);
                nombre = et_nombre.getText().toString();
                apellidos = et_apellidos.getText().toString();
                dni = et_dni.getText().toString();
                dir = et_dir.getText().toString();
                poblacion = et_poblacion.getText().toString();
                provincia = et_provincia.getText().toString();
                cpostal = et_cpostal.getText().toString();
                email = et_email.getText().toString();
                telefono = et_telefono.getText().toString();
                nacionalidad = et_nacionalidad.getText().toString();
                especialidad = et_especialidad.getText().toString();

                query.editarTrabajador(nombre, apellidos, dni, dir, poblacion, provincia, cpostal, email,
                        telefono, nacionalidad, vehiculo, especialidad, id);

                Intent intent = new Intent(AgregarTrabajadores.this, Trabajadores.class);
                startActivity(intent);
            }
        });

    }

    private void inicializarRecursos() {
        et_nombre = (EditText) findViewById(R.id.et_nombre_trabajador);
        et_apellidos = (EditText) findViewById(R.id.et_apellidos_trabajador);
        et_dni = (EditText) findViewById(R.id.et_dni_trabajador);
        et_dir = (EditText) findViewById(R.id.et_dir_trabajador);
        et_poblacion = (EditText) findViewById(R.id.et_poblacion_trabajador);
        et_provincia = (EditText) findViewById(R.id.et_provincia_trabajador);
        et_cpostal = (EditText) findViewById(R.id.et_cpostal_trabajador);
        et_email = (EditText) findViewById(R.id.et_email_trabajador);
        et_telefono = (EditText) findViewById(R.id.et_telefono_trabajador);
        et_nacionalidad = (EditText) findViewById(R.id.et_nacionalidad_trabajador);
        et_especialidad = (EditText) findViewById(R.id.et_especialidad_trabajador);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
