package appFacturacion;

import java.util.ArrayList;

public class Producto {

    private String CodigoProducto;
    private String Descripcion;
    private double Costo;

    public Producto(String CodigoProducto, String Descripcion, double Costo) {
        this.CodigoProducto = CodigoProducto;
        this.Descripcion = Descripcion;
        this.Costo = Costo;
    }

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


}
