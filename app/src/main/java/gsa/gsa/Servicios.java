package gsa.gsa;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import gsa.Interfaz.ClientesAdapter;
import gsa.Interfaz.FragmentoGestion;
import gsa.Interfaz.MovieTouchHelperServicios;
import gsa.Interfaz.MovieTouchHelperTrabajadores;
import gsa.Interfaz.ServiciosAdapter;
import gsa.Interfaz.TrabajadoresAdapter;
import gsa.database.GSAQuerys;

public class Servicios extends AppCompatActivity {

    private RecyclerView recycler;
    private ServiciosAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    ArrayList<String> elementos;
    private String nombre;
    private int coste;
    private List items;
    private Intent intent;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);

        items = new ArrayList();
        this.buscarServicios();

        for (int i = 0; i < elementos.size(); i++) {
            if (i % 2 == 0)
                nombre = elementos.get(i);
            else if (i % 2 == 1){
                coste = Integer.parseInt(elementos.get(i));
                items.add(new Servicio(nombre, coste));
            }
        }

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador_gestion);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new ServiciosAdapter(items);
        recycler.setAdapter(adapter);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        navView = (NavigationView)findViewById(R.id.navview);
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;
                        Fragment fragment = null;

                        switch (menuItem.getItemId()) {
                            case R.id.nav_clientes:
                                intent = new Intent(Servicios.this, FragmentoGestion.class);
                                intent.putExtra("pantalla", "Clientes");
                                startActivity(intent);
                                break;
                            case R.id.nav_trabajadores:
                                intent = new Intent(Servicios.this, FragmentoGestion.class);
                                intent.putExtra("pantalla", "Trabajadores");
                                startActivity(intent);
                                break;
                            case R.id.nav_facturas:
                                intent = new Intent(Servicios.this, FragmentoGestion.class);
                                intent.putExtra("pantalla", "Facturas");
                                startActivity(intent);
                                break;
                        }


                        menuItem.setChecked(true);
                        //getSupportActionBar().setTitle(menuItem.getTitle());
                        menuItem.getTitle();


                        drawerLayout.closeDrawers();

                        return true;
                    }
                });

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar(recycler.getChildAdapterPosition(v));
            }
        });

        ItemTouchHelper.Callback callback = new MovieTouchHelperServicios(adapter, this.getApplicationContext());
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recycler);

        FloatingActionButton fab_add = (FloatingActionButton) findViewById(R.id.fab_add_reciclador);
        FloatingActionButton fab_exit = (FloatingActionButton) findViewById(R.id.fab_exit_reciclador);

        if (fab_add != null) {
            fab_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Servicios.this, AgregarServicios.class);
                    startActivity(intent);
                }
            });
        }

        if (fab_exit != null) {
            fab_exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Servicios.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

    private void editar(int posicion) {
        Servicio servicio = (Servicio) items.get(posicion);
        GSAQuerys query = new GSAQuerys(getApplicationContext());
        nombre = servicio.getNombre();
        int id = query.getServicioID(nombre);
        String campos[] = query.getServicio(id);
        nombre = campos[0];
        coste = Integer.parseInt(campos[1]);

        Intent intent = new Intent(Servicios.this, AgregarServicios.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("coste", coste);
        startActivity(intent);
    }


    private void buscarServicios() {
        GSAQuerys query = new GSAQuerys(getApplicationContext());
        elementos = query.getServicios();
    }
}
