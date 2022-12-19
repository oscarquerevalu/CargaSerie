    package model;

public class SerieSociedad {
    private String sociedad;
    private String serie;
    private String cliente;

    private String centro;

    public SerieSociedad(){

    }

    public SerieSociedad(String serie){
        this.serie = serie;
    }

    public SerieSociedad(String sociedad, String serie, String cliente, String centro) {
        this.sociedad = sociedad;
        this.serie = serie;
        this.cliente = cliente;
        this.centro = centro;
    }

    public String getSociedad() {
        return sociedad;
    }

    public void setSociedad(String sociedad) {
        this.sociedad = sociedad;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }
}
