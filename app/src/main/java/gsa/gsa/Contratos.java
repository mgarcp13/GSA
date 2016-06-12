package gsa.gsa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import gsa.Interfaz.ContratosAdapter;
import gsa.Interfaz.FragmentoGestion;
import gsa.Interfaz.MovieTouchHelperContratos;
import gsa.Interfaz.MovieTouchHelperServicios;
import gsa.Interfaz.ServiciosAdapter;
import gsa.database.GSAQuerys;

/**
 * Created by mario on 10/06/16.
 */
public class Contratos extends AppCompatActivity {

    private RecyclerView recycler;
    private ContratosAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    ArrayList<String> elementos;
    private String cliente, trabajador, servicio;
    private int id, horas, coste;
    private List items;
    private Intent intent;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);

        items = new ArrayList();
        this.buscarContratos();

        for (int i = 0; i < elementos.size(); i++) {
            if (i % 6 == 0)
                id = Integer.parseInt(elementos.get(i));
            else if (i % 6 == 1)
                cliente = getNombreCliente(Integer.parseInt(elementos.get(i)));
            else if (i % 6 == 2)
                trabajador = getNombreTrabajador(Integer.parseInt(elementos.get(i)));
            else if (i % 6 == 3)
                servicio = getNombreServicio(Integer.parseInt(elementos.get(i)));
            else if (i % 6 == 4)
                horas = Integer.parseInt(elementos.get(i));
            else if (i % 6 == 5){
                coste = Integer.parseInt(elementos.get(i));
                items.add(new Contrato(id, cliente, trabajador, servicio, horas, coste));
            }
        }

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador_gestion);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new ContratosAdapter(items);
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
                                intent = new Intent(Contratos.this, FragmentoGestion.class);
                                intent.putExtra("pantalla", "Clientes");
                                startActivity(intent);
                                break;
                            case R.id.nav_trabajadores:
                                intent = new Intent(Contratos.this, FragmentoGestion.class);
                                intent.putExtra("pantalla", "Trabajadores");
                                startActivity(intent);
                                break;
                            case R.id.nav_servicios:
                                intent = new Intent(Contratos.this, FragmentoGestion.class);
                                intent.putExtra("pantalla", "Servicios");
                                startActivity(intent);
                                break;
                            case R.id.nav_facturas:
                                intent = new Intent(Contratos.this, FragmentoGestion.class);
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
                //editar(recycler.getChildAdapterPosition(v));
            }
        });

        ItemTouchHelper.Callback callback = new MovieTouchHelperContratos(adapter, this.getApplicationContext());
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recycler);

        FloatingActionButton fab_add = (FloatingActionButton) findViewById(R.id.fab_add_reciclador);
        FloatingActionButton fab_exit = (FloatingActionButton) findViewById(R.id.fab_exit_reciclador);

        if (fab_add != null) {
            fab_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Contratos.this, AgregarContrato.class);
                    startActivity(intent);
                }
            });
        }

        if (fab_exit != null) {
            fab_exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Contratos.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

    private String getNombreCliente(int id) {
        GSAQuerys query = new GSAQuerys(getApplicationContext());
        String nombre = query.getNombreCliente(id);
        return nombre;
    }

    private String getNombreTrabajador(int id) {
        GSAQuerys query = new GSAQuerys(getApplicationContext());
        String nombre = query.getNombreTrabajador(id);
        return nombre;
    }

    private String getNombreServicio(int id) {
        GSAQuerys query = new GSAQuerys(getApplicationContext());
        String nombre = query.getNombreServicio(id);
        return nombre;
    }

    private void buscarContratos() {
        GSAQuerys query = new GSAQuerys(getApplicationContext());
        elementos = query.getContratos();
    }
}
