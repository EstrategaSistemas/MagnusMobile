package com.salemobile.essis.magnus.Entidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Adan on 28/12/2015.
 */
public class Productos implements Serializable {
    private int IdProducto, IdTalla,Existencia;
    private String CodigoBarras,Descripcion,TipoProducto;
    private  Double Precio1,Precio2,Precio3,Precio4,Precio5;

    public  Productos(JSONObject ObjetoJSON) throws JSONException {
        IdProducto =ObjetoJSON.getInt("IdProducto");
        IdTalla = ObjetoJSON.getInt("IdTalla");
        Existencia = ObjetoJSON.getInt("Existencia");
        CodigoBarras = ObjetoJSON.getString("CodigoBarras");
        Descripcion = ObjetoJSON.getString("Descripcion");
        TipoProducto = ObjetoJSON.getString("TipoProducto");
        Precio1 = ObjetoJSON.getDouble("Precio1");
        Precio2 = ObjetoJSON.getDouble("Precio2");
        Precio3 = ObjetoJSON.getDouble("Precio3");
        Precio4 = ObjetoJSON.getDouble("Precio4");
        Precio5 = ObjetoJSON.getDouble("Precio5");

    }
    public  Productos()  {


    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {
        IdProducto = idProducto;
    }

    public int getIdTalla() {
        return IdTalla;
    }

    public void setIdTalla(int idTalla) {
        IdTalla = idTalla;
    }

    public int getExistencia() {
        return Existencia;
    }

    public void setExistencia(int existencia) {
        Existencia = existencia;
    }

    public String getCodigoBarras() {
        return CodigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        CodigoBarras = codigoBarras;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getTipoProducto() {
        return TipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        TipoProducto = tipoProducto;
    }

    public Double getPrecio1() {
        return Precio1;
    }

    public void setPrecio1(Double precio1) {
        Precio1 = precio1;
    }

    public Double getPrecio2() {
        return Precio2;
    }

    public void setPrecio2(Double precio2) {
        Precio2 = precio2;
    }

    public Double getPrecio3() {
        return Precio3;
    }

    public void setPrecio3(Double precio3) {
        Precio3 = precio3;
    }

    public Double getPrecio4() {
        return Precio4;
    }

    public void setPrecio4(Double precio4) {
        Precio4 = precio4;
    }

    public Double getPrecio5() {
        return Precio5;
    }

    public void setPrecio5(Double precio5) {
        Precio5 = precio5;
    }
}
