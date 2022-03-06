package appFacturacion;

import java.util.ArrayList;
import java.util.Date;

public class Corporativo extends Cliente {

    private String telefono;
    private String email;

    public Corporativo(String telefono, String email, String Nombre, String Sexo, Date fecha, DetalleOrden detalleOrden, Pago pago, Cliente client, String producto, float Precio, float cantidad, ArrayList<Double> muestra) {
        super(Nombre, Sexo, fecha, detalleOrden, pago, client, producto, Precio, cantidad, muestra);
        this.telefono = telefono;
        this.email = email;
    }

    

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    }


