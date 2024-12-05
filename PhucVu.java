import java.util.ArrayList;
import java.util.Scanner;

public class PhucVu extends NhanVien {
    private ArrayList<String> DSBanPhuTrach;
    private static double LUONGCOBAN = 12;
    private static int soLuong = 0;

    public PhucVu() {
        // Tu dong goi constructor khong tham so
        DSBanPhuTrach = new ArrayList<>();
        soLuong++;
    }

    public PhucVu(String maNhanVien, String hoVaTen, String gioiTinh, int ngaySinh, int thangSinh, int namSinh, int ngayKiHopDong, int thangKiHopDong, int namKiHopDong, int ngayHetHopDong, int thangHetHopDong, int namHetHopDong, double gioCong) {
        super(maNhanVien, hoVaTen, gioiTinh, ngaySinh, thangSinh, namSinh, ngayKiHopDong, thangKiHopDong, namKiHopDong, ngayHetHopDong, thangHetHopDong, namHetHopDong, gioCong);
        soLuong++;
        DSBanPhuTrach = new ArrayList<>();
        String maBan = "BA" + super.getMaNhanVien().substring(2);
        this.DSBanPhuTrach.add(maBan);

    }

    // 1. Nhap
    @Override
    public void nhapThongTin(Scanner sc) {
        super.nhapThongTin(sc);
        int n = 0;    // Moi nhan vien phu trach 2 ban
//        String maBan;
//        while (n != 2) {
//            System.out.printf("Nhập mã bàn thứ %d của nhân viên phụ trách: \n", n + 1);  //n chay tu 0
//            maBan = sc.nextLine();
//            this.DSBanPhuTrach.add(maBan); // Constructor da khoi tao roi;
//            n++;
//        }
        String maBan = "BA" + super.getMaNhanVien().substring(2);
        this.DSBanPhuTrach.add(maBan);
    }

    // 2. Xuat
    @Override
    public void xuatThongTin() {
        super.xuatThongTin();
        System.out.println("Lương cơ bản: " + PhucVu.LUONGCOBAN);
        System.out.print("Mã bàn nhân viên phụ trách: ");
        for (String maBan : DSBanPhuTrach) {
            System.out.print(maBan + " ");
        }
        System.out.print("\n");
    }

    // 3. Sua
//    @Override
//    public void menuThuocTinh() {
//        super.menuThuocTinh();
//        System.out.println("8. Mã bàn ăn nhân viên phụ trách");
//    }

//    @Override
//    public void choiceBrain(int choice) {
//        Scanner sc = new Scanner(System.in);
//        super.choiceBrain(choice);
//        if (choice == 8) {
//            DSBanPhuTrach.clear();
//            // Phai so luong ban phu trach moi
//            System.out.print("Nhập số lượng bàn nhân viên phụ trách: ");
//            int n = sc.nextInt(); sc.nextLine();
//            if (n>=0) {
//                for (int i = 0; i < n; i++) {
//                    System.out.printf("Mã bàn thứ %d của nhân viên phụ trách: ", i + 1);
//                    String maBan = sc.nextLine();
//                    DSBanPhuTrach.add(maBan);
//                }
//            }
//
//        }
//    }

    @Override
    public void suaThongTin(Scanner sc) {
        int choice;
        do {
            menuThuocTinh();
            System.out.println("0. Thoát");
            System.out.println("--------------------------");
            System.out.print("Lựa chọn: ");
            choice = sc.nextInt();
            sc.nextLine();
            super.choiceBrain(choice);
        }
        while (choice != 0);
    }

    // 4. Kiem tra nhan vien con ranh khong
    public boolean kiemTraNhanVienCoRanhKhong() {
        return DSBanPhuTrach.size() < 2;
    }

    // 5. Tinh luong
    @Override
    public double tinhLuong() {
        return LUONGCOBAN * super.getGioCong();
    }

    // 6. Xuat so luong phuc vu
    public static int xuatSoLuongPhucVu() {
        return soLuong;
    }

    // Phuong thuc toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String maBan : this.DSBanPhuTrach) {
            sb.append(maBan + ",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return super.toString() + "," + sb;
    }

    // Getter
    @Override
    public double getLuongCoBan(){ return LUONGCOBAN; }
    public ArrayList<String> getDSBanPhuTrach(){ return this.DSBanPhuTrach; }
}
