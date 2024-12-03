import java.util.Scanner;

public abstract class NhanVien implements INhapXuat{
    private String maNhanVien;
    private String hoVaTen;
    private String gioiTinh;
    private Date ngaySinh;
    private Date ngayKiHopDong;
    private Date ngayHetHopDong;
    private double gioCong;

    public NhanVien(){
        ngaySinh = new Date();
        ngayKiHopDong = new Date();
        ngayHetHopDong = new Date();
    }

    public NhanVien(String maNhanVien, String hoVaTen, String gioiTinh,
                    int ngaySinh, int thangSinh, int namSinh,
                    int ngayKiHopDong, int thangKiHopDong, int namKiHopDong,
                    int ngayHetHopDong, int thangHetHopDong, int namHetHopDong,
                    double gioCong) {

        this.maNhanVien = maNhanVien;
        this.hoVaTen = hoVaTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = new Date(ngaySinh, thangSinh, namSinh);
        this.ngayKiHopDong = new Date(ngayKiHopDong, thangKiHopDong, namKiHopDong);
        this.ngayHetHopDong = new Date(ngayHetHopDong, thangHetHopDong, namHetHopDong);
        this.gioCong = gioCong;
    }

    // 1. Nhập
    public void nhapThongTin(Scanner sc){
        System.out.print("Nhập mã nhân viên: "); this.maNhanVien = sc.nextLine();
        System.out.print("Nhập họ và tên: "); this.hoVaTen = sc.nextLine().toLowerCase();
        System.out.print("Nhập giới tính: "); this.gioiTinh = sc.nextLine().toLowerCase();
        System.out.println("---Ngày sinh---");
        ngaySinh.nhap(sc);
        System.out.println("---Ngày kí hợp đồng---");
        ngayKiHopDong.nhap(sc);
        // Kiem tra sinh truoc ki hop dong
        while (!Date.kiemTraDateATruocDateB(ngaySinh,ngayKiHopDong)){
            System.out.println("Lỗi: Ngày kí hợp đồng trước ngày sinh. Vui lòng nhập lại:");
            ngayKiHopDong.nhap(sc);
        }
        System.out.println("---Ngày hết hợp đồng---");
        ngayHetHopDong.nhap(sc);
        // Kiem tra ki hop dong truoc het hop dong
        while (!Date.kiemTraDateATruocDateB(ngayKiHopDong,ngayHetHopDong)){
            System.out.println("Lỗi: Ngày hết hợp đồng trước ngày ký hợp đồng. Vui lòng nhập lại:");
            ngayHetHopDong.nhap(sc);
        }
        System.out.print("Nhập giờ công: "); this.gioCong = sc.nextDouble(); sc.nextLine();
    }

    // 2.Xuất
    public void xuatThongTin(){
        System.out.println("Mã nhân viên: " + this.maNhanVien);
        System.out.println("Họ và tên: " + StringUtils.chuanHoaTen(this.hoVaTen));
        System.out.println("Giới tính: " + StringUtils.chuanHoaTen(this.gioiTinh));
        System.out.print("Ngày sinh: " + this.ngaySinh);
        System.out.print("Ngày kí hợp đồng: " + this.ngayKiHopDong);
        System.out.print("Ngày hết hợp đồng: " + this.ngayHetHopDong);
        System.out.println("Giờ công: " + this.gioCong);
    }

    // 3. Menu thuộc tính
    public void menuThuocTinh(){
        System.out.println("-------Bảng thuộc tính--------");
        System.out.println("1. Mã số nhân viên");
        System.out.println("2. Họ và tên");
        System.out.println("3. Giới tính");
        System.out.println("4. Ngày sinh");
        System.out.println("5. Ngày kí hợp đồng");
        System.out.println("6. Ngày hết hợp đồng");
        System.out.println("7. Giờ công");
    }

    public void choiceBrain(int choice) {
        Scanner sc = new Scanner(System.in);
        if (choice == 1) {
            System.out.print("Nhập mã số mới: ");
            String maSo = sc.nextLine();
            setMaSoNhanVien(maSo);
        } else if (choice == 2) {
            System.out.print("Nhập họ và tên mới: ");
            String hoten = sc.nextLine().toLowerCase();
            setHoVaTen(hoten);
        } else if (choice == 3) {
            System.out.print("Nhập giới tính: ");
            String gioitinh = sc.nextLine();
            setGioiTinh(gioitinh);
        } else if (choice == 4) {
            Date ngaysinh = new Date();
            ngaysinh.nhap(sc);
            setNgaySinh(ngaysinh);
        } else if (choice == 5) {
            Date ngayki = new Date();
            ngayki.nhap(sc);
            setNgaySinh(ngayki);
        } else if (choice == 6) {
            Date ngayhet = new Date();
            ngayhet.nhap(sc);
            setNgaySinh(ngayhet);
        } else if (choice == 7) {
            System.out.print("Nhập giờ công: ");
            double giocong = sc.nextDouble();
            sc.nextLine();
            setGioCong(giocong);
        }
    }

    // 4. Sửa thông tin
    public void suaThongTin(Scanner sc) {
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

    // 5. Tinh luong + lay luong co ban
    public abstract double tinhLuong();
    public abstract double getLuongCoBan();

    // 6. Reset GioCong
    public void resetGioCong(){
        this.gioCong = 0;
    }

    // Phuong thuc toString
    @Override
    public String toString() {
        return this.maNhanVien+","+this.hoVaTen+","+this.gioiTinh+","+
                this.ngaySinh.getNgay()+","+this.ngaySinh.getThang()+","+this.ngaySinh.getNam()+","+
                this.ngayKiHopDong.getNgay()+","+this.ngayKiHopDong.getThang()+","+this.ngayKiHopDong.getNam()+","+
                this.ngayHetHopDong.getNgay()+","+this.ngayHetHopDong.getThang()+","+this.ngayHetHopDong.getNam()+","+
                this.gioCong;
    }

    // Getter va Setter
    public double getGioCong(){
        return this.gioCong;
    }
    public String getMaNhanVien(){ return this.maNhanVien; }
    public Date getNgayHetHopDong(){ return this.ngayHetHopDong; }
    public String getHoVaTen(){ return this.hoVaTen; }
    public void setGioCong(double gioCong){
        this.gioCong = gioCong;
    }

    public void setMaSoNhanVien(String maSo){
        this.maNhanVien = maSo;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setNgayKiHopDong(Date ngayKiHopDong) {
        this.ngayKiHopDong = ngayKiHopDong;
    }

    public void setNgayHetHopDong(Date ngayHetHopDong) {
        this.ngayHetHopDong = ngayHetHopDong;
    }
}
