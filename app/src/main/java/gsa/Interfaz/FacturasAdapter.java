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
import gsa.gsa.Factura;
import gsa.gsa.Facturas;
import gsa.gsa.R;

/**
 * Created by mario on 11/06/16.
 */
public class FacturasAdapter extends RecyclerView.Adapter<FacturasAdapter.FacturasViewHolder>
        implements View.OnClickListener {

    private View.OnClickListener listener;
    private List<Factura> items;

    public static class FacturasViewHolder extends RecyclerView.ViewHolder {

        // Campos respectivos de un item
        public TextView nombre_cliente, importe_factura, pagado_factura;

        public FacturasViewHolder(View v) {
            super(v);
            nombre_cliente = (TextView) v.findViewById(R.id.factura_nombre_cliente);
            importe_factura = (TextView) v.findViewById(R.id.factura_importe);
            pagado_factura = (TextView) v.findViewById(R.id.factura_pagado);
        }
    }

    public FacturasAdapter(List<Factura> items) {

        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public FacturasViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.facturas_card, viewGroup, false);
        v.setOnClickListener(this);
        return new FacturasViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FacturasViewHolder viewHolder, int i) {
        viewHolder.nombre_cliente.setText("Cliente: " + items.get(i).getCliente());
        viewHolder.importe_factura.setText("Importe: " + items.get(i).getImporte() + "€");
        viewHolder.pagado_factura.setText("Pagado: " + items.get(i).getPagado());
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
        Factura factura = this.items.get(posicion);
        int id = factura.getId();
        GSAQuerys query = new GSAQuerys(c);
        query.eliminarFactura(id);
        Intent intent = new Intent(c.getApplicationContext(), Facturas.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(intent);
    }
}
