package com.salemobile.essis.magnus.EventsFragments;

import com.salemobile.essis.magnus.Entidades.Productos;

import java.util.List;

/**
 * Created by Adan on 04/01/2016.
 */
public class ProductosFragmentEventList {
    public List<Productos> valor;
    public ProductosFragmentEventList (List<Productos> valor){
        this.valor=valor;
        System.out.println("Clase ClientesFragmentEvent:" + valor);
    }

}
