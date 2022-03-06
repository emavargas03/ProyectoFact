
package appFacturacion;

import java.util.ArrayList;


public class Producto extends DetalleOrden{
    
    
    private String CodigoProducto;
    private String Descripcion;
    private double Costo;

    public Producto(String CodigoProducto, String Descripcion, double Costo, String producto, float precio, float cantidad, ArrayList<Double> productos) {
        super(producto, precio, cantidad, productos);
        this.CodigoProducto = CodigoProducto;
        this.Descripcion = Descripcion;
        this.Costo = Costo;
    }

    
    

    
    
}
