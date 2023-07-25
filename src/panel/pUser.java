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
public class pUser extends javax.swing.JPanel {

    public void innit() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("ID User");
        model.addColumn("Nama User");
        model.addColumn("Alamat");
        model.addColumn("No. Telp");
        model.addColumn("Status");
        model.addColumn("Username");
        model.addColumn("Password");
        model.addColumn("Akses");
        model.addColumn("");

        String c = txtCariUser.getText();
        try {
            int no = 1;

            String sql = "select * from user where CONCAT_WS('', id_user, nama, alamat, telepon, status, username, password, akses) like '%" + c + "%'";

            java.sql.Connection conn = (Connection) nek.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString(1),
                    res.getString(2), res.getString(3), res.getString(4),
                    res.getString(5), res.getString(6), res.getString(7),
                    res.getString(8)});
                tabelUser.setModel(model);

                TableColumn col;
                tabelUser.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

                col = tabelUser.getColumnModel().getColumn(0);
                col.setPreferredWidth(30);
                col = tabelUser.getColumnModel().getColumn(1);
                col.setPreferredWidth(70);
                col = tabelUser.getColumnModel().getColumn(2);
                col.setPreferredWidth(120);
                col = tabelUser.getColumnModel().getColumn(3);
                col.setPreferredWidth(485);
                col = tabelUser.getColumnModel().getColumn(4);
                col.setPreferredWidth(100);
                col = tabelUser.getColumnModel().getColumn(5);
                col.setPreferredWidth(84);
                col = tabelUser.getColumnModel().getColumn(6);
                col.setPreferredWidth(75);
                col = tabelUser.getColumnModel().getColumn(7);
                col.setPreferredWidth(75);
                col = tabelUser.getColumnModel().getColumn(8);
                col.setPreferredWidth(55);
                col = tabelUser.getColumnModel().getColumn(9);
                col.setPreferredWidth(118);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
    }
    
    public void eU() {
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                try {
                    String sql = "UPDATE user set id_user = '" + txtid.getText()
                            + "',nama = '" + txtnama.getText()
                            + "',alamat = '" + txtalamat.getText()
                            + "',telepon = '" + txtno.getText()
                            + "',status = '" + txtstatus.getText()
                            + "',username = '" + txtuser.getText()
                            + "',password = '" + txtpass.getText()
                            + "',akses = '" + txtakses.getText()
                            + "' where id_user ='" + txtid.getText() + "'";

                    java.sql.Connection conn = (Connection) nek.configDB();
                    java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
                    pstm.execute();

                    JOptionPane.showMessageDialog(null, "Edit Data Berhasil");

                    innit();
                    eU();
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }

            @Override
            public void onDelete(int row) {
                if (tabelUser.isEditing()) {
                    tabelUser.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) tabelUser.getModel();
                try {
                    String sql = "delete from user where id_user = '" + txtid.getText() + "'";

                    java.sql.Connection conn = (Connection) nek.configDB();
                    java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
                    pstm.execute();

                    JOptionPane.showMessageDialog(null, "Proses hapus data success");

                    innit();
                    eU();
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                model.removeRow(row);
            }

        };
        tabelUser.getColumnModel().getColumn(9).setCellRenderer(new TableActionCellRender());
        tabelUser.getColumnModel().getColumn(9).setCellEditor(new TableActionCellEditor(event));
    }
    /**
     * Creates new form pUser
     */
    public pUser() {
        initComponents();
        innit();
        eU();
        setOpaque(false);
        setBackground(new Color(39, 36, 29, 0));
        tabelUser.fixTable(jScrollPane2);
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
        txtCariUser = new javax.swing.JTextField();
        tambahuser = new javax.swing.JLabel();
        txtnama = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelUser = new tabledark.TableDark();
        txtid = new javax.swing.JLabel();
        txtalamat = new javax.swing.JLabel();
        txtno = new javax.swing.JLabel();
        txtstatus = new javax.swing.JLabel();
        txtuser = new javax.swing.JLabel();
        txtpass = new javax.swing.JLabel();
        txtakses = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCariUser.setBackground(new java.awt.Color(39, 36, 29));
        txtCariUser.setFont(new java.awt.Font("Carlito", 0, 14)); // NOI18N
        txtCariUser.setForeground(new java.awt.Color(250, 249, 247));
        txtCariUser.setBorder(null);
        border1.add(txtCariUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 210, 20));

        tambahuser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add-row-48.png"))); // NOI18N
        tambahuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahuserMouseClicked(evt);
            }
        });
        border1.add(tambahuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 780, -1, -1));

        txtnama.setBackground(new java.awt.Color(39, 36, 29));
        txtnama.setForeground(new java.awt.Color(39, 36, 29));
        txtnama.setText("nama");
        border1.add(txtnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 780, -1, -1));

        tabelUser.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelUser.setFont(new java.awt.Font("Carlito", 0, 14)); // NOI18N
        tabelUser.setRowHeight(40);
        tabelUser.setSelectionBackground(new java.awt.Color(250, 249, 247));
        tabelUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelUserMouseClicked(evt);
            }
        });
        tabelUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelUserKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelUserKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabelUser);

        border1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1220, 700));

        txtid.setForeground(new java.awt.Color(39, 36, 29));
        border1.add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, -1, -1));

        txtalamat.setForeground(new java.awt.Color(39, 36, 29));
        border1.add(txtalamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, -1, -1));

        txtno.setForeground(new java.awt.Color(39, 36, 29));
        border1.add(txtno, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, -1, -1));

        txtstatus.setForeground(new java.awt.Color(39, 36, 29));
        border1.add(txtstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, -1, -1));

        txtuser.setForeground(new java.awt.Color(39, 36, 29));
        border1.add(txtuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, -1, -1));

        txtpass.setForeground(new java.awt.Color(39, 36, 29));
        border1.add(txtpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, -1, -1));

        txtakses.setForeground(new java.awt.Color(39, 36, 29));
        border1.add(txtakses, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, -1, -1));
        border1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 210, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-2-24.png"))); // NOI18N
        border1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        add(border1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1235, 846));
    }// </editor-fold>//GEN-END:initComponents

    private void tambahuserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahuserMouseClicked
        Form_MenuA.pUser.setVisible(false);
        Form_MenuA.pTambahUser.setVisible(true);
    }//GEN-LAST:event_tambahuserMouseClicked

    private void tabelUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelUserMouseClicked
        int baris = tabelUser.getSelectedRow();
        String id = tabelUser.getValueAt(baris, 1).toString();
        txtid.setText(id);
        String nama = tabelUser.getValueAt(baris, 2).toString();
        txtnama.setText(nama);
        String alamat = tabelUser.getValueAt(baris, 3).toString();
        txtalamat.setText(alamat);
        String no = tabelUser.getValueAt(baris, 4).toString();
        txtno.setText(no);
        String status = tabelUser.getValueAt(baris, 5).toString();
        txtstatus.setText(status);
        String user = tabelUser.getValueAt(baris, 6).toString();
        txtuser.setText(user);
        String pass = tabelUser.getValueAt(baris, 7).toString();
        txtpass.setText(pass);
        String akses = tabelUser.getValueAt(baris, 8).toString();
        txtakses.setText(akses);
    }//GEN-LAST:event_tabelUserMouseClicked

    private void tabelUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelUserKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelUserKeyPressed

    private void tabelUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelUserKeyReleased
        int baris = tabelUser.getSelectedRow();
        String id = tabelUser.getValueAt(baris, 1).toString();
        txtid.setText(id);
        String nama = tabelUser.getValueAt(baris, 2).toString();
        txtnama.setText(nama);
        String alamat = tabelUser.getValueAt(baris, 3).toString();
        txtalamat.setText(alamat);
        String no = tabelUser.getValueAt(baris, 4).toString();
        txtno.setText(no);
        String status = tabelUser.getValueAt(baris, 5).toString();
        txtstatus.setText(status);
        String user = tabelUser.getValueAt(baris, 6).toString();
        txtuser.setText(user);
        String pass = tabelUser.getValueAt(baris, 7).toString();
        txtpass.setText(pass);
        String akses = tabelUser.getValueAt(baris, 8).toString();
        txtakses.setText(akses);
    }//GEN-LAST:event_tabelUserKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private panel.Border border1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private tabledark.TableDark tabelUser;
    private javax.swing.JLabel tambahuser;
    private javax.swing.JTextField txtCariUser;
    private javax.swing.JLabel txtakses;
    private javax.swing.JLabel txtalamat;
    private javax.swing.JLabel txtid;
    private javax.swing.JLabel txtnama;
    private javax.swing.JLabel txtno;
    private javax.swing.JLabel txtpass;
    private javax.swing.JLabel txtstatus;
    private javax.swing.JLabel txtuser;
    // End of variables declaration//GEN-END:variables
}
