/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utn.appFacturacion;

/*
Se importan los diferentes paquetes del proyecto, y biblioteca para los archivos de txt, además la biblioteca para los arreglos.
 */
import com.utn.gui.MenuPrincipal;
import com.utn.logica.*;
import com.utn.utilidades.Archivos;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProyectoFact {

    /*
    Se corre el programa Final, se crea los diferentes objetos de las tarjeas y se agregan a una lista, además se leen de archivos los productos y los clientes corporativos
     */
    public static void main(String[] args) throws Exception {
        TipoTarjeta visa = new TipoTarjeta("Visa");
        TipoTarjeta mastercard = new TipoTarjeta("Master card");
        TipoTarjeta americanexpress = new TipoTarjeta("American Express");
        ArrayList<TipoTarjeta> tarjetas = new ArrayList<TipoTarjeta>();
        tarjetas.add(visa);
        tarjetas.add(mastercard);
        tarjetas.add(americanexpress);
        Archivos archivos = new Archivos();

        ArrayList<Corporativo> clientesCorp = archivos.leerCorporativos();

        ArrayList<Producto> productos = archivos.leerProductos();

        new MenuPrincipal().setVisible(true);
    }

}
