package com.utn.logica;

import java.util.ArrayList;
import java.util.Date;

public class Contado extends Pago {

    private String tipoMoneda;

    public Contado(String tipoMoneda, double monto) {
        super(monto);
        this.tipoMoneda = tipoMoneda;
    }

    

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }
}
    /*
    public double conversionesColonesaDolares() {

    }*/
