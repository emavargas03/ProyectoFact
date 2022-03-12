package appFacturacion;

import java.util.ArrayList;
import java.util.Date;

public class Pago {

    private double monto;

    public Pago(double monto) {
        this.monto = monto;
    }

    public double montoLetras() {
        return monto;

    }
}
