
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ModelExcel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuario
 */
public class ControladorExcel implements ActionListener{
    
    ModelExcel modeloE = new ModelExcel();
    CargaSeries vistaE = new CargaSeries();
    JFileChooser selecArchivo = new JFileChooser();
    File archivo;
    int contAction;
    
    public ControladorExcel(CargaSeries vistaE, ModelExcel modeloE){
        this.vistaE = vistaE;
        this.modeloE = modeloE;
        this.vistaE.btnExportarExcel.addActionListener(this);
    }
    
    public void AgregarFiltro(){
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        contAction++;
        if(contAction==1)AgregarFiltro();

        if(e.getSource() == vistaE.btnExportarExcel)
        if(selecArchivo.showDialog(null, "Exportar") == JFileChooser.APPROVE_OPTION){
            archivo= selecArchivo.getSelectedFile();
            if(archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")){
                JOptionPane.showConfirmDialog(null, modeloE.Exportar(archivo, vistaE.jtDatos));
            }else{
               JOptionPane.showConfirmDialog(null, "Elija un formato valido.");
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
