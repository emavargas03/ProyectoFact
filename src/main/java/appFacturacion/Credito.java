package appFacturacion;

import java.util.ArrayList;
import java.util.Date;


public class Credito extends Pago{
    private String noTarjetaCredito;
    private TipoTarjeta tipoTarejta;

    public Credito(String noTarjetaCredito, TipoTarjeta tipoTarejta, double monto, Date fecha, DetalleOrden detalleOrden, Pago pago, Cliente client, String producto, double Precio, double cantidad, ArrayList<Double> muestra) {
        super(monto, fecha, detalleOrden, pago, client, producto, Precio, cantidad, muestra);
        this.noTarjetaCredito = noTarjetaCredito;
        this.tipoTarejta = tipoTarejta;
    }

    public String getNoTarjetaCredito() {
        return noTarjetaCredito;
    }

    public void setNoTarjetaCredito(String noTarjetaCredito) {
        this.noTarjetaCredito = noTarjetaCredito;
    }

    public TipoTarjeta getTipoTarejta() {
        return tipoTarejta;
    }

    public void setTipoTarejta(TipoTarjeta tipoTarejta) {
        this.tipoTarejta = tipoTarejta;
    }
    
    
}
