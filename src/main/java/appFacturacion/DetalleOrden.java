


package appFacturacion;

import java.util.ArrayList;

public abstract class DetalleOrden {
    
    private String Producto;
    private float Precio;
    private float Cantidad;
    private ArrayList<Double> Productos;

    public DetalleOrden(String producto, float precio, float cantidad, ArrayList<Double> productos) {
        this.Producto = producto;
        this.Precio = precio;
        this.Cantidad = cantidad;
        this.Productos = productos;
    }
    

    
    public String toString() {
        return "\nProducto: " + this.Producto
                + "\n " + this.Precio
                + "\nCantidad de Topping: " + this.Cantidad
                + "\nCantidad de Jarabe: " + this.Productos;
    }
    public float CalculoPago(float precio) {
        if(precio <=0){
            this.Precio = this.Precio*this.Cantidad;
        
        }else{
            System.out.println("Este artículo es gratis");
            
        }
        return Precio;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }

    public float getCantidad() {
        return Cantidad;
    }

    public void setCantidad(float Cantidad) {
        this.Cantidad = Cantidad;
    }

    public ArrayList<Double> getProductos() {
        return Productos;
    }

    public void setProductos(ArrayList<Double> Productos) {
        this.Productos = Productos;
    }
}


   
             
