package appFacturacion;

import java.util.ArrayList;
import java.util.Date;

public class Cliente extends Orden {

    private String Nombre;
    private String Sexo;

    public Cliente(String Nombre, String Sexo, Date fecha, DetalleOrden detalleOrden, Pago pago, Cliente client, String producto, float Precio, float cantidad, ArrayList<Double> muestra) {
        super(fecha, detalleOrden, pago, client, producto, Precio, cantidad, muestra);
        this.Nombre = Nombre;
        this.Sexo = Sexo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

}
