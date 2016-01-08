package com.salemobile.essis.magnus.Adaptadores;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.salemobile.essis.magnus.Entidades.Clientes;
import com.salemobile.essis.magnus.R;
public class ClientesView extends LinearLayout{
    private TextView credito;
    private TextView nombre;
    public ClientesView(Context context) {
        super(context);
        inflate(context, R.layout.list_item, this);
        nombre        = (TextView) findViewById(R.id.lblNombre);
        credito        = (TextView) findViewById(R.id.lblCredito);
    }
    public void setCliente(Clientes cliente) {
        nombre.setText(""+cliente.getNombre());
        credito.setText(""+cliente.getCredito());
    }
}
