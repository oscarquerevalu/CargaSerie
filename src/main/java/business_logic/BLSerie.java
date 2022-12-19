/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business_logic;

import com.google.gson.Gson;
import data_accesss.DAComprobante;
import data_accesss.DASerie;
import model.Comprobante;
import model.Serie;
import model.SerieSociedad;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import util.Propiedades;

import java.util.ArrayList;

/**
 *
 * @author OQUEREVALU
 */
public class BLSerie {

    private static Logger log = Logger.getLogger(BLSerie.class);

    Gson gson =  new Gson();

    public BLSerie() {
    }
    
    public SerieSociedad registrar(Propiedades propiedades, SerieSociedad serieSociedad){
                        Object condiciones[]={    serieSociedad.getSociedad(),
                                serieSociedad.getSerie(),
                                serieSociedad.getCliente(),
                                serieSociedad.getCentro()
                            };
        SerieSociedad registro=new DASerie(propiedades.getCadena_conexion()).Insertar("INSERT INTO SERIESSOCIEDAD\n" +
                "           (SOCIEDAD, SERIE, CLIENTE, CENTRO)\n" +
                "     VALUES\n" +
                "           (?, ? ,? ,? )", condiciones);
        return registro;
    }

    
    public Comprobante obtenerComprobante(Propiedades propiedades,String id){
        Object condiciones[]={id};
        DAComprobante da= new DAComprobante(propiedades.getCadena_conexion());
        log.info(propiedades.getCadena_conexion());
        ArrayList<Comprobante> lista=new ArrayList<Comprobante>();
        Comprobante Comprobante=new Comprobante();
        lista = da.listar(
                "select ID from Comprobantes where ID = ?", condiciones);
        if(lista.isEmpty()){
            log.info("vacio");
            log.info(id);
            Comprobante.setId(null);

        }else{
            Comprobante=lista.get(0);
        }
        return Comprobante;
    }

    public SerieSociedad obtenerSeriePorSociedad(Propiedades propiedades,String sociedad, String tipoComprobante, String centro){
        Object condiciones[]={sociedad, centro};
        DASerie da= new DASerie(propiedades.getCadena_conexion());
        log.info(propiedades.getCadena_conexion());
        ArrayList<SerieSociedad> lista=new ArrayList<SerieSociedad>();
        SerieSociedad serieSociedad=new SerieSociedad();

        if("B".equals(tipoComprobante)){
            lista = da.listar("SELECT TOP 1 SOCIEDAD, CLIENTE, SERIE, CENTRO FROM SERIESSOCIEDAD WHERE SERIE LIKE 'B%' AND SERIE NOT LIKE 'BC%' AND SERIE NOT LIKE 'BN%' AND SOCIEDAD = ? AND CENTRO = ? ORDER BY SERIE DESC", condiciones);
        }else if("F".equals(tipoComprobante)){
            lista = da.listar("SELECT TOP 1 SOCIEDAD, CLIENTE, SERIE, CENTRO FROM SERIESSOCIEDAD WHERE SERIE LIKE 'F%' AND SERIE NOT LIKE 'FC%' AND SERIE NOT LIKE 'FN%' AND SOCIEDAD = ? AND CENTRO = ? ORDER BY SERIE DESC", condiciones);
        }else if("BN".equals(tipoComprobante)){
            lista = da.listar("SELECT TOP 1 SOCIEDAD, CLIENTE, SERIE, CENTRO FROM SERIESSOCIEDAD WHERE (SERIE LIKE 'FC%' OR SERIE LIKE 'FN%') AND SOCIEDAD = ? AND CENTRO = ? ORDER BY SERIE DESC", condiciones);
        }else if("FN".equals(tipoComprobante)){
            lista = da.listar("SELECT TOP 1 SOCIEDAD, CLIENTE, SERIE, CENTRO FROM SERIESSOCIEDAD WHERE (SERIE LIKE 'BC%' OR SERIE LIKE 'BN%') AND SOCIEDAD = ? AND CENTRO = ? ORDER BY SERIE DESC", condiciones);
        }

        if(lista.isEmpty()){
            log.info("vacio");
            log.info(sociedad);

        }else{
            serieSociedad=lista.get(0);
        }
        return serieSociedad;
    }

    public boolean existeSeriePorSerie(Propiedades propiedades,String serie, String sociedad, String tipoComprobante){
        Object condiciones[]={serie, sociedad};
        DASerie da= new DASerie(propiedades.getCadena_conexion());
        log.info(propiedades.getCadena_conexion());
        ArrayList<SerieSociedad> lista=new ArrayList<SerieSociedad>();
        SerieSociedad serieSociedad=new SerieSociedad();

        if("B".equals(tipoComprobante)){
            lista = da.listar("SELECT TOP 1 SOCIEDAD, CLIENTE, SERIE, CENTRO FROM SERIESSOCIEDAD WHERE SERIE LIKE 'B%' AND SERIE NOT LIKE 'BC%' AND SERIE NOT LIKE 'BN%' AND SERIE = ? AND SOCIEDAD = ? ORDER BY SERIE DESC", condiciones);
        }else if("F".equals(tipoComprobante)){
            lista = da.listar("SELECT TOP 1 SOCIEDAD, CLIENTE, SERIE, CENTRO FROM SERIESSOCIEDAD WHERE SERIE LIKE 'F%' AND SERIE NOT LIKE 'FC%' AND SERIE NOT LIKE 'FN%' AND SERIE = AND SOCIEDAD = ? ? ORDER BY SERIE DESC", condiciones);
        }else if("FC".equals(tipoComprobante)){
            lista = da.listar("SELECT TOP 1 SOCIEDAD, CLIENTE, SERIE, CENTRO FROM SERIESSOCIEDAD WHERE (SERIE LIKE 'FC%' OR SERIE LIKE 'FN%') AND SERIE = ? AND SOCIEDAD = ? ORDER BY SERIE DESC", condiciones);
        }else if("BC".equals(tipoComprobante)){
            lista = da.listar("SELECT TOP 1 SOCIEDAD, CLIENTE, SERIE, CENTRO FROM SERIESSOCIEDAD WHERE (SERIE LIKE 'BC%' OR SERIE LIKE 'BN%') AND SERIE = ? AND SOCIEDAD = ? ORDER BY SERIE DESC", condiciones);
        }

        return !lista.isEmpty();
    }

    public Serie generaSerie(Propiedades propiedades,String sociedad, String centro){

        // Obtiene la boleta mayor
        SerieSociedad serieSociedad = obtenerSeriePorSociedad(propiedades, sociedad, "B", centro);

        int numeroBoleta = Integer.parseInt(serieSociedad.getSerie().replaceAll("B","")) ;

        Serie serie = new Serie();

        generaComprobante(propiedades, sociedad,numeroBoleta, serie);

        return serie;
    }

    public void generaComprobante(Propiedades propiedades, String sociedad, int numeroBoleta, Serie serie){
        numeroBoleta = numeroBoleta + 1;

        String nuevaBoleta = ("B"+StringUtils.leftPad(numeroBoleta+"", 3,"0"));
        log.info("nuevaBoleta: "+ nuevaBoleta);

        if(existeSeriePorSerie(propiedades,nuevaBoleta, sociedad, "B")){
            generaComprobante(propiedades,sociedad, numeroBoleta, serie);
            return;
        }else{
            log.info("no existe boleta");
        }

        String nuevaFactura = ("F"+StringUtils.leftPad(numeroBoleta+"", 3,"0"));
        log.info("nuevaFactura: "+ nuevaFactura);

        if(existeSeriePorSerie(propiedades,nuevaFactura, sociedad, "F")){
            generaComprobante(propiedades,sociedad, numeroBoleta, serie);
            return;
        }else{
            log.info("no existe factura");
        }

        String nuevaNotaBoleta = "";
        if(numeroBoleta<100){
            nuevaNotaBoleta = "BC"+numeroBoleta;
        }else if(numeroBoleta>99 && numeroBoleta<1000){
            nuevaNotaBoleta = "BC" + (String.valueOf(numeroBoleta).charAt(0))+(String.valueOf(numeroBoleta).charAt(2));
        }
        log.info("nuevaNotaBoleta: "+ nuevaNotaBoleta);

        if(existeSeriePorSerie(propiedades,nuevaNotaBoleta, sociedad, "BC")){
            if(numeroBoleta<100){
                nuevaNotaBoleta = "BN"+numeroBoleta;
            }else if(numeroBoleta>99 && numeroBoleta<1000){
                nuevaNotaBoleta = "BN" + (String.valueOf(numeroBoleta).charAt(0))+(String.valueOf(numeroBoleta).charAt(2));
            }
            log.info("nuevaNotaBoleta: "+ nuevaNotaBoleta);
            if(existeSeriePorSerie(propiedades,nuevaNotaBoleta, sociedad, "BC")){
                generaComprobante(propiedades,sociedad, numeroBoleta, serie);
                return;
            }else{
                log.info("no existe nota Boleta");
            }

        }else{
            log.info("no existe nota Boleta");
        }

        String nuevaNotaFactura = "";
        if(numeroBoleta<100){
            nuevaNotaFactura = "FC"+numeroBoleta;
        }else if(numeroBoleta>99 && numeroBoleta<1000){
            nuevaNotaFactura = "FC" + (String.valueOf(numeroBoleta).charAt(0))+(String.valueOf(numeroBoleta).charAt(2));
        }
        log.info("nuevaNotaFactura: "+ nuevaNotaFactura);

        if(existeSeriePorSerie(propiedades,nuevaNotaFactura, sociedad, "FC")){
            if(numeroBoleta<100){
                nuevaNotaFactura = "FN"+numeroBoleta;
            }else if(numeroBoleta>99 && numeroBoleta<1000){
                nuevaNotaFactura = "FN" + (String.valueOf(numeroBoleta).charAt(0))+(String.valueOf(numeroBoleta).charAt(2));
            }
            log.info("nuevaNotaFactura: "+ nuevaNotaFactura);
            if(existeSeriePorSerie(propiedades,nuevaNotaFactura, sociedad, "FC")){
                generaComprobante(propiedades,sociedad, numeroBoleta, serie);
                return;
            }else{
                log.info("no existe nota Factura");
            }

        }else{
            log.info("no existe nota Factura");
        }

        log.info("_________________");
        serie.setBoleta(nuevaBoleta);
        serie.setFactura(nuevaFactura);
        serie.setNotaBoleta(nuevaNotaBoleta);
        serie.setNotaFactura(nuevaNotaFactura);
        log.info("nuevaBoleta: "+ nuevaBoleta);
        log.info("nuevaFactura: "+ nuevaFactura);
        log.info("nuevaNotaBoleta: "+ nuevaNotaBoleta);
        log.info("nuevaNotaFactura: "+ nuevaNotaFactura);
        log.info("_________________");
    }
}
