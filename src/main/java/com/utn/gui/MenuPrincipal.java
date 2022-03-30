/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utn.gui;
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
/**
 *
 * @author Anthony
 */
public class MenuPrincipal extends javax.swing.JFrame {
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
        
        ArrayList<Producto> productos;
        
        //this.getContentPane().setBackground(new Color(255,255,255));
        initComponents();
       
        archivos=new Archivos();
        listaCompra=new ArrayList<Producto>();
        clientesCorp=new ArrayList<Corporativo>();
        
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
        
        String col[] = {"Codigo","Desc","Precio","Utilidad"};
        modelo = new DefaultTableModel(col,0);
        tblLista.setModel(modelo);
        
        
        
        
    }
    
    
    
    public void instanciaTarjetas(){
        TipoTarjeta visa = new TipoTarjeta("Visa");
        TipoTarjeta mastercard = new TipoTarjeta("Master card");
        TipoTarjeta americanexpress = new TipoTarjeta("American Express");
        tarjetas=new ArrayList<TipoTarjeta>();
        tarjetas.add(visa);
        tarjetas.add(mastercard);
        tarjetas.add(americanexpress);
    }
    
    public void llenarClientesCorp() throws Exception{
        clientesCorp=archivos.leerCorporativos();
        for (Corporativo cliente : clientesCorp) {
            cmbCliCorp.addItem(cliente.mostrarLista());
        }
    }
    
   
    
    public void llenarCombo() throws Exception{
        listaProductos=archivos.leerProductos();
        for(Producto producto:listaProductos){
            cmbProducto.addItem(producto.mostrarComboB());
        }
        
        for(TipoTarjeta tarjeta: tarjetas){
            cmbTarjetas.addItem(tarjeta.getDescripcion());
        }
    }
    
    public void limpiarPantalla(){
        listaCompra.clear();
        modelo.setRowCount(0);
        total=0;
        lblTotal.setText(total+"");
        rbCorp.setSelected(true);
        txtaDir.setText("");
        txtfDesc.setText("");
        txtfDesc.setEnabled(false);
        txtfNombre.setText("");
        txtfNoTarjeta.setText("");
        chbDesc.setSelected(false);
        bgContado.setSelected(true);
    }
    
    public void procesarorden(boolean procesarDesc){
        DetalleOrden deta=new DetalleOrden(listaCompra);
        String desclimit=txtfDesc.getText();
        Pago pag;
        Cliente client;
        int tipoCliente,tipoPago;
        if(rbCorp.isSelected()){
            Corporativo corp =clientesCorp.get(cmbCliCorp.getSelectedIndex());
            client=corp;
            //tipoCliente=0;
        }else{
            //tipoCliente=1;
            String s="";
            if(cmbSexo.getSelectedIndex()==1){
                s="F";
            }else{
                s="M";
            }
            Ocasional oca=new Ocasional(txtaDir.getText(), txtfNombre.getText(), s);
            client=oca;
            System.out.println(oca.toString());
        }
        int tipoMoneda=0;
        if(bgContado.isSelected()){
            String moneda="";
            
            tipoPago=0;
            if(cmbMoneda.getSelectedIndex()==0){
                moneda="Colones";
                
            }else{
                moneda="Dolares";
                tipoMoneda=1;
            }

            Contado cont=new Contado(moneda,tipoMoneda);
            pag=cont;
        }else{
            tipoPago=1;
            TipoTarjeta tarjeta=new TipoTarjeta(cmbTarjetas.getSelectedItem().toString());
            Credito cred=new Credito(txtfNoTarjeta.getText(), tarjeta,0);
            pag=cred;
        }
        Date fecha=new Date();
        Orden orden=new Orden(fecha, deta, pag, client, procesarDesc, desclimit);

        orden.finalizarOrden(tipoPago);
        this.limpiarPantalla();
    }
    
    public void finalizar(){
        String desclimit=txtfDesc.getText();
        String noTarjeta=txtfNoTarjeta.getText();
        if(chbDesc.isSelected()){
            if(desclimit.length()>2 || desclimit.isBlank() || noTarjeta.length() < 16 || noTarjeta.isBlank()){
                JOptionPane.showMessageDialog(null, "Todos los cambos deben de estar llenos");
                txtfDesc.setText("");
            }else{
                procesarorden(true);
            }
        }else{
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLista = new javax.swing.JTable();
        cmbProducto = new javax.swing.JComboBox<>();
        btnAgregar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        cmbTarjetas = new javax.swing.JComboBox<>();
        rbCorp = new javax.swing.JRadioButton();
        rbOca = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        cmbCliCorp = new javax.swing.JComboBox<>();
        spDir = new javax.swing.JScrollPane();
        txtaDir = new javax.swing.JTextArea();
        bgContado = new javax.swing.JRadioButton();
        bgCredito = new javax.swing.JRadioButton();
        cmbMoneda = new javax.swing.JComboBox<>();
        lblDir = new javax.swing.JLabel();
        chbDesc = new javax.swing.JCheckBox();
        txtfDesc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtfNombre = new javax.swing.JTextField();
        lblSexo = new javax.swing.JLabel();
        cmbSexo = new javax.swing.JComboBox<>();
        txtfNoTarjeta = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú Principal");
        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

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

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        btngCliente.add(rbCorp);
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

        btngCliente.add(rbOca);
        rbOca.setText("Ocasional");

        jLabel1.setText("Tipo Cliente");

        txtaDir.setColumns(20);
        txtaDir.setRows(5);
        txtaDir.setToolTipText("Direccion");
        txtaDir.setAutoscrolls(false);
        spDir.setViewportView(txtaDir);

        btngPago.add(bgContado);
        bgContado.setSelected(true);
        bgContado.setText("Contado");
        bgContado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                bgContadoStateChanged(evt);
            }
        });

        btngPago.add(bgCredito);
        bgCredito.setText("Credito");
        bgCredito.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                bgCreditoStateChanged(evt);
            }
        });

        cmbMoneda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Colones", "Dolares" }));

        lblDir.setText("Direccion");

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

        txtfDesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfDescKeyTyped(evt);
            }
        });

        jLabel2.setText("Total sin impuestos");

        lblNombre.setText("Nombre");

        txtfNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfNombreActionPerformed(evt);
            }
        });

        lblSexo.setText("Sexo");

        cmbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hombre", "Mujer" }));

        try {
            txtfNoTarjeta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####-####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel3.setText("%");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bgContado, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bgCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbTarjetas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtfNoTarjeta))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(rbOca, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rbCorp, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbCliCorp, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnFinalizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblSexo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDir, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                                    .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(spDir, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(txtfNombre)
                                    .addComponent(cmbSexo, 0, 178, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chbDesc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtfDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbCorp)
                        .addGap(18, 18, 18)
                        .addComponent(cmbCliCorp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(rbOca)
                        .addGap(12, 12, 12)
                        .addComponent(spDir, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(167, 167, 167)
                                .addComponent(lblDir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblNombre)
                                    .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblSexo)
                                    .addComponent(cmbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(chbDesc)
                                    .addComponent(txtfDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAgregar))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bgContado)
                    .addComponent(bgCredito)
                    .addComponent(cmbMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTarjetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFinalizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(txtfNoTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_formMouseExited

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        Producto produ=listaProductos.get(cmbProducto.getSelectedIndex());
        total+=(produ.getCosto()+(produ.getCosto()*produ.getUtilidad()));
        total=Math.round(total*100) /100d;
        System.out.println(total);
        lblTotal.setText(total+"");
        listaCompra.add(listaProductos.get(cmbProducto.getSelectedIndex()));
        String codigo=produ.getCodigoProducto();
        String desc=produ.getDescripcion();
        String precio=produ.getCosto()+"";
        String utilidad=produ.getUtilidad()+"";
        String prod[]={codigo,desc,precio,utilidad};
        modelo.addRow(prod);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void rbCorpStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbCorpStateChanged
        if(rbCorp.isSelected()){
            cmbCliCorp.setVisible(true);
            spDir.setVisible(false);
            lblDir.setVisible(false);
            lblNombre.setVisible(false);
            lblSexo.setVisible(false);
            cmbSexo.setVisible(false);
            txtfNombre.setVisible(false);
        }else{
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

    private void bgCreditoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_bgCreditoStateChanged
        if(bgCredito.isSelected()){
            cmbTarjetas.setVisible(true);
            txtfNoTarjeta.setVisible(true);
        }else{
            cmbTarjetas.setVisible(false);
            txtfNoTarjeta.setVisible(false);
        }
    }//GEN-LAST:event_bgCreditoStateChanged

    private void bgContadoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_bgContadoStateChanged
        if(bgContado.isSelected()){
            cmbMoneda.setVisible(true);
        }else{
            cmbMoneda.setVisible(false);
        }
    }//GEN-LAST:event_bgContadoStateChanged

    private void chbDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbDescActionPerformed
        
        if(chbDesc.isSelected()){
            
            txtfDesc.setEnabled(true);
        }else{
            txtfDesc.setEnabled(false);
        }
    }//GEN-LAST:event_chbDescActionPerformed

    private void chbDescStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chbDescStateChanged
        
    }//GEN-LAST:event_chbDescStateChanged

    private void txtfDescKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfDescKeyTyped
        char c = evt.getKeyChar();
        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtfDescKeyTyped

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        finalizar();
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void txtfNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfNombreActionPerformed

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDir;
    private javax.swing.JLabel lblNombre;
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
