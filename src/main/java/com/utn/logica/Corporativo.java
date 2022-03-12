package com.utn.logica;

import java.util.ArrayList;
import java.util.Date;

public class Corporativo extends Cliente {

    private String telefono;
    private String email;

    public Corporativo( String Nombre,String Sexo,String telefono, String email) {
        super(Nombre, Sexo);
        this.telefono = telefono;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Nombre: "+ this.getNombre()+"\n"+
                "Sexo: "+this.getSexo()+"\n"+
                "Telefono: "+this.telefono+"\n"+
                "Email: "+this.email;
    }
    
    

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
