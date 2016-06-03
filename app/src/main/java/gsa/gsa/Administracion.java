package gsa.gsa;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import gsa.Interfaz.Tabla;
import gsa.database.GSAQuerys;

public class Administracion extends AppCompatActivity {

    private Button add, editar, eliminar, cerrar;
    private Intent intent;
    private TableRow fila;
    private ArrayList<TableRow> listaFilas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion);

        Tabla tabla = new Tabla(this, (TableLayout)findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_usuariossistema);

        GSAQuerys query = new GSAQuerys(getApplicationContext());
        ArrayList<String> elementos = query.getUsuariosSistema();
        String[] entrada = new String[3];
        listaFilas = new ArrayList<>();

        for(int i = 0; i<elementos.size(); i++) {
            entrada[i % 3] = elementos.get(i);
            if (i % 3 == 2)
                listaFilas.add(tabla.agregarFilaTabla(entrada));
        }

        for(int i=0; i<listaFilas.size(); i++){
            fila = listaFilas.get(i);
            fila.setClickable(true);
            fila.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setBackgroundColor(Color.RED);
                    TextView sample = (TextView) fila.getChildAt(0);
                    String result = sample.getText().toString();
                    Log.i("Evento", result);
                }
            });

        }

        add = (Button) findViewById(R.id.btn_add);
        editar = (Button) findViewById(R.id.btn_editar);
        eliminar = (Button) findViewById(R.id.btn_eliminar);
        cerrar = (Button) findViewById(R.id.btn_cerrar);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Administracion.this, UsuariosSistema.class);
                startActivity(intent);
            }
        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Administracion.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
