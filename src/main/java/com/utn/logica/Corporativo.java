package com.utn.logica;
/*
Importamos la bibliteca de array para utilizar listas y se importa la biblioteca Date para utilizar fechas
*/
import java.util.ArrayList;
import java.util.Date;


/*
Esta sección del Proyecto se crea la clase Corparativo esa clase forma parte de Cliente
 */
public class Corporativo extends Cliente {

    private String telefono;
    private String email;

    
    /*
    Definición de variables 
     */
    public Corporativo( String Nombre,String Sexo,String telefono, String email) {
        super(Nombre, Sexo);
        this.telefono = telefono;
        this.email = email;
    }
    /*
    Impresión de los datos de la clase con el método ToString
     */
    @Override
    public String toString() {
        return "Nombre: "+ this.getNombre()+"\n"+
                "Sexo: "+this.getSexo()+"\n"+
                "Telefono: "+this.telefono+"\n"+
                "Email: "+this.email;
    }
    /*
    Se crean los respectivos geters y seters de la clase 
     */
    public String mostrarLista(){
        return this.getNombre()+" "+this.getEmail();
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
