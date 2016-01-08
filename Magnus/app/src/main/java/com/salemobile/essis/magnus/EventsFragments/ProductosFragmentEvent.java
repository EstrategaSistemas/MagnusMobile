package com.salemobile.essis.magnus.EventsFragments;
import com.salemobile.essis.magnus.Entidades.Productos;

import java.util.List;

/**
 * Created by Adan on 28/12/2015.
 */
public class ProductosFragmentEvent {
    public Productos valor;
    public ProductosFragmentEvent (Productos valor){
        this.valor=valor;
        System.out.println("Clase ClientesFragmentEvent:" + valor);
    }



}
