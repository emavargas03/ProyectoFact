/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.logica;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Anthony
 */
public class Ocasional extends Cliente {

    private String direccion;

    public Ocasional(String direccion, String Nombre, String Sexo) {
        super(Nombre, Sexo);
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Cliente: "+this.getNombre()+
                "\nDireccion: "+this.getDireccion()
                +"\nSexo: "+this.getSexo();
    }
    
    

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
