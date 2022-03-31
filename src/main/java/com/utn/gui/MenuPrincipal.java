/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.gui;

/*
Se importan los diferentes paquetes del proyecto, se importan las bibliotecas para trabajar con los Arraylist (listas), 
se importan diferentes herramientas para trabajar con los componentes de la GUI
 */
import com.utn.logica.*;
import com.utn.utilidades.Archivos;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MenuPrincipal extends javax.swing.JFrame {

    /*
    Se crean las listas productos y Clientes Corporativos, se crea el objeto que contiene los archivos, además se hacen diferentes varibles
     */
    ArrayList<Producto> listaProductos;
    ArrayList<Producto> listaCompra;
    ArrayList<Corporativo> clientesCorp;
    Archivos archivos;
    DefaultTableModel modelo;
    ArrayList<TipoTarjeta> tarjetas;
    double total;

    /**
     * Creates new form MneuPrincipal
     */
    public MenuPrincipal() throws Exception {
        /*
        Se crean los array y se muestran en consola los diferentes datos del problema 
         */
        ArrayList<Producto> productos;

        //this.getContentPane().setBackground(new Color(255,255,255));
        initComponents();

        archivos = new Archivos();
        listaCompra = new ArrayList<Producto>();
        clientesCorp = new ArrayList<Corporativo>();

        instanciaTarjetas();
        llenarCombo();
        llenarClientesCorp();
        spDir.setVisible(false);
        lblDir.setVisible(false);
        cmbTarjetas.setVisible(false);
        txtfDesc.setEnabled(false);
        txtfNoTarjeta.setVisible(false);
        lblNombre.setVisible(false);
        txtfNombre.setVisible(false);
        lblSexo.setVisible(false);
        cmbSexo.setVisible(false);
        lblNumTarjeta.setVisible(false);
        
        
        String col[] = {"Codigo", "Desc", "Precio", "Utilidad"};
        modelo = new DefaultTableModel(col, 0);
        tblLista.setModel(modelo);

    }

    /*
    Este método permite instanciar los tipos de tarjetas, y además se agrean al arreglo
     */
    public void instanciaTarjetas() {
        TipoTarjeta visa = new TipoTarjeta("Visa");
        TipoTarjeta mastercard = new TipoTarjeta("Master card");
        TipoTarjeta americanexpress = new TipoTarjeta("American Express");
        tarjetas = new ArrayList<TipoTarjeta>();
        tarjetas.add(visa);
        tarjetas.add(mastercard);
        tarjetas.add(americanexpress);
    }

    /*
    Este método es el en cargado de recorrer y llenar la Lista Clientes Corporativos.
     */
    public void llenarClientesCorp() throws Exception {
        clientesCorp = archivos.leerCorporativos();
        for (Corporativo cliente : clientesCorp) {
            cmbCliCorp.addItem(cliente.mostrarLista());
        }
    }/*
    Este método es el en cargado de recorrer y llenar la Lista Productos.
     */


    public void llenarCombo() throws Exception {
        listaProductos = archivos.leerProductos();
        for (Producto producto : listaProductos) {
            cmbProducto.addItem(producto.mostrarComboB());
        }

        for (TipoTarjeta tarjeta : tarjetas) {
            cmbTarjetas.addItem(tarjeta.getDescripcion());
        }
    }

    /*
    Este método permite limpipar los datos de la orden de compra
     */
    public void limpiarPantalla() {
        listaCompra.clear();
        modelo.setRowCount(0);
        total = 0;
        lblTotal.setText(total + "");
        rbCorp.setSelected(true);
        txtaDir.setText("");
        txtfDesc.setText("");
        txtfDesc.setEnabled(false);
        txtfNombre.setText("");
        txtfNoTarjeta.setText("");
        chbDesc.setSelected(false);
        bgContado.setSelected(true);
    }

    /*
    Este método es el que da forma a la orden final, se muestran los respectivos datos,
    y crea condíciones para realizar las distribuciones y verifica que los datos se almacenen de forma correcta. 
     */
    public void procesarorden(boolean procesarDesc) {
        DetalleOrden deta = new DetalleOrden(listaCompra);
        String desclimit = txtfDesc.getText();
        Pago pag;
        Cliente client;
        int tipoCliente, tipoPago;
        if (rbCorp.isSelected()) {
            Corporativo corp = clientesCorp.get(cmbCliCorp.getSelectedIndex());
            client = corp;
            //tipoCliente=0;
        } else {
            //tipoCliente=1;
            String s = "";
            if (cmbSexo.getSelectedIndex() == 1) {
                s = "F";
            } else {
                s = "M";
            }
            Ocasional oca = new Ocasional(txtaDir.getText(), txtfNombre.getText(), s);
            client = oca;
            System.out.println(oca.toString());
        }
        int tipoMoneda = 0;
        if (bgContado.isSelected()) {
            String moneda = "";

            tipoPago = 0;
            if (cmbMoneda.getSelectedIndex() == 0) {
                moneda = "Colones";

            } else {
                moneda = "Dolares";
                tipoMoneda = 1;
            }

            Contado cont = new Contado(moneda, tipoMoneda);
            pag = cont;
        } else {
            tipoPago = 1;
            TipoTarjeta tarjeta = new TipoTarjeta(cmbTarjetas.getSelectedItem().toString());
            Credito cred = new Credito(txtfNoTarjeta.getText(), tarjeta, 0);
            pag = cred;
        }
        Date fecha = new Date();
        Orden orden = new Orden(fecha, deta, pag, client, procesarDesc, desclimit);

        orden.finalizarOrden(tipoPago);
        this.limpiarPantalla();
    }

    public void finalizar() {
        String desclimit = txtfDesc.getText();
        String noTarjeta = txtfNoTarjeta.getText();
        if (chbDesc.isSelected()) {
            if (desclimit.length() > 2 || desclimit.isBlank() || noTarjeta.length() < 16 || noTarjeta.isBlank()) {
                JOptionPane.showMessageDialog(null, "Todos los cambos deben de estar llenos");
                txtfDesc.setText("");
            } else {
                procesarorden(true);
            }
        } else {
            procesarorden(false);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngCliente = new javax.swing.ButtonGroup();
        btngPago = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLista = new javax.swing.JTable();
        cmbProducto = new javax.swing.JComboBox<>();
        spDir = new javax.swing.JScrollPane();
        txtaDir = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        rbCorp = new javax.swing.JRadioButton();
        rbOca = new javax.swing.JRadioButton();
        cmbCliCorp = new javax.swing.JComboBox<>();
        lblDir = new javax.swing.JLabel();
        txtfNombre = new javax.swing.JTextField();
        cmbSexo = new javax.swing.JComboBox<>();
        txtfDesc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        chbDesc = new javax.swing.JCheckBox();
        lblSexo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        btnFinalizar = new javax.swing.JButton();
        txtfNoTarjeta = new javax.swing.JFormattedTextField();
        btnAgregar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        bgContado = new javax.swing.JRadioButton();
        bgCredito = new javax.swing.JRadioButton();
        cmbMoneda = new javax.swing.JComboBox<>();
        cmbTarjetas = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblNumTarjeta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú Principal");
        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblLista.setBackground(new java.awt.Color(255, 255, 204));
        tblLista.setFont(new java.awt.Font("Dubai Medium", 0, 12)); // NOI18N
        tblLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblLista);

        cmbProducto.setBackground(new java.awt.Color(255, 255, 204));
        cmbProducto.setFont(new java.awt.Font("Dubai Medium", 0, 12)); // NOI18N

        txtaDir.setBackground(new java.awt.Color(255, 255, 204));
        txtaDir.setColumns(20);
        txtaDir.setRows(5);
        txtaDir.setToolTipText("Direccion");
        txtaDir.setAutoscrolls(false);
        spDir.setViewportView(txtaDir);

        jLabel1.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        jLabel1.setText("Tipo Cliente");

        rbCorp.setBackground(new java.awt.Color(255, 255, 255));
        btngCliente.add(rbCorp);
        rbCorp.setFont(new java.awt.Font("Dubai Light", 0, 18)); // NOI18N
        rbCorp.setSelected(true);
        rbCorp.setText("Corporativo");
        rbCorp.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbCorpStateChanged(evt);
            }
        });
        rbCorp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCorpActionPerformed(evt);
            }
        });

        rbOca.setBackground(new java.awt.Color(255, 255, 255));
        btngCliente.add(rbOca);
        rbOca.setFont(new java.awt.Font("Dubai Light", 0, 18)); // NOI18N
        rbOca.setText("Ocasional");
        rbOca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOcaActionPerformed(evt);
            }
        });

        lblDir.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        lblDir.setText("Dirección");

        txtfNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfNombreActionPerformed(evt);
            }
        });

        cmbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hombre", "Mujer" }));

        txtfDesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfDescKeyTyped(evt);
            }
        });

        jLabel3.setText("%");

        chbDesc.setBackground(new java.awt.Color(255, 255, 255));
        chbDesc.setText("Descuento");
        chbDesc.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chbDescStateChanged(evt);
            }
        });
        chbDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbDescActionPerformed(evt);
            }
        });

        lblSexo.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        lblSexo.setText("Sexo");

        lblNombre.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        lblNombre.setText("Nombre");

        btnFinalizar.setBackground(new java.awt.Color(255, 255, 153));
        btnFinalizar.setFont(new java.awt.Font("Dubai Light", 0, 18)); // NOI18N
        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        try {
            txtfNoTarjeta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####-####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnAgregar.setBackground(new java.awt.Color(255, 255, 153));
        btnAgregar.setFont(new java.awt.Font("Dubai Light", 0, 18)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setMaximumSize(new java.awt.Dimension(462, 87));
        jPanel2.setMinimumSize(new java.awt.Dimension(462, 87));

        bgContado.setBackground(new java.awt.Color(255, 255, 204));
        btngPago.add(bgContado);
        bgContado.setSelected(true);
        bgContado.setText("Contado");
        bgContado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                bgContadoStateChanged(evt);
            }
        });

        bgCredito.setBackground(new java.awt.Color(255, 255, 204));
        btngPago.add(bgCredito);
        bgCredito.setText("Credito");
        bgCredito.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                bgCreditoStateChanged(evt);
            }
        });

        cmbMoneda.setBackground(new java.awt.Color(255, 255, 204));
        cmbMoneda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Colones", "Dolares" }));
        cmbMoneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMonedaActionPerformed(evt);
            }
        });

        cmbTarjetas.setBackground(new java.awt.Color(255, 255, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bgContado, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                    .addComponent(bgCredito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbMoneda, 0, 355, Short.MAX_VALUE)
                    .addComponent(cmbTarjetas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bgContado)
                    .addComponent(cmbMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bgCredito)
                    .addComponent(cmbTarjetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        jLabel4.setText("Facturación de productos");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        jLabel5.setText("¿Desea realizar un descuento?");

        jLabel2.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel2.setText("Total sin impuestos");

        lblTotal.setBackground(new java.awt.Color(255, 255, 204));
        lblTotal.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(0, 0, 0));

        lblNumTarjeta.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        lblNumTarjeta.setText("Número de Tarjeta:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblNumTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtfNoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(172, 172, 172)
                                .addComponent(btnFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(8, 8, 8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cmbProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(10, 10, 10))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spDir)
                                    .addComponent(txtfNombre)
                                    .addComponent(cmbSexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbCliCorp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblDir, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(rbOca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(rbCorp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(6, 6, 6)
                                                        .addComponent(chbDesc)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtfDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(rbCorp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rbOca)
                                .addGap(35, 35, 35)
                                .addComponent(cmbCliCorp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDir, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spDir, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblSexo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chbDesc)
                        .addComponent(txtfDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumTarjeta)
                    .addComponent(txtfNoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseExited

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        /*
        Este botón es el encargdo de almacenar los productos que se muestran en pantalla para hacer los cálculos totales,
        es esta sección se añade los datos y la cantidad del producto y se hace el cálculo Final 
         */
        Producto produ = listaProductos.get(cmbProducto.getSelectedIndex());
        total += (produ.getCosto() + (produ.getCosto() * produ.getUtilidad()));
        total = Math.round(total * 100) / 100d;
        System.out.println(total);
        lblTotal.setText(total + "");
        listaCompra.add(listaProductos.get(cmbProducto.getSelectedIndex()));
        String codigo = produ.getCodigoProducto();
        String desc = produ.getDescripcion();
        String precio = produ.getCosto() + "";
        String utilidad = produ.getUtilidad() + "";
        String prod[] = {codigo, desc, precio, utilidad};
        modelo.addRow(prod);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void rbCorpStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbCorpStateChanged
        /*
        Es la selección del cliente
         */
        if (rbCorp.isSelected()) {
            cmbCliCorp.setVisible(true);
            spDir.setVisible(false);
            lblDir.setVisible(false);
            lblNombre.setVisible(false);
            lblSexo.setVisible(false);
            cmbSexo.setVisible(false);
            txtfNombre.setVisible(false);
        } else {
            cmbCliCorp.setVisible(false);
            spDir.setVisible(true);
            lblDir.setVisible(true);
            lblNombre.setVisible(true);
            lblSexo.setVisible(true);
            cmbSexo.setVisible(true);
            txtfNombre.setVisible(true);
        }
    }//GEN-LAST:event_rbCorpStateChanged

    private void rbCorpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCorpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbCorpActionPerformed

    private void chbDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbDescActionPerformed
        /*
        En esta parte se gestiona el descuento que se desea aplicar
         */
        if (chbDesc.isSelected()) {

            txtfDesc.setEnabled(true);
        } else {
            txtfDesc.setEnabled(false);
        }
    }//GEN-LAST:event_chbDescActionPerformed

    private void chbDescStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chbDescStateChanged

    }//GEN-LAST:event_chbDescStateChanged

    private void txtfDescKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfDescKeyTyped
        /*
        Se verifica que el número ingresado para el descuento 
         */
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtfDescKeyTyped

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        /*
        Este botón ejecuta el método finalizar que temrina e imprime la orden final
         */
        if(listaCompra.isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe agregar productos antes de finalizar la venta");
        }else{
            finalizar();
        }
        
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void txtfNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfNombreActionPerformed

    private void rbOcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbOcaActionPerformed

    private void cmbMonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMonedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMonedaActionPerformed

    private void bgCreditoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_bgCreditoStateChanged
        /*
        La selección si se escoge la opción de Crédito
        */
        if (bgCredito.isSelected()) {
            cmbTarjetas.setVisible(true);
            txtfNoTarjeta.setVisible(true);
            lblNumTarjeta.setVisible(true);
        } else {
            cmbTarjetas.setVisible(false);
            txtfNoTarjeta.setVisible(false);
            lblNumTarjeta.setVisible(false);
        }
    }//GEN-LAST:event_bgCreditoStateChanged

    private void bgContadoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_bgContadoStateChanged
        /*
        La selección si se escoge la opción de Crédito
        */
        if (bgContado.isSelected()) {
            cmbMoneda.setVisible(true);
        } else {
            cmbMoneda.setVisible(false);
        }
    }//GEN-LAST:event_bgContadoStateChanged

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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MenuPrincipal().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bgContado;
    private javax.swing.JRadioButton bgCredito;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.ButtonGroup btngCliente;
    private javax.swing.ButtonGroup btngPago;
    private javax.swing.JCheckBox chbDesc;
    private javax.swing.JComboBox<String> cmbCliCorp;
    private javax.swing.JComboBox<String> cmbMoneda;
    private javax.swing.JComboBox<String> cmbProducto;
    private javax.swing.JComboBox<String> cmbSexo;
    private javax.swing.JComboBox<String> cmbTarjetas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDir;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNumTarjeta;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JRadioButton rbCorp;
    private javax.swing.JRadioButton rbOca;
    private javax.swing.JScrollPane spDir;
    private javax.swing.JTable tblLista;
    private javax.swing.JTextArea txtaDir;
    private javax.swing.JTextField txtfDesc;
    private javax.swing.JFormattedTextField txtfNoTarjeta;
    private javax.swing.JTextField txtfNombre;
    // End of variables declaration//GEN-END:variables
}
