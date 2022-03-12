package com.utn.utilidades;
// Importing input output classes
import com.utn.logica.Corporativo;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

 
// Main class
// ReadingFromFile
public class leerArchivos {
 
    // Main driver method
    public static void main(String[] args) throws Exception
    {
 
        
    }
    //System.getProperty("user.dir")+"\\Data\\ClientesCorporativos.txt")
    public ArrayList<Corporativo> leer() throws Exception{
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
    
}