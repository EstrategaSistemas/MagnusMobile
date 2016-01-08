package com.salemobile.essis.magnus.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.salemobile.essis.magnus.Entidades.Clientes;
import com.salemobile.essis.magnus.Entidades.Productos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adan on 28/12/2015.
 */
public class ProductosListAdapter extends BaseAdapter {
    private Activity ctx;
    private Context context;
    private List<Productos> clientes;
    public  ProductosListAdapter(ArrayList<Productos> clientes)
    {
        this.clientes = clientes;
        notifyDataSetChanged();
    }
    public int getCount() {
        return clientes.size();
    }
    public Object getItem(int position) {
        return clientes.get(position);
    }
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductosView view;
        if (convertView == null)
            view = new ProductosView(parent.getContext());
        else
            view = (ProductosView) convertView;
        view.setCliente(clientes.get(position));
        return view;
    }
}
