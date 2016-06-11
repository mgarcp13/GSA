package gsa.gsa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gsa.Interfaz.AdministracionAdapter;
import gsa.Interfaz.ClientesAdapter;
import gsa.Interfaz.FragmentoGestion;
import gsa.Interfaz.MovieTouchHelperAdministracion;
import gsa.Interfaz.MovieTouchHelperClientes;
import gsa.Interfaz.PlaceholderFragment;
import gsa.database.GSAQuerys;

public class Clientes extends AppCompatActivity {

    private RecyclerView recycler;
    private ClientesAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    ArrayList<String> elementos;
    private String nombre, apellidos, dni, dir, poblacion, provincia, cpostal, email, telefono;
    private List items;
    private Intent intent;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);

        items = new ArrayList();
        this.buscarClientes();

        for (int i = 0; i < elementos.size(); i++) {
            if (i % 3 == 0)
                nombre = elementos.get(i);
            else if (i % 3 == 1)
                apellidos = elementos.get(i);
            else if (i % 3 == 2) {
                dni = elementos.get(i);
                items.add(new Cliente(nombre, apellidos, dni));
            }
        }

        Gestion gestor = new Gestion();
        //gestor.accionesMenu();

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador_gestion);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new ClientesAdapter(items);
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
                            case R.id.nav_trabajadores:
                                intent = new Intent(Clientes.this, FragmentoGestion.class);
                                intent.putExtra("pantalla", "Trabajadores");
                                startActivity(intent);
                                break;
                            case R.id.nav_servicios:
                                intent = new Intent(Clientes.this, FragmentoGestion.class);
                                intent.putExtra("pantalla", "Servicios");
                                startActivity(intent);
                                break;
                            case R.id.nav_contratos:
                                intent = new Intent(Clientes.this, FragmentoGestion.class);
                                intent.putExtra("pantalla", "Contratos");
                                startActivity(intent);
                                break;
                            case R.id.nav_facturas:
                                intent = new Intent(Clientes.this, FragmentoGestion.class);
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

        ItemTouchHelper.Callback callback = new MovieTouchHelperClientes(adapter, this.getApplicationContext());
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recycler);

        FloatingActionButton fab_add = (FloatingActionButton) findViewById(R.id.fab_add_reciclador);
        FloatingActionButton fab_exit = (FloatingActionButton) findViewById(R.id.fab_exit_reciclador);

        if (fab_add != null) {
            fab_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Clientes.this, AgregarClientes.class);
                    startActivity(intent);
                }
            });
        }

        if (fab_exit != null) {
            fab_exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Clientes.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

    private void editar(int posicion) {

        Cliente cliente = (Cliente) items.get(posicion);
        GSAQuerys query = new GSAQuerys(getApplicationContext());
        dni = cliente.getDni();
        int id = query.getClienteID(dni);
        String campos[] = query.getCliente(id);
        nombre = campos[0];
        apellidos = campos[1];
        dni = campos[2];
        dir = campos[3];
        poblacion = campos[4];
        provincia = campos[5];
        cpostal = campos[6];
        email = campos[7];
        telefono = campos[8];

        Intent intent = new Intent(Clientes.this, AgregarClientes.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("apellidos", apellidos);
        intent.putExtra("dni", dni);
        intent.putExtra("direccion", dir);
        intent.putExtra("poblacion", poblacion);
        intent.putExtra("provincia", provincia);
        intent.putExtra("codpostal", cpostal);
        intent.putExtra("email", email);
        intent.putExtra("telefono", telefono);
        startActivity(intent);
    }


    private void buscarClientes() {
        GSAQuerys query = new GSAQuerys(getApplicationContext());
        elementos = query.getClientes();
    }

}
