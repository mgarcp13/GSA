package gsa.Interfaz;

/**
 * Created by mario on 8/06/16.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import gsa.database.GSAQuerys;
import gsa.gsa.Cliente;
import gsa.gsa.R;
import gsa.gsa.Servicios;
import gsa.gsa.Trabajador;
import gsa.gsa.Trabajadores;

/**
 * Created by mario on 6/06/16.
 */
public class TrabajadoresAdapter extends RecyclerView.Adapter<TrabajadoresAdapter.TrabajadoresViewHolder>
        implements View.OnClickListener {

    private View.OnClickListener listener;
    private List<Trabajador> items;

    public static class TrabajadoresViewHolder extends RecyclerView.ViewHolder {

        // Campos respectivos de un item
        public TextView nombre_apellidos, especialidad;

        public TrabajadoresViewHolder(View v) {
            super(v);
            nombre_apellidos = (TextView) v.findViewById(R.id.nombre_apellidos);
            especialidad = (TextView) v.findViewById(R.id.dni);
        }
    }

    public TrabajadoresAdapter(List<Trabajador> items) {

        this.items = items;
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    @Override
    public TrabajadoresViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.clientes_card, viewGroup, false);
        v.setOnClickListener(this);
        return new TrabajadoresViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TrabajadoresViewHolder viewHolder, int i) {
        viewHolder.nombre_apellidos.setText(items.get(i).getNombre() + " " + items.get(i).getApellidos());
        viewHolder.especialidad.setText(items.get(i).getEspecialidad() + "     Vehículo: " + items.get(i).getVehiculo());
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
        Trabajador trabajador = (Trabajador) this.items.get(posicion);
        String dni = trabajador.getDni().toString();
        GSAQuerys query = new GSAQuerys(c);
        int id = query.getTrabajadorID(dni);
        Intent intent = new Intent(c.getApplicationContext(), Trabajadores.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(intent);
    }
}

