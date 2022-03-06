
package appFacturacion;

import java.util.ArrayList;


public class Producto extends DetalleOrden{
    
    
    private String CodigoProducto;
    private String Descripcion;
    private double Costo;

    public Producto(String codigoProducto, String descripcion, double costo, String producto, double Precio, double cantidad, ArrayList<Double> muestra) {
        super(producto, Precio, cantidad, muestra);
        this.CodigoProducto = codigoProducto;
        this.Descripcion = descripcion;
        this.Costo = costo;
    }

    

    
    
}
