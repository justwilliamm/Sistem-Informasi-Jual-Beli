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
public class Form_MenuA extends javax.swing.JFrame {

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

    private void bnnit() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Satuan");
        model.addColumn("Harga");
        model.addColumn("Stok");
        model.addColumn("");

        String c = txtCariBrg.getText();
        try {
            int no = 1;

            String sql = "select * from barang where CONCAT_WS('', kd_barang, nama_barang, satuan, harga, stok) like '%" + c + "%'";

            java.sql.Connection conn = (Connection) nek.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString(1),
                    res.getString(2), res.getString(3), res.getString(4),
                    res.getString(5)});
                tabelBrg.setModel(model);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    /*public void eU() {
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
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                model.removeRow(row);
            }

        };
        tabelUser.getColumnModel().getColumn(9).setCellRenderer(new TableActionCellRender());
        tabelUser.getColumnModel().getColumn(9).setCellEditor(new TableActionCellEditor(event));
    }*/
    public void eB() {
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                try {
                    String sql = "UPDATE barang set kd_barang = '" + txtKode.getText()
                            + "',nama_barang = '" + txtNamaBrg.getText()
                            + "',satuan = '" + txtSatuan.getText()
                            + "',harga = '" + txtHarga.getText()
                            + "',stok = '" + txtStok.getText()
                            + "' where kd_barang = '" + txtKode.getText() + "'";

                    java.sql.Connection conn = (Connection) nek.configDB();
                    java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
                    pstm.execute();

                    JOptionPane.showMessageDialog(null, "Edit Data Berhasil");

                    bnnit();
                    eB();
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }

            @Override
            public void onDelete(int row) {
                if (tabelBrg.isEditing()) {
                    tabelBrg.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) tabelBrg.getModel();
                try {
                    String sql = "delete from barang where kd_barang = '" + txtKode.getText() + "'";

                    java.sql.Connection conn = (Connection) nek.configDB();
                    java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
                    pstm.execute();

                    JOptionPane.showMessageDialog(null, "Proses hapus data success");

                    bnnit();
                    eB();
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }

        };
        tabelBrg.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
        tabelBrg.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event));
    }

    public Form_MenuA() {
        initComponents();
        init();
        bnnit();
        gif();
        eB();
        pJual1.autoNFT();
        pJual1.eJ();
        tabelBrg.fixTable(jScrollPane1);
        pUser.setVisible(false);
        pBrg.setVisible(false);
        pTrx.setVisible(false);
        pTambahUser.setVisible(false);
        pTambahBrg.setVisible(false);
        pPass.setVisible(false);
        pAbout.setVisible(false);
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
        Master = new panel.Border();
        pUser = new panel.pUser();
        pBrg = new panel.Border();
        txtCariBrg = new javax.swing.JTextField();
        tambahbrg = new javax.swing.JLabel();
        txtkode = new javax.swing.JLabel();
        txtnamabrg = new javax.swing.JLabel();
        txtsatuan = new javax.swing.JLabel();
        txtharga = new javax.swing.JLabel();
        txtstok = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBrg = new tabledark.TableDark();
        pTrx = new panel.pTrx();
        pTambahUser = new panel.Border();
        tambahUser = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txtID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txtNama = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        txtAlamat = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        txtNo = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        txtStatus = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        txtUser = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        txtPass = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        txtAkses = new javax.swing.JTextField();
        kembaliUser = new javax.swing.JLabel();
        pTambahBrg = new panel.Border();
        tambahBrg = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        txtKode = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        txtNamaBrg = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        txtSatuan = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        txtHarga = new javax.swing.JTextField();
        jSeparator15 = new javax.swing.JSeparator();
        txtStok = new javax.swing.JTextField();
        kembaliBrg = new javax.swing.JLabel();
        menuMaster = new panel.menu();
        dBarang = new javax.swing.JLabel();
        admin2 = new javax.swing.JLabel();
        dTrx = new javax.swing.JLabel();
        dUser = new javax.swing.JLabel();
        admin1 = new javax.swing.JLabel();
        Penjualan = new panel.Border();
        pJual1 = new panel.pJual();
        Pengaturan = new panel.Border();
        menuPengaturan = new panel.menu();
        btOut = new javax.swing.JLabel();
        btPass = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btAbout = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        pAbout = new panel.pAbout();
        pPass = new panel.pPass();

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
        Home.add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, -1, -1));

        welcome2.setFont(new java.awt.Font("Carlito", 1, 48)); // NOI18N
        welcome2.setForeground(new java.awt.Color(232, 230, 225));
        welcome2.setText("Welcome,");
        Home.add(welcome2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        welcome3.setFont(new java.awt.Font("Carlito", 1, 48)); // NOI18N
        welcome3.setForeground(new java.awt.Color(232, 230, 225));
        welcome3.setText("Admin");
        Home.add(welcome3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, -1));

        tabbedPaneCustom1.addTab("Home", Home);

        pUser.setBackground(new java.awt.Color(39, 36, 29));
        Master.add(pUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, -1, -1));

        txtCariBrg.setBackground(new java.awt.Color(39, 36, 29));
        txtCariBrg.setFont(new java.awt.Font("Carlito", 0, 14)); // NOI18N
        txtCariBrg.setForeground(new java.awt.Color(250, 249, 247));
        txtCariBrg.setBorder(null);
        txtCariBrg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariBrgKeyReleased(evt);
            }
        });
        pBrg.add(txtCariBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 210, 20));

        tambahbrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add-row-48.png"))); // NOI18N
        tambahbrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahbrgMouseClicked(evt);
            }
        });
        pBrg.add(tambahbrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 780, -1, -1));

        txtkode.setForeground(new java.awt.Color(39, 36, 29));
        pBrg.add(txtkode, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, -1, -1));

        txtnamabrg.setForeground(new java.awt.Color(39, 36, 29));
        pBrg.add(txtnamabrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, -1, -1));

        txtsatuan.setForeground(new java.awt.Color(39, 36, 29));
        pBrg.add(txtsatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, -1, -1));

        txtharga.setForeground(new java.awt.Color(39, 36, 29));
        pBrg.add(txtharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, -1, -1));

        txtstok.setForeground(new java.awt.Color(39, 36, 29));
        pBrg.add(txtstok, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, -1, -1));
        pBrg.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 210, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-2-24.png"))); // NOI18N
        pBrg.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        tabelBrg.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelBrg.setFont(new java.awt.Font("Carlito", 0, 14)); // NOI18N
        tabelBrg.setRowHeight(40);
        tabelBrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBrgMouseClicked(evt);
            }
        });
        tabelBrg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelBrgKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabelBrg);

        pBrg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1220, 700));

        Master.add(pBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 1235, 846));

        pTrx.setPreferredSize(new java.awt.Dimension(1235, 845));
        Master.add(pTrx, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 1235, 846));

        tambahUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus-7-48.png"))); // NOI18N
        tambahUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahUserMouseClicked(evt);
            }
        });
        pTambahUser.add(tambahUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 780, -1, -1));
        pTambahUser.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 740, -1));

        txtID.setBackground(new java.awt.Color(39, 36, 29));
        txtID.setFont(new java.awt.Font("Carlito", 0, 24)); // NOI18N
        txtID.setForeground(new java.awt.Color(250, 249, 247));
        txtID.setBorder(null);
        txtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIDKeyReleased(evt);
            }
        });
        pTambahUser.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 740, -1));

        jLabel1.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(250, 249, 247));
        jLabel1.setText(":");
        pTambahUser.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 460, 50, -1));

        jLabel3.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(250, 249, 247));
        jLabel3.setText("Nama");
        pTambahUser.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 90, -1));

        jLabel4.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(250, 249, 247));
        jLabel4.setText("Akses");
        pTambahUser.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 120, -1));

        jLabel5.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(250, 249, 247));
        jLabel5.setText(":");
        pTambahUser.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 10, -1));

        jLabel6.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(250, 249, 247));
        jLabel6.setText(":");
        pTambahUser.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 10, -1));

        jLabel7.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(250, 249, 247));
        jLabel7.setText(":");
        pTambahUser.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 50, -1));

        jLabel8.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(250, 249, 247));
        jLabel8.setText(":");
        pTambahUser.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 50, -1));

        jLabel9.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(250, 249, 247));
        jLabel9.setText(":");
        pTambahUser.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 50, -1));

        jLabel10.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(250, 249, 247));
        jLabel10.setText(":");
        pTambahUser.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 50, -1));

        jLabel11.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(250, 249, 247));
        jLabel11.setText(":");
        pTambahUser.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 400, 50, -1));

        jLabel12.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(250, 249, 247));
        jLabel12.setText("ID User");
        pTambahUser.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 90, -1));

        jLabel13.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(250, 249, 247));
        jLabel13.setText("Alamat");
        pTambahUser.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 90, -1));

        jLabel14.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(250, 249, 247));
        jLabel14.setText("Telepon");
        pTambahUser.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 90, -1));

        jLabel15.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(250, 249, 247));
        jLabel15.setText("Status");
        pTambahUser.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 90, -1));

        jLabel16.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(250, 249, 247));
        jLabel16.setText("Username");
        pTambahUser.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 120, -1));

        jLabel17.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(250, 249, 247));
        jLabel17.setText("Password");
        pTambahUser.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 120, -1));
        pTambahUser.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 740, -1));

        txtNama.setBackground(new java.awt.Color(39, 36, 29));
        txtNama.setFont(new java.awt.Font("Carlito", 0, 24)); // NOI18N
        txtNama.setForeground(new java.awt.Color(250, 249, 247));
        txtNama.setBorder(null);
        txtNama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNamaKeyReleased(evt);
            }
        });
        pTambahUser.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 740, -1));
        pTambahUser.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 740, -1));

        txtAlamat.setBackground(new java.awt.Color(39, 36, 29));
        txtAlamat.setFont(new java.awt.Font("Carlito", 0, 24)); // NOI18N
        txtAlamat.setForeground(new java.awt.Color(250, 249, 247));
        txtAlamat.setBorder(null);
        txtAlamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAlamatKeyReleased(evt);
            }
        });
        pTambahUser.add(txtAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 740, -1));
        pTambahUser.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 740, -1));

        txtNo.setBackground(new java.awt.Color(39, 36, 29));
        txtNo.setFont(new java.awt.Font("Carlito", 0, 24)); // NOI18N
        txtNo.setForeground(new java.awt.Color(250, 249, 247));
        txtNo.setBorder(null);
        txtNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNoKeyReleased(evt);
            }
        });
        pTambahUser.add(txtNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 740, -1));
        pTambahUser.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 740, -1));

        txtStatus.setBackground(new java.awt.Color(39, 36, 29));
        txtStatus.setFont(new java.awt.Font("Carlito", 0, 24)); // NOI18N
        txtStatus.setForeground(new java.awt.Color(250, 249, 247));
        txtStatus.setBorder(null);
        txtStatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStatusKeyReleased(evt);
            }
        });
        pTambahUser.add(txtStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 740, -1));
        pTambahUser.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 740, -1));

        txtUser.setBackground(new java.awt.Color(39, 36, 29));
        txtUser.setFont(new java.awt.Font("Carlito", 0, 24)); // NOI18N
        txtUser.setForeground(new java.awt.Color(250, 249, 247));
        txtUser.setBorder(null);
        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUserKeyReleased(evt);
            }
        });
        pTambahUser.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 740, -1));
        pTambahUser.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 740, -1));

        txtPass.setBackground(new java.awt.Color(39, 36, 29));
        txtPass.setFont(new java.awt.Font("Carlito", 0, 24)); // NOI18N
        txtPass.setForeground(new java.awt.Color(250, 249, 247));
        txtPass.setBorder(null);
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPassKeyReleased(evt);
            }
        });
        pTambahUser.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 400, 740, -1));
        pTambahUser.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 490, 740, -1));

        txtAkses.setBackground(new java.awt.Color(39, 36, 29));
        txtAkses.setFont(new java.awt.Font("Carlito", 0, 24)); // NOI18N
        txtAkses.setForeground(new java.awt.Color(250, 249, 247));
        txtAkses.setBorder(null);
        txtAkses.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAksesKeyReleased(evt);
            }
        });
        pTambahUser.add(txtAkses, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, 740, -1));

        kembaliUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/undo-48.png"))); // NOI18N
        kembaliUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kembaliUserMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                kembaliUserMouseReleased(evt);
            }
        });
        pTambahUser.add(kembaliUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 780, -1, -1));

        Master.add(pTambahUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 1235, 846));

        tambahBrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus-7-48.png"))); // NOI18N
        tambahBrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahBrgMouseClicked(evt);
            }
        });
        pTambahBrg.add(tambahBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 780, -1, -1));
        pTambahBrg.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 740, -1));

        txtKode.setBackground(new java.awt.Color(39, 36, 29));
        txtKode.setFont(new java.awt.Font("Carlito", 0, 24)); // NOI18N
        txtKode.setForeground(new java.awt.Color(250, 249, 247));
        txtKode.setBorder(null);
        txtKode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKodeKeyReleased(evt);
            }
        });
        pTambahBrg.add(txtKode, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 740, -1));

        jLabel20.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(250, 249, 247));
        jLabel20.setText("Nama");
        pTambahBrg.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 90, -1));

        jLabel22.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(250, 249, 247));
        jLabel22.setText(":");
        pTambahBrg.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 10, -1));

        jLabel23.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(250, 249, 247));
        jLabel23.setText(":");
        pTambahBrg.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 10, -1));

        jLabel24.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(250, 249, 247));
        jLabel24.setText(":");
        pTambahBrg.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 50, -1));

        jLabel25.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(250, 249, 247));
        jLabel25.setText(":");
        pTambahBrg.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 50, -1));

        jLabel26.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(250, 249, 247));
        jLabel26.setText(":");
        pTambahBrg.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 50, -1));

        jLabel29.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(250, 249, 247));
        jLabel29.setText("Kode");
        pTambahBrg.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 90, -1));

        jLabel30.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(250, 249, 247));
        jLabel30.setText("Satuan");
        pTambahBrg.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 90, -1));

        jLabel31.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(250, 249, 247));
        jLabel31.setText("Harga");
        pTambahBrg.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 90, -1));

        jLabel32.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(250, 249, 247));
        jLabel32.setText("Stok");
        pTambahBrg.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 90, -1));
        pTambahBrg.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 740, -1));

        txtNamaBrg.setBackground(new java.awt.Color(39, 36, 29));
        txtNamaBrg.setFont(new java.awt.Font("Carlito", 0, 24)); // NOI18N
        txtNamaBrg.setForeground(new java.awt.Color(250, 249, 247));
        txtNamaBrg.setBorder(null);
        txtNamaBrg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNamaBrgKeyReleased(evt);
            }
        });
        pTambahBrg.add(txtNamaBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 740, -1));
        pTambahBrg.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 740, -1));

        txtSatuan.setBackground(new java.awt.Color(39, 36, 29));
        txtSatuan.setFont(new java.awt.Font("Carlito", 0, 24)); // NOI18N
        txtSatuan.setForeground(new java.awt.Color(250, 249, 247));
        txtSatuan.setBorder(null);
        txtSatuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSatuanKeyReleased(evt);
            }
        });
        pTambahBrg.add(txtSatuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 740, -1));
        pTambahBrg.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 740, -1));

        txtHarga.setBackground(new java.awt.Color(39, 36, 29));
        txtHarga.setFont(new java.awt.Font("Carlito", 0, 24)); // NOI18N
        txtHarga.setForeground(new java.awt.Color(250, 249, 247));
        txtHarga.setBorder(null);
        txtHarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHargaKeyReleased(evt);
            }
        });
        pTambahBrg.add(txtHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 740, -1));
        pTambahBrg.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 740, -1));

        txtStok.setBackground(new java.awt.Color(39, 36, 29));
        txtStok.setFont(new java.awt.Font("Carlito", 0, 24)); // NOI18N
        txtStok.setForeground(new java.awt.Color(250, 249, 247));
        txtStok.setBorder(null);
        txtStok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStokKeyReleased(evt);
            }
        });
        pTambahBrg.add(txtStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 740, -1));

        kembaliBrg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/undo-48.png"))); // NOI18N
        kembaliBrg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kembaliBrgMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                kembaliBrgMouseReleased(evt);
            }
        });
        pTambahBrg.add(kembaliBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 780, -1, -1));

        Master.add(pTambahBrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 1235, 846));

        dBarang.setFont(new java.awt.Font("Carlito", 1, 30)); // NOI18N
        dBarang.setForeground(new java.awt.Color(66, 61, 51));
        dBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/database-5-48.png"))); // NOI18N
        dBarang.setText(" Data Barang");
        dBarang.setAutoscrolls(true);
        dBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dBarangMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dBarangMouseReleased(evt);
            }
        });
        menuMaster.add(dBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 280, -1));

        admin2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/administrator-64.png"))); // NOI18N
        admin2.setAutoscrolls(true);
        menuMaster.add(admin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        dTrx.setFont(new java.awt.Font("Carlito", 1, 30)); // NOI18N
        dTrx.setForeground(new java.awt.Color(66, 61, 51));
        dTrx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/banknotes-48.png"))); // NOI18N
        dTrx.setText(" Data Transaksi");
        dTrx.setAutoscrolls(true);
        dTrx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dTrxMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dTrxMouseReleased(evt);
            }
        });
        menuMaster.add(dTrx, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 280, -1));

        dUser.setFont(new java.awt.Font("Carlito", 1, 30)); // NOI18N
        dUser.setForeground(new java.awt.Color(66, 61, 51));
        dUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/group-48.png"))); // NOI18N
        dUser.setText(" Data User");
        dUser.setAutoscrolls(true);
        dUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dUserMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                dUserMouseReleased(evt);
            }
        });
        menuMaster.add(dUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 280, -1));

        Master.add(menuMaster, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 850));

        admin1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/administrator-512.png"))); // NOI18N
        admin1.setAutoscrolls(true);
        Master.add(admin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 390, -1, -1));

        tabbedPaneCustom1.addTab("Master", Master);

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
        menuPengaturan.add(btOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 280, -1));

        btPass.setFont(new java.awt.Font("Carlito", 1, 30)); // NOI18N
        btPass.setForeground(new java.awt.Color(66, 61, 51));
        btPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit-48 (1).png"))); // NOI18N
        btPass.setText(" Ganti Password");
        btPass.setAutoscrolls(true);
        btPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btPassMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btPassMouseReleased(evt);
            }
        });
        menuPengaturan.add(btPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 280, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cog-64.png"))); // NOI18N
        menuPengaturan.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        btAbout.setFont(new java.awt.Font("Carlito", 1, 30)); // NOI18N
        btAbout.setForeground(new java.awt.Color(66, 61, 51));
        btAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/info-2-48.png"))); // NOI18N
        btAbout.setText(" About");
        btAbout.setAutoscrolls(true);
        btAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAboutMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btAboutMouseReleased(evt);
            }
        });
        menuPengaturan.add(btAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 280, -1));

        Pengaturan.add(menuPengaturan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 850));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cog-512.png"))); // NOI18N
        Pengaturan.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 380, -1, -1));
        Pengaturan.add(pAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));
        Pengaturan.add(pPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, 370));

        tabbedPaneCustom1.addTab("Pengaturan", Pengaturan);

        border.add(tabbedPaneCustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1580, 880));

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

    private void dUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dUserMouseClicked
        pUser.setVisible(true);
        pBrg.setVisible(false);
        pTrx.setVisible(false);
    }//GEN-LAST:event_dUserMouseClicked

    private void dBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dBarangMouseClicked
        pUser.setVisible(false);
        pBrg.setVisible(true);
        pTrx.setVisible(false);
    }//GEN-LAST:event_dBarangMouseClicked

    private void dTrxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dTrxMouseClicked
        pUser.setVisible(false);
        pBrg.setVisible(false);
        pTrx.setVisible(true);
    }//GEN-LAST:event_dTrxMouseClicked

    private void tabbedPaneCustom1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbedPaneCustom1MouseClicked
        pUser.setVisible(false);
        pBrg.setVisible(false);
        pTrx.setVisible(false);
        pTambahUser.setVisible(false);
        pTambahBrg.setVisible(false);
        pPass.setVisible(false);
        pAbout.setVisible(false);
    }//GEN-LAST:event_tabbedPaneCustom1MouseClicked

    private void tambahUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahUserMouseClicked
        try {
            String sql = "insert into user values('" + txtID.getText() + "','"
                    + txtNama.getText() + "','"
                    + txtAlamat.getText() + "','"
                    + txtNo.getText() + "','"
                    + txtStatus.getText() + "','"
                    + txtUser.getText() + "','"
                    + txtPass.getText() + "','"
                    + txtAkses.getText() + "')";

            java.sql.Connection conn = (Connection) nek.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();

            JOptionPane.showMessageDialog(null, "Proses Simpan Data Success!");

            pUser.setVisible(true);
            pUser.innit();
            pUser.eU();
            pTambahUser.setVisible(false);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_tambahUserMouseClicked

    private void txtIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDKeyReleased

    private void txtNamaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaKeyReleased

    private void txtAlamatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlamatKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlamatKeyReleased

    private void txtNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoKeyReleased

    private void txtStatusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStatusKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStatusKeyReleased

    private void txtUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserKeyReleased

    private void txtPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassKeyReleased

    private void txtAksesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAksesKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAksesKeyReleased

    private void kembaliUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kembaliUserMouseClicked
        pUser.setVisible(true);
        pTambahUser.setVisible(false);
    }//GEN-LAST:event_kembaliUserMouseClicked

    private void txtCariBrgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariBrgKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariBrgKeyReleased

    private void tambahbrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahbrgMouseClicked
        pBrg.setVisible(false);
        pTambahBrg.setVisible(true);
    }//GEN-LAST:event_tambahbrgMouseClicked

    private void tambahBrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahBrgMouseClicked
        try {
            String sql = "insert into barang values('" + txtKode.getText() + "','"
                    + txtNamaBrg.getText() + "','"
                    + txtSatuan.getText() + "','"
                    + txtHarga.getText() + "','"
                    + txtStok.getText() + "')";

            java.sql.Connection conn = (Connection) nek.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.execute();

            JOptionPane.showMessageDialog(null, "Proses Simpan Data Success!");

            pBrg.setVisible(true);
            bnnit();
            pTambahBrg.setVisible(false);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_tambahBrgMouseClicked

    private void txtKodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKodeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeKeyReleased

    private void txtNamaBrgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaBrgKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaBrgKeyReleased

    private void txtSatuanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSatuanKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSatuanKeyReleased

    private void txtHargaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHargaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaKeyReleased

    private void txtStokKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStokKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStokKeyReleased

    private void kembaliBrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kembaliBrgMouseClicked
        pBrg.setVisible(true);
        pTambahBrg.setVisible(false);
    }//GEN-LAST:event_kembaliBrgMouseClicked

    private void tabelBrgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBrgMouseClicked
        int baris = tabelBrg.getSelectedRow();
        String kd = tabelBrg.getValueAt(baris, 1).toString();
        txtkode.setText(kd);
        String nama = tabelBrg.getValueAt(baris, 2).toString();
        txtnamabrg.setText(nama);
        String satuan = tabelBrg.getValueAt(baris, 3).toString();
        txtsatuan.setText(satuan);
        String hrg = tabelBrg.getValueAt(baris, 4).toString();
        txtharga.setText(hrg);
        String stok = tabelBrg.getValueAt(baris, 5).toString();
        txtstok.setText(stok);
    }//GEN-LAST:event_tabelBrgMouseClicked

    private void tabelBrgKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelBrgKeyReleased
        int baris = tabelBrg.getSelectedRow();
        String kd = tabelBrg.getValueAt(baris, 1).toString();
        txtkode.setText(kd);
        String nama = tabelBrg.getValueAt(baris, 2).toString();
        txtnamabrg.setText(nama);
        String satuan = tabelBrg.getValueAt(baris, 3).toString();
        txtsatuan.setText(satuan);
        String hrg = tabelBrg.getValueAt(baris, 4).toString();
        txtharga.setText(hrg);
        String stok = tabelBrg.getValueAt(baris, 5).toString();
        txtstok.setText(stok);
    }//GEN-LAST:event_tabelBrgKeyReleased

    private void dUserMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dUserMouseReleased
        pUser.setVisible(true);
        pBrg.setVisible(false);
        pTrx.setVisible(false);
    }//GEN-LAST:event_dUserMouseReleased

    private void dBarangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dBarangMouseReleased
        pUser.setVisible(false);
        pBrg.setVisible(true);
        pTrx.setVisible(false);
    }//GEN-LAST:event_dBarangMouseReleased

    private void dTrxMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dTrxMouseReleased
        pUser.setVisible(false);
        pBrg.setVisible(false);
        pTrx.setVisible(true);
    }//GEN-LAST:event_dTrxMouseReleased

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

    private void btOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btOutMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btOutMouseClicked

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

    private void kembaliUserMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kembaliUserMouseReleased
        pUser.setVisible(true);
        pTambahUser.setVisible(false);
    }//GEN-LAST:event_kembaliUserMouseReleased

    private void kembaliBrgMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kembaliBrgMouseReleased
        pBrg.setVisible(true);
        pTambahBrg.setVisible(false);
    }//GEN-LAST:event_kembaliBrgMouseReleased

    private void btPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btPassMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btPassMouseClicked

    private void btPassMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btPassMouseReleased
        pPass.setVisible(true);
        pAbout.setVisible(false);
    }//GEN-LAST:event_btPassMouseReleased

    private void btAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAboutMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btAboutMouseClicked

    private void btAboutMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAboutMouseReleased
        pAbout.setVisible(true);
        pPass.setVisible(false);
    }//GEN-LAST:event_btAboutMouseReleased

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
            java.util.logging.Logger.getLogger(Form_MenuA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_MenuA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_MenuA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_MenuA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new Form_MenuA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private panel.Border Home;
    private panel.Border Master;
    private panel.Border Pengaturan;
    private panel.Border Penjualan;
    private javax.swing.JLabel admin;
    private javax.swing.JLabel admin1;
    private javax.swing.JLabel admin2;
    private panel.Border border;
    private javax.swing.JLabel btAbout;
    private javax.swing.JLabel btOut;
    private javax.swing.JLabel btPass;
    private javax.swing.JLabel dBarang;
    private javax.swing.JLabel dTrx;
    private javax.swing.JLabel dUser;
    private javax.swing.JLabel eks;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel kembaliBrg;
    private javax.swing.JLabel kembaliUser;
    private panel.menu menuMaster;
    private panel.menu menuPengaturan;
    public static javax.swing.JLabel nama;
    private panel.pAbout pAbout;
    private panel.Border pBrg;
    private panel.pJual pJual1;
    private panel.pPass pPass;
    private panel.Border pTambahBrg;
    public static panel.Border pTambahUser;
    private panel.pTrx pTrx;
    public static panel.pUser pUser;
    private raven.tabbed.TabbedPaneCustom tabbedPaneCustom1;
    private tabledark.TableDark tabelBrg;
    private javax.swing.JLabel tambahBrg;
    private javax.swing.JLabel tambahUser;
    private javax.swing.JLabel tambahbrg;
    private javax.swing.JTextField txtAkses;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtCariBrg;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNamaBrg;
    private javax.swing.JTextField txtNo;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtSatuan;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtStok;
    private javax.swing.JTextField txtUser;
    private javax.swing.JLabel txtharga;
    private javax.swing.JLabel txtkode;
    private javax.swing.JLabel txtnamabrg;
    private javax.swing.JLabel txtsatuan;
    private javax.swing.JLabel txtstok;
    private javax.swing.JLabel welcome;
    private javax.swing.JLabel welcome2;
    private javax.swing.JLabel welcome3;
    // End of variables declaration//GEN-END:variables
}
