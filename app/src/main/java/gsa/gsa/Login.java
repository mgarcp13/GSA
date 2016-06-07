package gsa.gsa;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import gsa.database.GSAQuerys;

public class Login extends AppCompatActivity {

    private EditText et_nombre, et_password;
    private String nombre, password;
    private Button login;
    private SQLiteDatabase db;
    private int acceso;
    private Intent intent;

    private final int ADMINISTRADOR = 0;
    private final int GESTOR = 1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //crear referencias a los campos de texto de Nombre y Contraseña
        et_nombre = (EditText) findViewById(R.id.et_usuario);
        et_password = (EditText) findViewById(R.id.et_password);
        login = (Button) findViewById(R.id.btn_login);


        //evento al pulsar el boton del login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtener el nombre y la contraseña
                nombre = et_nombre.getText().toString();
                password = et_password.getText().toString();

                GSAQuerys query = new GSAQuerys(getApplicationContext());
                acceso = query.checkLogin(nombre, password);

                //se definen los distintos tipos de accesos
                if (acceso == ADMINISTRADOR) {
                    intent = new Intent(Login.this, Administracion.class);
                    startActivity(intent);
                }

                //proceso de autenticacion
                else if (acceso == GESTOR) {
                    /*intent = new Intent(Login.this, Clientes.class);
                    startActivity(intent);*/
                    TextView control = (TextView) findViewById(R.id.control);
                    control.setText("Autenticado como Gestor");
                } else {
                    TextView control = (TextView) findViewById(R.id.control);
                    control.setText("Error de autenticación");
                }
            }
        });
    }

}
