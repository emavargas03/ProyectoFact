package com.utn.logica;

/*
Se importa la bibliteca de array para utilizar listas y la date para utilizar la fechas
 */
import java.util.ArrayList;
import java.util.Date;


/*
Esta sección del Proyecto se crea la clase Crédito hereda de la clase Pago
 */
public class Credito extends Pago {

    /*
    Definición de las variables
     */
    private String noTarjetaCredito;
    private TipoTarjeta tipoTarejta;

    /*
    Creación de los constructores de los atributos de la clase 
     */
    public Credito(String noTarjetaCredito, TipoTarjeta tipoTarejta, int moneda) {
        super(moneda);
        this.noTarjetaCredito = noTarjetaCredito;
        this.tipoTarejta = tipoTarejta;
    }

    /*
    Impresión de los datos de la clase con el método ToString, se muestra el Tipo pago
     */
    @Override
    public String toString() {
        return "Tipo Pago: " + this.getTipoTarejta().getDescripcion();
    }

    /*
    Se crean los respectivos geters y seters de la clase 
     */
    public String getNoTarjetaCredito() {
        return noTarjetaCredito;
    }

    public void setNoTarjetaCredito(String noTarjetaCredito) {
        this.noTarjetaCredito = noTarjetaCredito;
    }

    public TipoTarjeta getTipoTarejta() {
        return tipoTarejta;
    }

    public void setTipoTarejta(TipoTarjeta tipoTarejta) {
        this.tipoTarejta = tipoTarejta;
    }

}
