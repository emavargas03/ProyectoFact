
package appFacturacion;

import java.util.ArrayList;
import java.util.Date;

public class TipoTarjeta extends Credito{
    private String Descripcion;

    public TipoTarjeta(String Descripcion, String noTarjetaCredito, TipoTarjeta tipoTarejta, double monto, Date fecha, DetalleOrden detalleOrden, Pago pago, Cliente client, String producto, double Precio, double cantidad, ArrayList<Double> muestra) {
        super(noTarjetaCredito, tipoTarejta, monto, fecha, detalleOrden, pago, client, producto, Precio, cantidad, muestra);
        this.Descripcion = Descripcion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
}
