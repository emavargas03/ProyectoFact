/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appFacturacion;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Anthony
 */
public class Ocasional extends Cliente{
    
    private String direccion;

    public Ocasional(String direccion, String Nombre, String Sexo, Date fecha, DetalleOrden detalleOrden, Pago pago, Cliente client, String producto, double Precio, double cantidad, ArrayList<Double> muestra) {
        super(Nombre, Sexo, fecha, detalleOrden, pago, client, producto, Precio, cantidad, muestra);
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
