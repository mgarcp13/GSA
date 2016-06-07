package gsa.gsa;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import gsa.database.GSADatabase;
import gsa.database.GSAQuerys;
import gsa.database.GSAReaderDbHelper;

public class MainActivity extends AppCompatActivity {

    private EditText et_nombre, et_password;
    private String nombre, password;
    private Button login;
    private SQLiteDatabase db;
    private int acceso;

    private final int ADMINISTRADOR = 0;
    private final int GESTOR = 1;

    Button btnLogin;
    Button btnRegistro;
    GSADatabase database;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GSAReaderDbHelper database = new GSAReaderDbHelper(this);
        //this.database = new GSADatabase(database.getReadableDatabase());
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
                    intent = new Intent(MainActivity.this, Administracion.class);
                    startActivity(intent);
                }

                //proceso de autenticacion
                else if (acceso == GESTOR) {
                    intent = new Intent(MainActivity.this, Clientes.class);
                    startActivity(intent);

                } else {
                    TextView control = (TextView) findViewById(R.id.control);
                    control.setText("Error de autenticación");
                }
            }
        });

    }
}
