package gsa.Interfaz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import gsa.gsa.Administracion;
import gsa.gsa.Clientes;
import gsa.gsa.Contratos;
import gsa.gsa.MainActivity;
import gsa.gsa.Servicios;
import gsa.gsa.Trabajadores;

/**
 * Created by mario on 8/06/16.
 */
public class FragmentoGestion extends AppCompatActivity{

    private String pantalla;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pantalla = getIntent().getExtras().getString("pantalla").toString();
        comprobarAccion();
    }

    private void comprobarAccion() {
        if(pantalla.equals("Clientes"))
            startActivity(new Intent(FragmentoGestion.this, Clientes.class));
        else if(pantalla.equals("Trabajadores"))
            startActivity(new Intent(FragmentoGestion.this, Trabajadores.class));
        else if(pantalla.equals("Servicios"))
            startActivity(new Intent(FragmentoGestion.this, Servicios.class));
        else if(pantalla.equals("Contratos"))
            startActivity(new Intent(FragmentoGestion.this, Contratos.class));
        else if(pantalla.equals("Facturas"))
            startActivity(new Intent(FragmentoGestion.this, Clientes.class));

    }
}
