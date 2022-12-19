    package model;

public class Serie {
    private String boleta;
    private String factura;
    private String notaBoleta;
    private String notaFactura;

    public Serie(){

    }

    public String getBoleta() {
        return boleta;
    }

    public void setBoleta(String boleta) {
        this.boleta = boleta;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getNotaBoleta() {
        return notaBoleta;
    }

    public void setNotaBoleta(String notaBoleta) {
        this.notaBoleta = notaBoleta;
    }

    public String getNotaFactura() {
        return notaFactura;
    }

    public void setNotaFactura(String notaFactura) {
        this.notaFactura = notaFactura;
    }
}
