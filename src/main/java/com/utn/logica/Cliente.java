package com.utn.logica;

/*
Importamos la bibliteca de array para utilizar listas y se importa la biblioteca Date para utilizar fechas
*/
import java.util.ArrayList;
import java.util.Date;

/*
Esta sección del proyecto se crea la clase Cliente 
 */
public abstract class Cliente {

    /*
    Definición de variables 
     */
    private String Nombre;
    private String Sexo;

    /*
    Creación de los constructores de los atributos de la clase 
     */
    public Cliente(String Nombre, String Sexo) {
        this.Nombre = Nombre;
        this.Sexo = Sexo;
    }

    /*
    Se crean los respectivos geters y seters de la clase 
     */
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