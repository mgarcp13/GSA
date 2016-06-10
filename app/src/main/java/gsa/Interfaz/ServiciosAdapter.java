package gsa.Interfaz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gsa.database.GSAQuerys;
import gsa.gsa.R;
import gsa.gsa.Servicio;
import gsa.gsa.Servicios;
import gsa.gsa.Trabajador;

/**
 * Created by mario on 10/06/16.
 */
public class ServiciosAdapter  extends RecyclerView.Adapter<ServiciosAdapter.ServiciosViewHolder>
        implements View.OnClickListener {

    private View.OnClickListener listener;
    private List<Servicio> items;

    public static class ServiciosViewHolder extends RecyclerView.ViewHolder {

        // Campos respectivos de un item
        public TextView nombre, coste;

        public ServiciosViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_apellidos);
            coste = (TextView) v.findViewById(R.id.dni);
        }
    }

    public ServiciosAdapter(List<Servicio> items) {

        this.items = items;
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    @Override
    public ServiciosViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.clientes_card, viewGroup, false);
        v.setOnClickListener(this);
        return new ServiciosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ServiciosViewHolder viewHolder, int i) {
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.coste.setText("Coste: " + items.get(i).getCoste() + "€/hora");
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view){
        if(listener != null)
            listener.onClick(view);
    }

    public void remove(int posicion, Context c) {
        Servicio servicio = (Servicio) this.items.get(posicion);
        String nombre = servicio.getNombre().toString();
        GSAQuerys query = new GSAQuerys(c);
        int id = query.getServicioID(nombre);
        query.eliminarServicio(id);
        Intent intent = new Intent(c.getApplicationContext(), Servicios.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(intent);
    }
}



