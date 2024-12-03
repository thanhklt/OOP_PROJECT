import java.util.Scanner;

public class DauBep extends NhanVien {
    private static double LUONGCOBAN = 30;
    private static int soLuong = 0;
    private String capBac;

    public DauBep() {
        soLuong++;
    }

    public DauBep(String maNhanVien, String hoVaTen, String gioiTinh, int ngaySinh, int thangSinh, int namSinh, int ngayKiHopDong, int thangKiHopDong, int namKiHopDong, int ngayHetHopDong, int thangHetHopDong, int namHetHopDong, double gioCong, String capBac) {
        super(maNhanVien, hoVaTen, gioiTinh, ngaySinh, thangSinh, namSinh, ngayKiHopDong, thangKiHopDong, namKiHopDong, ngayHetHopDong, thangHetHopDong, namHetHopDong, gioCong);
        this.capBac = capBac;
        soLuong++;
    }

    // 1. Nhap
    public void nhapThongTin(Scanner sc){
        super.nhapThongTin(sc);
        System.out.print("Nhập cấp bậc: "); this.capBac = sc.nextLine();
    }

    // 2. Xuat
    public void xuatThongTin(){
        super.xuatThongTin();
        System.out.println("Cấp bậc: " + this.capBac);
        System.out.println("Lương cơ bản: " + DauBep.LUONGCOBAN);
    }

    // 3. Sua Thong Tin
    @Override
    public void menuThuocTinh() {
        super.menuThuocTinh();
        System.out.println("8. Cấp bậc");
   }
    @Override
    public void choiceBrain(int choice){
        Scanner sc = new Scanner(System.in);
        super.choiceBrain(choice);
        if(choice==8){
            System.out.print("Nhập cấp bậc: ");
            this.capBac = sc.nextLine();
        }
    }
    @Override
    public void suaThongTin(Scanner sc){
        int choice;
        do {
            menuThuocTinh();
            System.out.println("0. Thoát");
            System.out.println("--------------------------");
            System.out.print("Lựa chọn: ");
            choice = sc.nextInt();
            sc.nextLine();
            this.choiceBrain(choice);
        }
        while (choice != 0);
    }

    // 5. Tinh luong
    @Override
    public double tinhLuong(){
        return LUONGCOBAN *this.getGioCong();
    }

    // 6. Xuat so luong dau bep
    public static int xuatSoLuongDauBep(){
        return soLuong;
    }
    // Phuong thuc toString
    @Override
    public String toString(){
        return super.toString()+","+this.capBac;
    }

    // Getter
    @Override
    public double getLuongCoBan(){ return LUONGCOBAN; }
}
