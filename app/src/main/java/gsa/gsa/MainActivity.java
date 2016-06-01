package gsa.gsa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import gsa.database.GSADataSource;
import gsa.database.GSADatabase;
import gsa.database.GSAReaderDbHelper;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnRegistro;
    GSADatabase db;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GSAReaderDbHelper database = new GSAReaderDbHelper(this);
        //this.db = new GSADatabase(database.getReadableDatabase());
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.BtnLogin);
        btnRegistro = (Button) findViewById(R.id.BtnRegistro);

        // Evento al pulsar el boton login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, Login.class);
                //intent.putExtra("DATABASE", db);
                startActivity(intent);
            }
        });

        // Evento al pulsar el boton registro
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
