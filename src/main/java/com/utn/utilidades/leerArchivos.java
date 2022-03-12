package com.utn.utilidades;
// Importing input output classes
import com.utn.logica.Corporativo;
import com.utn.logica.Producto;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class leerArchivos {
 
           
    public ArrayList<Corporativo> leerCorporativos() throws Exception{
        // pass the path to the file as a parameter
        File file = new File(System.getProperty("user.dir")+"\\Data\\ClientesCorporativos.txt");
        Scanner sc = new Scanner(file);
        
        
        
        ArrayList<Corporativo> corporativos=new ArrayList<Corporativo>();
        
        while(sc.hasNextLine()){
            String[] arrOfStr = sc.nextLine().split(",", 4);
            String nom=arrOfStr[0];
            String sexo=arrOfStr[1];
            String tel=arrOfStr[2];
            String email=arrOfStr[3];
            Corporativo cliente=new Corporativo(nom, sexo, tel, email);
            corporativos.add(cliente);
        }
        
        
        
        return corporativos;
      }
    
    public ArrayList<Producto> leerProductos() throws Exception{
        // pass the path to the file as a parameter
        File file = new File(System.getProperty("user.dir")+"\\Data\\Productos.txt");
        Scanner sc = new Scanner(file);
        
        
        
        ArrayList<Producto> productos=new ArrayList<Producto>();
        
        while(sc.hasNextLine()){
            String[] arrOfStr = sc.nextLine().split(",", 5);
            String codigo=arrOfStr[0];
            String nom=arrOfStr[1];
            double costo=Double.parseDouble(arrOfStr[2]);
            double utilidad=Double.parseDouble(arrOfStr[3]);
            double impuesto=Double.parseDouble(arrOfStr[4]);
            
            Producto prod=new Producto(codigo, nom, costo, utilidad, impuesto);
            
            productos.add(prod);
        }
        
        
        
        return productos;
    }
    
    
    
}