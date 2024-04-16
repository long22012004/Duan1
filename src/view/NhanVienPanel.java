/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Model.NhanVien;
import Model.TaiKhoan;
import Model.chucvu;
import Service.Nhanvienservice;
import Service.chucvuservice;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class NhanVienPanel extends javax.swing.JPanel {

    DefaultTableModel model = new DefaultTableModel();
    private final Nhanvienservice nvsv = new Nhanvienservice();
    private List<NhanVien> lst = new ArrayList<>();
    private DefaultComboBoxModel modelCV = new DefaultComboBoxModel();
    private chucvuservice cvsv = new chucvuservice();
    private List<chucvu> listCV = new ArrayList<>();
    private DefaultComboBoxModel modelTK = new DefaultComboBoxModel();
    private List<TaiKhoan> listTK = new ArrayList<>();
    private TaiKhoanservice tksv = new TaiKhoanservice();

    public NhanVienPanel() {
        initComponents();
        model = (DefaultTableModel) tblnhanvien.getModel();
        lst = nvsv.getAllnhanvien();
        loadDataToTable(lst);
        modelCV = (DefaultComboBoxModel) cbbCV.getModel();
        listCV = cvsv.getAllchucvu();
        showChucVu(listCV);
        modelTK = (DefaultComboBoxModel) cbbTK.getModel();
        listTK = tksv.getAllTaiKhoan();
        showTaiKhoan(listTK);
    }

    void showChucVu(List<chucvu> list) {
        modelCV.removeAllElements();
        cbbCV.addItem("Chức Vụ");
        for (chucvu cv : list) {
            modelCV.addElement(cv.getTencv());
        }
    }

    void showTaiKhoan(List<TaiKhoan> list) {
        modelTK.removeAllElements();
        cbbTK.addItem("Tài Khoản");
        for (TaiKhoan tk : list) {
            modelTK.addElement(tk.getUserName());
        }
    }

    private void loadDataToTable(List<NhanVien> lstt) {
        model.setRowCount(0);
        for (NhanVien nv : lstt) {
            NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            model.addRow(new Object[]{
                nv.getIdnv(),
                nv.getManv(),
                nv.getTennv(),
                nv.getDiachi(),
                nv.getNamsinh(),
                nv.getSdt(),
                nv.getEmail(),
                nv.isGioitinh() ? "Nam" : "Nữ",
                format.format(nv.getLuong()),
                nv.getTenCV(),
                nv.getTenTK(),
                nv.isTrangthai() ? "Đang làm" : "Nghĩ Làm"});
        }

    }

    private void showDetail(int vt) {
        NhanVien nv = lst.get(vt);
        lbID.setText(nv.getIdnv() + "");
        txtmanv.setText(nv.getManv());
        txtten.setText(nv.getTennv());

        txtdiachi.setText(nv.getDiachi());
        txtnamsinh.setText(nv.getNamsinh() + "");
        txtsdt.setText(nv.getSdt() + "");
        txtemail.setText(nv.getEmail());

        if (nv.isGioitinh() == true) {
            rdonam.setSelected(true);
        } else {
            rdonu.setSelected(true);
        }
        txtluong.setText(nv.getLuong() + "");
        cbbCV.setSelectedItem(nv.getTenCV());
        cbbTK.setSelectedItem(nv.getTenTK());
//        cbostatus.setSelectedItem(tblnhanvien.getValueAt(index, 11).toString());
        if (nv.isTrangthai() == true) {
            rdolam.setSelected(true);
        } else {
            rdonghi.setSelected(true);
        }

    }

    private NhanVien getNhanVienFromInput() {
        NhanVien nv = new NhanVien();
        nv.setManv(txtmanv.getText());
        nv.setTennv(txtten.getText());
        nv.setDiachi(txtdiachi.getText());
        nv.setSdt(txtsdt.getText());
        nv.setEmail(txtemail.getText());
        nv.setTrangthai(rdolam.isSelected());
        nv.setGioitinh(rdonam.isSelected());
        nv.setLuong(Float.parseFloat(txtluong.getText()));
        nv.setNamsinh(Integer.parseInt(txtnamsinh.getText()));
        nv.setIdchucvu(cbbCV.getSelectedIndex());
        nv.setIdtaikhoan(cbbTK.getSelectedIndex());
        return nv;
    }

    boolean check() {
        String ten = txtten.getText();
        String email = txtemail.getText();
        String diaChi = txtdiachi.getText();
        String sdt = txtsdt.getText();
        String ma = txtmanv.getText();
        Pattern patternEmail = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z]+(.[a-zA-Z]+){2}$");
        Pattern patternSDT = Pattern.compile("^(0|\\+84)([0-9]{9})$");
        ArrayList<NhanVien> listsp = (ArrayList<NhanVien>) nvsv.getAllnhanvien();
        int listSize = listsp.size();
        for (int i = 0; i < listSize; i++) {
            if (listsp.get(i).getManv().equalsIgnoreCase(ma)) {
                JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại!");
                return false;
            }
        }
        if (ma.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống!");
            return false;
        } else {
            if (ten.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên Nhân Viên Không Được Trống!");
                return false;
            } else {
                if (email.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Email Nhân Viên Không Được Để Trống!");
                    return false;
                } else {
                    if (!patternEmail.matcher(email).find()) {
                        JOptionPane.showMessageDialog(this, "Không Đúng Định Dạng Email!");
                        return false;
                    } else {
                        if (diaChi.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Địa Chỉ Nhân Viên Không Được Trống!");
                            return false;
                        } else {
                            if (sdt.trim().isEmpty()) {
                                JOptionPane.showMessageDialog(this, "SĐT Nhân Viên Không Được Chống!");
                                return false;
                            } else {
                                if (!patternSDT.matcher(sdt).find()) {
                                    JOptionPane.showMessageDialog(this, "Không Đúng Định Dạng SĐT!");
                                    return false;
                                } else {
                                    if (txtluong.getText().trim().isEmpty()) {
                                        JOptionPane.showMessageDialog(this, "Luong Không được để trống!");
                                        return false;
                                    } else {
                                        try {
                                            Float luong = Float.parseFloat(txtluong.getText());
                                            if (luong < 0 && luong > 10000000) {
                                                JOptionPane.showMessageDialog(this, "Luong chi tu 0 den 10000000!");
                                                return false;
                                            }
                                        } catch (Exception e) {
                                            JOptionPane.showMessageDialog(this, "Luong phai la so!");
                                        }
                                    }
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
                                        if (cbbCV.getSelectedIndex() == 0) {
                                            JOptionPane.showMessageDialog(this, "Ban chua chon chuc vu!");
                                            return false;
                                        } else {
                                            if (cbbTK.getSelectedIndex() == 0) {
                                                JOptionPane.showMessageDialog(this, "Ban chua chon tai khoan!");
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

    boolean checkUpdate() {
        String ten = txtten.getText();
        String email = txtemail.getText();
        String diaChi = txtdiachi.getText();
        String sdt = txtsdt.getText();
        String ma = txtmanv.getText();
        Pattern patternEmail = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z]+(.[a-zA-Z]+){2}$");
        Pattern patternSDT = Pattern.compile("^(0|\\+84)([0-9]{9})$");
        if (ma.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống!");
            return false;
        } else {
            if (ten.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên Nhân Viên Không Được Trống!");
                return false;
            } else {
                if (email.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Email Nhân Viên Không Được Để Trống!");
                    return false;
                } else {
                    if (!patternEmail.matcher(email).find()) {
                        JOptionPane.showMessageDialog(this, "Không Đúng Định Dạng Email!");
                        return false;
                    } else {
                        if (diaChi.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(this, "Địa Chỉ Nhân Viên Không Được Trống!");
                            return false;
                        } else {
                            if (sdt.trim().isEmpty()) {
                                JOptionPane.showMessageDialog(this, "SĐT Nhân Viên Không Được Chống!");
                                return false;
                            } else {
                                if (!patternSDT.matcher(sdt).find()) {
                                    JOptionPane.showMessageDialog(this, "Không Đúng Định Dạng SĐT!");
                                    return false;
                                } else {
                                    if (txtluong.getText().trim().isEmpty()) {
                                        JOptionPane.showMessageDialog(this, "Luong Không được để trống!");
                                        return false;
                                    } else {
                                        try {
                                            Float luong = Float.parseFloat(txtluong.getText());
                                            if (luong < 0 && luong > 10000000) {
                                                JOptionPane.showMessageDialog(this, "Luong chi tu 0 den 10000000!");
                                                return false;
                                            }
                                        } catch (Exception e) {
                                            JOptionPane.showMessageDialog(this, "Luong phai la so!");
                                        }
                                    }
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
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupGender = new javax.swing.ButtonGroup();
        btnGroupOffice = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        txtten = new javax.swing.JTextField();
        txtdiachi = new javax.swing.JTextField();
        txtsdt = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        rdonam = new javax.swing.JRadioButton();
        rdonu = new javax.swing.JRadioButton();
        txtnamsinh = new javax.swing.JTextField();
        txtluong = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtmanv = new javax.swing.JTextField();
        rdolam = new javax.swing.JRadioButton();
        rdonghi = new javax.swing.JRadioButton();
        cbbCV = new javax.swing.JComboBox<>();
        cbbTK = new javax.swing.JComboBox<>();
        lbID = new javax.swing.JLabel();
        QLChucVu = new javax.swing.JLabel();
        QLTaiKhoan = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        btnadd1 = new javax.swing.JButton();
        btnupdate1 = new javax.swing.JButton();
        btndelete1 = new javax.swing.JButton();
        btnclear1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblnhanvien = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1418, 731));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel1.setText("IDNV");

        jLabel12.setText("Tên NV");

        jLabel13.setText("Địa chỉ");

        jLabel14.setText("SĐT");

        jLabel15.setText("Email");

        jLabel16.setText("Trạng thái");

        jLabel17.setText("Giới tính");

        jLabel18.setText("Lương");

        jLabel19.setText("Năm sinh");

        jLabel20.setText("Chức Vụ");

        jLabel21.setText("Tài Khoản");

        rdonam.setSelected(true);
        rdonam.setText("Nam");

        rdonu.setText("Nữ");
        rdonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdonuActionPerformed(evt);
            }
        });

        jLabel2.setText("Mã NV");

        buttonGroup1.add(rdolam);
        rdolam.setSelected(true);
        rdolam.setText("Đang làm");

        buttonGroup1.add(rdonghi);
        rdonghi.setText("Đã Nghỉ");

        cbbCV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbTK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbID.setText(" ");

        QLChucVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-24 (1).png"))); // NOI18N
        QLChucVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLChucVuMouseClicked(evt);
            }
        });

        QLTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-24 (1).png"))); // NOI18N
        QLTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLTaiKhoanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(25, 25, 25)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtten, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(lbID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(197, 197, 197)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(txtmanv))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(rdolam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rdonghi)))
                        .addGap(197, 197, 197)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtnamsinh)
                                    .addComponent(cbbCV, 0, 170, Short.MAX_VALUE)
                                    .addComponent(cbbTK, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtluong, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(rdonam, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdonu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(QLChucVu)
                    .addComponent(QLTaiKhoan))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(QLTaiKhoan)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(txtmanv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbID))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel17)
                            .addComponent(rdonam)
                            .addComponent(rdonu))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel18)
                            .addComponent(txtluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel19)
                            .addComponent(txtnamsinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15)
                                .addComponent(jLabel20)
                                .addComponent(cbbCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(QLChucVu))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel21)
                            .addComponent(rdolam)
                            .addComponent(rdonghi)
                            .addComponent(cbbTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        btnadd1.setText("Thêm Nhân Viên");
        btnadd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnadd1ActionPerformed(evt);
            }
        });

        btnupdate1.setText("Sửa Nhân Viên");
        btnupdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdate1ActionPerformed(evt);
            }
        });

        btndelete1.setText("Nghĩ Làm");
        btndelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelete1ActionPerformed(evt);
            }
        });

        btnclear1.setText("Tạo Mới");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnclear1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btndelete1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnadd1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnupdate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnadd1)
                .addGap(18, 18, 18)
                .addComponent(btnupdate1)
                .addGap(18, 18, 18)
                .addComponent(btndelete1)
                .addGap(18, 18, 18)
                .addComponent(btnclear1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblnhanvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID NV", "Mã NV", "Tên NV", "Địa chỉ", "Năm sinh", "SĐT", "Email", "Giới tính", "Lương", "Chức vụ", "Tài Khoản", "Trạng Thái"
            }
        ));
        tblnhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnhanvienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblnhanvien);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1125, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnadd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnadd1ActionPerformed
        // TODO add your handling code here:
        if (check()) {
            NhanVien nv = getNhanVienFromInput();
            boolean add = nvsv.addnhanvien(nv);
            if (add == true) {
                lst = nvsv.getAllnhanvien();
                loadDataToTable(lst);
                JOptionPane.showMessageDialog(this, "Them thanh cong");
            } else {
                JOptionPane.showMessageDialog(this, "Them that bai");
            }
        }
    }//GEN-LAST:event_btnadd1ActionPerformed

    private void btnupdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdate1ActionPerformed
        // TODO add your handling code here:
        int row = tblnhanvien.getSelectedRow();
        int colum = 0;
        if (row >= 0) {
            int ma = (int) tblnhanvien.getValueAt(row, colum);
            if (checkUpdate()) {
                NhanVien d = getNhanVienFromInput();
                if (d != null) {
                    boolean update = nvsv.updatenhanvien(d, ma);
                    if (update == true) {
                        lst = nvsv.getAllnhanvien();
                        loadDataToTable(lst);
                        JOptionPane.showMessageDialog(this, "cap nhat thanh cong");
                    } else {
                        JOptionPane.showMessageDialog(this, "Cap nhat that bai");
                        return;
                    }
                }
            }
        }
    }//GEN-LAST:event_btnupdate1ActionPerformed

    private void btndelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelete1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btndelete1ActionPerformed

    private void tblnhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnhanvienMouseClicked
        // TODO add your handling code here:
        int row = tblnhanvien.getSelectedRow();
        showDetail(row);
    }//GEN-LAST:event_tblnhanvienMouseClicked

    private void rdonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdonuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdonuActionPerformed

    private void QLChucVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLChucVuMouseClicked
        // TODO add your handling code here:
        ChucVuView cv = new ChucVuView();
        cv.setVisible(true);
    }//GEN-LAST:event_QLChucVuMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
         String input = txtTimKiem.getText();
        if (nvsv.timKiem(input)!=null) {
            loadDataToTable(nvsv.timKiem(input));
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void QLTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLTaiKhoanMouseClicked
        // TODO add your handling code here:
        TaiKhoan1View tk = new TaiKhoan1View();
        tk.setVisible(true);
    }//GEN-LAST:event_QLTaiKhoanMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel QLChucVu;
    private javax.swing.JLabel QLTaiKhoan;
    private javax.swing.ButtonGroup btnGroupGender;
    private javax.swing.ButtonGroup btnGroupOffice;
    private javax.swing.JButton btnadd1;
    private javax.swing.JButton btnclear1;
    private javax.swing.JButton btndelete1;
    private javax.swing.JButton btnupdate1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbCV;
    private javax.swing.JComboBox<String> cbbTK;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbID;
    private javax.swing.JRadioButton rdolam;
    private javax.swing.JRadioButton rdonam;
    private javax.swing.JRadioButton rdonghi;
    private javax.swing.JRadioButton rdonu;
    private javax.swing.JTable tblnhanvien;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtluong;
    private javax.swing.JTextField txtmanv;
    private javax.swing.JTextField txtnamsinh;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txtten;
    // End of variables declaration//GEN-END:variables
}
