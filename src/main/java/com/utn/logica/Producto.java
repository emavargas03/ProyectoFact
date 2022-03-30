package com.utn.logica;



public class Producto {
    /*
    Se definen las varibles de la clase 
    */
    private String CodigoProducto;
    private String Descripcion;
    private double Costo;
    protected double utilidad;
    protected double impuesto;

    /*
    Se crean los respectivos constructores para la clase
    */
    public Producto(String CodigoProducto, String Descripcion, double Costo, double utilidad, double impuesto) {
        this.CodigoProducto = CodigoProducto;
        this.Descripcion = Descripcion;
        this.Costo = Costo;
        this.utilidad=utilidad;
        this.impuesto=impuesto;
    }

    /*
    Método Tostring que permite mostrar los datos impresos de la clase Producto
    */
    @Override
    public String toString() {
        return "Codigo: "+getCodigoProducto()+
                " Nombre: "+getDescripcion()+
                " Precio: "+(getCosto()+(getCosto()*getUtilidad()));
    }
    
     /*
    Método que reliza la impresión para el combobox
    */
    public String mostrarComboB(){
        return getCodigoProducto()+" "+getDescripcion();
    }
    
    
    /*
    Se crean los respectivos geters y seters de la clase 
     */

    public String getCodigoProducto() {
        return CodigoProducto;
    }

    public void setCodigoProducto(String CodigoProducto) {
        this.CodigoProducto = CodigoProducto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double Costo) {
        this.Costo = Costo;
    }

    
    /**
     * @return the utilidad
     */
    public double getUtilidad() {
        return utilidad;
    }

    /**
     * @param utilidad the utilidad to set
     */
    public void setUtilidad(double utilidad) {
        this.utilidad = utilidad;
    }

    /**
     * @return the impuesto
     */
    public double getImpuesto() {
        return impuesto;
    }

    /**
     * @param impuesto the impuesto to set
     */
    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }


}
