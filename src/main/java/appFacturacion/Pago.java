/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appFacturacion;

import java.util.ArrayList;
import java.util.Date;


public class Pago extends Orden{
    
    private double monto;
    
    public Pago(double monto, Date fecha, DetalleOrden detalleOrden, Pago pago, Cliente client, String producto, double Precio, double cantidad, ArrayList<Double> muestra) {
        super(fecha, detalleOrden, pago, client, producto, Precio, cantidad, muestra);
        this.monto = monto;
    }
    public double montoLetras() {
        return monto;

    }
}
