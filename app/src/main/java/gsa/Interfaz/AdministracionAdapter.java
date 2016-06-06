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
 * Created by Mario on 03/06/2016.
 */
public class AdministracionAdapter extends RecyclerView.Adapter<AdministracionAdapter.AdministracionViewHolder>
                                implements View.OnClickListener {

    private View.OnClickListener listener;
    private List<UsuariosSistema> items;

    public static class AdministracionViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView usuario;
        public TextView password;
        public TextView acceso;

        public AdministracionViewHolder(View v) {
            super(v);
            usuario = (TextView) v.findViewById(R.id.usuario);
            password = (TextView) v.findViewById(R.id.password);
            acceso = (TextView) v.findViewById(R.id.acceso);
        }
    }

    public AdministracionAdapter(List<UsuariosSistema> items) {

        this.items = items;
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    @Override
    public AdministracionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.administracion_card, viewGroup, false);
        return new AdministracionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdministracionViewHolder viewHolder, int i) {
        viewHolder.usuario.setText(items.get(i).getUsuario());
        viewHolder.password.setText(items.get(i).getPassword());
        viewHolder.acceso.setText(items.get(i).getAcceso());
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

