package com.salemobile.essis.magnus.Adaptadores;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.salemobile.essis.magnus.Entidades.Clientes;
import java.util.ArrayList;
import java.util.List;
public class ClienteListAdapter extends BaseAdapter {
    private Activity ctx;
    private Context context;
    private List<Clientes> clientes;
    public  ClienteListAdapter(ArrayList<Clientes> clientes)
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
        ClientesView view;
        if (convertView == null)
            view = new ClientesView(parent.getContext());
        else
            view = (ClientesView) convertView;
        view.setCliente(clientes.get(position));
        return view;
    }
}
