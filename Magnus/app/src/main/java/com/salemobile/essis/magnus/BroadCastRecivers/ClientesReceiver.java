package com.salemobile.essis.magnus.BroadCastRecivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import com.salemobile.essis.magnus.Entidades.Clientes;

/**
 * Created by Adan on 24/11/2015.
 */
public class ClientesReceiver extends BroadcastReceiver {
    public static final int CLIENTE_SELECCIONADO=1;
    public static final int CLIENTE_AGREGADO=2;
private final ArrayAdapter<Clientes> adapter;
    public ClientesReceiver(ArrayAdapter<Clientes> adapter)
    {
        this.adapter = adapter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
    int operacion = intent.getIntExtra("action",-1);
        switch (operacion){
            case CLIENTE_SELECCIONADO : seleccionCliente(intent); break;
        }
    }

    private Clientes seleccionCliente(Intent intent) {
        Clientes cliente = (Clientes)intent.getSerializableExtra("datos");
        return  cliente;
    }
}
