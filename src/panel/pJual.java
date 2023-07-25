/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import conek.nek;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import raven.cell.PanelPlus;
import raven.cell.TableActionCellEditor1;
import raven.cell.TableActionCellRender1;
import raven.cell.TableActionEvent1;
import proyect.Form_MenuA;

/**
 *
 * @author ASUS
 */
public class pJual extends javax.swing.JPanel {

    NumberFormat nft = NumberFormat.getNumberInstance(new Locale("in", "ID"));
    DefaultTableModel model = new DefaultTableModel();

    private void clear_1(){
        txtKode.setText("");
        txtStokw.setText("");
        txtNama.setText("");
        txtHarga.setText("");
        txtJumlah.setText("" + 1);
    }
    
    private void clear_2() {
        txtFak.setText("");
        txtB.setText("");
        txtK.setText("");
        txtTotal.setText("");
        autoNFT();
    }

    public void autoNFT() {

        try {

            String sql = "Select * from penjualan order by no_faktur desc";

            java.sql.Connection conn = (Connection) nek.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            if (res.next()) {
                String id = res.getString("no_faktur").substring(5);
                String AN = "" + (Integer.parseInt(id) + 1);
                String No1 = "";
                if (AN.length() == 1) {
                    No1 = "0000";
                } else if (AN.length() == 2) {
                    No1 = "000";
                } else if (AN.length() == 3) {
                    No1 = "00";
                } else if (AN.length() == 4) {
                    No1 = "0";
                } else if (AN.length() == 5) {
                    No1 = "";
                }
                txtFak.setText("NFSMW" + No1 + AN);
            } else {
                txtFak.setText("NFSMW00001");
            }
        } catch (SQLException e) {
            System.out.println(": rorre" + e.getMessage());
        }
    }

    private void showBarang() {
        DefaultTableModel modelb = new DefaultTableModel();
        modelb.addColumn("Kode Barang");
        modelb.addColumn("Nama Barang");
        modelb.addColumn("Harga");
        modelb.addColumn("Stok");

        String cari = txtCari.getText();
        try {

            String sql = "Select * from barang where CONCAT_WS('', kd_barang, nama_barang) like'%" + cari + "%'";

            java.sql.Connection conn = (Connection) nek.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                modelb.addRow(new Object[]{res.getString(1),
                    res.getString(2), res.getString(4), res.getString(5)});
            }
            tabelBarang.setModel(modelb);

            TableColumn col;
            tabelBarang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

            col = tabelBarang.getColumnModel().getColumn(0);
            col.setPreferredWidth(75);
            col = tabelBarang.getColumnModel().getColumn(1);
            col.setPreferredWidth(170);
            col = tabelBarang.getColumnModel().getColumn(2);
            col.setPreferredWidth(70);
            col = tabelBarang.getColumnModel().getColumn(3);
            col.setPreferredWidth(45);
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    private void titleCart() {
        model.addColumn("No");
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Sub Total");

        tabelCart.setModel(model);
        TableColumn col;
        tabelCart.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

        col = tabelCart.getColumnModel().getColumn(0);
        col.setPreferredWidth(30);
        col = tabelCart.getColumnModel().getColumn(1);
        col.setPreferredWidth(100);
        col = tabelCart.getColumnModel().getColumn(2);
        col.setPreferredWidth(534);
        col = tabelCart.getColumnModel().getColumn(3);
        col.setPreferredWidth(100);
        col = tabelCart.getColumnModel().getColumn(4);
        col.setPreferredWidth(50);
        col = tabelCart.getColumnModel().getColumn(5);
        col.setPreferredWidth(100);
    }

    private void tang() {
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
        Date now = new Date();
        String d = df.format(now);
        tgl.setText(d);
    }

    private void load() {
        DefaultTableModel modell = (DefaultTableModel) tabelCart.getModel();
        Object obj[] = new Object[6];

        int b = tabelBarang.getSelectedRow()
                ,har, jum;
        
        har = Integer.valueOf(tabelBarang.getValueAt(b, 2).toString());
        jum = Integer.valueOf(txtJumlah.getText());

        obj[1] = tabelBarang.getValueAt(b, 0).toString();
        obj[2] = tabelBarang.getValueAt(b, 1).toString();
        obj[3] = tabelBarang.getValueAt(b, 2).toString();
        obj[4] = txtJumlah.getText();//Integer.valueOf(tabelBarang.getSelectedRow(PanelPlus.txtJumlah.getText()));
        obj[5] = har * jum;


        modell.addRow(obj);

        int ba = modell.getRowCount();
        for (int a = 0; a < ba; a++) {
            String no = String.valueOf(a + 1);
            modell.setValueAt(no + ".", a, 0);
        }
        tabelCart.setRowHeight(25);
    }

    private void totb() {
        int jb = tabelCart.getRowCount();
        int totb = 0;
        int jba, hb;
        for (int i = 0; i < jb; i++) {
            hb = Integer.parseInt(tabelCart.getValueAt(i, 3).toString());
            jba = Integer.parseInt(tabelCart.getValueAt(i, 4).toString());
            totb = totb + (jba * hb);
        }
        txtTotal.setText("Rp " + nft.format(totb));
    }

    private void tam() {
        int j, h, t;

        j = Integer.valueOf(txtJumlah.getText());
        h = Integer.valueOf(txtHarga.getText());
        t = j * h;
        txtTotal.setText("Rp " + nft.format(t));
        totb();
    }

    private void clear_cart() {
        DefaultTableModel model = (DefaultTableModel) tabelCart.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }

    public void eJ() {
        TableActionEvent1 event = new TableActionEvent1() {

            @Override
            public void onNum(int row) {

            }

            @Override
            public void onAdd(int row) {
                load();
                tam();
                try {
                    int b = tabelCart.getRowCount();
                    for (int i = 0; i < b; i++) {
                        String up = "update barang set stok='" + (Integer.valueOf(tabelCart.getValueAt(i, 4).toString()) - 1)
                                + "' where kd_barang='" + tabelCart.getValueAt(i, 1).toString() + "'";

                        java.sql.Connection conn = (Connection) nek.configDB();
                        java.sql.PreparedStatement pstm = conn.prepareStatement(up);
                        pstm.execute();
                        pstm.close();

                    }
                    JOptionPane.showMessageDialog(null, "Data masuk keranjang!");
                    showBarang();
                    eJ();
                } catch (HeadlessException | SQLException e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, "wat");
                }
            }
        };
        //tabelBarang.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender1());
        //tabelBarang.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor1(event));
    }

    /**
     * Creates new form pUser
     */
    public pJual() {
        initComponents();
        autoNFT();
        tang();
        //eJ();
        showBarang();
        titleCart();
        setOpaque(false);
        setBackground(new Color(39, 36, 29, 0));
        tabelBarang.fixTable(jScrollPane2);
        txtJumlah.setText("" + 1);
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
        tabelBarang = new tabledark.TableDark();
        jSeparator1 = new javax.swing.JSeparator();
        btSimpan = new javax.swing.JLabel();
        txtFak = new javax.swing.JLabel();
        tgl = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelCart = new tabledark.TableDark();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txtK = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        txtID = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btTambah = new javax.swing.JLabel();
        txtKode = new javax.swing.JLabel();
        txtNama = new javax.swing.JLabel();
        txtHarga = new javax.swing.JLabel();
        txtStokw = new javax.swing.JLabel();
        txtB = new javax.swing.JTextField();
        btHapus = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        txtJumlah = new javax.swing.JTextField();
        jumDown = new javax.swing.JLabel();
        jumUp = new javax.swing.JLabel();
        txtStokk = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCari.setBackground(new java.awt.Color(39, 36, 29));
        txtCari.setFont(new java.awt.Font("Carlito", 0, 12)); // NOI18N
        txtCari.setForeground(new java.awt.Color(250, 249, 247));
        txtCari.setBorder(null);
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });
        border1.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 30, 210, 20));

        tabelBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelBarang.setFont(new java.awt.Font("Carlito", 0, 14)); // NOI18N
        tabelBarang.setRowHeight(50);
        tabelBarang.setSelectionBackground(new java.awt.Color(250, 249, 247));
        tabelBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelBarangMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabelBarang);

        border1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 80, 380, 590));
        border1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 50, 210, 10));

        btSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-48.png"))); // NOI18N
        btSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btSimpanMouseReleased(evt);
            }
        });
        border1.add(btSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 770, -1, -1));

        txtFak.setFont(new java.awt.Font("Carlito", 1, 18)); // NOI18N
        txtFak.setForeground(new java.awt.Color(250, 249, 247));
        txtFak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        border1.add(txtFak, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 160, 20));

        tgl.setFont(new java.awt.Font("Carlito", 1, 18)); // NOI18N
        tgl.setForeground(new java.awt.Color(250, 249, 247));
        tgl.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        border1.add(tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 160, 20));

        txtTotal.setFont(new java.awt.Font("Carlito", 1, 48)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(250, 249, 247));
        txtTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotal.setText("Rp 0");
        border1.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 770, 390, 50));

        tabelCart.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelCart.setFont(new java.awt.Font("Carlito", 0, 14)); // NOI18N
        tabelCart.setRowHeight(50);
        tabelCart.setSelectionBackground(new java.awt.Color(250, 249, 247));
        tabelCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tabelCartMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tabelCart);

        border1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 920, 590));

        jLabel6.setFont(new java.awt.Font("Carlito", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(250, 249, 247));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("No. Faktur:");
        border1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 90, -1));
        border1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 820, 170, 10));

        txtK.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        txtK.setForeground(new java.awt.Color(250, 249, 247));
        txtK.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtK.setText("Rp 0");
        border1.add(txtK, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 790, 170, 30));

        jLabel8.setFont(new java.awt.Font("Carlito", 1, 48)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(250, 249, 247));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Total");
        border1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 770, 160, 50));

        jLabel9.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(250, 249, 247));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Kembalian");
        border1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 790, 160, 30));

        jLabel10.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(250, 249, 247));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Bayar");
        border1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 750, 160, 30));
        border1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 820, 390, 30));
        border1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 780, 170, 10));

        txtID.setForeground(new java.awt.Color(39, 36, 29));
        border1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 30, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/search-2-24.png"))); // NOI18N
        border1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 30, -1, -1));

        btTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus-7-48.png"))); // NOI18N
        btTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btTambahMouseReleased(evt);
            }
        });
        border1.add(btTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 690, -1, -1));

        txtKode.setForeground(new java.awt.Color(39, 36, 29));
        border1.add(txtKode, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 30, -1, -1));

        txtNama.setForeground(new java.awt.Color(39, 36, 29));
        border1.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 30, -1, -1));

        txtHarga.setForeground(new java.awt.Color(39, 36, 29));
        border1.add(txtHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 30, -1, -1));

        txtStokw.setForeground(new java.awt.Color(39, 36, 29));
        border1.add(txtStokw, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, 40, 30));

        txtB.setBackground(new java.awt.Color(39, 36, 29));
        txtB.setFont(new java.awt.Font("Carlito", 1, 24)); // NOI18N
        txtB.setForeground(new java.awt.Color(250, 249, 247));
        txtB.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtB.setBorder(null);
        txtB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBKeyReleased(evt);
            }
        });
        border1.add(txtB, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 750, 170, 30));

        btHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete-48.png"))); // NOI18N
        btHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btHapusMouseReleased(evt);
            }
        });
        border1.add(btHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 690, -1, -1));
        border1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 740, 70, 10));

        txtJumlah.setBackground(new java.awt.Color(39, 36, 29));
        txtJumlah.setFont(new java.awt.Font("Carlito", 0, 24)); // NOI18N
        txtJumlah.setForeground(new java.awt.Color(250, 249, 247));
        txtJumlah.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtJumlah.setBorder(null);
        txtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtJumlahKeyReleased(evt);
            }
        });
        border1.add(txtJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 710, 70, 30));

        jumDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/arrow-204-16.png"))); // NOI18N
        jumDown.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jumDownMouseReleased(evt);
            }
        });
        border1.add(jumDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 730, -1, -1));

        jumUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/arrow-142-16.png"))); // NOI18N
        jumUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jumUpMouseReleased(evt);
            }
        });
        border1.add(jumUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 700, -1, -1));

        txtStokk.setForeground(new java.awt.Color(39, 36, 29));
        border1.add(txtStokk, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 30, 40, 30));

        add(border1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1575, 844));
    }// </editor-fold>//GEN-END:initComponents

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        showBarang();
    }//GEN-LAST:event_txtCariKeyReleased

    private void tabelBarangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarangMouseReleased
        int baris = tabelBarang.getSelectedRow();
        String kode = tabelBarang.getValueAt(baris, 0).toString();
        txtKode.setText(kode);
        String nama = tabelBarang.getValueAt(baris, 1).toString();
        txtNama.setText(nama);
        String harga = tabelBarang.getValueAt(baris, 2).toString();
        txtHarga.setText(harga);
        String stok = tabelBarang.getValueAt(baris, 3).toString();
        txtStokw.setText(stok);
        txtJumlah.setText("" + 1);
        
    }//GEN-LAST:event_tabelBarangMouseReleased

    private void tabelCartMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelCartMouseReleased
        int baris = tabelCart.getSelectedRow();
        String kode = tabelCart.getValueAt(baris, 1).toString();
        txtKode.setText(kode);
        String nama = tabelCart.getValueAt(baris, 2).toString();
        txtNama.setText(nama);
        String harga = tabelCart.getValueAt(baris, 3).toString();
        txtHarga.setText(harga);
        String stok = tabelCart.getValueAt(baris, 4).toString();
        txtStokw.setText(stok);
        
        try {
            String sql = "Select stok from barang where kd_barang ='" + txtKode.getText() + "'";

            java.sql.Connection conn = (Connection) nek.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);

            if(res.next()){
                txtStokk.setText(res.getString("stok"));
            }
        } catch (SQLException e) {
            System.out.println("Error" + e.getMessage());
        }
    }//GEN-LAST:event_tabelCartMouseReleased

    private void btSimpanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSimpanMouseReleased
        int t, b;
        t = Integer.parseInt(txtTotal.getText().replace(".", "").replace("Rp", "").replace(" ", ""));
        b = Integer.parseInt(txtB.getText().replace(".", ""));

        if (b < t) {
            JOptionPane.showMessageDialog(null, "Uang tidak cukup!");
            txtB.requestFocus();
        } else {
            String p = "yyyy-MM-dd";
            SimpleDateFormat fm = new SimpleDateFormat(p);
            Date now = new Date();
            String tangga = String.valueOf(fm.format(now));
            try {
                int bb = tabelCart.getRowCount();
                for (int i = 0; i < bb; i++) {
                    String sql = "insert into penjualan values( NULL,'"
                            + txtFak.getText() + "','"
                            + tangga + "','"
                            + tabelCart.getValueAt(i, 1) + "','"
                            + txtID.getText() + "','"
                            + tabelCart.getValueAt(i, 4) + "','"
                            + tabelCart.getValueAt(i, 5).toString().replace(".", "") + "')";

                    java.sql.Connection conn = (Connection) nek.configDB();
                    java.sql.PreparedStatement pstm = conn.prepareStatement(sql);
                    pstm.execute();
                    pstm.close();
                }
                JOptionPane.showMessageDialog(null, "Proses simpan data success!!");
            } catch (HeadlessException | SQLException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "ha");
            }
            JasperReport jr;
            JasperPrint jp;
            JasperDesign jd;

            try {
                java.sql.Connection conn = (Connection) nek.configDB();
                HashMap parameter = new HashMap();
                parameter.put("no_faktur", txtFak.getText());
                parameter.put("totalbelanja", txtTotal.getText().replace(".", "").replace("Rp", "").replace(" ", ""));
                parameter.put("bayar", txtB.getText());
                parameter.put("kembalian", txtK.getText());

                File report = new File("src/lapar/strokeBelanja.jrxml");
                jd = JRXmlLoader.load(report);
                jr = JasperCompileManager.compileReport(jd);
                jp = JasperFillManager.fillReport(jr, parameter, conn);
                JasperViewer.viewReport(jp, false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            clear_cart();
            clear_2();
            showBarang();
            txtB.setText("");
            txtK.setText("0");
            txtTotal.setText("Rp 0");
        }

    }//GEN-LAST:event_btSimpanMouseReleased

    private void btTambahMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btTambahMouseReleased
        load();
        tam();
        
        int b = tabelCart.getRowCount(),
                stok = Integer.valueOf(txtStokw.getText()),
                jum = Integer.valueOf(txtJumlah.getText()), tot;
        
        tot = stok - jum;
        
        try {
            for (int i = 0; i < b; i++) {
                String up = "update barang set stok='" + tot
                        + "' where kd_barang='" + txtKode.getText() + "'";

                java.sql.Connection conn = (Connection) nek.configDB();
                java.sql.PreparedStatement pstm = conn.prepareStatement(up);
                pstm.execute();
                pstm.close();

            }
            txtJumlah.setText("1");
            showBarang();
            //eJ();
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "wat");
        }
    }//GEN-LAST:event_btTambahMouseReleased

    private void txtBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBKeyReleased
        int t, b, k;
        t = Integer.parseInt(txtTotal.getText().replace(".", "").replace("Rp", "").replace(" ", ""));
        b = Integer.parseInt(txtB.getText().replace(".", ""));

        if (b >= t) {
            k = b - t;

            txtK.setText(nft.format(k));
            if (k == 0) {
                txtK.setText("0");
            }
        } else if (b < t) {
            txtK.setText("0");
        }
    }//GEN-LAST:event_txtBKeyReleased

    private void btHapusMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btHapusMouseReleased
        DefaultTableModel m = (DefaultTableModel) tabelCart.getModel();
        
        try{
        int b = tabelCart.getRowCount();
        for(int i = 0; i < b; i++){
            int jum = Integer.valueOf(txtStokw.getText()),
                    stok = Integer.valueOf(txtStokk.getText()),
                    tot = jum + stok;
            String up = "update barang set stok='" + tot
                    +"' where kd_barang='" + txtKode.getText() + "'";
            
            java.sql.Connection conn = (Connection)nek.configDB();
            java.sql.PreparedStatement pstm = conn.prepareStatement(up);
            pstm.execute();
            pstm.close();
        }
        JOptionPane.showMessageDialog(null, "Transaksi batal!");
        showBarang();
        }catch (HeadlessException | SQLException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "wat");
        }
        
        int bb = tabelCart.getSelectedRow();
        m.removeRow(bb);
        tabelCart.setRowHeight(25);
        totb();
        txtB.setText("");
        txtK.setText("Rp 0");
        clear_1();
    }//GEN-LAST:event_btHapusMouseReleased

    private void txtJumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahKeyReleased

    private void jumUpMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jumUpMouseReleased
        int jum, stok, plus, min;
        jum = Integer.parseInt(txtJumlah.getText());
        stok = Integer.valueOf(txtStokw.getText());
        plus = jum + 1;
        min = jum - 1;
        
        txtJumlah.setText(""+ plus);
        
        if(plus > stok){
            JOptionPane.showMessageDialog(null, "Stok tidak cukup!");
            txtJumlah.setText(""+ jum);
        }
    }//GEN-LAST:event_jumUpMouseReleased

    private void jumDownMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jumDownMouseReleased
        int jum, min;
        jum = Integer.parseInt(txtJumlah.getText());
        min = jum - 1;
        
        txtJumlah.setText(""+ min);
        
        if(min < 1){
            txtJumlah.setText(""+ 1);
        }
    }//GEN-LAST:event_jumDownMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private panel.Border border1;
    private javax.swing.JLabel btHapus;
    private javax.swing.JLabel btSimpan;
    private javax.swing.JLabel btTambah;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel jumDown;
    private javax.swing.JLabel jumUp;
    private tabledark.TableDark tabelBarang;
    private tabledark.TableDark tabelCart;
    private javax.swing.JLabel tgl;
    private javax.swing.JTextField txtB;
    private javax.swing.JTextField txtCari;
    private javax.swing.JLabel txtFak;
    public static javax.swing.JLabel txtHarga;
    public static javax.swing.JLabel txtID;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JLabel txtK;
    public static javax.swing.JLabel txtKode;
    public static javax.swing.JLabel txtNama;
    public static javax.swing.JLabel txtStokk;
    public static javax.swing.JLabel txtStokw;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
