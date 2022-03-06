
package appFacturacion;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;


public class Orden extends DetalleOrden{
    
    private Date fecha;
    private DetalleOrden detalleOrden;
    private Pago pago;
    private Cliente client;

    public Orden(Date fecha, DetalleOrden detalleOrden, Pago pago, Cliente client, String producto, float precio, float cantidad, ArrayList<Double> productos) {
        super(producto, precio, cantidad, productos);
        this.fecha = fecha;
        this.detalleOrden = detalleOrden;
        this.pago = pago;
        this.client = client;
    }
}

    
