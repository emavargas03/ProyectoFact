package com.utn.logica;

import java.util.ArrayList;
import java.util.Date;

public abstract class Cliente {

    private String Nombre;
    private String Sexo;

    public Cliente(String Nombre, String Sexo) {
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
