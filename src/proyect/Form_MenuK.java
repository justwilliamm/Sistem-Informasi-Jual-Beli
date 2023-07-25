/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyect;

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

/**
 *
 * @author ASUS
 */
public class Form_MenuK extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    private void gif() {
        Thread s = new Thread() {
            public void run() {
                for (int i = 1;; i++) {
                    if (i >= 148) {
                        welcome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/welcome (148).png")));
                    } else {
                        welcome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/welcome (" + i + ").png")));
                    }
                    try {
                        sleep(10);
                    } catch (Exception e) {
                    }
                }
            }
        };
        s.start();
    }

    
    
    public Form_MenuK() {
        initComponents();
        init();
        gif();
        
        this.setLocationRelativeTo(null);
        
    }

    private void init() {
        setBackground(new Color(0, 0, 0, 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        welcome = new javax.swing.JLabel();
        border = new panel.Border();
        eks = new javax.swing.JLabel();
        tabbedPaneCustom1 = new raven.tabbed.TabbedPaneCustom();
        Home = new panel.Border();
        admin = new javax.swing.JLabel();
        nama = new javax.swing.JLabel();
        welcome2 = new javax.swing.JLabel();
        welcome3 = new javax.swing.JLabel();
        Penjualan = new panel.Border();
        pJual1 = new panel.pJual();
        Pengaturan = new panel.Border();
        menuPengaturan = new panel.menu();
        btOut = new javax.swing.JLabel();
        pTest = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(welcome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1910, -1));

        border.setBackground(new java.awt.Color(66, 61, 51));

        eks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/x-mark-20.png"))); // NOI18N
        eks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eksMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                eksMouseReleased(evt);
            }
        });
        border.add(eks, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 10, -1, -1));

        tabbedPaneCustom1.setBackground(new java.awt.Color(211, 206, 196));
        tabbedPaneCustom1.setForeground(new java.awt.Color(39, 36, 29));
        tabbedPaneCustom1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tabbedPaneCustom1.setSelectedColor(new java.awt.Color(211, 206, 196));
        tabbedPaneCustom1.setUnselectedColor(new java.awt.Color(98, 93, 82));
        tabbedPaneCustom1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbedPaneCustom1MouseClicked(evt);
            }
        });

        admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/administrator-512.png"))); // NOI18N
        admin.setAutoscrolls(true);
        Home.add(admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 390, -1, -1));

        nama.setFont(new java.awt.Font("Carlito", 1, 48)); // NOI18N
        nama.setForeground(new java.awt.Color(232, 230, 225));
        nama.setText("...");
        Home.add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, -1, -1));

        welcome2.setFont(new java.awt.Font("Carlito", 1, 48)); // NOI18N
        welcome2.setForeground(new java.awt.Color(232, 230, 225));
        welcome2.setText("Welcome,");
        Home.add(welcome2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        welcome3.setFont(new java.awt.Font("Carlito", 1, 48)); // NOI18N
        welcome3.setForeground(new java.awt.Color(232, 230, 225));
        welcome3.setText("Kasir");
        Home.add(welcome3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, -1));

        tabbedPaneCustom1.addTab("Home", Home);

        Penjualan.add(pJual1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tabbedPaneCustom1.addTab("Penjualan", Penjualan);

        btOut.setFont(new java.awt.Font("Carlito", 1, 30)); // NOI18N
        btOut.setForeground(new java.awt.Color(66, 61, 51));
        btOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logout-48.png"))); // NOI18N
        btOut.setText(" Log Out");
        btOut.setAutoscrolls(true);
        btOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btOutMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btOutMouseReleased(evt);
            }
        });
        menuPengaturan.add(btOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 280, -1));

        Pengaturan.add(menuPengaturan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 850));

        tabbedPaneCustom1.addTab("Pengaturan", Pengaturan);

        border.add(tabbedPaneCustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1580, 880));

        pTest.setBackground(new java.awt.Color(50, 50, 50));
        border.add(pTest, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, 140, 150));

        getContentPane().add(border, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 900));

        setBounds(0, 0, 1600, 900);
    }// </editor-fold>//GEN-END:initComponents

    private void eksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eksMouseClicked
        int cus = JOptionPane.showConfirmDialog(null,
                "Keluar dari aplikasi?",
                "Konfirmasi", JOptionPane.OK_CANCEL_OPTION);

        if (cus == JOptionPane.OK_OPTION) {
            System.exit(0);
        } else {
            JOptionPane.getRootFrame();
        }
    }//GEN-LAST:event_eksMouseClicked

    private void eksMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eksMouseReleased
        int cus = JOptionPane.showConfirmDialog(null,
                "Keluar dari aplikasi?",
                "Konfirmasi", JOptionPane.OK_CANCEL_OPTION);

        if (cus == JOptionPane.OK_OPTION) {
            System.exit(0);
        } else {
            JOptionPane.getRootFrame();
        }
    }//GEN-LAST:event_eksMouseReleased

    private void tabbedPaneCustom1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbedPaneCustom1MouseClicked
        
    }//GEN-LAST:event_tabbedPaneCustom1MouseClicked

    private void btOutMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btOutMouseReleased
        int cus = JOptionPane.showConfirmDialog(null,
            "Yakin ingin log out?",
            "Konfirmasi", JOptionPane.OK_CANCEL_OPTION);

        if (cus == JOptionPane.OK_OPTION) {
            this.dispose();
            new Form_Login().setVisible(true);
        } else {
            JOptionPane.getRootFrame();
        }
    }//GEN-LAST:event_btOutMouseReleased

    private void btOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btOutMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btOutMouseClicked

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
            java.util.logging.Logger.getLogger(Form_MenuK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_MenuK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_MenuK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_MenuK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_MenuK().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private panel.Border Home;
    private panel.Border Pengaturan;
    private panel.Border Penjualan;
    private javax.swing.JLabel admin;
    private panel.Border border;
    private javax.swing.JLabel btOut;
    private javax.swing.JLabel eks;
    private panel.menu menuPengaturan;
    protected javax.swing.JLabel nama;
    private panel.pJual pJual1;
    private javax.swing.JPanel pTest;
    private raven.tabbed.TabbedPaneCustom tabbedPaneCustom1;
    private javax.swing.JLabel welcome;
    private javax.swing.JLabel welcome2;
    private javax.swing.JLabel welcome3;
    // End of variables declaration//GEN-END:variables
}
