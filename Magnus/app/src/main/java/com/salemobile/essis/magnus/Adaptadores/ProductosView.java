package com.salemobile.essis.magnus.Adaptadores;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.salemobile.essis.magnus.Entidades.Clientes;
import com.salemobile.essis.magnus.Entidades.Productos;
import com.salemobile.essis.magnus.R;

/**
 * Created by Adan on 28/12/2015.
 */
public class ProductosView extends LinearLayout {
    private TextView credito;
    private TextView nombre;
    public ProductosView(Context context) {
        super(context);
        inflate(context, R.layout.item_producto, this);
        nombre        = (TextView) findViewById(R.id.lblNombre);
        credito        = (TextView) findViewById(R.id.lblCredito);
    }
    public void setCliente(Productos cliente) {
        nombre.setText(""+cliente.getDescripcion());
        credito.setText(""+cliente.getTipoProducto());
    }
}
