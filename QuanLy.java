import java.util.Scanner;

public class QuanLy extends NhanVien {
    private static double LUONGCOBAN = 20;
    private static int soLuong = 0;


    public QuanLy() {
        soLuong++;
    }

    public QuanLy(String maNhanVien, String hoVaTen, String gioiTinh, int ngaySinh, int thangSinh, int namSinh, int ngayKiHopDong, int thangKiHopDong, int namKiHopDong, int ngayHetHopDong, int thangHetHopDong, int namHetHopDong, double gioCong) {
        super(maNhanVien, hoVaTen, gioiTinh, ngaySinh, thangSinh, namSinh, ngayKiHopDong, thangKiHopDong, namKiHopDong, ngayHetHopDong, thangHetHopDong, namHetHopDong, gioCong);
        soLuong++;
    }


    // 1. Nhap
    public void nhapThongTin(Scanner sc) {
        super.nhapThongTin(sc);
    }

    // 2. Xuat
    public void xuatThongTin() {
        super.xuatThongTin();
        System.out.println("Lương cơ bản: " + QuanLy.LUONGCOBAN);
    }

    // 3. Sua Thong Tin
    @Override
    public void menuThuocTinh() {
        super.menuThuocTinh();
    }

    public void suaThongTin() {
        Scanner sc = new Scanner(System.in);
        super.suaThongTin(sc);
    }

    // 4. Tinh luong
    @Override
    public double tinhLuong() {
        return LUONGCOBAN * super.getGioCong();
    }

    // 5. Xuat so luong quan ly
    public static int xuatSoLuongQuanLy() {
        return soLuong;
    }

    // Getter
    @Override
    public double getLuongCoBan() {
        return LUONGCOBAN;
    }
}
