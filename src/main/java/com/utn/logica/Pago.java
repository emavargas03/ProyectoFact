package com.utn.logica;

import com.utn.utilidades.Numero_a_Letra;
import java.util.ArrayList;
import java.util.Date;

public abstract class Pago {

    

    public Pago() {
        
    }

    public String montoLetras(double montoImp) {
        Numero_a_Letra d= new Numero_a_Letra();
        
        String letras= d.Convertir(montoImp+"", true);
        
        
        return letras;        
    }
}
