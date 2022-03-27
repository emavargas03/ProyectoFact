
package com.utn.logica;

import com.utn.utilidades.Archivos;
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
    
    private boolean descuento;
    private String porDesc;
    
    
    
    public Orden(Date fecha, DetalleOrden detalleOrden, Pago pago, Cliente client,boolean descuento,String porDesc) {
        this.fecha = fecha;
        this.detalleOrden = detalleOrden;
        this.pago = pago;
        this.client = client;
//        this.impuesto=impuesto;
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


    @Override
    public String toString() {
        return "";
    }
    
    
    public void finalizarOrden(int tipoCliente,int tipoPago){
        Archivos archivo=new Archivos();
        
        String orden="Cliente: "+client.getNombre()+" Fecha: "+fecha.toString();
        
        float totalOficial= 0.0f;
        if(descuento){
            String porc="0."+porDesc;
            float descuento = Float.parseFloat(porc);
            totalOficial=this.calculoImpuesto()-(this.calculoImpuesto()*descuento);
        }else{
            totalOficial=this.calculoImpuesto();
        }
        if(tipoPago==0){
                Credito cred=(Credito) this.getPago();
                orden+="Tipo Pago: "+cred.getNoTarjetaCredito();
        }
        
        
        orden+=" Total: "+totalOficial;
    }

    
}

    
