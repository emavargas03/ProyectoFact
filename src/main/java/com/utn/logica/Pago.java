package com.utn.logica;

/*
Se importa la utilidades.Numero_a_Letra que es una biblioteca que trae el método para poder analisar un número y devolver su valor en letra o sea escrito
La nbilioteca array para utilizar las listas
 */
import com.utn.utilidades.Archivos;
import com.utn.utilidades.Numero_a_Letra;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

public abstract class Pago {

    /*
    Se definen las variables de las clases
     */
    protected int moneda;

    /*
    Se crea el cosntructor de la clase
     */
    public Pago(int moneda) {
        this.moneda = moneda;
    }

    /*
    Este método es donde se transforma el número a letras
     */
    public String montoLetras(String montoImp) {
        Numero_a_Letra d = new Numero_a_Letra();

        String letras = d.Convertir(montoImp + "", true);
        System.out.println(letras);

        return letras;
    }

    /*
    Método que convierte los colones en dolares con el monto actual del dolar
     */
    public String conversion(float colones) throws FileNotFoundException {
        Archivos arch = new Archivos();

        Float valorDolar = arch.tipoCambio();

        float totalDolares = colones / valorDolar;

        totalDolares = Math.round(totalDolares * 100) / 100f;

        return totalDolares + "";
    }

    /*
    Los  respectivos geters y seter de la clase
     */
    /**
     * @return the moneda
     */
    public int getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(int moneda) {
        this.moneda = moneda;
    }
}
