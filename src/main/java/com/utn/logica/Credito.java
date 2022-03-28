package com.utn.logica;

import java.util.ArrayList;
import java.util.Date;

public class Credito extends Pago {

    private String noTarjetaCredito;
    private TipoTarjeta tipoTarejta;

    public Credito(String noTarjetaCredito, TipoTarjeta tipoTarejta,int moneda) {
        super(moneda);
        this.noTarjetaCredito = noTarjetaCredito;
        this.tipoTarejta = tipoTarejta;
    }

    @Override
    public String toString() {
        return "Tipo Pago: "+this.getTipoTarejta().getDescripcion();
    }
    
    

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
