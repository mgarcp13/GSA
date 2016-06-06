package gsa.gsa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gsa.Interfaz.AdministracionAdapter;
import gsa.database.GSAQuerys;

public class Administracion extends AppCompatActivity {

    private Button add, editar, eliminar, cerrar;
    private String usuario, password, acceso;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private Intent intent;
    private TableRow fila;
    private ArrayList<TableRow> listaFilas;
    private List items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion);

        //Tabla tabla = new Tabla(this, (TableLayout)findViewById(R.id.tabla));
        //tabla.agregarCabecera(R.array.cabecera_usuariossistema);

        GSAQuerys query = new GSAQuerys(getApplicationContext());
        ArrayList<String> elementos = query.getUsuariosSistema();

        items = new ArrayList();

        for (int i = 0; i < elementos.size(); i++) {
            if (i % 3 == 0)
                usuario = elementos.get(i);
            else if (i % 3 == 1)
                password = elementos.get(i);
            else if (i % 3 == 2) {
                acceso = elementos.get(i);
                items.add(new UsuariosSistema(usuario, password, acceso));
            }
        }

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new AdministracionAdapter(items);

        recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("************", "Seleccionado el elemento en la posicion " + recycler.getChildAdapterPosition(v) + "");
                editar(recycler.getChildAdapterPosition(v));
                //Toast.makeText(v.getContext(), "Pulsado el elemento " + getArguments.getInt(INDICE_SECCION))
            }
        });

        recycler.setAdapter(adapter);

    }


    
        public void editar(int posicion) {
            /*View v = recycler.getChildAt((int) adapter.getItemId(posicion));
            Log.i("************", "Seleccionado el elemento en la posicion " + posicion + "");
            TextView tvUsuario = (TextView) v.findViewById(R.id.usuario);
            TextView tvPassword = (TextView) v.findViewById(R.id.password);
            TextView tvAcceso = (TextView) v.findViewById(R.id.acceso);

            String usuario = tvUsuario.getText().toString();
            String acceso = tvAcceso.getText().toString();
            String password = tvPassword.getText().toString();*/

            UsuariosSistema usuariosSistema = (UsuariosSistema) items.get(posicion);
            usuario = usuariosSistema.getUsuario();
            password = usuariosSistema.getPassword();
            acceso = usuariosSistema.getAcceso();

            Intent intent = new Intent(Administracion.this, AgregarUsuariosSistemaActivity.class);
            intent.putExtra("usuario", usuario);
            intent.putExtra("password", password);
            intent.putExtra("acceso", acceso);
            startActivity(intent);

        }
        /*FloatingActionButton wd_add = (FloatingActionButton) findViewById(R.id.wd_add);
        wd_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Administracion.this, AgregarUsuariosSistemaActivity.class);
                startActivity(intent);
            }
        });*/

        /*String[] entrada = new String[3];
        listaFilas = new ArrayList<>();

        for(int i = 0; i<elementos.size(); i++) {
            entrada[i % 3] = elementos.get(i);
            if (i % 3 == 2)
                listaFilas.add(tabla.agregarFilaTabla(entrada));
        }*/

        /*for(int i=0; i<listaFilas.size(); i++){
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

        }*/

        //add = (Button) findViewById(R.id.btn_add);
        //editar = (Button) findViewById(R.id.btn_editar);
        //eliminar = (Button) findViewById(R.id.btn_eliminar);
        //cerrar = (Button) findViewById(R.id.btn_cerrar);

        /*add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Administracion.this, AgregarUsuariosSistemaActivity.class);
                startActivity(intent);
            }
        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Administracion.this, MainActivity.class);
                startActivity(intent);
            }
        });*/

    

   

}
