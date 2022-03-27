
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
        ArrayList<String> listaProdFinal = new ArrayList<String>();
        
        /*for (int i = 0; i < this.detalleOrden.getProductos().size(); i++) {
            Producto productoActual =this.detalleOrden.getProductos().get(i);
            
        }*/
        String ord=this.getClient().toString()+
                "\n"+this.getPago().toString()
                +"\n";
        
        for (Producto producto : this.getDetalleOrden().getProductos()) {
            ord+=producto.toString()
                    +"\n";
        }
        
        ord+="Subtotal: "+this.calculoTotal()+
                "\nTotal: " +this.calculoImpuesto();
        return ord;
    }
    
    
    
    
    public void finalizarOrden(int tipoPago){
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
        if(tipoPago==1){
                Credito cred=(Credito) this.getPago();
                orden+="Tipo Pago: "+cred.getTipoTarejta().getDescripcion() +" Numero Tarjeta: "+cred.getNoTarjetaCredito();
                
        }else{
            Contado cont=(Contado) this.getPago();
            orden+="Tipo Pago: Contado, Moneda: "+cont.getTipoMoneda();
        }
        
        
        
        System.out.println(orden);
        orden+=" Total: "+pago.montoLetras(totalOficial+"");
        JOptionPane.showMessageDialog(null, this.toString());
        System.out.println(orden);
    }

    
}

    
