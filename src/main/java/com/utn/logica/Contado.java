package com.utn.logica;

import com.utn.utilidades.Archivos;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

public class Contado extends Pago {

    private String tipoMoneda;

    public Contado(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }
    
    public double conversionesColonesaDolares(double colones) throws FileNotFoundException {
        Archivos obtener=new Archivos();
        double resultado=colones/obtener.tipoCambio();
        
        return resultado;
    }
}
    
    
