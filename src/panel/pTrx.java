/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import conek.nek;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import proyect.Form_MenuA;

/**
 *
 * @author ASUS
 */
public class pTrx extends javax.swing.JPanel {

    NumberFormat nft = NumberFormat.getNumberInstance(new Locale("in", "ID"));
    
    public void innit() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("No Faktur");
        model.addColumn("Tanggal");
        model.addColumn("ID User");
        model.addColumn("Nama");
        model.addColumn("Status");
        model.addColumn("Username");
        model.addColumn("Akses");
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Satuan");
        model.addColumn("Harga");
        model.addColumn("Stok");
        model.addColumn("Jumlah");
        model.addColumn("Sub Total");
        
        String caridata= txtCari.getText();
        try{
            int no = 1;
            String sql = "Select * from relasidatatransaksi where nama_barang like '%" + caridata 
                    + "%' or username like'%" + caridata + "%' or no_faktur like '%" + caridata 
                    + "%' order by no_faktur asc";
            
            java.sql.Connection conn = (Connection)nek.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            
            while(res.next()){
                model.addRow(new Object[]{no++, res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4),
                    res.getString(5),res.getString(6),res.getString(7),
                    res.getString(8),res.getString(9),res.getString(10),
                    res.getString(11),res.getString(12),res.getString(13),
                    res.getString(14)});
            tabelJual.setModel(model);
            
            TableColumn col;
            tabelJual.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
            
            col = tabelJual.getColumnModel().getColumn(0);
            col.setPreferredWidth(50);
            col = tabelJual.getColumnModel().getColumn(1);
            col.setPreferredWidth(100);
            col = tabelJual.getColumnModel().getColumn(2);
            col.setPreferredWidth(80);
            col = tabelJual.getColumnModel().getColumn(3);
            col.setPreferredWidth(70);
            col = tabelJual.getColumnModel().getColumn(4);
            col.setPreferredWidth(130);
            col = tabelJual.getColumnModel().getColumn(5);
            col.setPreferredWidth(84);
            col = tabelJual.getColumnModel().getColumn(6);
            col.setPreferredWidth(80);
            col = tabelJual.getColumnModel().getColumn(7);
            col.setPreferredWidth(60);
            col = tabelJual.getColumnModel().getColumn(8);
            col.setPreferredWidth(90);
            col = tabelJual.getColumnModel().getColumn(9);
            col.setPreferredWidth(150);
            col = tabelJual.getColumnModel().getColumn(10);
            col.setPreferredWidth(60);
            col = tabelJual.getColumnModel().getColumn(11);
            col.setPreferredWidth(60);
            col = tabelJual.getColumnModel().getColumn(12);
            col.setPreferredWidth(50);
            col = tabelJual.getColumnModel().getColumn(13);
            col.setPreferredWidth(60);
            col = tabelJual.getColumnModel().getColumn(14);
            col.setPreferredWidth(80);
            }
            long totalsub = 0, totaljum = 0;
            int total = tabelJual.getRowCount();
            for (int i = 0; i < total; i++){
                totalsub = totalsub + Long.parseLong(model.getValueAt(i, 14).toString());
                totaljum = totaljum + Long.parseLong(model.getValueAt(i, 13).toString());
            }
            txtTotalBeli.setText(String.valueOf(totaljum));
            txtTotalSub.setText("Rp"+ nft.format(totalsub));
        }catch (SQLException e){
            System.out.println("Error" + e.getMessage());
        }
    }
    
    /**
     * Creates new form pUser
     */
    public pTrx() {
        initComponents();
        innit();
        setOpaque(false);
        setBackground(new Color(39, 36, 29, 0));
        tabelJual.fixTable(jScrollPane2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        border1 = new panel.Border();
        txtCari = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelJual = new tabledark.TableDark();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtTotalBeli = new javax.swing.JLabel();
        refresh = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTotalSub = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCari.setBackground(new java.awt.Color(39, 36, 29));
        txtCari.setFont(new java.awt.Font("Carlito", 0, 14)); // NOI18N
        txtCari.setForeground(new java.awt.Color(250, 249, 247));
        txtCari.setBorder(null);
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });
        border1.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 210, 20));

        tabelJual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No.", "ID User", "Nama", "Alamat", "Telepon", "Status", "Username", "Password", "Akses", "Edit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelJual.setFont(new java.awt.Font("Carlito", 0, 14)); // NOI18N
        tabelJual.setRowHeight(40);
        tabelJual.setSelectionBackground(new java.awt.Color(250, 249, 247));
        jScrollPane2.setViewportView(tabelJual);

        border1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1220, 700));
        border1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 210, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-2-24.png"))); // NOI18N
        border1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        txtTotalBeli.setFont(new java.awt.Font("Carlito", 1, 18)); // NOI18N
        txtTotalBeli.setForeground(new java.awt.Color(250, 249, 247));
        txtTotalBeli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        border1.add(txtTotalBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 780, 160, 20));

        refresh.setFont(new java.awt.Font("Carlito", 1, 18)); // NOI18N
        refresh.setForeground(new java.awt.Color(250, 249, 247));
        refresh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sinchronize-48.png"))); // NOI18N
        refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshMouseClicked(evt);
            }
        });
        border1.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 10, 50, -1));

        jLabel4.setFont(new java.awt.Font("Carlito", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(250, 249, 247));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Total Penjualan:");
        border1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 810, 140, -1));

        txtTotalSub.setFont(new java.awt.Font("Carlito", 1, 18)); // NOI18N
        txtTotalSub.setForeground(new java.awt.Color(250, 249, 247));
        txtTotalSub.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        border1.add(txtTotalSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 810, 160, 20));

        jLabel5.setFont(new java.awt.Font("Carlito", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(250, 249, 247));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Total Item:");
        border1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 780, 90, -1));

        add(border1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1235, 846));
    }// </editor-fold>//GEN-END:initComponents

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        innit();
    }//GEN-LAST:event_txtCariKeyReleased

    private void refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseClicked
        txtCari.setText("");
        innit();
    }//GEN-LAST:event_refreshMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private panel.Border border1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel refresh;
    private tabledark.TableDark tabelJual;
    private javax.swing.JTextField txtCari;
    private javax.swing.JLabel txtTotalBeli;
    private javax.swing.JLabel txtTotalSub;
    // End of variables declaration//GEN-END:variables
}
