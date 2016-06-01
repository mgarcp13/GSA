package gsa.gsa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import gsa.database.GSAQuerys;

public class UsuariosSistema extends AppCompatActivity {

    private Button Aceptar;
    EditText et_nombre, et_password, et_acceso;
    private String nombre, password;
    private int acceso;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios_sistema);

        Aceptar = (Button) findViewById(R.id.btn_aceptar);
        Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_nombre = (EditText) findViewById(R.id.et_usuario);
                et_password = (EditText) findViewById(R.id.et_password);
                et_acceso = (EditText) findViewById(R.id.et_acceso);

                nombre = et_nombre.getText().toString();
                password = et_password.getText().toString();
                acceso = Integer.parseInt(et_acceso.getText().toString());

                GSAQuerys query = new GSAQuerys(getApplicationContext());
                query.addUsuarioSistema(nombre, password, acceso);

                intent = new Intent(UsuariosSistema.this, Administracion.class);
                startActivity(intent);
            }
        });
    }

}
