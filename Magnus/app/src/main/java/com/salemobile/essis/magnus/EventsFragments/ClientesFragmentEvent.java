package com.salemobile.essis.magnus.EventsFragments;

import com.salemobile.essis.magnus.Entidades.Clientes;
import com.salemobile.essis.magnus.Entidades.Productos;

/**
 * Created by Adan on 14/12/2015.
 */
public class ClientesFragmentEvent {
    public Clientes valor;
    public ClientesFragmentEvent (Clientes valor){
        this.valor=valor;
        System.out.println("Clase ClientesFragmentEvent:" + valor);
    }
}
