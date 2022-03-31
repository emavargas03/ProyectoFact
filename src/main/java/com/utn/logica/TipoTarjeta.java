package com.utn.logica;

/*
Importamos la bibliteca de array para utilizar listas y se importa la biblioteca Date para utilizar fechas
 */
import java.util.ArrayList;
import java.util.Date;

public class TipoTarjeta {

    /*
    Se define la variable Descripción
     */
    private String Descripcion;

    /*
    El respectivo contructor
     */
    public TipoTarjeta(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    /*
    Lo respectivo set y get de la clase
     */
    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

}
