/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Model.ChatLieu;
import Model.Dep;
import Model.DepChiTiet;
import Model.KichThuoc;
import Model.KieuDang;
import Model.MauSac;
import Model.NhaCungCap;
import Model.XuatXu;
import Service.ChatLieuService;
import Service.DepChiTietInterface;
import Service.DepChiTietService;
import Service.DepInterface;
import Service.DepService;
import Service.KichThuocService;
import Service.KieuDangService;
import Service.MauSacService;
import Service.NhaCungCapService;
import Service.XuatXuService;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DepPanel extends javax.swing.JPanel {

    private DefaultTableModel modelThuocTinh = new DefaultTableModel();
    private DefaultComboBoxModel modelXuatXu = new DefaultComboBoxModel();
    private DefaultComboBoxModel modelChatLieu = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel modelKieuDang = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel modelNhaCungCap = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel modelLocTen = new DefaultComboBoxModel();
    private DefaultComboBoxModel modelLocTrangThai = new DefaultComboBoxModel();
    private DefaultComboBoxModel modelLocXuatXu = new DefaultComboBoxModel();
    private DefaultTableModel modelSanPhamBouth = new DefaultTableModel();
    private List<Dep> listDep = new ArrayList<>();
    private List<XuatXu> listXX = new ArrayList<>();
    private final DepInterface dsv = new DepService();
    private ChatLieuService clsv = new ChatLieuService();
    private NhaCungCapService nccsv = new NhaCungCapService();
    private KieuDangService kdsv = new KieuDangService();
    private XuatXuService xxsv = new XuatXuService();
    private List<ChatLieu> listCL = new ArrayList<>();
    private List<NhaCungCap> listNCC = new ArrayList<>();
    private List<KieuDang> listKD = new ArrayList<>();
    DefaultTableModel tableModel = new DefaultTableModel();
    DefaultTableModel tableModelChiTiet = new DefaultTableModel();
    private DefaultComboBoxModel modelKichThuoc = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel modelMauSac = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel modelSanPham = new DefaultComboBoxModel();
    private List<KichThuoc> listKT = new ArrayList<>();
    private MauSacService mssv = new MauSacService();
    private KichThuocService ktsv = new KichThuocService();
    private List<MauSac> listMS = new ArrayList<>();
    private List<DepChiTiet> listDepChiTiet = new ArrayList<>();
    private final DepChiTietInterface dctsv = new DepChiTietService();
    NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

    public DepPanel() {
        initComponents();
        tableModel = (DefaultTableModel) htDanhSachSP.getModel();
        modelSanPhamBouth = (DefaultTableModel) htSoLuongSanPham.getModel();
        cbbTenChatLieu.setModel(modelChatLieu);
        listCL = clsv.getAll();
        showChatlieu(listCL);
        cbbTenNhaCungCap.setModel(modelNhaCungCap);
        listNCC = nccsv.getAll();
        showNhaCungCap(listNCC);
        cbbTenKieuDang.setModel(modelKieuDang);
        listKD = kdsv.getAll();
        showKieuDang(listKD);
        cbbTenXuatXu.setModel(modelXuatXu);
        listXX = xxsv.getAll();
        showXuatXu(listXX);
        listDep = dsv.getAll();
        showDataDep(listDep);
        tableModelChiTiet = (DefaultTableModel) htChiTietSanPham.getModel();
        cbbTenKichThuoc.setModel(modelKichThuoc);
        listKT = ktsv.getAll();
        showKichThuoc(listKT);
        cbbTenMauSac.setModel(modelMauSac);
        listMS = mssv.getAll();
        showMauSac(listMS);
        cbbTenSanPham.setModel(modelSanPham);
        listDep = dsv.getTen();
        showDep(listDep);
        listDepChiTiet = dctsv.getAll();
        showDataDepChiTiet(listDepChiTiet);
    }

    void showDataDep(List<Dep> listt) {
        tableModel.setRowCount(0);
        for (Dep d : listt) {
            tableModel.addRow(new Object[]{d.getId(), d.getMaSP(), d.getTenSP(), d.getTenQuocGia(), d.getTenNhaCungCap(), d.getTenChatLieu(), d.getTenKieuDang(), dsv.countSoLuong(d.getId()), d.isTrangThai() ? "Đang Kinh Doanh" : "Ngừng Kinh Doanh"});
        }
    }

    void showDepLoc(List<Dep> list) {
        modelLocTen.removeAllElements();
        for (Dep d : list) {
            modelLocTen.addElement(d.getTenSP());
        }
    }

    void showChatlieu(List<ChatLieu> list) {
        modelChatLieu.removeAllElements();
        cbbTenChatLieu.addItem("Chất Liệu");
        for (ChatLieu cl : list) {
            modelChatLieu.addElement(cl.getTenChatLieu());
        }
    }

    void showNhaCungCap(List<NhaCungCap> list) {
        modelNhaCungCap.removeAllElements();
        cbbTenNhaCungCap.addItem("Nhà Cung Cấp");
        for (NhaCungCap ncc : list) {
            modelNhaCungCap.addElement(ncc.getTenNhaCungCap());
        }
    }

    void showXuatXu(List<XuatXu> list) {
        modelXuatXu.removeAllElements();
        cbbTenXuatXu.addItem("Xuất Xứ");
        for (XuatXu xx : list) {
            modelXuatXu.addElement(xx.getQuocGia());
        }
    }

    void showKieuDang(List<KieuDang> list) {
        modelKieuDang.removeAllElements();
        cbbTenKieuDang.addItem("Kiểu Dáng");
        for (KieuDang kd : list) {
            modelKieuDang.addElement(kd.getTenKieuDang());
        }
    }

    void showDetaiDep(int viTri) {
        Dep d = dsv.getAll().get(viTri);
        txtMaSP.setText(d.getMaSP());
        txtTenSP.setText(d.getTenSP());
        cbbTenChatLieu.setSelectedItem(d.getTenChatLieu());
        cbbTenKieuDang.setSelectedItem(d.getTenKieuDang());
        cbbTenNhaCungCap.setSelectedItem(d.getTenNhaCungCap());
        cbbTenXuatXu.setSelectedItem(d.getTenQuocGia());
        if (d.isTrangThai() == true) {
            rdKinhDoanh.setSelected(true);
        } else {
            rdNgungKinhDoanh.setSelected(true);
        }

        lbIDSP.setText(d.getId() + "");
    }

    private Dep getDep() {
        Dep d = new Dep();
        d.setMaSP((txtMaSP.getText()));
        d.setTenSP(txtTenSP.getText());
        d.setId_ChatLieu(cbbTenChatLieu.getSelectedIndex());
        d.setId_KieuDang(cbbTenKieuDang.getSelectedIndex());
        d.setId_NhaCungCap(cbbTenNhaCungCap.getSelectedIndex());
        d.setId_XuatXu(cbbTenXuatXu.getSelectedIndex());
        if (rdKinhDoanh.isSelected() == true) {
            d.setTrangThai(true);
        } else {
            d.setTrangThai(false);
        }
        return d;
    }

    private boolean checkDep() {
        if (txtMaSP.getText().trim().isEmpty() || txtTenSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Dữ liệu không được để trống");
            return false;
        }

        ArrayList<Dep> listsp = (ArrayList<Dep>) dsv.getAll();
        int listSize = listsp.size();
        for (int i = 0; i < listSize; i++) {
            if (listsp.get(i).getMaSP().equalsIgnoreCase(txtMaSP.getText())) {
                JOptionPane.showMessageDialog(this, "Ma nay da ton tai truoc do");
                return false;
            }
             if (listsp.get(i).getTenSP().equalsIgnoreCase(txtTenSP.getText())) {
                JOptionPane.showMessageDialog(this, "Ten nay da ton tai truoc do");
                return false;
            }
        }
        return true;
    }

    private boolean checkUpdate() {
        if (txtMaSP.getText().trim().isEmpty() || txtTenSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Dữ liệu không được để trống");
            return false;
        }

        return true;
    }

    void clearDep() {
        lbIDSP.setText("");
        txtMaSP.setText("");
        txtTenSP.setText("");
        cbbTenChatLieu.setSelectedIndex(0);
        cbbTenXuatXu.setSelectedIndex(0);
        cbbTenKieuDang.setSelectedIndex(0);
        cbbTenNhaCungCap.setSelectedIndex(0);
        rdKinhDoanh.setSelected(true);
    }

    void clearDepChiTiet() {
        cbbTenSanPham.setSelectedIndex(0);
        cbbTenKichThuoc.setSelectedIndex(0);
        cbbTenMauSac.setSelectedIndex(0);
        txtGiaBanCT.setText("");
        txtSoLuongCT.setText("");
        rdKinhDoanh.setSelected(true);
    }

    private void SanPhamBouth() {
        modelSanPhamBouth.setRowCount(0);
        int selected = htDanhSachSP.getSelectedRow();
        int i = 0;
        if (selected >= 0) {
            int id = (int) htDanhSachSP.getValueAt(selected, 0);
            List<Object[]> listoj = dsv.DepBouth(id);
            for (Object[] row : listoj) {
                modelSanPhamBouth.addRow(new Object[]{row[0], row[1], row[2],row[3], format.format(row[4])});
            }
        }
    }

    void showDataDepChiTiet(List<DepChiTiet> listct) {
        tableModelChiTiet.setRowCount(0);
        for (DepChiTiet dct : listct) {
            tableModelChiTiet.addRow(new Object[]{dct.getiD_DepChiTiet(), dct.getMa_Dep(), dct.getTenDep(),
                dct.getTenMauSac(), dct.getTenKichThuoc(), dct.getMoTa(), dct.getGiaBan(), dct.getSoLuong()
            });
        }
    }

    void showDep(List<Dep> list) {
        modelSanPham.removeAllElements();
        cbbTenSanPham.addItem("Sản Phẩm");
        for (Dep d : list) {
            modelSanPham.addElement(d.getTenSP());
        }
    }

    void showKichThuoc(List<KichThuoc> listt) {
        modelKichThuoc.removeAllElements();
        cbbTenKichThuoc.addItem("Kích Thước");
        for (KichThuoc kt : listt) {
            modelKichThuoc.addElement(kt.getSize());
        }
    }

    void showMauSac(List<MauSac> list) {
        modelMauSac.removeAllElements();
        cbbTenMauSac.addItem("Màu Sắc");
        for (MauSac ms : list) {
            modelMauSac.addElement(ms.getTenMauSac());
        }
    }

    void showDetaiDepChiTiet(int vitri1) {
        DepChiTiet dct = listDepChiTiet.get(vitri1);
        cbbTenSanPham.setSelectedItem(dct.getTenDep());
        cbbTenKichThuoc.setSelectedItem(dct.getTenKichThuoc());
        cbbTenMauSac.setSelectedItem(dct.getTenMauSac());
        txtMoTa.setText(dct.getMoTa());
        txtGiaBanCT.setText(dct.getGiaBan() + "");
        txtSoLuongCT.setText(dct.getSoLuong() + "");
    }

    private DepChiTiet getDepChiTiet() {
        DepChiTiet dct = new DepChiTiet();
        dct.setId_Dep(cbbTenSanPham.getSelectedIndex());
        dct.setId_Kichthuoc(cbbTenKichThuoc.getSelectedIndex());
        dct.setId_mauSac(cbbTenMauSac.getSelectedIndex());
        dct.setSoLuong(Integer.parseInt(txtSoLuongCT.getText()));
        dct.setGiaBan(Float.parseFloat(txtGiaBanCT.getText()));
        dct.setMoTa(txtMoTa.getText());
        return dct;
    }

    private boolean checkDepChiTieṭ̣̣̣̣̣̣() {
        if (cbbTenSanPham.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Ban chua chon san pham!");
            return false;

        } else {
            if (cbbTenMauSac.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Ban chua chon mau sac!");
                return false;
            } else {
                if (txtGiaBanCT.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Gia ban khong duoc de trong!");
                    return false;
                } else {
                    if (txtSoLuongCT.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "So luong khong duoc de trong!");
                        return false;
                    } else {
                        if (cbbTenKichThuoc.getSelectedIndex() == 0) {
                            JOptionPane.showMessageDialog(this, "Ban chua chon kich thuoc!");
                            return false;
                        } else {
                            if (txtMoTa.getText().trim().isEmpty()) {
                                JOptionPane.showMessageDialog(this, "Mo ta khong duoc de trong!");
                                return false;
                            } else {
                                if (cbbTenSanPham.getSelectedIndex() == 0) {
                                    JOptionPane.showMessageDialog(this, "Ban chua chon san pham!");
                                    return false;
                                } else {
                                    if (cbbTenMauSac.getSelectedIndex() == 0) {
                                        JOptionPane.showMessageDialog(this, "Ban chua chon mau sac!");
                                        return false;
                                    } else {
                                        if (cbbTenKichThuoc.getSelectedIndex() == 0) {
                                            JOptionPane.showMessageDialog(this, "Ban chua chon size!");
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

    public boolean checkThuocTinh() {
        if (txtTenTab3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long nhap ten thuoc tinh!");
            return false;
        }
        return true;
    }

    void showDataXuatXu(int index) {
        XuatXu xx = xxsv.getAll().get(index);
        txtMaTab3.setText(xx.getiD_XuatXu() + "");
        txtTenTab3.setText(xx.getQuocGia());
    }

    void showDataChatLieu(int index) {
        ChatLieu chatLieu = clsv.getAll().get(index);
        txtMaTab3.setText(chatLieu.getId_ChatLieu() + "");
        txtTenTab3.setText(chatLieu.getTenChatLieu());
    }

    void showDataMauSac(int index) {
        MauSac ms = mssv.getAll().get(index);
        txtMaTab3.setText(ms.getiD_MauSac() + "");
        txtTenTab3.setText(ms.getTenMauSac());
    }

    void showDataKichThuoc(int index) {
        KichThuoc kt = ktsv.getAll().get(index);
        txtMaTab3.setText(kt.getiD_KichThuoc() + "");
        txtTenTab3.setText(kt.getSize());
    }

    void showDataKieuDang(int index) {
        KieuDang kd = kdsv.getAll().get(index);
        txtMaTab3.setText(kd.getiD_KieuDang() + "");
        txtTenTab3.setText(kd.getTenKieuDang());
    }

    void showDataNhaCungCap(int index) {
        NhaCungCap ncc = nccsv.getAll().get(index);
        txtMaTab3.setText(ncc.getiD_NhaCungCap() + "");
        txtTenTab3.setText(ncc.getTenNhaCungCap());
    }

    public void clearFormThuocTinh() {
        txtMaTab3.setText("");
        txtTenTab3.setText("");
    }

    void fillTableChatLieu(List<ChatLieu> listChatLieu) {
        modelThuocTinh = (DefaultTableModel) tblThuocTinh.getModel();
        modelThuocTinh.setRowCount(0);
        int i = 1;
        for (ChatLieu chatlieu : listChatLieu) {
            Object[] row = {i++, chatlieu.getId_ChatLieu(), chatlieu.getTenChatLieu()};
            modelThuocTinh.addRow(row);
        }
    }

    void fillTableMauSac(List<MauSac> listMauSac) {
        modelThuocTinh = (DefaultTableModel) tblThuocTinh.getModel();
        modelThuocTinh.setRowCount(0);
        int i = 1;
        for (MauSac ms : listMauSac) {
            Object[] row = {i++, ms.getiD_MauSac(), ms.getTenMauSac()};
            modelThuocTinh.addRow(row);
        }
    }

    void fillTableKieuDang(List<KieuDang> listKieuDang) {
        modelThuocTinh = (DefaultTableModel) tblThuocTinh.getModel();
        modelThuocTinh.setRowCount(0);
        int i = 1;
        for (KieuDang kd : listKieuDang) {
            Object[] row = {i++, kd.getiD_KieuDang(), kd.getTenKieuDang()};
            modelThuocTinh.addRow(row);
        }
    }

    void fillTableXuatXu(List<XuatXu> listXuatXu) {
        modelThuocTinh = (DefaultTableModel) tblThuocTinh.getModel();
        modelThuocTinh.setRowCount(0);
        int i = 1;
        for (XuatXu xx : listXuatXu) {
            Object[] row = {i++, xx.getiD_XuatXu(), xx.getQuocGia()};
            modelThuocTinh.addRow(row);
        }
    }

    void fillTableNhaCungCap(List<NhaCungCap> listNhaCungCap) {
        modelThuocTinh = (DefaultTableModel) tblThuocTinh.getModel();
        modelThuocTinh.setRowCount(0);
        int i = 1;
        for (NhaCungCap ncc : listNhaCungCap) {
            Object[] row = {i++, ncc.getiD_NhaCungCap(), ncc.getTenNhaCungCap()};
            modelThuocTinh.addRow(row);
        }
    }

    void fillTableKichThuoc(List<KichThuoc> lisKichThuoc) {
        modelThuocTinh = (DefaultTableModel) tblThuocTinh.getModel();
        modelThuocTinh.setRowCount(0);
        int i = 1;
        for (KichThuoc kt : listKT) {
            Object[] row = {i++, kt.getiD_KichThuoc(), kt.getSize()};
            modelThuocTinh.addRow(row);
        }
    }

    XuatXu readFormXuatXu() {
        XuatXu xx = new XuatXu();
        xx.setQuocGia(txtTenTab3.getText());
        return xx;
    }

    KichThuoc readFormKichThuoc() {
        KichThuoc kt = new KichThuoc();
        kt.setSize(txtTenTab3.getText());
        return kt;
    }

    MauSac readFormMauSac() {
        MauSac ms = new MauSac();
        ms.setTenMauSac(txtTenTab3.getText());
        return ms;
    }

    KieuDang readFormKieuDang() {
        KieuDang kd = new KieuDang();
        kd.setTenKieuDang(txtMaTab3.getText());
        return kd;
    }

    ChatLieu readFormChatLieu() {
        ChatLieu cl = new ChatLieu();
        cl.setTenChatLieu(txtTenTab3.getText());
        return cl;
    }

    NhaCungCap readFormNhaCungCap() {
        NhaCungCap ncc = new NhaCungCap();
        ncc.setTenNhaCungCap(txtTenTab3.getText());
        return ncc;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        rdKinhDoanh = new javax.swing.JRadioButton();
        rdNgungKinhDoanh = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cbbTenXuatXu = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        lbIDSP = new javax.swing.JLabel();
        cbbTenChatLieu = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbbTenNhaCungCap = new javax.swing.JComboBox<>();
        cbbTenKieuDang = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        htDanhSachSP = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        htSoLuongSanPham = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        txtTimKiemSP = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbbTenSanPham = new javax.swing.JComboBox<>();
        cbbTenMauSac = new javax.swing.JComboBox<>();
        cbbTenKichThuoc = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtSoLuongCT = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtGiaBanCT = new javax.swing.JTextField();
        QLMauSac = new javax.swing.JLabel();
        QLNhaCungCap = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        htChiTietSanPham = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        btnThemChiTiet1 = new javax.swing.JButton();
        txtSuaChiTiet1 = new javax.swing.JButton();
        btnClearCT1 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        txtMin = new javax.swing.JTextField();
        txtMax = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtMaTab3 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTenTab3 = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        rbxx = new javax.swing.JRadioButton();
        rbkd = new javax.swing.JRadioButton();
        rdncc = new javax.swing.JRadioButton();
        rdcl = new javax.swing.JRadioButton();
        rdms = new javax.swing.JRadioButton();
        rdSize = new javax.swing.JRadioButton();
        jPanel21 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblThuocTinh = new javax.swing.JTable();

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Danh sách thuộc tính");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel1.setText("Mã sản phẩm:");

        jLabel2.setText("Tên Sản Phẩm:");

        jLabel3.setText("Xuất Xứ:");

        jLabel23.setText("Trạng Thái:");

        buttonGroup1.add(rdKinhDoanh);
        rdKinhDoanh.setSelected(true);
        rdKinhDoanh.setText("Kinh Doanh");

        buttonGroup1.add(rdNgungKinhDoanh);
        rdNgungKinhDoanh.setText("Ngừng Kinh Doanh");

        jLabel16.setText("Chất Liệu:");

        jLabel17.setText("Kiểu Dáng:");

        jLabel12.setText("ID Sản Phẩm:");

        lbIDSP.setText(" ");

        cbbTenChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Nhà Cung Cấp:");

        cbbTenNhaCungCap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbTenKieuDang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-24 (1).png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-24 (1).png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-24 (1).png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-24 (1).png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(52, 52, 52))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(66, 66, 66))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(66, 66, 66)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(rdKinhDoanh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                                .addComponent(rdNgungKinhDoanh))
                            .addComponent(cbbTenNhaCungCap, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbTenChatLieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbTenXuatXu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenSP)
                            .addComponent(lbIDSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaSP)
                            .addComponent(cbbTenKieuDang, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(0, 1, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lbIDSP))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbTenXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(cbbTenChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17)
                                .addComponent(cbbTenKieuDang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbbTenNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(rdKinhDoanh)
                    .addComponent(rdNgungKinhDoanh)))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        htDanhSachSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Sản Phẩm", "Mã Sản Phẩm", "Tên Sản Phẩm", "Xuất Xứ", "Nhà Cung Cấp", "Chất Liệu", "Kiểu Dáng", "Số Lượng", "Trạng Thái"
            }
        ));
        htDanhSachSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                htDanhSachSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(htDanhSachSP);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức Năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        btnThem.setText("Thêm Sản Phẩm");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });

        btnSua.setText("Sửa Sản Phẩm");
        btnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaMouseClicked(evt);
            }
        });

        btnXoa.setText("Ngừng Kinh Doanh");
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
        });

        btnClear.setText("Tạo Mới");
        btnClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClearMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnClear, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSua)
                .addGap(18, 18, 18)
                .addComponent(btnXoa)
                .addGap(18, 18, 18)
                .addComponent(btnClear)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi Tiết Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        htSoLuongSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Tên Sản Phẩm", "Màu Sắc", "Kích Thước", "Số Lượng", "Đơn Giá"
            }
        ));
        jScrollPane2.setViewportView(htSoLuongSanPham);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm Kiếm ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        txtTimKiemSP.setForeground(new java.awt.Color(153, 153, 153));
        txtTimKiemSP.setText("Tìm kiếm");
        txtTimKiemSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemSPMouseClicked(evt);
            }
        });
        txtTimKiemSP.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtTimKiemSPInputMethodTextChanged(evt);
            }
        });
        txtTimKiemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemSPActionPerformed(evt);
            }
        });
        txtTimKiemSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemSPKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 208, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phảm", jPanel3);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi Tiết Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel5.setText("Tên Sản Phẩm");

        jLabel7.setText("Tên Màu Sắc:");

        jLabel8.setText("Size");

        jLabel15.setText("Mô Tả:");

        cbbTenMauSac.setToolTipText("");

        jLabel14.setText("Số Lượng:");

        jLabel18.setText("Giá Bán:");

        QLMauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-24 (1).png"))); // NOI18N
        QLMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLMauSacMouseClicked(evt);
            }
        });

        QLNhaCungCap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-add-24 (1).png"))); // NOI18N
        QLNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QLNhaCungCapMouseClicked(evt);
            }
        });

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane4.setViewportView(txtMoTa);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-update-24 (1).png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbTenMauSac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbTenKichThuoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbTenSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(QLNhaCungCap)
                            .addComponent(QLMauSac))
                        .addGap(69, 69, 69))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(44, 44, 44)))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSoLuongCT, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(txtGiaBanCT))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbbTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoLuongCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbbTenKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(QLNhaCungCap)
                                    .addComponent(jLabel7)
                                    .addComponent(cbbTenMauSac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(QLMauSac)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(62, 62, 62))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaBanCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(62, 62, 62))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))))))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản Lý Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        htChiTietSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Chi Tiết", "Mã SP", "Tên SP", "Màu Sắc", "Kích Thước", "Mô Tả", "Giá Bán", "Số Lượng"
            }
        ));
        htChiTietSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                htChiTietSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(htChiTietSanPham);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1121, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức Năng"));

        btnThemChiTiet1.setText("Thêm Sản Phẩm");
        btnThemChiTiet1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemChiTiet1MouseClicked(evt);
            }
        });

        txtSuaChiTiet1.setText("Sửa Sản Phẩm");
        txtSuaChiTiet1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSuaChiTiet1MouseClicked(evt);
            }
        });

        btnClearCT1.setText("Làm Mới");
        btnClearCT1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClearCT1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThemChiTiet1)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnClearCT1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSuaChiTiet1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThemChiTiet1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSuaChiTiet1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnClearCT1)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm Sản Phẩm Theo Giá"));

        txtMin.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Min", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        txtMax.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Max", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        btnTim.setText("Tìm Kiếm");
        btnTim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(txtMin, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(txtMax, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnTim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtMax, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMin, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(209, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm Chi Tiết", jPanel11);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Thuộc Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel19.setText("Mã thuộc tính");

        txtMaTab3.setEnabled(false);

        jLabel20.setText("Tên thuộc tính");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addGap(32, 32, 32)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaTab3)
                    .addComponent(txtTenTab3, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtMaTab3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtTenTab3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thuộc Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        buttonGroup1.add(rbxx);
        rbxx.setText("Xuất Xứ");
        rbxx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbxxMouseClicked(evt);
            }
        });

        buttonGroup1.add(rbkd);
        rbkd.setText("Kiểu Dáng");
        rbkd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbkdMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdncc);
        rdncc.setText("Nhà Cung Cấp");
        rdncc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdnccMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdcl);
        rdcl.setText("Chất liệu");
        rdcl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdclMouseClicked(evt);
            }
        });
        rdcl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdclActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdms);
        rdms.setText("Màu Sắc");
        rdms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdmsMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdSize);
        rdSize.setText("Size");
        rdSize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdSizeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbxx)
                    .addComponent(rbkd)
                    .addComponent(rdSize))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdncc)
                    .addComponent(rdcl)
                    .addComponent(rdms, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbxx)
                    .addComponent(rdncc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdcl)
                    .addComponent(rdSize))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbkd)
                    .addComponent(rdms))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức Năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setText("Làm mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                        .addGap(14, 14, 14))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh  Sách Thuộc Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã thuộc tính", "Tên thuộc tính"
            }
        ));
        tblThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblThuocTinhMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(tblThuocTinh);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(89, 89, 89)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 180, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thuộc Tính", jPanel16);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void htDanhSachSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_htDanhSachSPMouseClicked
        // TODO add your handling code here:
        int row = htDanhSachSP.getSelectedRow();
        showDetaiDep(row);
        SanPhamBouth();
    }//GEN-LAST:event_htDanhSachSPMouseClicked

    private void txtTimKiemSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemSPMouseClicked
        // TODO add your handling code here:
        txtTimKiemSP.setText("");
    }//GEN-LAST:event_txtTimKiemSPMouseClicked

    private void txtTimKiemSPInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtTimKiemSPInputMethodTextChanged
        // TODO add your handling code here

    }//GEN-LAST:event_txtTimKiemSPInputMethodTextChanged

    private void txtTimKiemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemSPActionPerformed

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        // TODO add your handling code here:
        boolean checkDelete = dsv.xoaDep(Integer.valueOf(htDanhSachSP.getValueAt(htDanhSachSP.getSelectedRow(), 0).toString()), 0);
        if (checkDelete == false) {
            JOptionPane.showMessageDialog(this, "Xóa Thất Bại");
        } else {
            rdNgungKinhDoanh.setSelected(true);
            listDep = dsv.getAll();
            showDataDep(listDep);
            JOptionPane.showMessageDialog(this, "Xóa Thành Công");
        }
    }//GEN-LAST:event_btnXoaMouseClicked

    private void btnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseClicked
        // TODO add your handling code here:
        int row = htDanhSachSP.getSelectedRow();
        int colum = 0;
        if (row >= 0) {
            int ma = (int) htDanhSachSP.getValueAt(row, colum);
            if (checkUpdate()) {
                Dep d = getDep();
                if (d != null) {
                    boolean update = dsv.update(d, ma);
                    if (update == true) {
                        listDep = dsv.getAll();
                        showDataDep(listDep);
                        JOptionPane.showMessageDialog(this, "cap nhat thanh cong");
                    } else {
                        JOptionPane.showMessageDialog(this, "Cap nhat that bai");
                        return;
                    }
                }
            }
        }
    }//GEN-LAST:event_btnSuaMouseClicked

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        // TODO add your handling code here:
        if (checkDep()) {
            Dep d = getDep();
            boolean addDep = dsv.addDep(d);
            if (addDep == true) {
                listDep = dsv.getAll();
                showDataDep(listDep);
                clearDep();
                JOptionPane.showMessageDialog(this, "Add sản phẩm thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Add that bai");
                return;
            }
        }
    }//GEN-LAST:event_btnThemMouseClicked

    private void btnClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseClicked
        // TODO add your handling code here:
        clearDep();
    }//GEN-LAST:event_btnClearMouseClicked

    private void btnThemChiTiet1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemChiTiet1MouseClicked
        // TODO add your handling code here:
        if (checkDepChiTieṭ̣̣̣̣̣̣()) {
            DepChiTiet dct = getDepChiTiet();
            boolean add = dctsv.addDepChiTiet(dct);
            if (add == true) {
                listDepChiTiet = dctsv.getAll();
                showDataDepChiTiet(listDepChiTiet);
                JOptionPane.showMessageDialog(this, "Them thanh cong");
            } else {
                JOptionPane.showMessageDialog(this, "Them that bai");
                return;
            }
        }
    }//GEN-LAST:event_btnThemChiTiet1MouseClicked

    private void txtSuaChiTiet1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSuaChiTiet1MouseClicked
        // TODO add your handling code here:
        int row = htChiTietSanPham.getSelectedRow();
        int colum = 0;
        if (row >= 0) {
            int ma = (int) htChiTietSanPham.getValueAt(row, colum);
            if (checkDepChiTieṭ̣̣̣̣̣̣()) {
                DepChiTiet dct = getDepChiTiet();
                boolean update = dctsv.sua(dct, ma);
                if (update == true) {
                    listDepChiTiet = dctsv.getAll();
                    showDataDepChiTiet(listDepChiTiet);
                    JOptionPane.showMessageDialog(this, "Sua thanh cong");
                } else {
                    JOptionPane.showMessageDialog(this, "Sua that bai!");
                    return;
                }
            }
        }
    }//GEN-LAST:event_txtSuaChiTiet1MouseClicked

    private void btnClearCT1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearCT1MouseClicked
        // TODO add your handling code here:
        clearDepChiTiet();
    }//GEN-LAST:event_btnClearCT1MouseClicked

    private void QLMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLMauSacMouseClicked
        // TODO add your handling code here:
       ViewKichThuoc kt = new ViewKichThuoc();
        kt.setVisible(true);
    }//GEN-LAST:event_QLMauSacMouseClicked

    private void QLNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QLNhaCungCapMouseClicked
        // TODO add your handling code here:
        ViewMauSac ms = new ViewMauSac();
        ms.setVisible(true);
    }//GEN-LAST:event_QLNhaCungCapMouseClicked

    private void htChiTietSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_htChiTietSanPhamMouseClicked
        // TODO add your handling code here:
        int row = htChiTietSanPham.getSelectedRow();
        showDetaiDepChiTiet(row);
    }//GEN-LAST:event_htChiTietSanPhamMouseClicked

    private void rbxxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbxxMouseClicked
        // TODO add your handling code here:
        if (rbxx.isSelected()) {
            fillTableXuatXu(xxsv.getAll());
        }
        clearFormThuocTinh();
    }//GEN-LAST:event_rbxxMouseClicked

    private void rbkdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbkdMouseClicked
        // TODO add your handling code here:
        if (rbkd.isSelected()) {
            fillTableKieuDang(kdsv.getAll());
        }
        clearFormThuocTinh();
    }//GEN-LAST:event_rbkdMouseClicked

    private void rdnccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdnccMouseClicked
        // TODO add your handling code here:
        if (rdncc.isSelected()) {
            fillTableNhaCungCap(nccsv.getAll());
        }
        clearFormThuocTinh();
    }//GEN-LAST:event_rdnccMouseClicked

    private void rdclMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdclMouseClicked
        // TODO add your handling code here:
        if (rdcl.isSelected()) {
            fillTableChatLieu(clsv.getAll());
        }
        clearFormThuocTinh();
    }//GEN-LAST:event_rdclMouseClicked

    private void rdclActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdclActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdclActionPerformed

    private void rdmsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdmsMouseClicked
        // TODO add your handling code here:
        if (rdms.isSelected()) {
            fillTableMauSac(mssv.getAll());
        }
        clearFormThuocTinh();
    }//GEN-LAST:event_rdmsMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (rbxx.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn  thêm thuộc tính xuất xứ không  ?");
            if (confirm == 0) {
                if (checkThuocTinh()) {
                    XuatXu xx = readFormXuatXu();
                    boolean add = xxsv.add(xx);
                    if (add == true) {
                        fillTableXuatXu(xxsv.getAll());
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                        clearFormThuocTinh();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm thất bại");
                    }

                }
            }
        }
        if (rdSize.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn  thêm thuộc tính Kích thước không ?");
            if (confirm == 0) {
                if (checkThuocTinh()) {
                    KichThuoc kt = readFormKichThuoc();
                    boolean add = ktsv.add(kt);
                    if (add == true) {
                        fillTableKichThuoc(ktsv.getAll());
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                        clearFormThuocTinh();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm thất bại");
                    }
                }
            }
        }
        if (rbkd.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn  thêm thuộc tính kiểu dáng không ?");
            if (confirm == 0) {
                if (checkThuocTinh()) {
                    KieuDang kd = readFormKieuDang();
                    boolean add = kdsv.add(kd);
                    if (add == true) {
                        fillTableKieuDang(kdsv.getAll());
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                        clearFormThuocTinh();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm thất bại");
                    }
                }
            }
        }
        if (rdncc.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm  thuộc tính nhà cung cấp không ?");
            if (confirm == 0) {
                if (checkThuocTinh()) {
                    NhaCungCap ncc = readFormNhaCungCap();
                    boolean add = nccsv.add(ncc);
                    if (add == true) {
                        fillTableNhaCungCap(nccsv.getAll());
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                        clearFormThuocTinh();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm thất bại");
                    }
                }
            }
        }
        if (rdcl.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn  thêm thuộc tính chất liệu không ?");
            if (confirm == 0) {
                if (checkThuocTinh()) {
                    ChatLieu cl = readFormChatLieu();
                    boolean add = clsv.add(cl);
                    if (add == true) {
                        fillTableChatLieu(clsv.getAll());
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                        clearFormThuocTinh();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm thất bại");
                    }
                }
            }
        }
        if (rdms.isSelected()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm thuộc tính màu sắc  không ?");
            if (confirm == 0) {
                if (checkThuocTinh()) {
                    MauSac ms = readFormMauSac();
                    boolean add = mssv.addMauSac(ms);
                    if (add == true) {
                        fillTableMauSac(mssv.getAll());
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                        clearFormThuocTinh();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm thất bại");
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        clearFormThuocTinh();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tblThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhMouseClicked
        if (rbxx.isSelected()) {
            int index = tblThuocTinh.getSelectedRow();
            showDataXuatXu(index);
        }
        if (rdcl.isSelected()) {
            int index = tblThuocTinh.getSelectedRow();
            showDataChatLieu(index);
        }
        if (rdncc.isSelected()) {
            int index = tblThuocTinh.getSelectedRow();
            showDataNhaCungCap(index);
        }
        if (rbkd.isSelected()) {
            int index = tblThuocTinh.getSelectedRow();
            showDataKieuDang(index);
        }
        if (rdSize.isSelected()) {
            int index = tblThuocTinh.getSelectedRow();
            showDataKichThuoc(index);
        }
        if (rdms.isSelected()) {
            int index = tblThuocTinh.getSelectedRow();
            showDataMauSac(index);
        }
    }//GEN-LAST:event_tblThuocTinhMouseClicked

    private void tblThuocTinhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblThuocTinhMousePressed

    private void txtTimKiemSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSPKeyReleased
        // TODO add your handling code here:
        String input = txtTimKiemSP.getText();
        if (dsv.timKiem(input)!=null) {
            showDataDep(dsv.timKiem(input));
        }
    }//GEN-LAST:event_txtTimKiemSPKeyReleased

    private void rdSizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdSizeMouseClicked
        // TODO add your handling code here:
        if (rdSize.isSelected()) {
            fillTableKichThuoc(ktsv.getAll());
        }
        clearFormThuocTinh();
    }//GEN-LAST:event_rdSizeMouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        ViewXuatXu  xx= new ViewXuatXu();
        xx.setVisible(true);
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        ViewChatLieu cl = new ViewChatLieu();
        cl.setVisible(true);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        ViewKieuDang kd = new ViewKieuDang();
        kd.setVisible(true);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        ViewNhaCungCap ncc = new ViewNhaCungCap();
        ncc.setVisible(true);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void btnTimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimMouseClicked
        // TODO add your handling code here:
        Float min = Float.parseFloat(txtMin.getText());
        Float max = Float.parseFloat(txtMax.getText());
        if(dctsv.tim(min, max)!=null){
            showDataDepChiTiet(dctsv.tim(min, max));
        }
        else{
            JOptionPane.showMessageDialog(this, "Khong cos san pham trong khoang gia nay!");
        }
    }//GEN-LAST:event_btnTimMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        listDep = dsv.getTen();
        showDep(listDep);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel QLMauSac;
    private javax.swing.JLabel QLNhaCungCap;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClearCT1;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemChiTiet1;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbTenChatLieu;
    private javax.swing.JComboBox<String> cbbTenKichThuoc;
    private javax.swing.JComboBox<String> cbbTenKieuDang;
    private javax.swing.JComboBox<String> cbbTenMauSac;
    private javax.swing.JComboBox<String> cbbTenNhaCungCap;
    private javax.swing.JComboBox<String> cbbTenSanPham;
    private javax.swing.JComboBox<String> cbbTenXuatXu;
    private javax.swing.JTable htChiTietSanPham;
    private javax.swing.JTable htDanhSachSP;
    private javax.swing.JTable htSoLuongSanPham;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbIDSP;
    private javax.swing.JRadioButton rbkd;
    private javax.swing.JRadioButton rbxx;
    private javax.swing.JRadioButton rdKinhDoanh;
    private javax.swing.JRadioButton rdNgungKinhDoanh;
    private javax.swing.JRadioButton rdSize;
    private javax.swing.JRadioButton rdcl;
    private javax.swing.JRadioButton rdms;
    private javax.swing.JRadioButton rdncc;
    private javax.swing.JTable tblThuocTinh;
    private javax.swing.JTextField txtGiaBanCT;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMaTab3;
    private javax.swing.JTextField txtMax;
    private javax.swing.JTextField txtMin;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSoLuongCT;
    private javax.swing.JButton txtSuaChiTiet1;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenTab3;
    private javax.swing.JTextField txtTimKiemSP;
    // End of variables declaration//GEN-END:variables

}
