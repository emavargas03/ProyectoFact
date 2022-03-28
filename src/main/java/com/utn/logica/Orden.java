
package com.utn.logica;

import com.utn.utilidades.Archivos;
import com.utn.utilidades.Numero_a_Letra;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        total=Math.round(total*100) /100f;
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
        total=Math.round(total*100) /100f;
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
        Numero_a_Letra as=new Numero_a_Letra();
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
        
        
        if(descuento){
            float totalOficial= 0.0f;
            String porc="0."+porDesc;
            
            float descuento = Float.parseFloat(porc);
            float cantidad=this.calculoImpuesto()-(this.calculoImpuesto()*descuento);
            totalOficial=cantidad;
            totalOficial=this.calculoImpuesto()-(this.calculoImpuesto()*descuento);
            if (this.getPago().getMoneda()==1) {
                String totalDolares="";
                String totalFinalDolares="";
                try {
                    totalDolares= this.getPago().conversion(this.calculoTotal());
                    totalFinalDolares=this.getPago().conversion(totalOficial);
                } catch (FileNotFoundException ex) {
                }
                ord+="Subtotal: "+ as.Convertir(totalDolares, true)+" Dolares"+
                "\nTotal IVAI + Descuento: " +as.Convertir(totalFinalDolares+"", true)+" Dolares";
            }else{
                System.out.println(totalOficial);
                ord+="Subtotal: "+as.Convertir(this.calculoTotal()+"", true)+" Colones"+
                "\nTotal IVAI + Descuento: " +as.Convertir(totalOficial+"", true)+" Colones";
            }
            
        }else{
            //totalOficial=this.calculoImpuesto();
            if (this.getPago().getMoneda()==1){
                String totalDolares="";
                String totalFinalDolares="";
                
                try {
                    totalDolares= this.getPago().conversion(this.calculoTotal());
                    totalFinalDolares=this.getPago().conversion(this.calculoImpuesto());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Orden.class.getName()).log(Level.SEVERE, null, ex);
                }
                ord+="Subtotal: "+as.Convertir(totalDolares, true)+" Dolares"+
                "\nTotal: " +as.Convertir(totalFinalDolares+"", true)+" Dolares";
            }else{
                ord+="Subtotal: "+as.Convertir(this.calculoTotal()+"", true)+" Colones"+
                "\nTotal: " +as.Convertir(this.calculoImpuesto()+"", true)+" Colones";
            }
            
        }
        
        return ord;
    }
    
    public void finalizarOrden(int tipoPago){
        Archivos archivo=new Archivos();
        
        String orden="Cliente: "+client.getNombre()+" Fecha: "+fecha.toString();
        
        
        if(tipoPago==1){
                Credito cred=(Credito) this.getPago();
                orden+="Tipo Pago: "+cred.getTipoTarejta().getDescripcion() +" Numero Tarjeta: "+cred.getNoTarjetaCredito();
                
        }else{
            Contado cont=(Contado) this.getPago();
            orden+="Tipo Pago: Contado, Moneda: "+cont.getTipoMoneda();
        }
        
        
        
        System.out.println(orden);
        //orden+=" Total: "+pago.montoLetras(totalOficial+"");
        JOptionPane.showMessageDialog(null, this.toString());
        //System.out.println(orden);
        Archivos arch=new Archivos();
        arch.guardarOrden(this.toString()+"\n----------------------------------------------\n");
    }

    
}

    
