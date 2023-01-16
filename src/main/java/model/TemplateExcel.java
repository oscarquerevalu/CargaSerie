/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Usuario
 */
public class TemplateExcel {

      
    private String sociedad;
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

    private String serie;

    private String codListComprobante;

    
    public TemplateExcel(){

    }

    public TemplateExcel(String centro){
        this.centro = centro;
    }

    public TemplateExcel(String sociedad, String centro,
            String detaCaja, String negocio, String ubicacion, String licencia, String idOperador, 
            String caja, String anydesk, String ip, String hostname, String serie, String codListComprobante
    ) {
        this.sociedad = sociedad;
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
        this.serie = serie;
        this.codListComprobante = codListComprobante;
    }
    
    
    /**
     * @return the sociedad
     */
    public String getSociedad() {
        return sociedad;
    }

    /**
     * @param sociedad the sociedad to set
     */
    public void setSociedad(String sociedad) {
        this.sociedad = sociedad;
    }

    /**
     * @return the centro
     */
    public String getCentro() {
        return centro;
    }

    /**
     * @param centro the centro to set
     */
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

    /**
     * @return the negocio
     */
    public String getNegocio() {
        return negocio;
    }

    /**
     * @param negocio the negocio to set
     */
    public void setNegocio(String negocio) {
        this.negocio = negocio;
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the licencia
     */
    public String getLicencia() {
        return licencia;
    }

    /**
     * @param licencia the licencia to set
     */
    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    /**
     * @return the idOperador
     */
    public String getIdOperador() {
        return idOperador;
    }

    /**
     * @param idOperador the idOperador to set
     */
    public void setIdOperador(String idOperador) {
        this.idOperador = idOperador;
    }

    /**
     * @return the caja
     */
    public String getCaja() {
        return caja;
    }

    /**
     * @param caja the caja to set
     */
    public void setCaja(String caja) {
        this.caja = caja;
    }

    /**
     * @return the anydesk
     */
    public String getAnydesk() {
        return anydesk;
    }

    /**
     * @param anydesk the anydesk to set
     */
    public void setAnydesk(String anydesk) {
        this.anydesk = anydesk;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @param hostname the hostname to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }


    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCodListComprobante() {
        return codListComprobante;
    }

    public void setCodListComprobante(String codListComprobante) {
        this.codListComprobante = codListComprobante;
    }
}
