package appFacturacion;

import java.util.ArrayList;
import java.util.Date;

public class Credito extends Pago {

    private String noTarjetaCredito;
    private TipoTarjeta tipoTarejta;

    public Credito(String noTarjetaCredito, TipoTarjeta tipoTarejta, double monto) {
        super(monto);
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
