package gsa.gsa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class AgregarUsuariosSistemaActivity extends AppCompatActivity {

    private Button Aceptar;
    EditText et_nombre, et_password;
    Spinner spinner_acceso;
    private String usuario, password, acceso;
    private int cod_acceso;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_usuarios_sistema);
        acceso = "Administrador";
        et_nombre = (EditText) findViewById(R.id.et_usuario);
        et_password = (EditText) findViewById(R.id.et_password);
        spinner_acceso = (Spinner) findViewById(R.id.spiner_acceso);

        ArrayAdapter<CharSequence> adaptador =
                ArrayAdapter.createFromResource(this, R.array.accesos, android.R.layout.simple_spinner_item);

        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinner_acceso.setAdapter(adaptador);
        spinner_acceso.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        acceso = parent.getItemAtPosition(position).toString();
                        Log.i("****************", "acceso= " + acceso);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        if(getIntent().getExtras() == null)
            addUsuarioSistema();
        else{
            String usuario = getIntent().getExtras().getString("usuario");
            String password = getIntent().getExtras().getString("password");
            cod_acceso = getIntent().getExtras().getInt("acceso");
            editarUsuarioSistema(usuario, password);
        }


    }

    private void editarUsuarioSistema(final String usuario, final String password) {
        et_nombre.setText(usuario);
        et_password.setText(password);

        final GSAQuerys query = new GSAQuerys(getApplicationContext());
        final int id = query.getUsuarioSistemaID(usuario, password, cod_acceso);

        Aceptar = (Button) findViewById(R.id.btn_agregar_usuariosistema);
        Aceptar.setText("Editar");

        Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = et_nombre.getText().toString();
                String password = et_password.getText().toString();

                if(acceso.equals("Administrador"))
                    cod_acceso = 0;
                else if(acceso.equals("Gestor"))
                    cod_acceso = 1;

                GSAQuerys query = new GSAQuerys(getApplicationContext());
                query.editarUsuarioSistema(usuario, password, cod_acceso, id);

                intent = new Intent(AgregarUsuariosSistemaActivity.this, Administracion.class);
                startActivity(intent);
            }
        });

    }

    public void addUsuarioSistema(){
        Aceptar = (Button) findViewById(R.id.btn_agregar_usuariosistema);

        Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et_nombre = (EditText) findViewById(R.id.et_usuario);
                et_password = (EditText) findViewById(R.id.et_password);
                usuario = et_nombre.getText().toString();
                password = et_password.getText().toString();
                //cod_acceso = Integer.parseInt(spinner_acceso.getText().toString());

                if(acceso.equals("Administrador"))
                    cod_acceso = 0;
                else if(acceso.equals("Gestor"))
                    cod_acceso = 1;

                GSAQuerys query = new GSAQuerys(getApplicationContext());
                query.addUsuarioSistema(usuario, password, cod_acceso);

                intent = new Intent(AgregarUsuariosSistemaActivity.this, Administracion.class);
                startActivity(intent);
            }
        });
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
