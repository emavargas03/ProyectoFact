/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.logica;



/**
 *
 * @author Emanuel
 */

/*
Esta sección del Proyecto se crea la clase Ocasional esa clase forma parte de Cliente
 */
public class Ocasional extends Cliente {

    /*
    Definición de variables 
     */ 
    private String direccion;

    
     /*
    Creación de los constructores de los atributos de la clase 
     */
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
    
    
    /*
    Se crean los respectivos geters y seters de la clase 
     */
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
