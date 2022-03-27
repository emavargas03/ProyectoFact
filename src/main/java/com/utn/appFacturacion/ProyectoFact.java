/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utn.appFacturacion;

import com.utn.gui.MenuPrincipal;
import com.utn.logica.*;
import com.utn.utilidades.Archivos;
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
        ArrayList<TipoTarjeta> tarjetas=new ArrayList<TipoTarjeta>();
        tarjetas.add(visa);
        tarjetas.add(mastercard);
        tarjetas.add(americanexpress);
        Archivos archivos=new Archivos();
        
        archivos.guardarOrden("");
        ArrayList<Corporativo> clientesCorp= archivos.leerCorporativos();
        
        ArrayList<Producto> productos = archivos.leerProductos();
        
            
        
        new MenuPrincipal().setVisible(true);
    }
    
}
