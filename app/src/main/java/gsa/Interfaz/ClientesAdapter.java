package gsa.Interfaz;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gsa.gsa.R;
import gsa.gsa.UsuariosSistema;

/**
 * Created by mario on 6/06/16.
 */
public class ClientesAdapter extends RecyclerView.Adapter<ClientesAdapter.ClientesViewHolder>
        implements View.OnClickListener {

    private View.OnClickListener listener;
    private List<UsuariosSistema> items;

    public static class ClientesViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView apellidos;
        public TextView email;

        public ClientesViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre);
            apellidos = (TextView) v.findViewById(R.id.apellidos);
            email = (TextView) v.findViewById(R.id.email);
        }
    }

    public ClientesAdapter(List<UsuariosSistema> items) {

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
        return new ClientesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ClientesViewHolder viewHolder, int i) {
        viewHolder.nombre.setText(items.get(i).getUsuario());
        viewHolder.apellidos.setText(items.get(i).getPassword());
        viewHolder.email.setText(items.get(i).getAcceso());
    }

    public void setOnClickListener(View.OnClickListener listener){

        this.listener = listener;
    }

    @Override
    public void onClick(View view){
        if(listener != null)
            listener.onClick(view);
    }
}
