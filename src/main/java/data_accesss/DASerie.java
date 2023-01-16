/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_accesss;

import com.google.gson.Gson;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import conexion.OperacionSqlServer;
import model.Comprobante;
import model.SerieSociedad;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.TemplateExcel;

/**
 *
 * @author OQUEREVALU
 */
public class DASerie {
    private static Logger log = Logger.getLogger(DASerie.class);
    OperacionSqlServer operacion3;
    String jsonRespuesta="";
    Gson gson =  new Gson();
    int total;

    public DASerie(String cadena_conexion){
        operacion3=new OperacionSqlServer(cadena_conexion);	
    }
    

    public SerieSociedad Insertar(String consultaObjetos, Object condiciones[]){
        SerieSociedad serieSociedad= new SerieSociedad();
        try {
            ResultSet rsCombo=operacion3.EjecutarConsulta(consultaObjetos, condiciones);
            while (rsCombo!=null&&rsCombo.next()){
                serieSociedad=
                new SerieSociedad(
                        rsCombo.getString("SOCIEDAD"),
                        rsCombo.getString("SERIE"),
                        rsCombo.getString("CLIENTE"),
                        rsCombo.getString("CENTRO"),
                        rsCombo.getString("DETA_CAJA"),
                        rsCombo.getString("NEGOCIO"),
                        rsCombo.getString("UBICACION"),
                        rsCombo.getString("LICENCIA"),
                        rsCombo.getString("ID_OPERADOR"),
                        rsCombo.getString("CAJA"),
                        rsCombo.getString("ANYDESK"),
                        rsCombo.getString("DIRECCION_IP"),
                        rsCombo.getString("HOSTNAME"),
                        rsCombo.getString("CODLISTCOMPROBANTE")
                  );
                }
            if(rsCombo!=null){rsCombo.close();}
        } catch (SQLException ex) {
            log.error(ex.getMessage());
            serieSociedad=null;
        }
        operacion3.cerrarConexion();
        return serieSociedad;
    }
    
    public ArrayList<SerieSociedad> listar(String consultaObjetos, Object condiciones[]){
        ArrayList<SerieSociedad> lista=new ArrayList<>();
        try {
            ResultSet rsCombo = operacion3.EjecutarConsulta(consultaObjetos,condiciones);
            while (rsCombo!=null&&rsCombo.next()){
                lista.add(
                        new SerieSociedad(rsCombo.getString("SOCIEDAD"),
                                rsCombo.getString("SERIE"),
                                rsCombo.getString("CLIENTE"),
                                rsCombo.getString("CENTRO"),
                                rsCombo.getString("DETA_CAJA"),
                                rsCombo.getString("NEGOCIO"),
                                rsCombo.getString("UBICACION"),
                                rsCombo.getString("LICENCIA"),
                                rsCombo.getString("ID_OPERADOR"),
                                rsCombo.getString("CAJA"),
                                rsCombo.getString("ANYDESK"),
                                rsCombo.getString("DIRECCION_IP"),
                                rsCombo.getString("HOSTNAME"),
                                rsCombo.getString("CODLISTCOMPROBANTE")
                          ) 
                        );
            }
            if(rsCombo!=null){rsCombo.close();}
            operacion3.cerrarConexion();
            return lista;
        } catch (SQLException ex) {
            log.error(ex.getMessage());
            operacion3.cerrarConexion();
            return lista;
        }
    }
    
     public ArrayList<TemplateExcel> listarExcelTemplate(String consultaObjetos, Object condiciones[]){
        ArrayList<TemplateExcel> lista=new ArrayList<>();
        try {
            ResultSet rsCombo = operacion3.EjecutarConsulta(consultaObjetos,condiciones);
            while (rsCombo!=null&&rsCombo.next()){
                lista.add(
                        new TemplateExcel(rsCombo.getString("SOCIEDAD"),
                                rsCombo.getString("CENTRO"),
                                rsCombo.getString("DETA_CAJA"),
                                rsCombo.getString("NEGOCIO"),
                                rsCombo.getString("UBICACION"),
                                rsCombo.getString("LICENCIA"),
                                rsCombo.getString("ID_OPERADOR"),
                                rsCombo.getString("CAJA"),
                                rsCombo.getString("ANYDESK"),
                                rsCombo.getString("DIRECCION_IP"),
                                rsCombo.getString("HOSTNAME"),
                                rsCombo.getString("SERIE"),
                                rsCombo.getString("CODLISTCOMPROBANTE")
                          ) 
                        );
            }
            if(rsCombo!=null){rsCombo.close();}
            operacion3.cerrarConexion();
            return lista;
        } catch (SQLException ex) {
            log.error(ex.getMessage());
            operacion3.cerrarConexion();
            return lista;
        }
    }
    
    public SerieSociedad obtenerSerieSociedad(String consultaObjetos, Object condiciones[]){
        SerieSociedad serieSociedad = new SerieSociedad();
        try {
            ResultSet rsCombo = operacion3.EjecutarConsulta(consultaObjetos,condiciones);
            while (rsCombo!=null&&rsCombo.next()){
               
                       serieSociedad = new SerieSociedad(rsCombo.getString("SOCIEDAD"),
                                rsCombo.getString("SERIE"),
                                rsCombo.getString("CLIENTE"),
                                rsCombo.getString("CENTRO"),
                                rsCombo.getString("DETA_CAJA"),
                                rsCombo.getString("NEGOCIO"),
                                rsCombo.getString("UBICACION"),
                                rsCombo.getString("LICENCIA"),
                                rsCombo.getString("ID_OPERADOR"),
                                rsCombo.getString("CAJA"),
                                rsCombo.getString("ANYDESK"),
                                rsCombo.getString("DIRECCION_IP"),
                                rsCombo.getString("HOSTNAME"),
                               rsCombo.getString("CODLISTCOMPROBANTE")
                          );
                      
            }
             if(rsCombo!=null){rsCombo.close();}
        } catch (SQLException ex) {
            log.error(ex.getMessage());
            serieSociedad=null;
        }
        operacion3.cerrarConexion();
        return serieSociedad;
    }
}
