package com.utn.utilidades;
// Importing input output classes
import com.utn.logica.Corporativo;
import com.utn.logica.Producto;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Archivos {
 
    public Archivos(){
        
    }    
    
    
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
    
    public void agregarOrden(String orden) throws FileNotFoundException{
        File file = new File(System.getProperty("user.dir")+"\\Data\\Transacciones.txt");
        Scanner sc = new Scanner(file);
        String strOrdenes=sc.nextLine();
        
        FileWriter flwriter = null;
        try {//además de la ruta del archivo recibe un parámetro de tipo boolean, que le indican que se va añadir más registros 
                flwriter = new FileWriter(System.getProperty("user.dir")+"\\Data\\Transacciones", true);
                BufferedWriter bufferedWriter=new BufferedWriter(flwriter);

                bufferedWriter.write(strOrdenes+orden);
                bufferedWriter.close();
                System.out.println("Archivo modificado satisfactoriamente..");

        } catch (Exception e) {
            e.printStackTrace();
            } finally {
                if (flwriter != null) {
                    try {
                        flwriter.close();
                    } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public double tipoCambio() throws FileNotFoundException{
        File file = new File(System.getProperty("user.dir")+"\\Data\\TipoCambio.txt");
        Scanner sc = new Scanner(file);
        String strCambio=sc.nextLine();
        double cambio=Double.parseDouble(strCambio);
        
        return cambio;
    }
    
    
    
}