


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
    public double CalculoPago() {
     if (this.pago
             /*
() == this.destino.getCodZona()){
            return this.peso * 10;
        }else if (this.peso <= 500){
            return (100 * 10) + ((this.peso - 100) * 15);
        }else{
            return (100 * 10) + (400 * 15) + ((this.peso - 500) * 20);
        }
*/
}
             }
