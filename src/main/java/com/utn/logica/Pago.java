package com.utn.logica;

import com.utn.utilidades.Numero_a_Letra;
import java.util.ArrayList;
import java.util.Date;

public class Pago {

    private double monto;

    public Pago(double monto) {
        this.monto = monto;
    }

    public String montoLetras() {
        Numero_a_Letra d= new Numero_a_Letra();
        
        String letras= d.Convertir(monto+"", true);
        
        
        return letras;        
    }
}
