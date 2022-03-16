
package com.utn.logica;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;


public class Orden {
    
    private Date fecha;
    private DetalleOrden detalleOrden;
    private Pago pago;
    private Cliente client;
    protected float impuesto;
    private boolean descuento;
    private double porDesc;
    
    
    
    public Orden(Date fecha, DetalleOrden detalleOrden, Pago pago, Cliente client, float impuesto,boolean descuento,double porDesc) {
        this.fecha = fecha;
        this.detalleOrden = detalleOrden;
        this.pago = pago;
        this.client = client;
        this.impuesto=impuesto;
        this.descuento=descuento;
        this.porDesc=porDesc;
    }
    
    public float calculoTotal(){
        ArrayList<Producto> detalle=detalleOrden.getProductos();
        float total=0f;
        
        for (Producto producto : detalle) {
            total+=(producto.getCosto()+(producto.getCosto()*producto.getUtilidad()));
            
        }
        
        return total;
    }
    
    public float calculoImpuesto(){
        ArrayList<Producto> detalle=detalleOrden.getProductos();
        float total=0f;
        
        for (Producto producto : detalle) {
            double totalU=producto.getCosto()*producto.getUtilidad();
            double totalInc=producto.getCosto()+totalU;
            total+=totalInc+(totalInc*producto.getImpuesto());
        }
        
        return total;
    }
    
    public double aplicarDescuento(double descuento){
        double totalDesc=0;
        totalDesc=this.calculoImpuesto()+(this.calculoImpuesto()*descuento);
        
        return totalDesc;
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

    @Override
    public String toString() {
        return "";
    }
    
    
    public void finalizarOrden(boolean aplicarDesc,double porcentaje){
        
    }

    
}

    
