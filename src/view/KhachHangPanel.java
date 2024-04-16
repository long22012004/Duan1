/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Model.KhachHang;
import Service.KhachHang_Service;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class KhachHangPanel extends javax.swing.JPanel {

    KhachHang_Service service = new KhachHang_Service();
    List<KhachHang> list = new ArrayList<>();
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel modelKHBought = new DefaultTableModel();
    NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

    public KhachHangPanel() {
        initComponents();
        modelKHBought = (DefaultTableModel) DanhSachMua.getModel();
        model = (DefaultTableModel) tblkhachhang.getModel();
        list = service.getAll();
        fillTable(list);
    }

    void fillTable(List<KhachHang> listKH) {
        model.setRowCount(0);
        for (KhachHang kh : listKH) {
            model.addRow(new Object[]{kh.getId_kh(), kh.getMa(), kh.getTenkh(), kh.getDiaChi(), kh.getSdt(), kh.getEmail(), kh.isGioiTinh() ? "Nữ" : "Nam", kh.getNamSinh()});
        }

    }

    void ShowData(int index) {
        KhachHang kh = list.get(index);
        txtMa.setText(kh.getMa());
        txttenkh.setText(kh.getTenkh());
        txtdiachi.setText(kh.getDiaChi());
        txtsdt.setText(kh.getSdt());
        txtemail.setText(kh.getEmail());
        if (kh.isGioiTinh() == true) {
           rdonu.setSelected(true);
        } else {
            rdonam.setSelected(true);
        }
        txtnamsinh.setText(String.valueOf(kh.getNamSinh()));
    }

    KhachHang readForm() {
        KhachHang kh = new KhachHang();
        kh.setMa(txtMa.getText());
        kh.setTenkh(txttenkh.getText());
        kh.setDiaChi(txtdiachi.getText());
        kh.setSdt(txtsdt.getText());
        kh.setEmail(txtemail.getText());
        kh.setGioiTinh(rdonam.isSelected());
        kh.setGioiTinh(rdonu.isSelected());
        kh.setNamSinh(Integer.parseInt(txtnamsinh.getText()));
        return kh;
    }

    void reset() {
        txtMa.setText("");
        txttenkh.setText("");
        txtdiachi.setText("");
        txtsdt.setText("");
        txtemail.setText("");
        txtnamsinh.setText("");

    }

    private boolean checkKhachHang() {
        String ten = txttenkh.getText();
        String email = txtemail.getText();
        String diaChi = txtdiachi.getText();
        String sdt = txtsdt.getText();
        String ma = txtMa.getText();
        Pattern patternEmail = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z]+(.[a-zA-Z]+){2}$");
        Pattern patternSDT = Pattern.compile("^(0|\\+84)([0-9]{9})$");
        ArrayList<KhachHang> listsp = (ArrayList<KhachHang>) service.getAll();
        int listSize = listsp.size();
        for (int i = 0; i < listSize; i++) {
            if (listsp.get(i).getMa().equalsIgnoreCase(ma)) {
                JOptionPane.showMessageDialog(this, "Mã khách hàng đã tồn tại!");
                return false;
            }
        }
        if (ma.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng không được để trống!");
            return false;
        } else {
            if (ten.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên Khách Hàng Không Được Trống!");
                return false;
            } else {
                if (email.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Email Khách Hàng Không Được Để Trống!");
                    return false;
                } else {
                    if (!patternEmail.matcher(email).find()) {
                        JOptionPane.showMessageDialog(this, "Không Đúng Định Dạng Email!");
                        return false;
                    } else {
                        if (diaChi.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Địa Chỉ Khách Hàng Không Được Trống!");
                            return false;
                        } else {
                            if (sdt.trim().isEmpty()) {
                                JOptionPane.showMessageDialog(this, "SĐT Khách Hàng Không Được Chống!");
                                return false;
                            } else {
                                if (!patternSDT.matcher(sdt).find()) {
                                    JOptionPane.showMessageDialog(this, "Không Đúng Định Dạng SĐT!");
                                    return false;
                                } else {
                                    if (ma.trim().isEmpty()) {
                                        JOptionPane.showMessageDialog(this, "Mã Không được để trống!");
                                        return false;
                                    } else {
                                        if (txtnamsinh.getText().trim().isEmpty()) {
                                            JOptionPane.showMessageDialog(this, "Năm sinh không được để trống!");
                                            return false;
                                        } else {
                                            try {
                                                Integer nam = Integer.parseInt(txtnamsinh.getText());
                                                if (nam < 0 && nam > 2023) {
                                                    JOptionPane.showMessageDialog(this, "Năm sinh không tồn tại!");
                                                    return false;
                                                }
                                            } catch (Exception e) {
                                                JOptionPane.showMessageDialog(this, "Năm sinh phải là số!");
                                                return false;
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean checkUpdate() {
        String ten = txttenkh.getText();
        String email = txtemail.getText();
        String diaChi = txtdiachi.getText();
        String sdt = txtsdt.getText();
        String ma = txtMa.getText();
        Pattern patternEmail = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z]+(.[a-zA-Z]+){2}$");
        Pattern patternSDT = Pattern.compile("^(0|\\+84)([0-9]{9})$");
        int count = 0;
        if (ma.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng không được để trống!");
            return false;
        } else {
            if (ten.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên Khách Hàng Không Được Trống!");
                return false;
            } else {
                if (email.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Email Khách Hàng Không Được Để Trống!");
                    return false;
                } else {
                    if (!patternEmail.matcher(email).find()) {
                        JOptionPane.showMessageDialog(this, "Không Đúng Định Dạng Email!");
                        return false;
                    } else {
                        if (diaChi.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Địa Chỉ Khách Hàng Không Được Trống!");
                            return false;
                        } else {
                            if (sdt.trim().isEmpty()) {
                                JOptionPane.showMessageDialog(this, "SĐT Khách Hàng Không Được Chống!");
                                return false;
                            } else {
                                if (!patternSDT.matcher(sdt).find()) {
                                    JOptionPane.showMessageDialog(this, "Không Đúng Định Dạng SĐT!");
                                    return false;
                                } else {
                                    if (ma.trim().isEmpty()) {
                                        JOptionPane.showMessageDialog(this, "Mã Không được để trống!");
                                        return false;
                                    } else {
                                        if (txtnamsinh.getText().trim().isEmpty()) {
                                            JOptionPane.showMessageDialog(this, "Năm sinh không được để trống!");
                                            return false;
                                        } else {
                                            try {
                                                Integer nam = Integer.parseInt(txtnamsinh.getText());
                                                if (nam < 0 && nam > 2023) {
                                                    JOptionPane.showMessageDialog(this, "Năm sinh không tồn tại!");
                                                    return false;
                                                }
                                            } catch (Exception e) {
                                                JOptionPane.showMessageDialog(this, "Năm sinh phải là số!");
                                                return false;
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private void KhachHangBought() {
        modelKHBought.setRowCount(0);
        int selected = tblkhachhang.getSelectedRow();
        int i = 0;
        if (selected >= 0) {
            int id = (int) tblkhachhang.getValueAt(selected, 0);
            List<Object[]> listoj = service.KhachHangBought(id);
            for (Object[] row : listoj) {
                i++;
                modelKHBought.addRow(new Object[]{i, row[0], row[1], format.format(row[2]), row[3], format.format(row[4])});
            }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtsdt = new javax.swing.JTextField();
        txtdiachi = new javax.swing.JTextField();
        txttenkh = new javax.swing.JTextField();
        txtMa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rdonam = new javax.swing.JRadioButton();
        rdonu = new javax.swing.JRadioButton();
        txtnamsinh = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblkhachhang = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnadd = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnreset = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        DanhSachMua = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Khách Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel1.setText("Mã Khách hàng:");

        jLabel2.setText("Tên Khách Hàng:");

        jLabel3.setText("Địa Chỉ:");

        jLabel5.setText("Số Điện Thoại:");

        jLabel4.setText("Email:");

        jLabel7.setText("Năm Sinh:");

        jLabel8.setText("Giới Tính:");

        buttonGroup1.add(rdonam);
        rdonam.setSelected(true);
        rdonam.setText("Nam");

        buttonGroup1.add(rdonu);
        rdonu.setText("Nữ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(65, 65, 65)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rdonam)
                        .addGap(26, 26, 26)
                        .addComponent(rdonu)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnamsinh, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(52, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttenkh, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtnamsinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(rdonam)
                    .addComponent(rdonu))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Khách Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblkhachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Khách hàng", "Mã Khách Hàng", "Tên Khách hàng", "Địa chỉ", "SDT", "Email", "Giới Tính", "Năm Sinh"
            }
        ));
        tblkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkhachhangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblkhachhang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1123, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức Năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        btnadd.setText("Thêm Khách Hàng");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnupdate.setText("Sửa  Khách Hàng");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btnreset.setText("Tạo Mới");
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnadd)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnreset, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnupdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(11, 11, 11))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnadd)
                .addGap(18, 18, 18)
                .addComponent(btnupdate)
                .addGap(18, 18, 18)
                .addComponent(btnreset)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản Phẩm Đã Mua", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        DanhSachMua.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Dép", "Tên Dép", "Đơn Giá", "Số Lượng", "Tổng Tiền"
            }
        ));
        jScrollPane3.setViewportView(DanhSachMua);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtTimKiem.setForeground(new java.awt.Color(204, 204, 204));
        txtTimKiem.setText("Tìm Kiếm Theo Mã/Tên/Địa Chỉ/Số Điện Thoại/Email");
        txtTimKiem.setBorder(null);
        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Tìm Kiếm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jLabel9)
                .addGap(26, 26, 26)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1157, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 646, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblkhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkhachhangMouseClicked
        // TODO add your handling code here:
        int index = tblkhachhang.getSelectedRow();
        ShowData(index);
        this.KhachHangBought();
    }//GEN-LAST:event_tblkhachhangMouseClicked

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btnresetActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        int row = tblkhachhang.getSelectedRow();
        int colum = 0;
        if (row >= 0) {
            int ma = (int) tblkhachhang.getValueAt(row, colum);
            if (checkUpdate()) {
                KhachHang kh = readForm();
                boolean update = service.updateKH(kh, ma);
                if (update == true) {
                    list = service.getAll();
                    fillTable(list);
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thất bại!");
                }
            }
        }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        // TODO add your handling code here:
        if (checkKhachHang()) {
            KhachHang kh = readForm();
            boolean add = service.addKH(kh);
            if (add == true) {
                list = service.getAll();
                fillTable(list);
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!");
                return;
            }
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        String tim = txtTimKiem.getText();
        if (service.timKiem(tim)!= null) {
           fillTable(service.timKiem(tim));
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked
        // TODO add your handling code here:
        txtTimKiem.setText("");
    }//GEN-LAST:event_txtTimKiemMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DanhSachMua;
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnreset;
    private javax.swing.JButton btnupdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JRadioButton rdonam;
    private javax.swing.JRadioButton rdonu;
    private javax.swing.JTable tblkhachhang;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtnamsinh;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txttenkh;
    // End of variables declaration//GEN-END:variables
}
