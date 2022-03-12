/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utn.appFacturacion;

import com.utn.logica.*;
import com.utn.utilidades.leerArchivos;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ejvar
 */
public class ProyectoFact {
    public static void main(String[] args) throws Exception {
        TipoTarjeta visa = new TipoTarjeta("Visa");
        TipoTarjeta mastercard = new TipoTarjeta("Master card");
        TipoTarjeta americanexpress = new TipoTarjeta("American Express");
        leerArchivos archivos=new leerArchivos();
        
        
        ArrayList<Corporativo> clientesCorp= archivos.leer();
        
        for (Corporativo corporativo : clientesCorp) {
            JOptionPane.showMessageDialog(null, corporativo.toString());
        }
    }
    
    
    
}
