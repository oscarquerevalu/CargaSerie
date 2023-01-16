/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import business_logic.BLSerie;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.ModelExcel;
import model.Serie;
import model.SerieSociedad;
import model.TemplateExcel;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.Propiedades;
import util.Util;

/**
 *
 * @author tauro
 */
public class CargaSeries extends javax.swing.JFrame  {

    /**
     * Creates new form Verificar
     */
    private static Logger log = Logger.getLogger(CargaSeries.class);
    String codigSociedad, codigSociedadExcel;
    String codigCentro, codigCentroExcel;
    Propiedades archivo_propiedades;
    DefaultTableModel modelo = new DefaultTableModel();
    
    
    public CargaSeries() {
        initComponents();
        
        File propiedades=new File("C:\\Config\\consulta.properties");
        
        if(!propiedades.exists()){
            archivo_propiedades=new Propiedades(
                    "jdbc:sqlserver://localhost;encrypt=false;databaseName=FMEDITERRANEO;user=sa; password=P@ssword2019;",
                    "127.0.0.1",
                    "TestUser",
                    "123456",
                    3
            );
            new Util().escribirPropiedades(archivo_propiedades);
        }else{
            archivo_propiedades=new Util().leerPropiedades();
        }
        
        comboBox1.addItem("---- SELECCIONE ----");
        comboBox1.addItem("3001 - Plaza Norte y GTT");
        comboBox1.addItem("3008 - Panisteria");
        comboBox1.addItem("4000 - Servicio trastienda Integrada - Entre Paginas");
        comboBox1.addItem("4007 - Centro Comercial Mall del Sur");
        comboBox1.addItem("4501 - Consorcio Mediterraneo");

        /*comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.info(comboBox1.getSelectedItem().toString());

                if(!comboBox1.getSelectedItem().toString().equals("---- SELECCIONE ----")){
                    agregaCboCentroItems();
                }else {
                    txtBoleta.setText("");
                    txtFactura.setText("");
                    txtNotaBoleta.setText("");
                    txtNotaFactura.setText("");
                    cboCentro.removeAllItems();
                }

            }
        });*/
        
        /*cboCentro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cboCentro.getSelectedItem() != null){
                    log.info(cboCentro.getSelectedItem().toString());

                    if(!cboCentro.getSelectedItem().toString().equals("---- SELECCIONE ----")){
                        String splitSociedad[] = comboBox1.getSelectedItem().toString().split("-");
                        codigSociedad = splitSociedad[0].trim();
                        log.info(codigSociedad);

                        String splitCentro[] = cboCentro.getSelectedItem().toString().split("-");
                        codigCentro = splitCentro[0].trim();

                        log.info(codigCentro);

                        BLSerie blSerie = new BLSerie();
                        Serie serie = blSerie.generaSerie(archivo_propiedades, codigSociedad, codigCentro);
                        txtBoleta.setText(serie.getBoleta());
                        txtFactura.setText(serie.getFactura());
                        txtNotaBoleta.setText(serie.getNotaBoleta());
                        txtNotaFactura.setText(serie.getNotaFactura());
                    }else {
                        txtBoleta.setText("");
                        txtFactura.setText("");
                        txtNotaBoleta.setText("");
                        txtNotaFactura.setText("");
                    }
                }

            }
        });*/
        
        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        
        
        
        cboSociedadExcel.addItem("---- SELECCIONE ----");
        cboSociedadExcel.addItem("3001 - Plaza Norte y GTT");
        cboSociedadExcel.addItem("3008 - Panisteria");
        cboSociedadExcel.addItem("4000 - Servicio trastienda Integrada - Entre Paginas");
        cboSociedadExcel.addItem("4007 - Centro Comercial Mall del Sur");
        cboSociedadExcel.addItem("4501 - Consorcio Mediterraneo");

        /*cboSociedadExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.info(cboSociedadExcel.getSelectedItem().toString());

                if(!cboSociedadExcel.getSelectedItem().toString().equals("---- SELECCIONE ----")){
                    agregaCboCentroExcelItems();
                }
            }
        });*/
        
       /*cboCentroExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cboCentroExcel.getSelectedItem() != null){
                    log.info(cboCentroExcel.getSelectedItem().toString());

                    if(!cboCentroExcel.getSelectedItem().toString().equals("---- SELECCIONE ----")){
                        String splitSociedadExcel[] = cboSociedadExcel.getSelectedItem().toString().split("-");
                        codigSociedadExcel = splitSociedadExcel[0].trim();
                        log.info(codigSociedadExcel);

                        String splitCentroExcel[] = cboCentroExcel.getSelectedItem().toString().split("-");
                        codigCentroExcel = splitCentroExcel[0].trim();

                        log.info(codigCentroExcel);
                     
                    }
                }

            }
        });*/
        
        modelo.addColumn("SOCIEDAD");
        modelo.addColumn("CENTRO");
        modelo.addColumn("SERIES");
        modelo.addColumn("DETA_CAJA");
        modelo.addColumn("NEGOCIO");
        modelo.addColumn("UBICACION");
        modelo.addColumn("LICENCIA");
        modelo.addColumn("ID_OPERADOR");
        modelo.addColumn("CAJA");
        modelo.addColumn("ANYDESK");
        modelo.addColumn("DIRECCION_IP");
        modelo.addColumn("HOSTNAME");
        jtDatos.setModel(modelo);
        
    }
        
     private void onCancel() {
        // add your code here if necessary
        dispose();
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jtDatos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtIdOperador = new javax.swing.JTextField();
        txtCaja = new javax.swing.JTextField();
        txtAnydesk = new javax.swing.JTextField();
        txtIp = new javax.swing.JTextField();
        txtLicencia = new javax.swing.JTextField();
        txtHostname = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnExportarExcel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboSociedadExcel = new javax.swing.JComboBox<>();
        cboCentroExcel = new javax.swing.JComboBox<>();
        txtSearchBoleta = new javax.swing.JTextField();
        btnBuscarBoleta = new javax.swing.JButton();
        buttonOk = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDetaCaja = new javax.swing.JTextField();
        txtNegocio = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboBox1 = new javax.swing.JComboBox<>();
        cboCentro = new javax.swing.JComboBox<>();
        txtUbicacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtBoleta = new javax.swing.JTextField();
        txtFactura = new javax.swing.JTextField();
        txtNotaBoleta = new javax.swing.JTextField();
        txtNotaFactura = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GENERAR SERIE");
        setBackground(new java.awt.Color(153, 204, 255));
        setForeground(java.awt.Color.white);
        setResizable(false);

        jtDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtDatos.setOpaque(false);
        jtDatos.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jtDatos);

        jPanel3.setBackground(new java.awt.Color(205, 226, 247));
        jPanel3.setMaximumSize(new java.awt.Dimension(33000, 32767));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("HOSTNAME:");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("LICENCIA:");

        jPanel2.setBackground(new java.awt.Color(240, 243, 246));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnExportarExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExportarExcel.setText("EXPORTAR EXCEL");
        btnExportarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarExcelActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("SOCIEDAD:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("CENTRO:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Nro. BOLETA:");

        cboSociedadExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSociedadExcelActionPerformed(evt);
            }
        });

        cboCentroExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCentroExcelActionPerformed(evt);
            }
        });

        btnBuscarBoleta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBuscarBoleta.setText("BUSCAR");
        btnBuscarBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarBoletaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboCentroExcel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboSociedadExcel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtSearchBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnExportarExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBuscarBoleta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(7, 7, 7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboSociedadExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboCentroExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSearchBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExportarExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        buttonOk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonOk.setText("GRABAR");
        /*buttonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOkActionPerformed(evt);
            }
        });*/

        buttonCancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonCancel.setText("CANCEL");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("DETALLE CAJA:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("NEGOCIO:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("UBICACION:");

        jPanel1.setBackground(new java.awt.Color(240, 243, 246));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("SOCIEDAD:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("CENTRO:");

        comboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox1ActionPerformed(evt);
            }
        });

        cboCentro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCentroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(comboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboCentro, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(comboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboCentro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("ID OPERADOR");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("CAJA:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("FACTURA:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("NOTA BOLETA:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("NOTA FACTURA:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("ANYDESK:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("DIRECCION IP:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("BOLETA:");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 3, 15)); // NOI18N
        jLabel19.setText("Generar Series");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 3, 15)); // NOI18N
        jLabel20.setText("Generar Excel");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(367, 367, 367))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel14)
                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUbicacion)
                            .addComponent(txtNegocio)
                            .addComponent(txtDetaCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtNotaFactura, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNotaBoleta, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtFactura, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtBoleta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(109, 109, 109)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                            .addComponent(txtIdOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAnydesk, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18)
                                .addComponent(jLabel17))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtLicencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtHostname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(txtNotaBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtIdOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtNotaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAnydesk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDetaCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNegocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtHostname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txtLicencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        txtNotaFactura.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    private void btnExportarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarExcelActionPerformed
        if((codigSociedadExcel != null && !codigSociedadExcel.equals("") && !codigSociedadExcel.equals("---- SELECCIONE ----")) || (codigCentroExcel != null   && !codigCentroExcel.equals("") && !codigCentroExcel.equals("---- SELECCIONE ----"))){
            try{

                log.info("Cantidad Vacia ========>" );
                BLSerie blSerie = new BLSerie();
                ArrayList<TemplateExcel> lista = blSerie.listaSociedadSeries(archivo_propiedades, codigSociedadExcel, codigCentroExcel);
                log.info("Cantidad ========>" + lista.size());
                int cantidadColumnas = lista.size();
                reiniciaModelo();

                for(int i = 0; i < lista.size(); i++){
                    Object[] fila = {
                            lista.get(i).getSociedad(),
                            lista.get(i).getCentro(),
                            lista.get(i).getCodListComprobante(),
                            lista.get(i).getDetaCaja(),
                            lista.get(i).getNegocio(),
                            lista.get(i).getUbicacion(),
                            lista.get(i).getLicencia(),
                            lista.get(i).getIdOperador(),
                            lista.get(i).getCaja(),
                            lista.get(i).getAnydesk(),
                            lista.get(i).getIp(),
                            lista.get(i).getHostname()
                    };
                    modelo.addRow(fila);
                }



                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("Datos");
                Row rowCol = sheet.createRow(0);
                for(int r = 0; r < jtDatos.getColumnCount(); r++){
                    Cell cell = rowCol.createCell(r);
                    cell.setCellValue(jtDatos.getColumnName(r));
                }

                for(int h = 0; h < jtDatos.getRowCount(); h++){
                    Row row = sheet.createRow(h + 1);
                    for(int k = 0; k < jtDatos.getColumnCount(); k++){
                        Cell cell = row.createCell(k);
                        if(jtDatos.getValueAt(h, k) != null){
                            cell.setCellValue(jtDatos.getValueAt(h, k).toString());
                        }

                    }
                }
                Date date = new Date();
                SimpleDateFormat DateFor = new SimpleDateFormat("dd-M-yyyy hh:mm:s");
                String stringDate = DateFor.format(date);
                String dateActual = StringUtils.replace(stringDate, ":", "-");

                String path = "C:\\Listado_Boletas";
                File dirs = new File(path);

                if(!dirs.exists()){
                    dirs.mkdirs();
                }

                FileOutputStream out = new FileOutputStream(new File(dirs,"Listado Series " + dateActual + ".xlsx"));
                wb.write(out);
                wb.close();
                out.close();
                JOptionPane.showMessageDialog(getContentPane(),
                        "Ruta: C:\\Listado_Boletas",
                        "Archivo generado",
                        JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Archivo creado.");
            }catch(IOException io){
                System.out.println(io);
            }
        }else{
            JOptionPane.showMessageDialog(getContentPane(),
                    "Seleccione datos correctamente.",
                    "Descarga Excel",
                    JOptionPane.ERROR_MESSAGE);
        }

       
     
    }//GEN-LAST:event_btnExportarExcelActionPerformed

    private void cboSociedadExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSociedadExcelActionPerformed
        // TODO add your handling code here:
        codigSociedadExcel = "";
        if(!cboSociedadExcel.getSelectedItem().toString().equals("---- SELECCIONE ----")){
               agregaCboCentroExcelItems();
               String splitSociedadExcel[] = cboSociedadExcel.getSelectedItem().toString().split("-");
               codigSociedadExcel = splitSociedadExcel[0].trim();
               log.info(codigSociedadExcel);
        }
    }//GEN-LAST:event_cboSociedadExcelActionPerformed

    private void cboCentroExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCentroExcelActionPerformed
        // TODO add your handling code here:
        codigCentroExcel = "";
        if(cboCentroExcel.getSelectedItem() != null){
            log.info(cboCentroExcel.getSelectedItem().toString());

            if(!cboCentroExcel.getSelectedItem().toString().equals("---- SELECCIONE ----")){
                /*String splitSociedadExcel[] = cboSociedadExcel.getSelectedItem().toString().split("-");
                codigSociedadExcel = splitSociedadExcel[0].trim();
                log.info(codigSociedadExcel);*/

                String splitCentroExcel[] = cboCentroExcel.getSelectedItem().toString().split("-");
                codigCentroExcel = splitCentroExcel[0].trim();

                log.info(codigCentroExcel);

            }
        }
    }//GEN-LAST:event_cboCentroExcelActionPerformed

    private void cboCentroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCentroActionPerformed
        // TODO add your handling code here:
        codigCentro = "";
        txtBoleta.setEditable(true);
        txtFactura.setEditable(false);
        txtNotaBoleta.setEditable(true);
        txtNotaFactura.setEditable(true);
        txtDetaCaja.setEditable(true);
        txtDetaCaja.setEditable(true);
        txtNegocio.setEditable(true);
        txtUbicacion.setEditable(true);
        txtLicencia.setEditable(true);
        txtIdOperador.setEditable(true);
        txtCaja.setEditable(true);
        txtAnydesk.setEditable(true);
        txtIp.setEditable(true);
        txtHostname.setEditable(true);
        if(cboCentro.getSelectedItem() != null){
            log.info(cboCentro.getSelectedItem().toString());
                
            if(!cboCentro.getSelectedItem().toString().equals("---- SELECCIONE ----")){
                /*String splitSociedad[] = comboBox1.getSelectedItem().toString().split("-");
                codigSociedad = splitSociedad[0].trim();
                log.info(codigSociedad);*/

                String splitCentro[] = cboCentro.getSelectedItem().toString().split("-");
                codigCentro = splitCentro[0].trim();

                log.info(codigCentro);

                BLSerie blSerie = new BLSerie();
                Serie serie = blSerie.generaSerie(archivo_propiedades, codigSociedad, codigCentro);
                txtBoleta.setText(serie.getBoleta());
                txtFactura.setText(serie.getFactura());
                txtNotaBoleta.setText(serie.getNotaBoleta());
                txtNotaFactura.setText(serie.getNotaFactura());
                txtBoleta.setEditable(false);
                txtFactura.setEditable(false);
                txtNotaBoleta.setEditable(false);
                txtNotaFactura.setEditable(false);
                txtDetaCaja.setEditable(true);
                txtDetaCaja.setEditable(true);
                txtNegocio.setEditable(true);
                txtUbicacion.setEditable(true);
                txtLicencia.setEditable(true);
                txtIdOperador.setEditable(true);
                txtCaja.setEditable(true);
                txtAnydesk.setEditable(true);
                txtIp.setEditable(true);
                txtHostname.setEditable(true);
                
            }else {
                txtBoleta.setText("");
                txtFactura.setText("");
                txtNotaBoleta.setText("");
                txtNotaFactura.setText("");
                txtDetaCaja.setText("");
                txtNegocio.setText("");
                txtUbicacion.setText("");
                txtLicencia.setText("");
                txtIdOperador.setText("");
                txtCaja.setText("");
                txtAnydesk.setText("");
                txtIp.setText("");
                txtHostname.setText("");
            }
        }
                
    }//GEN-LAST:event_cboCentroActionPerformed

    private void comboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox1ActionPerformed
        // TODO add your handling code here:
       codigSociedad = "";
       if(!comboBox1.getSelectedItem().toString().equals("---- SELECCIONE ----")){
            agregaCboCentroItems();
            String splitSociedad[] = comboBox1.getSelectedItem().toString().split("-");
            codigSociedad = splitSociedad[0].trim();
            log.info(codigSociedad);
        }else {
            txtBoleta.setText("");
            txtFactura.setText("");
            txtNotaBoleta.setText("");
            txtNotaFactura.setText("");
            cboCentro.removeAllItems();
        }
    }//GEN-LAST:event_comboBox1ActionPerformed

    private void buttonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkActionPerformed
        // TODO add your handling code here:
        if(!"".equals(txtBoleta.getText()) && !"".equals(txtFactura.getText()) && !"".equals(txtNotaBoleta.getText()) && !"".equals(txtNotaFactura.getText())){
            BLSerie blSerie = new BLSerie();
            SerieSociedad nuevaSerie = new SerieSociedad();
            nuevaSerie.setSociedad(codigSociedad);
            nuevaSerie.setCentro(codigCentro);
            nuevaSerie.setCliente("-");
            nuevaSerie.setDetaCaja(txtDetaCaja.getText());
            nuevaSerie.setNegocio(txtNegocio.getText());
            nuevaSerie.setUbicacion(txtUbicacion.getText());
            nuevaSerie.setLicencia(txtLicencia.getText());
            nuevaSerie.setIdOperador(txtIdOperador.getText());
            nuevaSerie.setCaja(txtCaja.getText());
            nuevaSerie.setAnydesk(txtAnydesk.getText());
            
            nuevaSerie.setIp(txtIp.getText());
            nuevaSerie.setHostname(txtHostname.getText());
            
            String codListComprobante = "'" + txtBoleta.getText() + "', " + "'" + txtFactura.getText() + "', " + "'" + txtNotaBoleta.getText() + "', " + "'" + txtNotaFactura.getText() + "'";
            nuevaSerie.setCodListComprobante(codListComprobante);
            
            nuevaSerie.setSerie(txtBoleta.getText());
            blSerie.registrar(archivo_propiedades, nuevaSerie);
            nuevaSerie.setSerie(txtFactura.getText());
            blSerie.registrar(archivo_propiedades, nuevaSerie);
            nuevaSerie.setSerie(txtNotaBoleta.getText());
            blSerie.registrar(archivo_propiedades, nuevaSerie);
            nuevaSerie.setSerie(txtNotaFactura.getText());
            blSerie.registrar(archivo_propiedades, nuevaSerie);

           JOptionPane.showMessageDialog(getContentPane(),
                    "Serie registrada correctamente.",
                    "Registrar Serie",
                    JOptionPane.INFORMATION_MESSAGE);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(getContentPane(),
                    "Validar datos de serie.",
                    "Registrar Serie",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonOkActionPerformed

    private void btnBuscarBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarBoletaActionPerformed
        // TODO add your handling code here:
        limpiar();
        //if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
        BLSerie blSerie = new BLSerie();
        if(codigSociedadExcel != null && codigCentroExcel != null && !codigSociedadExcel.equals("")  && !codigCentroExcel.equals("") && !codigCentroExcel.equals("---- SELECCIONE ----") && !codigSociedadExcel.equals("---- SELECCIONE ----")
            && !"".equals(txtSearchBoleta.getText())){
            SerieSociedad serieSociecdad = blSerie.obtenerBoleta(archivo_propiedades, codigSociedadExcel, codigCentroExcel, txtSearchBoleta.getText());
            String codLisComprobante= serieSociecdad.getCodListComprobante();
            log.info(codLisComprobante);
            log.info(serieSociecdad.getCodListComprobante());
            log.info(serieSociecdad);
            if(codLisComprobante == null){
                JOptionPane.showMessageDialog(getContentPane(),
                        "Datos de Serie no encontrados",
                        "Consulta Serie",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            String[] parts = codLisComprobante.split("-");
            if(serieSociecdad != null){
                txtBoleta.setText(parts[0].toString());
                txtFactura.setText(parts[1].toString());
                txtNotaBoleta.setText(parts[2].toString());
                txtNotaFactura.setText(parts[3].toString());
                txtDetaCaja.setText(serieSociecdad.getDetaCaja());
                txtNegocio.setText(serieSociecdad.getNegocio());
                txtUbicacion.setText(serieSociecdad.getUbicacion());
                txtLicencia.setText(serieSociecdad.getLicencia());
                txtIdOperador.setText(serieSociecdad.getIdOperador());
                txtCaja.setText(serieSociecdad.getCaja());
                txtAnydesk.setText(serieSociecdad.getAnydesk());
                txtIp.setText(serieSociecdad.getIp());
                txtHostname.setText(serieSociecdad.getHostname());


                txtBoleta.setEditable(false);
                txtFactura.setEditable(false);
                txtNotaBoleta.setEditable(false);
                txtNotaFactura.setEditable(false);
                /*txtDetaCaja.setEditable(false);
                txtDetaCaja.setEditable(false);
                txtNegocio.setEditable(false);
                txtUbicacion.setEditable(false);
                txtLicencia.setEditable(false);
                txtIdOperador.setEditable(false);
                txtCaja.setEditable(false);
                txtAnydesk.setEditable(false);
                txtIp.setEditable(false);
                txtHostname.setEditable(false);*/
            }
        }else{
            JOptionPane.showMessageDialog(getContentPane(),
                    "Selecciona correctamente los datos.",
                    "Consulta Serie",
                    JOptionPane.ERROR_MESSAGE);
        }

            
        //}
    }//GEN-LAST:event_btnBuscarBoletaActionPerformed

    private void limpiar() {
        txtBoleta.setText("");
        txtFactura.setText("");
        txtNotaBoleta.setText("");
        txtNotaFactura.setText("");
        comboBox1.setSelectedIndex(0);
        txtDetaCaja.setText("");
        txtNegocio.setText("");
        txtUbicacion.setText("");
        txtLicencia.setText("");
        txtIdOperador.setText("");
        txtCaja.setText("");
        txtAnydesk.setText("");
        txtIp.setText("");
        txtHostname.setText("");
    }
    
     private void agregaCboCentroExcelItems(){
        cboCentroExcel.removeAllItems();
        /*
        ---- SELECCIONE ----
        3001 - Plaza Norte y GTT
        3008 - Panisteria
        4000 - Servicio trastienda Integrada - Entre Paginas
        4007 - Centro Comercial Mall del Sur
        4501 - Consorcio Mediterraneo
         */
        cboCentroExcel.addItem("---- SELECCIONE ----");
        if("3001 - Plaza Norte y GTT".equals(cboSociedadExcel.getSelectedItem().toString())){
            cboCentroExcel.addItem("3001 - Plaza Norte");
        } else if ("3008 - Panisteria".equals(cboSociedadExcel.getSelectedItem().toString())){
            cboCentroExcel.addItem("3801 - La Panisteria - Plaza Norte");
            cboCentroExcel.addItem("3802 - La Panisteria - Mall del Sur");
        } else if ("4000 - Servicio trastienda Integrada - Entre Paginas".equals(cboSociedadExcel.getSelectedItem().toString())){
            cboCentroExcel.addItem("4300 - Entre Paginas - Ferias");
            cboCentroExcel.addItem("4301 - Entre Paginas - Gran Terminal Terrestre");
            cboCentroExcel.addItem("4302 - Entre Paginas CC Plaza Norte 01");
            cboCentroExcel.addItem("4304 - Entre Paginas - Mall del Sur 01");
            cboCentroExcel.addItem("4305 - Entre Paginas - Mall del Sur 2 - PB");
            cboCentroExcel.addItem("4306 - Entre Paginas CC Plaza Norte 03");
            cboCentroExcel.addItem("4307 - Librería Ecomerce");
        } else if ("4007 - Centro Comercial Mall del Sur".equals(cboSociedadExcel.getSelectedItem().toString())){
            cboCentroExcel.addItem("4007 - Mall del Sur");
        } else if ("4501 - Consorcio Mediterraneo".equals(cboSociedadExcel.getSelectedItem().toString())){
            cboCentroExcel.addItem("4611 - Mediterraneo - El Polo");
            cboCentroExcel.addItem("4614 - Mediterraneo - Plaza Norte");
            cboCentroExcel.addItem("4615 - Mediterraneo - Mall del Sur");
            cboCentroExcel.addItem("4616 - Mediterraneo - Petit Thouars");
            cboCentroExcel.addItem("4617 - Mediterraneo - MDS Patio Comida");
            cboCentroExcel.addItem("4618 - Mediterraneo - Benavides");
            cboCentroExcel.addItem("4623 - Mediterraneo - Real Plaza Centro");
            cboCentroExcel.addItem("4624 - Mediterraneo - Raul Ferrero");
            cboCentroExcel.addItem("4625 - Mediterraneo - Modulos PN");
            cboCentroExcel.addItem("4629 - Mediterraneo - Capon");
            cboCentroExcel.addItem("4630 - Mediterraneo - Aranibar");
            cboCentroExcel.addItem("4631 - Mediterraneo - La Marina");
            cboCentroExcel.addItem("4632 - Mediterraneo - CC El Polo");
            cboCentroExcel.addItem("4633 - Mediterraneo - Chacarilla");
            cboCentroExcel.addItem("4634 - Mediterraneo - Modulos MDS");
            cboCentroExcel.addItem("4711 - Don Buffet Plaza Norte");
            cboCentroExcel.addItem("4712 - Don Buffet Mall del Sur");
        }
    }
     
     
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CargaSeries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CargaSeries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CargaSeries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CargaSeries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CargaSeries().setVisible(true);
            }
        });
    }
    
    private void onOK() {
        // add your code here

        if(!"".equals(txtBoleta.getText()) && !"".equals(txtFactura.getText()) && !"".equals(txtNotaBoleta.getText()) && !"".equals(txtNotaFactura.getText())){
            BLSerie blSerie = new BLSerie();
            SerieSociedad nuevaSerie = new SerieSociedad();
            nuevaSerie.setSociedad(codigSociedad != null && !codigSociedad.equals("")? codigSociedad: codigSociedadExcel);
            nuevaSerie.setCentro(codigCentro != null && !codigCentro.equals("")? codigCentro: codigCentroExcel);
            nuevaSerie.setCliente("-");
            nuevaSerie.setDetaCaja(txtDetaCaja.getText());
            nuevaSerie.setNegocio(txtNegocio.getText());
            nuevaSerie.setUbicacion(txtUbicacion.getText());
            nuevaSerie.setLicencia(txtLicencia.getText());
            nuevaSerie.setIdOperador(txtIdOperador.getText());
            nuevaSerie.setCaja(txtCaja.getText());
            nuevaSerie.setAnydesk(txtAnydesk.getText());
            nuevaSerie.setIp(txtIp.getText());
            nuevaSerie.setHostname(txtHostname.getText());
            String codListComprobante = txtBoleta.getText() + "-" + txtFactura.getText() + "-" + txtNotaBoleta.getText() + "-" + txtNotaFactura.getText();
            nuevaSerie.setCodListComprobante(codListComprobante);
          
            nuevaSerie.setSerie(txtBoleta.getText());
            if(!txtBoleta.getText().equals("") && !txtBoleta.getText().equals("_"))
                blSerie.registrar(archivo_propiedades, nuevaSerie);
            nuevaSerie.setSerie(txtFactura.getText());
            if(!txtFactura.getText().equals("") && !txtFactura.getText().equals("_"))
                blSerie.registrar(archivo_propiedades, nuevaSerie);
            nuevaSerie.setSerie(txtNotaBoleta.getText());
            if(!txtNotaBoleta.getText().equals("") && !txtNotaBoleta.getText().equals("_"))
                blSerie.registrar(archivo_propiedades, nuevaSerie);
            nuevaSerie.setSerie(txtNotaFactura.getText());
            if(!txtNotaFactura.getText().equals("") && !txtNotaFactura.getText().equals("_"))
                blSerie.registrar(archivo_propiedades, nuevaSerie);

            JOptionPane.showMessageDialog(getContentPane(),
                    "Serie registrada correctamente.",
                    "Registrar Serie",
                    JOptionPane.INFORMATION_MESSAGE);
            limpiar();
        }else{
           JOptionPane.showMessageDialog(getContentPane(),
                    "Validar datos de serie.",
                    "Registrar Serie",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void agregaCboCentroItems(){
        cboCentro.removeAllItems();
        /*
        ---- SELECCIONE ----
        3001 - Plaza Norte y GTT
        3008 - Panisteria
        4000 - Servicio trastienda Integrada - Entre Paginas
        4007 - Centro Comercial Mall del Sur
        4501 - Consorcio Mediterraneo
         */
        cboCentro.addItem("---- SELECCIONE ----");
        if("3001 - Plaza Norte y GTT".equals(comboBox1.getSelectedItem().toString())){
            cboCentro.addItem("3001 - Plaza Norte");
        } else if ("3008 - Panisteria".equals(comboBox1.getSelectedItem().toString())){
            cboCentro.addItem("3801 - La Panisteria - Plaza Norte");
            cboCentro.addItem("3802 - La Panisteria - Mall del Sur");
        } else if ("4000 - Servicio trastienda Integrada - Entre Paginas".equals(comboBox1.getSelectedItem().toString())){
            cboCentro.addItem("4300 - Entre Paginas - Ferias");
            cboCentro.addItem("4301 - Entre Paginas - Gran Terminal Terrestre");
            cboCentro.addItem("4302 - Entre Paginas CC Plaza Norte 01");
            cboCentro.addItem("4304 - Entre Paginas - Mall del Sur 01");
            cboCentro.addItem("4305 - Entre Paginas - Mall del Sur 2 - PB");
            cboCentro.addItem("4306 - Entre Paginas CC Plaza Norte 03");
            cboCentro.addItem("4307 - Librería Ecomerce");
        } else if ("4007 - Centro Comercial Mall del Sur".equals(comboBox1.getSelectedItem().toString())){
            cboCentro.addItem("4007 - Mall del Sur");
        } else if ("4501 - Consorcio Mediterraneo".equals(comboBox1.getSelectedItem().toString())){
            cboCentro.addItem("4611 - Mediterraneo - El Polo");
            cboCentro.addItem("4614 - Mediterraneo - Plaza Norte");
            cboCentro.addItem("4615 - Mediterraneo - Mall del Sur");
            cboCentro.addItem("4616 - Mediterraneo - Petit Thouars");
            cboCentro.addItem("4617 - Mediterraneo - MDS Patio Comida");
            cboCentro.addItem("4618 - Mediterraneo - Benavides");
            cboCentro.addItem("4623 - Mediterraneo - Real Plaza Centro");
            cboCentro.addItem("4624 - Mediterraneo - Raul Ferrero");
            cboCentro.addItem("4625 - Mediterraneo - Modulos PN");
            cboCentro.addItem("4629 - Mediterraneo - Capon");
            cboCentro.addItem("4630 - Mediterraneo - Aranibar");
            cboCentro.addItem("4631 - Mediterraneo - La Marina");
            cboCentro.addItem("4632 - Mediterraneo - CC El Polo");
            cboCentro.addItem("4633 - Mediterraneo - Chacarilla");
            cboCentro.addItem("4634 - Mediterraneo - Modulos MDS");
            cboCentro.addItem("4711 - Don Buffet Plaza Norte");
            cboCentro.addItem("4712 - Don Buffet Mall del Sur");
        }
    }

    private void reiniciaModelo(){
        modelo = new DefaultTableModel();
        modelo.addColumn("SOCIEDAD");
        modelo.addColumn("CENTRO");
        modelo.addColumn("SERIES");
        modelo.addColumn("DETA_CAJA");
        modelo.addColumn("NEGOCIO");
        modelo.addColumn("UBICACION");
        modelo.addColumn("LICENCIA");
        modelo.addColumn("ID_OPERADOR");
        modelo.addColumn("CAJA");
        modelo.addColumn("ANYDESK");
        modelo.addColumn("DIRECCION_IP");
        modelo.addColumn("HOSTNAME");
        jtDatos.setModel(modelo);
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarBoleta;
    public javax.swing.JButton btnExportarExcel;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonOk;
    public javax.swing.JComboBox<String> cboCentro;
    private javax.swing.JComboBox<String> cboCentroExcel;
    private javax.swing.JComboBox<String> cboSociedadExcel;
    public javax.swing.JComboBox<String> comboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jtDatos;
    private javax.swing.JTextField txtAnydesk;
    private javax.swing.JTextField txtBoleta;
    private javax.swing.JTextField txtCaja;
    private javax.swing.JTextField txtDetaCaja;
    private javax.swing.JTextField txtFactura;
    private javax.swing.JTextField txtHostname;
    private javax.swing.JTextField txtIdOperador;
    private javax.swing.JTextField txtIp;
    private javax.swing.JTextField txtLicencia;
    private javax.swing.JTextField txtNegocio;
    private javax.swing.JTextField txtNotaBoleta;
    private javax.swing.JTextField txtNotaFactura;
    private javax.swing.JTextField txtSearchBoleta;
    private javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}

