package com.utn.logica;

/*
Se importa la bibliteca de array para utilizar listas
 */
import java.util.ArrayList;

public class DetalleOrden {
     /*
    Se define el la Lista Productos
     */
    private ArrayList<Producto> Productos;
    
    
    /*
    Se crea el constructor para la lista
     */
    public DetalleOrden(ArrayList<Producto> productos) {
        
        this.Productos = productos;
    }
    
    /*
    El método toStrin para imprimir los datos de producto
     */
    
    @Override
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
    
     /*
    Se crean los respectivos geters y seters de la clase 
     */
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


   
             
