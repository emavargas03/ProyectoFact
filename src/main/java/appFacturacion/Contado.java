package appFacturacion;

import java.util.ArrayList;
import java.util.Date;

public class Contado extends Pago {
    private String tipoMoneda;

    public Contado(String tipoMoneda, double monto, Date fecha, DetalleOrden detalleOrden, Pago pago, Cliente client, String producto, double Precio, double cantidad, ArrayList<Double> muestra) {
        super(monto, fecha, detalleOrden, pago, client, producto, Precio, cantidad, muestra);
        this.tipoMoneda = tipoMoneda;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }
    public double conversionesColonesaDolares() {    
        

}
