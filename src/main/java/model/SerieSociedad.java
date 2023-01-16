    package model;

public class SerieSociedad {
    private String sociedad;
    private String serie;
    private String cliente;
    private String centro;
    private String detaCaja;
    private String negocio;
    private String ubicacion;
    private String licencia;
    private String idOperador;
    private String caja;
    private String anydesk;
    private String ip;
    private String hostname;
    private String codListComprobante;

    
    public SerieSociedad(){

    }

    public SerieSociedad(String serie){
        this.serie = serie;
    }

    public SerieSociedad(String sociedad, String serie, String cliente, String centro,
            String detaCaja, String negocio, String ubicacion, String licencia, String idOperador, 
            String caja, String anydesk, String ip, String hostname, String codListComprobante
    ) {
        this.sociedad = sociedad;
        this.serie = serie;
        this.cliente = cliente;
        this.centro = centro;
        this.detaCaja = detaCaja;
        this.negocio = negocio;
        this.ubicacion = ubicacion;
        this.licencia = licencia;
        this.idOperador = idOperador;
        this.caja = caja;
        this.anydesk = anydesk;
        this.ip = ip;
        this.hostname = hostname;
        this.codListComprobante = codListComprobante;
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

    /**
     * @return the detaCaja
     */
    public String getDetaCaja() {
        return detaCaja;
    }

    /**
     * @param detaCaja the detaCaja to set
     */
    public void setDetaCaja(String detaCaja) {
        this.detaCaja = detaCaja;
    }
    

    public String getNegocio() {
        return negocio;
    }

   
    public void setNegocio(String negocio) {
        this.negocio = negocio;
    }

   
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

  
    public String getLicencia() {
        return licencia;
    }


    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

  
    public String getIdOperador() {
        return idOperador;
    }

   
    public void setIdOperador(String idOperador) {
        this.idOperador = idOperador;
    }


    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public String getAnydesk() {
        return anydesk;
    }

    public void setAnydesk(String anydesk) {
        this.anydesk = anydesk;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * @return the codListComprobante
     */
    public String getCodListComprobante() {
        return codListComprobante;
    }

    /**
     * @param codListComprobante the codListComprobante to set
     */
    public void setCodListComprobante(String codListComprobante) {
        this.codListComprobante = codListComprobante;
    }
    
    
}
