/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Model.DepChiTiet;
import Model.GioHang;
import Model.HinhThucThanhToan;
import Model.HoaDon;
import Model.HoaDonChiTiet;
import Model.KhachHang;
import Model.modelChiTietSanPham;
import Model.modelGioHang;
import Service.ChiTietSanPhamRepoImpl;
import Service.DepChiTietService;
import Service.DepService;
import Service.HinhThucThanhToanSerVice;
import Service.HoaDonChiTietService;
import Service.HoaDon_Service;
import Service.KhachHang_Service;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.Auth;

/**
 *
 * @author Admin
 */
public class BanHangPanel extends javax.swing.JPanel {

    private static List<GioHang> listGH = new ArrayList<>();
    private DefaultComboBoxModel modelTrangThai = new DefaultComboBoxModel();
    //Call Model
    DefaultComboBoxModel modelKH = new DefaultComboBoxModel();
    DefaultTableModel DSSPModel = new DefaultTableModel();
    DefaultTableModel HoaDonModel = new DefaultTableModel();
    DefaultTableModel GioHangModel = new DefaultTableModel();
    DefaultComboBoxModel modelGiamGia = new DefaultComboBoxModel();
    //Call Service
    DepService spsv = new DepService();
    HoaDonChiTietService hdctsv = new HoaDonChiTietService();
    HoaDon_Service hdsv = new HoaDon_Service();
//    GioHangService hsv = new GioHangService();
    DepChiTietService ctspsv = new DepChiTietService();
    KhachHang_Service khsv = new KhachHang_Service();
//    HoaDonChiTietServiceImpl hdctsv = new HoaDonChiTietServiceImpl();
    //Call Repo
    ChiTietSanPhamRepoImpl ctspr = new ChiTietSanPhamRepoImpl();
    DefaultComboBoxModel cbbHTTTmodel = new DefaultComboBoxModel();
    List<HinhThucThanhToan> listHTTT = new ArrayList<>();
    HinhThucThanhToanSerVice htttsv = new HinhThucThanhToanSerVice();
    List<modelChiTietSanPham> listMCTSP = new ArrayList<>();
    List<KhachHang> listKH = new ArrayList<>();
    List<HoaDon> listHD = new ArrayList<>();
    NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

    public BanHangPanel() {
        initComponents();
        HoaDonModel = (DefaultTableModel) tblHoaDon.getModel();
        DSSPModel = (DefaultTableModel) tblSanPham.getModel();
        GioHangModel = (DefaultTableModel) tblGioHang.getModel();
        this.showDataHoaDon();
        cbbHTTTmodel = (DefaultComboBoxModel) cbbHinhThucThanhToan.getModel();
        listHTTT = htttsv.getAll();
        modelKH = (DefaultComboBoxModel) cbbKhachHang.getModel();
        listKH = khsv.getAll();
        this.showKH(listKH);
        this.showHTTT(listHTTT);
        listMCTSP = ctspr.getBanHang();
        this.showDataDSSP(listMCTSP);
    }

    void showHTTT(List<HinhThucThanhToan> listhttt) {
        cbbHTTTmodel.removeAllElements();
        cbbHTTTmodel.addElement("Hình Thức Thanh Toán");
        for (HinhThucThanhToan httt : listhttt) {
            cbbHTTTmodel.addElement(httt.getHinhThucThanhToan());
        }
    }

    private void createHoaDon() {
        HoaDon hd = new HoaDon();
        String maHD = this.gennerateMa();
        hd.setMaHoaDon(maHD);
        hd.setIdNhanVien(Auth.idNhanVien());
        hd.setTrangThai("Chờ Thanh Toán");
        hd.setNgayTao(this.getToDay());
        boolean createHD = hdsv.createBill(hd);
        if (createHD == true) {
            this.showDataHoaDon();
            JOptionPane.showMessageDialog(this, "Tạo Hóa Đơn Thành Công!");
        }
    }

    void showKH(List<KhachHang> listkh) {
        modelKH.removeAllElements();
        modelKH.addElement("Khách Hàng");
        for (KhachHang kh : listkh) {
            modelKH.addElement(kh.getTenkh());
        }
    }

    private void showDataHoaDon() {
        HoaDonModel.setRowCount(0);
        int i = 0;
        for (Object[] row : hdsv.DanhSachHoaDon()) {
            i++;
            HoaDonModel.addRow(new Object[]{i, row[0], row[1], row[2], row[3]});
        }
    }

    private String gennerateMa() {
        HoaDon hd = hdsv.selectTop1DESC();
        String maHDSub = hd.getMaHoaDon().substring(2);
        Integer soMaHD = Integer.parseInt(maHDSub);
        String maHDFinal = "HD" + (soMaHD + 01);
        return maHDFinal;
    }

    private LocalDate getToDay() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        String ngay = "";
        if (month < 9 && date < 10) {
            ngay = (year + "-" + "0" + (month + 1) + "-" + "0" + date);
        } else if (month < 9 && date >= 10) {
            ngay = (year + "-" + "0" + (month + 1) + "-" + date);
        } else if (month >= 9 && date < 10) {
            ngay = (year + "-" + (month + 1) + "-" + "0" + date);
        } else if (month >= 9 && date >= 10) {
            ngay = (year + "-" + (month + 1) + "-" + date);
        }
        LocalDate ngay1 = LocalDate.parse(ngay);
        return ngay1;
    }

    private void showDataDSSP(List<modelChiTietSanPham> listttChiTietSanPhams) {
        DSSPModel.setRowCount(0);
        for (modelChiTietSanPham mctsp : listttChiTietSanPhams) {
            Object[] row = {false, mctsp.getMaSp(), mctsp.getTenSanPham(),mctsp.getTenQuocGia(),   mctsp.getTenNhaCungCap(),mctsp.getTenChatLieu(), mctsp.getTenKieuDang(),
                mctsp.getTenMauSac(), mctsp.getKichThuoc(), mctsp.getDonGia(),
                mctsp.getSoLuong(), mctsp.getMoTa()};
            DSSPModel.addRow(row);
        }
    }

    //FillData Giỏ Hàng
    private void showDataGioHang(List<GioHang> list) {
        GioHangModel.setRowCount(0);
        int i = 0;
        for (GioHang mgh : list) {
            i++;
            GioHangModel.addRow(new Object[]{i, mgh.getMaSP(), mgh.getTenSP(), mgh.getSoLuong(), mgh.getThanhTien()});
        }
    }

    private void showDataGioHangByHD(List<GioHang> list, String hoaDon) {
        GioHangModel.setRowCount(0);
        int i = 0;
        for (GioHang gh1 : list) {
            if (hoaDon.equalsIgnoreCase(gh1.getMaHoaDon())) {
                i++;
                GioHangModel.addRow(new Object[]{i, gh1.getMaSP(), gh1.getTenSP(), gh1.getSoLuong(), gh1.getThanhTien()});
            }
        }
    }

    private GioHang getGH(Integer idCTSP, String maHD) {
        for (int i = 0; i < listGH.size(); i++) {
            GioHang gh1 = listGH.get(i);
            if (gh1.getIdCTSP() == idCTSP && gh1.getMaHoaDon() == maHD) {
                return gh1;
            }
        }
        return null;
    }

    void clearText() {
        txtGhiChu.setText("");
        txtTongTienHang.setText("");
        txtKhachTra.setText("");
        txtTienThua.setText("");
        cbbKhachHang.setSelectedIndex(0);
        cbbHinhThucThanhToan.setSelectedIndex(0);
    }

    private void tienKhachTra() throws ParseException {
        String tongTienHangBefore = txtTongTienHang.getText();
        Number tongTienHangCV = format.parse(tongTienHangBefore);
        Float tongTienHangAfter = tongTienHangCV.floatValue();
        Float khachTraTM = Float.parseFloat(txtKhachTra.getText());
        Float tienThua = khachTraTM - tongTienHangAfter;
        txtTienThua.setText(format.format(tienThua));
    }

    private void XoaSanPhamKhoiGioHang() {
        int selectedHD = tblHoaDon.getSelectedRow();
        if (selectedHD >= 0) {
            String maHD = (String) tblHoaDon.getValueAt(selectedHD, 1);
            String trangThaiHD = (String) tblHoaDon.getValueAt(selectedHD, 3);
            if (trangThaiHD.equalsIgnoreCase("Chờ Thanh Toán")) {
                int selected = tblGioHang.getSelectedRow();
                if (selected >= 0) {
                    GioHang gh = listGH.get(selected);
                    DepChiTiet ctsp = ctspsv.getCTSPByID(gh.getIdCTSP());
                    boolean updateSoLuong = ctspsv.updateSoLuongCTSP(ctsp.getSoLuong() + gh.getSoLuong(), ctsp.getiD_DepChiTiet());
                    if (updateSoLuong == true) {
                        listGH.remove(gh);

                        txtTongTienHang.setText("");

                        this.showDataGioHangByHD(listGH, maHD);
                        listMCTSP = ctspr.getBanHang();
                        this.showDataDSSP(listMCTSP);
                        JOptionPane.showMessageDialog(this, "Xóa Sản Phẩm Thành Công!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Sản Phẩm!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Hóa Đơn Này Đang Được Vận Chuyển Không Thể Xóa!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Hóa Đơn!");
        }

    }

    private void XoaAllSanPhamGioHang() {
        int selected = tblHoaDon.getSelectedRow();
        if (selected >= 0) {
            String maHD = (String) tblHoaDon.getValueAt(selected, 1);
            String trangThaiHD = (String) tblHoaDon.getValueAt(selected, 3);
            if (trangThaiHD.equalsIgnoreCase("Chờ Thanh Toán")) {
                Iterator<GioHang> itr = listGH.iterator();
                while (itr.hasNext()) {
                    GioHang gh = itr.next();
                    if (gh.getMaHoaDon().equals(maHD)) {
                        DepChiTiet ctsp = ctspsv.getCTSPByID(gh.getIdCTSP());
                        boolean updateSoLuong = ctspsv.updateSoLuongCTSP(ctsp.getSoLuong() + gh.getSoLuong(), ctsp.getiD_DepChiTiet());
                        if (updateSoLuong == true) {
                            itr.remove();
                        }
                    }
                }
                txtTongTienHang.setText("");
                this.showDataGioHangByHD(listGH, maHD);
                listMCTSP = ctspr.getBanHang();
                this.showDataDSSP(listMCTSP);
                JOptionPane.showMessageDialog(this, "Xóa Tất Cả Sản Phẩm Thành Công!");
            } else {
                JOptionPane.showMessageDialog(this, "Hóa Đơn Này Đang Được Vận Chuyển Không Thể Xóa!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Hóa Đơn!");
        }

    }

    private void pushSPToCart() {

        //code new
        int selectedHD = tblHoaDon.getSelectedRow();
        if (selectedHD >= 0) {
            String trangThai = (String) tblHoaDon.getValueAt(selectedHD, 3);
            String maHD = (String) tblHoaDon.getValueAt(selectedHD, 1);
            if (trangThai.equalsIgnoreCase("Chờ Thanh Toán")) {
                int selected = tblSanPham.getSelectedRow();
                if (selected >= 0) {
                    modelChiTietSanPham mctsp = listMCTSP.get(selected);
                    boolean choose = (boolean) tblSanPham.getValueAt(selected, 0);
                    if (choose == true) {
                        int confirm = JOptionPane.showConfirmDialog(this, "Bạn Muốn Chọn Sản Phẩm Này ?", "Xác Nhận", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            //Lấy id CTSP
                            DepChiTiet ctsp = ctspsv.getCTSPByID(mctsp.getIdCTSP());
                            //Nếu Sản Phẩm Đó Có Trong LIst r thì thực hiện update
                            GioHang gh = this.getGH(ctsp.getiD_DepChiTiet(), maHD);
                            if (gh != null) {
                                //Trùng
                                String soLuong = JOptionPane.showInputDialog("Mời Bạn Nhập Số Lượng:");
                                if (soLuong != null) {
                                    try {
                                        int soLuongMua = Integer.parseInt(soLuong);
                                        if (soLuongMua <= 0) {
                                            JOptionPane.showMessageDialog(this, "Số Lượng Mua Phải Lớn Hơn 0!");
                                        } else {
                                            if (soLuongMua > ctsp.getSoLuong()) {
                                                JOptionPane.showMessageDialog(this, "Số Lượng Kho Không Đủ!");
                                            } else {
                                                gh.setSoLuong(soLuongMua + gh.getSoLuong());
                                                Float tongTien = gh.getSoLuong() * ctsp.getGiaBan();
                                                gh.setThanhTien(tongTien);
                                                int soLuongCon = (ctsp.getSoLuong() - soLuongMua);
                                                boolean updateSoLuong = ctspsv.updateSoLuongCTSP(soLuongCon, ctsp.getiD_DepChiTiet());
                                                if (updateSoLuong == true) {
                                                    for (GioHang gh2 : listGH) {
                                                        Float tongTienHang = 0.0f;
                                                        if (gh2.getMaHoaDon().equalsIgnoreCase(maHD)) {
                                                            tongTienHang += gh2.getThanhTien();
                                                            txtTongTienHang.setText(format.format(tongTienHang));
                                                        }
                                                    }
                                                    this.showDataGioHangByHD(listGH, maHD);
                                                    listMCTSP = ctspr.getBanHang();
                                                    this.showDataDSSP(listMCTSP);
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(this, "Số Lượng Phải Là Chữ");
                                    }
                                }
                            } else {
                                //Không Trùng
                                String soLuong = JOptionPane.showInputDialog("Mời Bạn Nhập Số Lượng:");
                                if (soLuong != null) {
                                    try {
                                        int soLuongMua = Integer.parseInt(soLuong);
                                        if (soLuongMua <= 0) {
                                            JOptionPane.showMessageDialog(this, "Số Lượng Mua Phải Lớn Hơn 0!");
                                        } else {
                                            if (soLuongMua > ctsp.getSoLuong()) {
                                                JOptionPane.showMessageDialog(this, "Số Lượng Kho Không Đủ!");
                                            } else {
                                                GioHang gh1 = new GioHang();
                                                gh1.setIdCTSP(ctsp.getiD_DepChiTiet());
                                                gh1.setMaSP(mctsp.getMaSp());
                                                gh1.setTenSP(mctsp.getTenSanPham());
                                                gh1.setSoLuong(soLuongMua);
                                                gh1.setMaHoaDon(maHD);
                                                Float thanhTien = (ctsp.getGiaBan() * soLuongMua);
                                                gh1.setThanhTien(thanhTien);
                                                listGH.add(gh1);
                                                int soLuongCon = (ctsp.getSoLuong() - soLuongMua);
                                                boolean updateSoLuong = ctspsv.updateSoLuongCTSP(soLuongCon, ctsp.getiD_DepChiTiet());
                                                if (updateSoLuong == true) {
                                                    double tongTienHang = 0.0;
                                                    for (GioHang gh2 : listGH) {
                                                        if (gh2.getMaHoaDon().equalsIgnoreCase(maHD)) {
                                                            tongTienHang += gh2.getThanhTien();
                                                            txtTongTienHang.setText(format.format(tongTienHang));
                                                        }
                                                    }
                                                    this.showDataGioHangByHD(listGH, maHD);
                                                    listMCTSP = ctspr.getBanHang();
                                                    this.showDataDSSP(listMCTSP);
                                                }
                                            }
                                        }
                                    } catch (Exception e) {
                                        JOptionPane.showMessageDialog(this, "Số Lượng Phải Là Chữ");
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Hóa Đơn Này Không Thể Thêm SP");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Hóa Đơn");
        }
    }

    private void thanhToanBill() throws ParseException {
        String tienThuaBefore = txtTienThua.getText();
        if (cbbKhachHang.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Khách Hàng!");
        } else {
            if (txtTongTienHang.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn Chưa Chọn Sản Phẩm!");
            } else {
                if (cbbHinhThucThanhToan.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(this, "Ban chua chon hinh thuc thanh toan!");
                } else {
                    if (tienThuaBefore.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Chưa Trả Đủ Tiền!");
                    } else {
                        Number tienThuaCV = format.parse(tienThuaBefore);
                        Float tienThuaAfter = tienThuaCV.floatValue();
                        if (tienThuaAfter < 0) {
                            JOptionPane.showMessageDialog(this, "Chưa Trả Đủ Tiền!");
                        } else {
                            int selected = tblHoaDon.getSelectedRow();
                            if (selected >= 0) {
                                //Mã HĐ
                                String maHD = (String) tblHoaDon.getValueAt(selected, 1);
                                //Tìm Kiếm THeo
                                //COnvert
                                String tongTienHangBefore = txtTongTienHang.getText();
                                Number tongTienHangCV = format.parse(tongTienHangBefore);
                                Float tongTienHangAfter = tongTienHangCV.floatValue();
                                String khachTraTMBefore = txtKhachTra.getText();
                                Float khachTraTMAfter = Float.parseFloat(khachTraTMBefore);
                                String tienThuaBefore1 = txtTienThua.getText();
                                NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
                                Number tienThuaCV1 = numberFormat.parse(tienThuaBefore1);
                                Float tienThuaAfter1 = tienThuaCV1.floatValue();

                                //Hỏi Mã Giao Dịch
                                //Tạo 1 Hóa Đơn Để update
                                HoaDon hd = new HoaDon();
                                hd.setIdKhachHang(cbbKhachHang.getSelectedIndex());
                                hd.setIdHTTT(cbbHinhThucThanhToan.getSelectedIndex());
                                hd.setTongTienHang(tongTienHangAfter);
                                hd.setTienKhachDua(khachTraTMAfter);
                                hd.setTienThua(tienThuaAfter1);
                                hd.setNgayTao(getToDay());
                                hd.setIdNhanVien(Auth.idNhanVien());
                                hd.setTrangThai("Đã Thanh Toán");
                                hd.setGhiChu(txtGhiChu.getText());
                                boolean thanhToanHD = hdsv.ThanhToanHoaDon(hd, maHD);
                                if (thanhToanHD == true) {
                                    HoaDonChiTiet hdct = new HoaDonChiTiet();
                                    HoaDon hd1 = hdsv.getHoaDonByMa(maHD);
                                    Iterator<GioHang> itr = listGH.iterator();
                                    while (itr.hasNext()) {
                                        GioHang gh = itr.next();
                                        if (gh.getMaHoaDon().equals(maHD)) {
                                            hdct.setIdHoaDon(hd1.getIdHoaDon());
                                            hdct.setIdSPCT(gh.getIdCTSP());
                                            hdct.setSoLuong(gh.getSoLuong());
                                            boolean addHDCT = hdctsv.addHDCT(hdct);
                                            if (addHDCT == true) {
                                                itr.remove();
                                            }
                                        }

                                        JOptionPane.showMessageDialog(this, "Thanh Toán Thành Công!");
                                        this.showDataHoaDon();
                                        this.clearText();
                                        GioHangModel.setRowCount(0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void detailHoaDonSP(List<Object[]> listSP) {
        GioHangModel.setRowCount(0);
        int i = 0;
        for (Object[] row : listSP) {
            i++;
            GioHangModel.addRow(new Object[]{i, row[0], row[1], row[3], format.format(row[4])});
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

        btnGroupStatusOrder = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        panelCart = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        panelProduct = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        panelOrder = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        lblGiamGia = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        cbbHinhThucThanhToan = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        txtKhachTra = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTongTienHang = new javax.swing.JTextField();
        btnThanhToan2 = new javax.swing.JButton();
        btnThemNhanh = new javax.swing.JButton();
        cbbKhachHang = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1082, 615));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        panelCart.setBackground(new java.awt.Color(255, 255, 255));
        panelCart.setBorder(javax.swing.BorderFactory.createTitledBorder("Giỏ Hàng"));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Tổng Tiền"
            }
        ));
        jScrollPane2.setViewportView(tblGioHang);

        jButton4.setText("Xóa Sản Phẩm");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jButton5.setText("Xóa Tất Cả");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCartLayout = new javax.swing.GroupLayout(panelCart);
        panelCart.setLayout(panelCartLayout);
        panelCartLayout.setHorizontalGroup(
            panelCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCartLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(panelCartLayout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)))
                .addContainerGap())
        );
        panelCartLayout.setVerticalGroup(
            panelCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCartLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        panelProduct.setBackground(new java.awt.Color(255, 255, 255));
        panelProduct.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Sản Phẩm"));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Chọn", "Mã Sản Phẩm", "Tên Sản Phẩm", "Xuất Xứ", "Nhà Cung Cấp", "Chất Liệu", "Kiểu Dáng", "Màu Sắc", "Size", "Đơn Giá", "Số Lượng", "Mô Tả"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        txtTimKiem.setText("Tìm theo mã/tên");
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

        jLabel4.setText("Tìm kiếm");

        javax.swing.GroupLayout panelProductLayout = new javax.swing.GroupLayout(panelProduct);
        panelProduct.setLayout(panelProductLayout);
        panelProductLayout.setHorizontalGroup(
            panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(panelProductLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel4)
                .addGap(31, 31, 31)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(675, Short.MAX_VALUE))
        );
        panelProductLayout.setVerticalGroup(
            panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProductLayout.createSequentialGroup()
                .addGroup(panelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 955, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 663, Short.MAX_VALUE)
        );

        panelOrder.setBackground(new java.awt.Color(255, 255, 255));
        panelOrder.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa Đơn"));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Tên Nhân Viên", "Trạng Thái", "Ngày Tạo"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        jButton3.setText("Tạo Hóa Đơn");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelOrderLayout = new javax.swing.GroupLayout(panelOrder);
        panelOrder.setLayout(panelOrderLayout);
        panelOrderLayout.setHorizontalGroup(
            panelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOrderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        panelOrderLayout.setVerticalGroup(
            panelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOrderLayout.createSequentialGroup()
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi Tiết Hóa Đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        lblGiamGia.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblGiamGia.setForeground(new java.awt.Color(5, 56, 202));

        jLabel29.setText("Hình Thức Thanh Toán");

        cbbHinhThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbHinhThucThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbHinhThucThanhToanActionPerformed(evt);
            }
        });

        jLabel35.setText("Tiền Khách Đưa");

        txtKhachTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKhachTraActionPerformed(evt);
            }
        });
        txtKhachTra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKhachTraKeyReleased(evt);
            }
        });

        jLabel37.setText("Tiền Trả Lại");

        txtTienThua.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txtTienThua.setForeground(new java.awt.Color(193, 86, 196));

        jLabel39.setText("Ghi Chú Hóa Đơn");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));

        jLabel1.setText("Tông Tiền Hàng");

        btnThanhToan2.setBackground(new java.awt.Color(204, 204, 204));
        btnThanhToan2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThanhToan2.setText("Thanh Toán");
        btnThanhToan2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThanhToan2MouseClicked(evt);
            }
        });

        btnThemNhanh.setText("Thêm Nhanh");
        btnThemNhanh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNhanhMouseClicked(evt);
            }
        });

        cbbKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Khách Hàng");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-update-24 (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel37)
                            .addComponent(jLabel39)
                            .addComponent(jLabel1)
                            .addComponent(jLabel35)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtTongTienHang, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbbHinhThucThanhToan, javax.swing.GroupLayout.Alignment.LEADING, 0, 257, Short.MAX_VALUE))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(cbbKhachHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnThemNhanh)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addGap(387, 387, 387)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(btnThanhToan2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNhanh)
                    .addComponent(cbbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(lblGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbHinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addGap(25, 25, 25)))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTongTienHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addGap(25, 25, 25)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(25, 25, 25)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThanhToan2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panelProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelCart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 675, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        this.pushSPToCart();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int selected = tblHoaDon.getSelectedRow();
        if (selected >= 0) {
            String maHD = (String) tblHoaDon.getValueAt(selected, 1);
            String trangThaiHD = (String) tblHoaDon.getValueAt(selected, 3);
            if (trangThaiHD.equalsIgnoreCase("Đang Giao Hàng")) {
                Object[] list = hdsv.getChiTietLSHoaDon(maHD);
                List<Object[]> listSP = hdsv.getSanPhamLichSuHoaDon(maHD);
                this.detailHoaDonSP(listSP);
            } else {
                showDataGioHangByHD(listGH, maHD);
                double tongTienHang = 0.0;
                for (GioHang gh : listGH) {
                    if (gh.getMaHoaDon().equalsIgnoreCase(maHD)) {
                        tongTienHang += gh.getThanhTien();
                        txtTongTienHang.setText(format.format(tongTienHang));
                    }
                }
                txtGhiChu.setText("");
            }
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
//        this.grabFocus();
    }//GEN-LAST:event_formMouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        this.createHoaDon();
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.XoaAllSanPhamGioHang();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        this.XoaSanPhamKhoiGioHang();
    }//GEN-LAST:event_jButton4MouseClicked

    private void btnThanhToan2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThanhToan2MouseClicked

        try {
            // TODO add your handling code here:
            this.thanhToanBill();
        } catch (ParseException ex) {
            Logger.getLogger(BanHangPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnThanhToan2MouseClicked

    private void txtKhachTraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKhachTraKeyReleased

        try {
            // TODO add your handling code here:
            this.tienKhachTra();
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(BanHangPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtKhachTraKeyReleased

    private void txtKhachTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKhachTraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKhachTraActionPerformed

    private void cbbHinhThucThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbHinhThucThanhToanActionPerformed
        // TODO add your handling code here:
        //        this.chooseHTTT();
    }//GEN-LAST:event_cbbHinhThucThanhToanActionPerformed

    private void btnThemNhanhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNhanhMouseClicked
        // TODO add your handling code here:
        ThemNhanhKhachHang tnkh = new ThemNhanhKhachHang();
        tnkh.setVisible(true);
    }//GEN-LAST:event_btnThemNhanhMouseClicked

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked
        // TODO add your handling code here:
        txtTimKiem.setText("");
    }//GEN-LAST:event_txtTimKiemMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
        String tim = txtTimKiem.getText();
        if (ctspr.timKiem(tim) != null) {
            this.showDataDSSP(ctspr.timKiem(tim));
        }
    }//GEN-LAST:event_txtTimKiemKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupStatusOrder;
    private javax.swing.JButton btnThanhToan2;
    private javax.swing.JButton btnThemNhanh;
    private javax.swing.JComboBox<String> cbbHinhThucThanhToan;
    private javax.swing.JComboBox<String> cbbKhachHang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JPanel panelCart;
    private javax.swing.JPanel panelOrder;
    private javax.swing.JPanel panelProduct;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtKhachTra;
    private javax.swing.JLabel txtTienThua;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTienHang;
    // End of variables declaration//GEN-END:variables
}
