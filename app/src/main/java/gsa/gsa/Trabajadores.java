package gsa.gsa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import gsa.Interfaz.FragmentoGestion;
import gsa.Interfaz.MovieTouchHelperClientes;
import gsa.Interfaz.MovieTouchHelperTrabajadores;
import gsa.Interfaz.TrabajadoresAdapter;
import gsa.database.GSAQuerys;

/**
 * Created by mario on 8/06/16.
 */
public class Trabajadores extends AppCompatActivity{

    private RecyclerView recycler;
    private TrabajadoresAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    ArrayList<String> elementos;
    private String nombre, apellidos, dni, dir, poblacion, provincia, cpostal, email, telefono,
            nacionalidad, vehiculo, especialidad;
    private List items;
    private Intent intent;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);

        items = new ArrayList();
        this.buscarTrabajadores();

        for (int i = 0; i < elementos.size(); i++) {
            if (i % 5 == 0)
                nombre = elementos.get(i);
            else if (i % 5 == 1)
                apellidos = elementos.get(i);
            else if (i % 5 == 2)
                dni = elementos.get(i);
            else if (i % 5 == 3)
                vehiculo = elementos.get(i);
            else if (i % 5 == 4) {
                especialidad = elementos.get(i);
                items.add(new Trabajador(nombre, apellidos, especialidad, vehiculo, dni));
            }
        }

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador_gestion);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new TrabajadoresAdapter(items);
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
                                intent = new Intent(Trabajadores.this, FragmentoGestion.class);
                                intent.putExtra("pantalla", "Clientes");
                                startActivity(intent);
                                break;
                            case R.id.nav_servicios:
                                intent = new Intent(Trabajadores.this, FragmentoGestion.class);
                                intent.putExtra("pantalla", "Servicios");
                                startActivity(intent);
                                break;
                            case R.id.nav_facturas:
                                intent = new Intent(Trabajadores.this, FragmentoGestion.class);
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

        ItemTouchHelper.Callback callback = new MovieTouchHelperTrabajadores(adapter, this.getApplicationContext());
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recycler);

        FloatingActionButton fab_add = (FloatingActionButton) findViewById(R.id.fab_add_reciclador);
        FloatingActionButton fab_exit = (FloatingActionButton) findViewById(R.id.fab_exit_reciclador);

        if (fab_add != null) {
            fab_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Trabajadores.this, AgregarTrabajadores.class);
                    startActivity(intent);
                }
            });
        }

        if (fab_exit != null) {
            fab_exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Trabajadores.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void editar(int posicion) {

        Trabajador trabajador = (Trabajador) items.get(posicion);
        GSAQuerys query = new GSAQuerys(getApplicationContext());
        dni = trabajador.getDni();
        int id = query.getTrabajadorID(dni);
        String campos[] = query.getTrabajador(id);
        nombre = campos[0];
        apellidos = campos[1];
        dni = campos[2];
        dir = campos[3];
        poblacion = campos[4];
        provincia = campos[5];
        cpostal = campos[6];
        email = campos[7];
        telefono = campos[8];
        nacionalidad = campos[9];
        vehiculo = campos[10];
        especialidad = campos[11];

        Intent intent = new Intent(Trabajadores.this, AgregarTrabajadores.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("apellidos", apellidos);
        intent.putExtra("dni", dni);
        intent.putExtra("direccion", dir);
        intent.putExtra("poblacion", poblacion);
        intent.putExtra("provincia", provincia);
        intent.putExtra("codpostal", cpostal);
        intent.putExtra("email", email);
        intent.putExtra("telefono", telefono);
        intent.putExtra("nacionalidad", nacionalidad);
        intent.putExtra("vehiculo", vehiculo);
        intent.putExtra("especialidad", especialidad);
        startActivity(intent);
    }


    private void buscarTrabajadores() {
        GSAQuerys query = new GSAQuerys(getApplicationContext());
        elementos = query.getTrabajadores();
    }
}
