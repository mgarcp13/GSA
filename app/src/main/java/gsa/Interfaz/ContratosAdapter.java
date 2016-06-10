package gsa.Interfaz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gsa.database.GSAQuerys;
import gsa.gsa.Contrato;
import gsa.gsa.Contratos;
import gsa.gsa.R;

/**
 * Created by mario on 10/06/16.
 */
public class ContratosAdapter extends RecyclerView.Adapter<ContratosAdapter.ContratosViewHolder>
        implements View.OnClickListener {

    private View.OnClickListener listener;
    private List<Contrato> items;

    public static class ContratosViewHolder extends RecyclerView.ViewHolder {

        // Campos respectivos de un item
        public TextView nombre_cliente, nombre_trabajador, nombre_servicio, horas, coste;

        public ContratosViewHolder(View v) {
            super(v);
            nombre_cliente = (TextView) v.findViewById(R.id.nombre_cliente);
            nombre_trabajador = (TextView) v.findViewById(R.id.nombre_trabajador);
            nombre_servicio = (TextView) v.findViewById(R.id.nombre_servicio);
            horas = (TextView) v.findViewById(R.id.horas_contratadas);
            coste = (TextView) v.findViewById(R.id.coste_contrato);
        }
    }

    public ContratosAdapter(List<Contrato> items) {

        this.items = items;
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    @Override
    public ContratosViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contratos_card, viewGroup, false);
        v.setOnClickListener(this);
        return new ContratosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContratosViewHolder viewHolder, int i) {
        viewHolder.nombre_cliente.setText("Cliente: " + items.get(i).getCliente());
        viewHolder.nombre_trabajador.setText("Trabajador: " + items.get(i).getTrabajador());
        viewHolder.nombre_servicio.setText("Servicio: " + items.get(i).getServicio());
        viewHolder.horas.setText("Horas: " + items.get(i).getHoras());
        viewHolder.coste.setText("Importe: " + items.get(i).getCoste());
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
        Contrato contrato = (Contrato) this.items.get(posicion);
        int id = contrato.getID();
        GSAQuerys query = new GSAQuerys(c);
        //query.eliminarContrato(id);
        Intent intent = new Intent(c.getApplicationContext(), Contratos.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(intent);
    }
}
