/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.BanDAO;
import dao.HoaDonDAO;
import dao.LoaiThucPhamDAO;
import dao.MainDAO;
import dao.ThucPhamDAO;
import dto.BanDTO;
import dto.ChiTietHDDTO;
import dto.HoaDonDTO;
import dto.MainDTO;
import dto.ThucPhamDTO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Administrator
 */
public class FrmMain extends javax.swing.JFrame {

    private int vt = 0;
    DefaultTableModel model;
    DefaultTableModel model1;
    static String MaBan;
    static Boolean TTB;
    static Boolean TinhTrangHD;
    String TenTP = "";
    public static String ThanhToan = "";
    public static int Tien = 0;
    public static float Giamgia = 0;
    public static float Phi = 0;
    public static String chuyengop = "";
    public static String chuyen = "";
    ArrayList<ThucPhamDTO> list2;
    public String nvString="";

    /**
     * Creates new form FrmMain
     */
    public FrmMain() {
        initComponents(); 
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.lbuser.setText(DlgLogin.nvien);
        model = (DefaultTableModel) tbCTHD.getModel();
        model1 = (DefaultTableModel) tbTP.getModel();
        ArrayList<String> list1 = new LoaiThucPhamDAO().readTenLTP();
        for (String s : list1) {
            cbLTP1.addItem(s);
        }
        cbLTP1.addItem("Toàn bộ");

        jPa.setVisible(true);
        showTable();
        TableColumnModel cModel = tbCTHD.getColumnModel();
        cModel.getColumn(0).setPreferredWidth(100);
        cModel.getColumn(1).setPreferredWidth(10);
        TableColumnModel cModel1 = tbTP.getColumnModel();
        cModel1.getColumn(0).setPreferredWidth(105);
        cModel1.getColumn(1).setPreferredWidth(40);
    }

    public void showTable() {
        BanDAO dao = new BanDAO();
        ArrayList<BanDTO> list = dao.readByAll();

        for (BanDTO dto : list) {
            JButton bt = new JButton();
            //bt.setSize(BanDAO.weight, BanDAO.hight);
            ImageIcon icon = new ImageIcon(getClass().getResource("/icon/223.png"));
            bt.setIcon(icon);
            bt.setText(dto.getMaBan());
            int i;
            if (dto.isTinhTrang()) {
                i = 1;
            } else {
                i = 0;
            }
            switch (i) {
                case 1:
                    bt.setBackground(Color.ORANGE);
                    break;
                default:
                    bt.setBackground(Color.LIGHT_GRAY);
                    break;
            }
            bt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MaBan = dto.getMaBan();
                    TTB = dto.isTinhTrang();
                    lbBan.setText(MaBan);
                    if (TTB) {
                        lbTT.setText("Đang phục vụ");
                        lbBan.setText(MaBan);
                    } else {
                        lbBan.setText(MaBan);
                        lbTT.setText("Trống");
                    }
                    lbTien.setText("");
                    lbT.setText("");
                    lbThanhToan.setText("");
                    TienGiam.setValue((Integer) 0);
                    TienDV.setValue((Integer) 0);
                    if (TTB) {
                        bangTT(TTB);
                    }
                    clearTable();
                    if (TTB) {
                        showList(dto.getMaBan());
                    }
                }
            });
            jPa.add(bt);
        }

    }

    public void bangTT(boolean TTB) {
        Tien = new MainDAO().readTongTien(MaBan);
        lbTien.setText("" + Tien);
        int MaHD = new MainDAO().readMaHD(MaBan);
        lbT.setText(new MainDAO().ngayVao(MaHD));
        Giamgia = (float) (new MainDAO().khoanPhi("Giảm giá") * Tien * 0.01);
        Phi = (float) (new MainDAO().khoanPhi("Phí DV") * Tien * 0.01);
        TienDV.setValue((float) Phi);
        TienGiam.setValue((float) Giamgia);
        ThanhToan = "" + (Tien - Giamgia + Phi);
        lbThanhToan.setText("" + ThanhToan);
    }

    public void settbTP() {
        for (ThucPhamDTO dto : list2) {
            model1.addRow(new Object[]{
                dto.getTenTP(), dto.getDonvt(), (int) dto.getGia()});
        }
        tbTP.updateUI();
    }

    public void showList(String MaBan) {
        MainDAO dAO = new MainDAO();
        ArrayList<MainDTO> list = dAO.readCTHD(MaBan);
        for (int j = 0; j < list.size(); j++) {
            if (TenTP.equals(list.get(j).getTenTP())) {
                vt = j;
            }
        }
        for (MainDTO dto : list) {
            model.addRow(new Object[]{dto.getTenTP(), dto.getSoluong(), dto.getDonvt(), dto.getGia(), dto.getThanhtien()});
        }
        tbCTHD.updateUI();
    }

    public void clearTable() {
        int dong = model.getRowCount();
        for (int i = dong; i > 0; i--) {
            model.removeRow(i - 1);
        }
    }

    public void updateList(String MaBan) {
        clearTable();
        showList(MaBan);
    }

    public void clearTable1() {
        int dong = model1.getRowCount();
        for (int i = dong; i > 0; i--) {
            model1.removeRow(i - 1);
        }
    }

    public void updateList1() {
        clearTable1();
        settbTP();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbCTHD = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TienDV = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        TienGiam = new javax.swing.JSpinner();
        jButton6 = new javax.swing.JButton();
        lbThanhToan = new javax.swing.JLabel();
        lbTien = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btUp = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnDel1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        spSL = new javax.swing.JSpinner();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lbtinhtrang = new javax.swing.JLabel();
        lbBan = new javax.swing.JLabel();
        lbTT = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbuser = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbT = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cbLTP1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtTK = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbTP = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPa = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Admin = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUẢN LÝ NHÀ HÀNG OWL");

        tbCTHD.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tbCTHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên món", "SL", "Đơn vị", "Đơn giá", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbCTHD.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tbCTHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCTHDMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbCTHDMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tbCTHD);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Phí dịch vụ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel2.setText("Tổng tiền");

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel3.setText("Giảm giá");

        TienDV.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel4.setText("Tổng thanh toán :");

        TienGiam.setEnabled(false);

        jButton6.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 0, 0));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/printer.png"))); // NOI18N
        jButton6.setText("Thanh toán");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        lbThanhToan.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lbThanhToan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbTien.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lbTien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TienDV)
                    .addComponent(lbTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TienGiam)
                    .addComponent(lbThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(lbTien, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(TienDV, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(lbThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btUp.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        btUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/rewind.png"))); // NOI18N
        btUp.setText("Thêm món");
        btUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpActionPerformed(evt);
            }
        });

        btnDel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/fast-forward.png"))); // NOI18N
        btnDel.setText("Giảm món");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        btnDel1.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        btnDel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete (1).png"))); // NOI18N
        btnDel1.setText("Xóa món");
        btnDel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDel1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel11.setText("Số lượng ");

        spSL.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        spSL.setModel(new javax.swing.SpinnerNumberModel(1, 1, 50, 1));
        spSL.setToolTipText("");
        spSL.setMaximumSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spSL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spSL, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnDel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDel)
                        .addComponent(btUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btUp, btnDel, btnDel1});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnDel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(btUp)
                .addGap(33, 33, 33)
                .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btUp, btnDel, btnDel1});

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setForeground(new java.awt.Color(255, 51, 51));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/h.png"))); // NOI18N
        jLabel5.setText("BÀN ");

        lbtinhtrang.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbtinhtrang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/clipboard.png"))); // NOI18N
        lbtinhtrang.setText("Tình trạng :");

        lbBan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbBan.setForeground(new java.awt.Color(255, 51, 51));

        lbTT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbTT.setForeground(new java.awt.Color(255, 51, 51));

        jLabel13.setBackground(new java.awt.Color(255, 255, 204));
        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/123.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/login (1).png"))); // NOI18N
        jLabel16.setText("Nhân Viên : ");

        lbuser.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lbuser.setForeground(new java.awt.Color(255, 51, 51));

        jLabel18.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/resume (1).png"))); // NOI18N
        jLabel18.setText("Ngày giờ đến : ");

        lbT.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        lbT.setForeground(new java.awt.Color(255, 51, 51));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 3, 22)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 51));
        jLabel6.setText("NHÀ HÀNG OWL");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbtinhtrang, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbBan, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(lbT, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbTT, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addGap(29, 29, 29)
                        .addComponent(lbuser, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbBan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbT, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbtinhtrang, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(lbTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbuser, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        cbLTP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLTP1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        jLabel8.setText("Tìm kiếm");

        txtTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTKActionPerformed(evt);
            }
        });
        txtTK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTKKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/food.png"))); // NOI18N
        jLabel7.setText("Loại TP");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbLTP1, 0, 117, Short.MAX_VALUE)
                    .addComponent(txtTK))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLTP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbTP.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tbTP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên", "Đơn vị", "Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbTP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTPMouseClicked(evt);
            }
        });
        tbTP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTPKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbTP);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton4.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/covered-food-tray-on-a-hand-of-hotel-room-service.png"))); // NOI18N
        jButton4.setText("Mở Bàn");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete (1).png"))); // NOI18N
        jButton7.setText("Hủy Bàn");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus.png"))); // NOI18N
        jButton3.setText("Gộp bàn");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/exchange.png"))); // NOI18N
        jButton2.setText("Chuyển bàn");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(42, 42, 42)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton2, jButton3, jButton4, jButton7});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton2))
                .addGap(529, 529, 529))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton2, jButton3, jButton4, jButton7});

        jPa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPa.setPreferredSize(new java.awt.Dimension(229, 0));
        jPa.setLayout(new java.awt.GridLayout(5, 0, 0, 2));

        jMenuBar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuBar1MouseClicked(evt);
            }
        });

        Admin.setText("QUẢN LÝ");
        Admin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AdminMouseClicked(evt);
            }
        });
        jMenuBar1.add(Admin);

        jMenu2.setText("ĐĂNG XUẤT");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPa, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPa, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(7, 7, 7)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbCTHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCTHDMouseClicked
        int i = tbCTHD.getSelectedRow();
        TenTP = "" + tbCTHD.getValueAt(i, 0);
    }//GEN-LAST:event_tbCTHDMouseClicked

    private void tbCTHDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCTHDMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbCTHDMouseEntered

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (!TTB) {
            JOptionPane.showMessageDialog(null, "Bàn này trống");
        } else if (JOptionPane.showConfirmDialog(null, "Bạn muốn thanh toán", "Thông báo", 2) == 0) {
            FrmBill bil = new FrmBill();
            bil.setVisible(true);
            int MaHD = new MainDAO().readMaHD(MaBan);
            if (new MainDAO().updateKhoanphi(MaHD, Phi, Giamgia)) {
                if (new BanDAO().updateTT(false, MaBan) && new HoaDonDAO().updateTT(true, MaHD)) {
                    jPa.removeAll();
                    showTable();
                    jPa.updateUI();
                    tbTP.updateUI();
                    updateList(MaBan);
                }
            }

        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpActionPerformed
        if (!new MainDAO().checkTTB(MaBan)) {
            //kiem tra ban da đc mở hay chưa
            if (JOptionPane.showConfirmDialog(null, "Bàn này chưa đc mở, bạn muốn mở bàn này không ", "Thong bao", 2) == 0) {
                //chưa đc mở thì mở bàn trc khi thêm món ăn
                if (new MainDAO().moBan(false, MaBan)) {
                }
            }
        }
        if (JOptionPane.showConfirmDialog(null, "ban chắc chắn muốn thêm món ", "Thông báo", 2) == 0) {
            int Soluong = (Integer) spSL.getValue();
            spSL.setValue((Integer) 1);
            new MainDAO().goiMon(TenTP, MaBan, Soluong);
            updateList(MaBan);
            tbCTHD.setRowSelectionInterval(vt, vt);
            jPa.removeAll();
            showTable();
            jPa.updateUI();
            bangTT(true);
        } else {
            jPa.removeAll();
            showTable();
            lbTT.setText("Đang phục vụ");
            TTB = true;
            lbT.setText(new MainDAO().ngayVao(new MainDAO().readMaHD(MaBan)));
            jPa.updateUI();
        }
    }//GEN-LAST:event_btUpActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Bạn muốn giảm số lượng món này", "Thông báo", 2) == 0) {
            int Soluong = (Integer) spSL.getValue();
            new MainDAO().giamMon(TenTP, Soluong, MaBan);
            updateList(MaBan);
            tbCTHD.setRowSelectionInterval(vt, vt);
            jPa.removeAll();
            showTable();
            jPa.updateUI();
            bangTT(true);
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnDel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDel1ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa món này", "Thông báo", 2) == 0) {
            new MainDAO().xoaMon(TenTP, MaBan);
            updateList(MaBan);
            bangTT(true);
        }
    }//GEN-LAST:event_btnDel1ActionPerformed

    private void cbLTP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLTP1ActionPerformed
        String MaLTP = "" + cbLTP1.getSelectedItem().toString();
        clearTable1();
        if (MaLTP.equals("Toàn bộ")) {
            list2 = new ThucPhamDAO().readByAll();
            updateList1();
        } else {
            list2 = new ThucPhamDAO().readAllName(MaLTP);
            updateList1();
        }
    }//GEN-LAST:event_cbLTP1ActionPerformed

    private void txtTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTKActionPerformed
        // TODO add your handling code here:
//        clearTable1();
//        tbTP.updateUI();
//        String TenLTP = "" + cbLTP1.getSelectedItem();
//        String MaLTP = new LoaiThucPhamDAO().readMaLTP(TenLTP);
//        ArrayList<ThucPhamDTO> list = new ThucPhamDAO().tkRieng(txtTK.getText(), MaLTP);
//        for (ThucPhamDTO dto : list) {
//            model1.addRow(new Object[]{
//                dto.getTenTP(), dto.getDonvt(), (int) dto.getGia()});
//        }
//        tbTP.updateUI();
//        updateList(MaBan);
    }//GEN-LAST:event_txtTKActionPerformed

    private void tbTPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTPMouseClicked
        // TODO add your handling code here:
        int i = tbTP.getSelectedRow();
        TenTP = "" + tbTP.getValueAt(i, 0);
    }//GEN-LAST:event_tbTPMouseClicked

    private void tbTPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTPKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tbTPKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Bạn muốn mở bàn ", "Thông báo", 2) == 0) {
            boolean TTB1 = new MainDAO().checkTTB(MaBan);
            if (new MainDAO().moBan(TTB1, MaBan)) {
                jPa.removeAll();
                showTable();
                lbTT.setText("Đang phục vụ");
                TTB = true;
                lbT.setText(new MainDAO().ngayVao(new MainDAO().readMaHD(MaBan)));
                jPa.updateUI();
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Bạn muốn xóa bàn", "Thông báo", 2) == 0) {
            int i = new MainDAO().readMaHD(MaBan);
            HoaDonDTO dto = new HoaDonDTO();
            ChiTietHDDTO dt = new ChiTietHDDTO();
            dto.setMaHD(i);
            dt.setMaHD(i);
            if (new MainDAO().xoaBAn(dt, dto, MaBan)) {
                //cập nhật
                jPa.removeAll();
                showTable();
                updateList(MaBan);
                lbTien.setText("");
                lbThanhToan.setText("");
                lbT.setText("");
                TTB = false;
                lbTT.setText("Trống");
                jPa.updateUI();
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        chuyengop = "g";
        DlgChuyenGop dl = new DlgChuyenGop(this, true);
        dl.setVisible(true);
        if (FrmMain.chuyen.equals("ok")) {
            int MaHDCu = new MainDAO().readMaHD(MaBan);
            String MaBanMoi = "" + dl.s;
            int MaHDMoi = new MainDAO().readMaHD(MaBanMoi);
            if (!TTB) {
                JOptionPane.showMessageDialog(null, "Bàn chưa được mở");
            } else if (MaBanMoi.equals(MaBan)) {
                JOptionPane.showMessageDialog(null, "Bàn chọn đã bị trùng");
            } else {
                if (!new MainDAO().checkTTB(MaBanMoi)) {
                    JOptionPane.showMessageDialog(null, "Bàn bạn muốn gộp chưa được mở, vui lòng chọn chuyển bàn");
                } else if (new MainDAO().thaydoi(MaBanMoi, MaHDCu, MaHDMoi)) {
                    if (new BanDAO().updateTT(false, MaBan)) {
                        jPa.removeAll();
                        showTable();
                        MaBan = MaBanMoi;
                        updateList(MaBan);
                        lbBan.setText(MaBan);
                        bangTT(true);
                        jPa.updateUI();

                    }
                }
            }
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        chuyengop = "chuyen";
        DlgChuyenGop dl = new DlgChuyenGop(this, true);
        dl.setVisible(true);
        if (FrmMain.chuyen.equals("ok")) {
            String MaBanMoi = "" + dl.s;
            if (!TTB) {
                JOptionPane.showMessageDialog(null, "Bàn này vẫn chưa được mở, không chuyển bàn được");
            }
            if (new MainDAO().checkTTB(MaBanMoi)) {
                JOptionPane.showMessageDialog(null, "Bàn bạn muốn chuyển đang phục vụ, hãy chọn bàn khác");
                //mo ban
            } else if (new BanDAO().updateTT(true, MaBanMoi)) {
                //chuyen hon don
                int MaHD = new MainDAO().readMaHD(MaBan);
                if (new MainDAO().chuyenBan(MaBanMoi, MaHD)) {
                    //cap nhat lai
                    if (new BanDAO().updateTT(false, MaBan)) {
                        jPa.removeAll();
                        showTable();
                        updateList(MaBan);
                        lbTien.setText("");
                        lbThanhToan.setText("");
                        jPa.updateUI();
                        MaBan = MaBanMoi;
                        showList(MaBan);
                        lbBan.setText(MaBan);
                        bangTT(true);
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtTKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTKKeyPressed
        clearTable1();
        tbTP.updateUI();
        String TenLTP = "" + cbLTP1.getSelectedItem();
        String MaLTP = new LoaiThucPhamDAO().readMaLTP(TenLTP);
        ArrayList<ThucPhamDTO> list = new ThucPhamDAO().tkRieng(txtTK.getText(), MaLTP);
        for (ThucPhamDTO dto : list) {
            model1.addRow(new Object[]{
                dto.getTenTP(), dto.getDonvt(), (int) dto.getGia()});
        }
        tbTP.updateUI();
        updateList(MaBan);
    }//GEN-LAST:event_txtTKKeyPressed

    private void AdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdminMouseClicked
        // TODO add your handling code here:
        if (!DlgLogin.nvien.equals("Admin")) {
            Admin.setEnabled(false);
            FrmAdmin frm = new FrmAdmin();
            frm.setVisible(false);

        }else{
        FrmAdmin frm = new FrmAdmin();
        frm.setVisible(true);
        }
    }//GEN-LAST:event_AdminMouseClicked

    private void jMenuBar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar1MouseClicked
     
    }//GEN-LAST:event_jMenuBar1MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(null, "Bạn muốn đăng xuất", "Thông báo", 2)==0){
           this.setVisible(false);
           DlgLogin dl= new DlgLogin(this, true);
           dl.setVisible(true);
        }
    }//GEN-LAST:event_jMenu2MouseClicked

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
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Admin;
    private javax.swing.JSpinner TienDV;
    private javax.swing.JSpinner TienGiam;
    private javax.swing.JButton btUp;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnDel1;
    private javax.swing.JComboBox<String> cbLTP1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbBan;
    private javax.swing.JLabel lbT;
    private javax.swing.JLabel lbTT;
    private javax.swing.JLabel lbThanhToan;
    private javax.swing.JLabel lbTien;
    private javax.swing.JLabel lbtinhtrang;
    private javax.swing.JLabel lbuser;
    private javax.swing.JSpinner spSL;
    private javax.swing.JTable tbCTHD;
    private javax.swing.JTable tbTP;
    private javax.swing.JTextField txtTK;
    // End of variables declaration//GEN-END:variables
}
