
package com.utn.logica;
/*
Se importan las biliotecas: BufferedWriter para leer el texto de una secuencia de entrada (como un archivo) almacenando en el búfer caracteres
El FileWriter se utiliza para escribir un matriz de caracteres en este caso ayuda para utilizar los archivos
La biblioteca ArrayList para utilizar listas 
El JOption es para imprimir fuera de la consola una ventana con información 
 */
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
   /*
    Se crean las varibles de la clase 
    */ 
    private Date fecha;
    private DetalleOrden detalleOrden;
    private Pago pago;
    private Cliente client;
    private boolean descuento;
    private String porDesc;
    
    
    /*
    Creación de los constructores de los atributos de la clase 
     */
    public Orden(Date fecha, DetalleOrden detalleOrden, Pago pago, Cliente client,boolean descuento,String porDesc) {
        
        this.fecha = fecha;
        
        this.detalleOrden = detalleOrden;
        
        this.pago = pago;
        
        this.client = client;
        
        this.descuento=descuento;
        
        this.porDesc=porDesc;
    }
    /*
    El calculo total consiste en utilizar el costo del producto y la utilidad(Esta es la gancias)para generar el costo total
    */
    public float calculoTotal(){
        ArrayList<Producto> detalle=detalleOrden.getProductos();
        float total=0f;
        for (Producto producto : detalle) {
            total+=(producto.getCosto()+(producto.getCosto()*producto.getUtilidad()));
        }
        total=Math.round(total*100) /100f;
        return total;
    }
    /*
    El metodo calcular impuesto consiste en sumar el Calculo total más el impuesto
    */
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
    
        
/*
    Se crean los respectivos geters y seters de la clase 
     */
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
    /*
    Medoto finalizar orden
     */
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

        JOptionPane.showMessageDialog(null, this.toString());

        Archivos arch=new Archivos();
        arch.guardarOrden(this.toString()+"\n----------------------------------------------\n");
    }

    
}

    
