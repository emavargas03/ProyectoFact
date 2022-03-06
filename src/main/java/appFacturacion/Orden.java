
package appFacturacion;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;


public class Orden extends DetalleOrden{
    
    private Date fecha;
    private DetalleOrden detalleOrden;
    private Pago pago;
    private Cliente client;

    public Orden(Date fecha, DetalleOrden detalleOrden, Pago pago, Cliente client, String producto, float Precio, float cantidad, ArrayList<Double> muestra) {
        super(producto, Precio, cantidad, muestra);
        this.fecha = fecha;
        this.detalleOrden = detalleOrden;
        this.pago = pago;
        this.client = client;
    }
public double CalculoTotal() {
     if (this.pago.getPago()!=0){
            return this.pago.getPago() * 10;
        }else if (this.pago <= 500){
            return (100 * 10) + ((this.pago - 100) * 15);
        }else{
            return (100 * 10) + (400 * 15) + ((this.pago - 500) * 20);
        }
    
}

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public DetalleOrden getDetalleOrden() {
        return detalleOrden;
    }

    public void setDetalleOrden(DetalleOrden detalleOrden) {
        this.detalleOrden = detalleOrden;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Cliente getClient() {
        return client;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }
