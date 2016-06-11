package gsa.gsa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import gsa.Interfaz.DialogoFactura;
import gsa.Interfaz.FacturasAdapter;
import gsa.Interfaz.FragmentoGestion;
import gsa.Interfaz.MovieTouchHelperFacturas;
import gsa.database.GSAQuerys;

/**
 * Created by mario on 11/06/16.
 */
public class Facturas extends AppCompatActivity {

    private RecyclerView recycler;
    private FacturasAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    ArrayList<String> elementos;
    private String cliente, pagado;
    private int id_factura, importe;
    private List items;
    private Intent intent;
    private DrawerLayout drawerLayout;
    private NavigationView navView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);

        items = new ArrayList();
        this.buscarFacturas();

        for (int i = 0; i < elementos.size(); i++) {
            if (i % 4 == 0)
                id_factura = Integer.parseInt(elementos.get(i));
            else if (i % 4 == 1){
                cliente = getNombreCliente(Integer.parseInt(elementos.get(i)));
            }

            else if (i % 4 == 2)
                importe = (Integer.parseInt(elementos.get(i)));
            else if (i % 4 == 3){
                pagado = elementos.get(i);
                items.add(new Factura(id_factura, cliente, importe, pagado));
            }
        }

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador_gestion);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new FacturasAdapter(items);
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
                                intent = new Intent(Facturas.this, FragmentoGestion.class);
                                intent.putExtra("pantalla", "Clientes");
                                startActivity(intent);
                                break;
                            case R.id.nav_trabajadores:
                                intent = new Intent(Facturas.this, FragmentoGestion.class);
                                intent.putExtra("pantalla", "Trabajadores");
                                startActivity(intent);
                                break;
                            case R.id.nav_servicios:
                                intent = new Intent(Facturas.this, FragmentoGestion.class);
                                intent.putExtra("pantalla", "Servicios");
                                startActivity(intent);
                                break;
                            case R.id.nav_contratos:
                                intent = new Intent(Facturas.this, FragmentoGestion.class);
                                intent.putExtra("pantalla", "Contratos");
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
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoFactura dialogo = new DialogoFactura();
                dialogo.show(fragmentManager, "tagConfirmacion");
            }
        });

        ItemTouchHelper.Callback callback = new MovieTouchHelperFacturas(adapter, this.getApplicationContext());
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recycler);

        FloatingActionButton fab_add = (FloatingActionButton) findViewById(R.id.fab_add_reciclador);
        FloatingActionButton fab_exit = (FloatingActionButton) findViewById(R.id.fab_exit_reciclador);
        FloatingActionButton fab_pagar_factura = (FloatingActionButton) findViewById(R.id.fab_pagar_factura);

        if (fab_pagar_factura != null) {
            fab_pagar_factura.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("*******", "sd");
                    pagarFactura(recycler.getChildAdapterPosition(view));
                }
            });
        }

        if (fab_add != null) {
            fab_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Facturas.this, GenerarFactura.class);
                    startActivity(intent);
                }
            });
        }

        if (fab_exit != null) {
            fab_exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Facturas.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

    private void pagarFactura(int posicion) {
        Factura factura = (Factura) items.get(posicion);
        Log.i("*****", "Pagar la factura en la posicion " + posicion);
        id_factura = factura.getId();
        GSAQuerys query = new GSAQuerys(getApplicationContext());
        query.pagarFactura(id_factura);

    }

    private void buscarFacturas() {
        GSAQuerys query = new GSAQuerys(getApplicationContext());
        elementos = query.getFacturas();

    }

    private String getNombreCliente(int id) {
        GSAQuerys query = new GSAQuerys(getApplicationContext());
        cliente = query.getNombreCliente(id);
        return cliente;
    }

}
