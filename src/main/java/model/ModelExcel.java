/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JTable;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Usuario
 */
public class ModelExcel {
    Workbook wb;
    
    public String Exportar(File archivo, JTable tablaD){
        String respuesta = "No se realizo con exito la operacion";
        int numFila = tablaD.getRowCount(), numColumna = tablaD.getColumnCount();
        if(archivo.getName().endsWith("xls")){
            wb = new HSSFWorkbook();
        }else{
            wb = new XSSFWorkbook();
        }
        
        Sheet hoja = wb.createSheet("Datos");
        
        try{
            for(int i = -1; i < numFila; i++){
                Row fila = hoja.createRow(i + 1);
                for(int j = -1; j < numColumna; j++){
                    Cell celda = fila.createCell(j);
                    if(i==-1){
                       celda.setCellValue(String.valueOf(tablaD.getColumnName(j)));
                    }else{
                        celda.setCellValue(String.valueOf(tablaD.getValueAt(i, j)));
                    }
                    wb.write(new FileOutputStream(archivo));
                }
            }
            respuesta = "Exportacion exitosa.";
        }catch (Exception e){}
        return respuesta;
    }
    
}
