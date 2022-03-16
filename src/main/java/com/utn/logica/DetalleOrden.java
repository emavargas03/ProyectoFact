package com.utn.logica;

import java.util.ArrayList;

public class DetalleOrden {
    
    private ArrayList<Producto> Productos;
    
    
    
    public DetalleOrden(ArrayList<Producto> productos) {
        
        this.Productos = productos;
    }
    

    
    public String toString() {
        String productos="";
        for (Producto Producto1 : Productos) {
            productos+="\nCodigo: "+Producto1.getCodigoProducto()+
                    "\nDescripcion: "+Producto1.getDescripcion();
        }
        
        return "\nDetalle Orden"+productos;
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
//    public int getCantidad() {
//        return cantidad;
//    }
//
//    /**
//     * @param cantidad the cantidad to set
//     */
//    public void setCantidad(int cantidad) {
//        this.cantidad = cantidad;
//    }
}


   
             
