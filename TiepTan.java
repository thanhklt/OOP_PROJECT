import java.util.Scanner;

public class TiepTan extends NhanVien{
    public static double LUONGCOBAN = 13;
    public static int soLuong = 0;

    public TiepTan(){
        // Tu goi constructor khong tham so cua NhanVien
        soLuong++;
    }

    public TiepTan(String maNhanVien, String hoVaTen, String gioiTinh, int ngaySinh, int thangSinh, int namSinh, int ngayKiHopDong, int thangKiHopDong, int namKiHopDong, int ngayHetHopDong, int thangHetHopDong, int namHetHopDong, double gioCong) {
        super(maNhanVien, hoVaTen, gioiTinh, ngaySinh, thangSinh, namSinh, ngayKiHopDong, thangKiHopDong, namKiHopDong, ngayHetHopDong, thangHetHopDong, namHetHopDong, gioCong);
        soLuong++;
    }

    // 1. Nhap
    public void nhapThongTin(Scanner sc){
        super.nhapThongTin(sc);
    }

    // 2. Xuat
    public void xuatThongTin(){
        super.xuatThongTin();
        System.out.println("Lương cơ bản: " + TiepTan.LUONGCOBAN);
    }

    // 3. Sua Thong Tin
    @Override
    public void menuThuocTinh() {
        super.menuThuocTinh();
    }

    public void suaThongTin(){
        Scanner sc = new Scanner(System.in);
        super.suaThongTin(sc);
    }

    // 4. Tinh luong tiep tan
    @Override
    public double tinhLuong(){
        return LUONGCOBAN*super.getGioCong();
    }

    // 5. Xuat so luong tiep tan
    public static int xuatSoLuongTiepTan(){
        return soLuong;
    }

    // Getter
    @Override
    public double getLuongCoBan(){ return LUONGCOBAN; }
}
