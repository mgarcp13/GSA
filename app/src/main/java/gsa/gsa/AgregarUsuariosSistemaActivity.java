package gsa.gsa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import gsa.database.GSAQuerys;

public class AgregarUsuariosSistemaActivity extends AppCompatActivity {

    private Button Aceptar;
    EditText et_nombre, et_password, et_acceso;
    private String usuario, password;
    private int acceso;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_usuarios_sistema);
        et_nombre = (EditText) findViewById(R.id.et_usuario);
        et_password = (EditText) findViewById(R.id.et_password);
        et_acceso = (EditText) findViewById(R.id.et_acceso);

        if(getIntent().getExtras() == null)
            addUsuarioSistema();
        else{
            String usuario = getIntent().getExtras().getString("usuario");
            String password = getIntent().getExtras().getString("password");
            String acceso = getIntent().getExtras().getString("acceso");
            editarUsuarioSistema(usuario, password, acceso);
        }


    }

    private void editarUsuarioSistema(final String usuario, final String password, final String acceso) {
        et_nombre.setText(usuario);
        et_password.setText(password);
        et_acceso.setText(acceso);

        final GSAQuerys query = new GSAQuerys(getApplicationContext());
        final int id = query.getUsuarioSistemaID(usuario, password, Integer.parseInt(acceso));

        Aceptar = (Button) findViewById(R.id.btn_aceptar);
        Aceptar.setText("Editar");

        Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = et_nombre.getText().toString();
                String password = et_password.getText().toString();
                String acceso = et_acceso.getText().toString();
                GSAQuerys query = new GSAQuerys(getApplicationContext());
                query.editarUsuarioSistema(usuario, password, Integer.parseInt(acceso), id);

                intent = new Intent(AgregarUsuariosSistemaActivity.this, Administracion.class);
                startActivity(intent);
            }
        });




    }

    public void addUsuarioSistema(){
        Aceptar = (Button) findViewById(R.id.btn_aceptar);
        Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_nombre = (EditText) findViewById(R.id.et_usuario);
                et_password = (EditText) findViewById(R.id.et_password);
                et_acceso = (EditText) findViewById(R.id.et_acceso);

                usuario = et_nombre.getText().toString();
                password = et_password.getText().toString();
                acceso = Integer.parseInt(et_acceso.getText().toString());

                GSAQuerys query = new GSAQuerys(getApplicationContext());
                query.addUsuarioSistema(usuario, password, acceso);

                intent = new Intent(AgregarUsuariosSistemaActivity.this, Administracion.class);
                startActivity(intent);
            }
        });
    }

}
