
package com.utn.logica;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;


public class Orden {
    
    private Date fecha;
    private DetalleOrden detalleOrden;
    private Pago pago;
    private Cliente client;
    protected float impuesto;
    
    
    public Orden(Date fecha, DetalleOrden detalleOrden, Pago pago, Cliente client, float impuesto) {
        this.fecha = fecha;
        this.detalleOrden = detalleOrden;
        this.pago = pago;
        this.client = client;
        this.impuesto=impuesto;
    }
    
    public float calculoTotal(){
        ArrayList<Producto> detalle=detalleOrden.getProductos();
        float total=0f;
        
        for (Producto producto : detalle) {
            total+=(producto.getCantidad()*(producto.getCosto()+(producto.getCosto()*producto.getUtilidad())));
        }
        
        return total;
    }
    
    public float calculoImpuesto(){
        
        return this.calculoTotal()+(this.calculoTotal()*impuesto);
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

    /**
     * @return the impuesto
     */
    public float getImpuesto() {
        return impuesto;
    }

    /**
     * @param impuesto the impuesto to set
     */
    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }

    
}

    
