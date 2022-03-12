package com.utn.logica;

import java.util.ArrayList;

public class DetalleOrden {
    
    private ArrayList<Producto> Productos;
    protected int cantidad;
    
    
    public DetalleOrden(ArrayList<Producto> productos,int cantidad) {
        this.cantidad=cantidad;
        this.Productos = productos;
    }
    

    
    public String toString() {
        return "\nProducto: ";
    }
    
    public ArrayList<Producto> getProductos() {
        return Productos;
    }

    public void setProductos(ArrayList<Producto> Productos) {
        this.Productos = Productos;
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
}


   
             
