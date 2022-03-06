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
public class Cliente extends Orden{
    
    private String Nombre;
    private String Sexo;

    public Cliente(String Nombre, String Sexo, Date fecha, DetalleOrden detalleOrden, Pago pago, Cliente client, String producto, double Precio, double cantidad, ArrayList<Double> muestra) {
        super(fecha, detalleOrden, pago, client, producto, Precio, cantidad, muestra);
        this.Nombre = Nombre;
        this.Sexo = Sexo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    
    }


        
    

   
    

