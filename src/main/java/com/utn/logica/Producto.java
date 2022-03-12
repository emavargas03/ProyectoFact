package com.utn.logica;

import java.util.ArrayList;

public class Producto {

    private String CodigoProducto;
    private String Descripcion;
    private double Costo;
    protected int cantidad;
    protected double utilidad;

    public Producto(String CodigoProducto, String Descripcion, double Costo, int cantidad, double utilidad) {
        this.CodigoProducto = CodigoProducto;
        this.Descripcion = Descripcion;
        this.Costo = Costo;
        this.cantidad=cantidad;
        this.utilidad=utilidad;
    }

    public String getCodigoProducto() {
        return CodigoProducto;
    }

    public void setCodigoProducto(String CodigoProducto) {
        this.CodigoProducto = CodigoProducto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double Costo) {
        this.Costo = Costo;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the utilidad
     */
    public double getUtilidad() {
        return utilidad;
    }

    /**
     * @param utilidad the utilidad to set
     */
    public void setUtilidad(double utilidad) {
        this.utilidad = utilidad;
    }


}
