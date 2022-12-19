import business_logic.BLSerie;
import model.Serie;
import model.SerieSociedad;
import org.apache.log4j.Logger;
import util.Propiedades;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class CargaSerie2 extends JDialog {

    private static Logger log = Logger.getLogger(CargaSerie2.class);
    private JPanel contentPane = new JPanel();
    private JButton buttonOK = new JButton("Grabar");
    private JButton buttonCancel = new JButton("Cancelar");
    private JComboBox comboBox1 = new JComboBox<>();
    private JTextField txtBoleta = new JTextField();
    private JTextField txtFactura = new JTextField();
    private JTextField txtNotaBoleta = new JTextField();
    private JTextField txtNotaFactura = new JTextField();
    private JComboBox cboCentro = new JComboBox<>();
    private static Propiedades archivo_propiedades;

    JLabel lblSociedad = new JLabel("Sociedad");
    JLabel lblCentro = new JLabel("Centro");
    JLabel lblBoleta = new JLabel("Boleta");
    JLabel lblFactura = new JLabel("Factura");
    JLabel lblNBoleta = new JLabel("Nota Boleta");
    JLabel lblNFactura = new JLabel("Nota Factura");

    String codigSociedad;

    String codigCentro;

    public CargaSerie2() {
        //super(new javax.swing.JFrame(), true);
        JPanel pl = new JPanel();
        JPanel pr = new JPanel();
        JPanel pf = new JPanel();
        contentPane.setPreferredSize(new Dimension(600, 400));

        pl.setPreferredSize(new Dimension(100, 300));
        pr.setPreferredSize(new Dimension(300, 300));
        pf.setPreferredSize(new Dimension(250, 60));

        lblSociedad.setPreferredSize(new Dimension(100, 30));
        lblCentro.setPreferredSize(new Dimension(100, 30));
        lblBoleta.setPreferredSize(new Dimension(100, 30));
        lblFactura.setPreferredSize(new Dimension(100, 30));
        lblNBoleta.setPreferredSize(new Dimension(100, 30));
        lblNFactura.setPreferredSize(new Dimension(100, 30));

        comboBox1.setPreferredSize(new Dimension(300, 30));
        cboCentro.setPreferredSize(new Dimension(300, 30));
        txtBoleta.setPreferredSize(new Dimension(300, 30));
        txtBoleta.setEditable(false);
        txtFactura.setPreferredSize(new Dimension(300, 30));
        txtFactura.setEditable(false);
        txtNotaBoleta.setPreferredSize(new Dimension(300, 30));
        txtNotaBoleta.setEditable(false);
        txtNotaFactura.setPreferredSize(new Dimension(300, 30));
        txtNotaFactura.setEditable(false);
        comboBox1.addItem("---- SELECCIONE ----");
        comboBox1.addItem("3001 - Plaza Norte y GTT");
        comboBox1.addItem("3008 - Panisteria");
        comboBox1.addItem("4000 - Servicio trastienda Integrada - Entre Paginas");
        comboBox1.addItem("4007 - Centro Comercial Mall del Sur");
        comboBox1.addItem("4501 - Consorcio Mediterraneo");
        pl.add(lblSociedad);
        pl.add(lblCentro);
        pl.add(lblBoleta);
        pl.add(lblFactura);
        pl.add(lblNBoleta);
        pl.add(lblNFactura);


        pr.add(comboBox1);
        pr.add(cboCentro);
        pr.add(txtBoleta);
        pr.add(txtFactura);
        pr.add(txtNotaBoleta);
        pr.add(txtNotaFactura);

        contentPane.add(pl);
        contentPane.add(pr);
        buttonOK.setPreferredSize(new Dimension(100, 30) );
        buttonCancel.setPreferredSize(new Dimension(100, 30));
        pf.add(buttonOK);
        pf.add(buttonCancel);
        contentPane.add(pf);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        contentPane.setSize( 1024, 768 );

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        comboBox1.addActionListener(new ActionListener() {
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
        });

        cboCentro.addActionListener(new ActionListener() {
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
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here

        if(!"".equals(txtBoleta.getText()) && !"".equals(txtFactura.getText()) && !"".equals(txtNotaBoleta.getText()) && !"".equals(txtNotaFactura.getText())){
            BLSerie blSerie = new BLSerie();
            SerieSociedad nuevaSerie = new SerieSociedad();
            nuevaSerie.setSociedad(codigSociedad);
            nuevaSerie.setCentro(codigCentro);
            nuevaSerie.setCliente("-");
            nuevaSerie.setSerie(txtBoleta.getText());
            blSerie.registrar(archivo_propiedades, nuevaSerie);
            nuevaSerie.setSerie(txtFactura.getText());
            blSerie.registrar(archivo_propiedades, nuevaSerie);
            nuevaSerie.setSerie(txtNotaBoleta.getText());
            blSerie.registrar(archivo_propiedades, nuevaSerie);
            nuevaSerie.setSerie(txtNotaFactura.getText());
            blSerie.registrar(archivo_propiedades, nuevaSerie);

            JOptionPane.showMessageDialog(contentPane,
                    "Serie registrada correctamente.",
                    "Registrar Serie",
                    JOptionPane.INFORMATION_MESSAGE);
            limpiar();
        }else{
            JOptionPane.showMessageDialog(contentPane,
                    "Validar datos de serie.",
                    "Registrar Serie",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void limpiar() {
        txtBoleta.setText("");
        txtFactura.setText("");
        txtNotaBoleta.setText("");
        txtNotaFactura.setText("");
        comboBox1.setSelectedIndex(0);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        File propiedades = new File("consulta.properties");
        log.info("carga");
        if (!propiedades.exists()) {
            archivo_propiedades = new Propiedades(
                    "jdbc:sqlserver://localhost;encrypt=false;databaseName=FMEDITERRANEO;user=sa; password=P@ssword2019;",
                    "127.0.0.1",
                    "TestUser",
                    "123456",
                    3
            );
            new Util().escribirPropiedades(archivo_propiedades);
        } else {
            archivo_propiedades = new Util().leerPropiedades();
        }
        CargaSerie2 dialog = new CargaSerie2();

        log.info("carga");
        dialog.pack();
        log.info("pack");
        dialog.setVisible(true);
        log.info("true");
        System.exit(0);
        log.info("exit");
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
}
