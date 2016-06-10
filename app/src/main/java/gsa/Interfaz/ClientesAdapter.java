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
import gsa.gsa.Cliente;
import gsa.gsa.Clientes;
import gsa.gsa.R;
import gsa.gsa.UsuariosSistema;

/**
 * Created by mario on 6/06/16.
 */
public class ClientesAdapter extends RecyclerView.Adapter<ClientesAdapter.ClientesViewHolder>
        implements View.OnClickListener {

    private View.OnClickListener listener;
    private List<Cliente> items;

    public static class ClientesViewHolder extends RecyclerView.ViewHolder {

        // Campos respectivos de un item
        public TextView nombre_apellidos, dni;

        public ClientesViewHolder(View v) {
            super(v);
            nombre_apellidos = (TextView) v.findViewById(R.id.nombre_apellidos);
            dni = (TextView) v.findViewById(R.id.dni);
        }
    }

    public ClientesAdapter(List<Cliente> items) {

        this.items = items;
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    @Override
    public ClientesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.clientes_card, viewGroup, false);
        v.setOnClickListener(this);
        return new ClientesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ClientesViewHolder viewHolder, int i) {
        viewHolder.nombre_apellidos.setText(items.get(i).getNombre() + " " + items.get(i).getApellidos());
        viewHolder.dni.setText(items.get(i).getDni());
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
        Cliente cliente = (Cliente) this.items.get(posicion);
        String dni = cliente.getDni().toString();
        GSAQuerys query = new GSAQuerys(c);
        int id = query.getClienteID(dni);
        query.eliminarCliente(id);
        Intent intent = new Intent(c.getApplicationContext(), Clientes.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(intent);
    }
}
