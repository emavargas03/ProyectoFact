package com.utn.logica;

import java.util.ArrayList;

public class DetalleOrden {
    
    private ArrayList<Producto> Productos;

    public DetalleOrden(ArrayList<Producto> productos) {
        
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
}


   
             
