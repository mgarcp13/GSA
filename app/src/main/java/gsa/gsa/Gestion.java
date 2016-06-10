package gsa.gsa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import gsa.Interfaz.FragmentoGestion;

/**
 * Created by mario on 8/06/16.
 */
public class Gestion extends AppCompatActivity{

    private Toolbar appbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private Intent intent;

    public Gestion(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);

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
                                intent = new Intent(Gestion.this, FragmentoGestion.class);
                                intent.putExtra("pantalla", "Clientes");
                                startActivity(intent);
                                break;
                            case R.id.nav_trabajadores:
                                intent = new Intent(Gestion.this, FragmentoGestion.class);
                                intent.putExtra("pantalla", "Trabajadores");
                                startActivity(intent);
                                break;
                            case R.id.nav_servicios:
                                intent = new Intent(Gestion.this, FragmentoGestion.class);
                                intent.putExtra("pantalla", "Servicios");
                                startActivity(intent);
                                break;
                            case R.id.nav_facturas:
                                intent = new Intent(Gestion.this, FragmentoGestion.class);
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
    }

    public void accionesMenu(){

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
}
