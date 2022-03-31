package com.utn.logica;

/*
Se importan los archivos, sus utilidades y las blibliotecas date para la fecha y la de los arrayList para trabajar con listas
 */
import com.utn.utilidades.Archivos;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;


/*
Esta sección del proyecto se crea la clase Contado que Hereda de pago 
 */
public class Contado extends Pago {

    /*
    Definición de variables 
     */
    private String tipoMoneda;

    /*
    Creación del constructor del atributo de la clase 
     */
    public Contado(String tipoMoneda, int moneda) {
        super(moneda);
        this.tipoMoneda = tipoMoneda;
    }
    /*
    Método toStrin que permite ver en pantalla el tipo de moneda
    */
    @Override
    public String toString() {
        return "Tipo Moneda: " + this.getTipoMoneda();
    }

    /*
    Se crean los respectivos geters y seters de la clase 
     */
    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    /*
    Se descarga de archivos el tipo de moneda y el monto con el que se hace la conversión de Colones a Dolares
     */
    public double conversionesColonesaDolares(double colones) throws FileNotFoundException {
        Archivos obtener = new Archivos();
        double resultado = colones / obtener.tipoCambio();

        return resultado;
    }
}
