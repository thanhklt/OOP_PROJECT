import java.util.Scanner;

public class BanAn implements INhapXuat{
    private String maBan;
    private boolean tinhTrangBan;
    private String maNhanVienPhuTrach;

    public BanAn(){
        maBan = "";
        tinhTrangBan = false;  // 0: chua duoc su dung
        maNhanVienPhuTrach = "";
    }

    public BanAn(String maBan, boolean tinhTrangBan, String maNhanVienPhuTrach) {
        this.maBan = maBan;
        this.tinhTrangBan = tinhTrangBan;
        this.maNhanVienPhuTrach = maNhanVienPhuTrach;
    }
    public BanAn(String maBan, boolean tinhTrangBan){
        this.maBan = maBan;
        this.tinhTrangBan = tinhTrangBan;
        maNhanVienPhuTrach ="";
    }

    // 1. Nhap thong tin
    @Override
    public void nhapThongTin(Scanner sc){
        System.out.print("Nhập mã bàn: "); this.maBan = sc.nextLine();
        tinhTrangBan = false;
    }

    // 2. Xuat thong tin
    @Override
    public void xuatThongTin(){
        System.out.println("Mã bàn: " + this.maBan);
        if (!this.tinhTrangBan)
            System.out.println("Tình trạng bàn: Rảnh");
        else
            System.out.println("Tình trạng bàn: Bận");
        if (!maNhanVienPhuTrach.equals("")){
            System.out.println("Nhân viên phụ trách bàn: " +this.maNhanVienPhuTrach);
        }
        else{
            System.out.println("Nhân viên phụ trách bàn: Không có");
        }
    }

    // 3. Sửa thông tin bàn ăn
    void menuThuocTinh(){
        System.out.println("-------Bảng thuộc tính--------");
        System.out.println("1/ Mã bàn");
        System.out.println("2/ Nhân viên phụ trách");
        System.out.println("0/ Thoát");
    }
    public void choiceBrain(int choice, DanhSachNhanVien dsNV){
        Scanner sc = new Scanner(System.in);
        if (choice == 1){
            System.out.print("Nhập mã bàn mới: ");
            this.maBan = sc.nextLine();
        }
        else if (choice == 2){
            if (!this.tinhTrangBan){
                System.out.println("Lỗi: Bàn đang trống");
            }
            else{
                System.out.print("Nhập mã nhân viên phục vụ: ");
                String maNVPhucVu = sc.nextLine();
                if (!StringUtils.layHaiChuCaiDauCuaMa(maNVPhucVu).equals("PV")){
                    System.out.println("Lỗi: Mã nhân viên không phải của phục vụ");
                }
                else {
                    PhucVu nvPhucVu = (PhucVu) dsNV.timNVTheoMaSo(maNVPhucVu);
                    if (nvPhucVu == null) {
                        System.out.println("Lỗi: Không tìm thấy mã số");
                    } else {
                        if (nvPhucVu instanceof PhucVu && nvPhucVu.kiemTraNhanVienCoRanhKhong()) {
                            this.maNhanVienPhuTrach = nvPhucVu.getMaNhanVien();
                            System.out.println("Đã thêm nhân viên "+nvPhucVu.getMaNhanVien()+" quản lý bàn");
                        } else {
                            System.out.println("Lỗi: Không có nhân viên phục vụ rảnh");
                        }
                    }
                }
            }
        }
    }
    public void suaThongTin(Scanner sc, DanhSachNhanVien dsNV){
        int choice;
        do{
            menuThuocTinh();
            System.out.print("Lựa chọn: ");
            choice=sc.nextInt(); sc.nextLine();
            choiceBrain(choice,dsNV);
        }while(choice!=0);
    }

    // Phuong thuoc to_string
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.maBan+",");
        if(!tinhTrangBan) sb.append("0");
        else{
            sb.append("1," + this.maNhanVienPhuTrach);
        }
        return sb.toString();
    }
    // Getter
    public String getMaBan(){ return this.maBan; }
    public boolean getTinhTrangBan(){ return this.tinhTrangBan; }
    public String setMaNhanVienPhuTrach(String maSo){ return this.maNhanVienPhuTrach; }
    public void setTinhTrangBan (boolean tinhTrangBan) { this.tinhTrangBan = tinhTrangBan; }

}
