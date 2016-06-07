package gsa.gsa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gsa.Interfaz.AdministracionAdapter;
import gsa.Interfaz.ClientesAdapter;
import gsa.database.GSAQuerys;

public class Clientes extends AppCompatActivity {

    private RecyclerView recycler;
    private ClientesAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    ArrayList<String> elementos;
    private String nombre, apellidos, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List items = new ArrayList();
        this.buscarClientes();

        for (int i = 0; i < elementos.size(); i++) {
            if (i % 3 == 0)
                nombre = elementos.get(i);
            else if (i % 3 == 1)
                apellidos = elementos.get(i);
            else if (i % 3 == 2) {
                email = elementos.get(i);
                items.add(new Cliente(nombre, apellidos, email));
            }
        }

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador_clientes);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new ClientesAdapter(items);
        recycler.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Clientes.this, AgregarClientes.class);
                    startActivity(intent);
                }
            });
        }


    }


    private void buscarClientes() {
        GSAQuerys query = new GSAQuerys(getApplicationContext());
        elementos = query.getClientes();
    }

}
