import java.util.Scanner;

public class NguyenLieu implements INhapXuat{
    private String maNguyenLieu;
    private String tenNguyenLieu; // De lowercase
    private double soLuongNguyenLieu;

    public NguyenLieu() {
        maNguyenLieu = "";
        tenNguyenLieu = "";
        soLuongNguyenLieu = 0;
    }

    public NguyenLieu(String maNguyenLieu, String tenNguyenLieu, double soLuongNguyenLieu) {
        this.maNguyenLieu = maNguyenLieu;
        this.tenNguyenLieu = tenNguyenLieu.toLowerCase();
        this.soLuongNguyenLieu = soLuongNguyenLieu;
    }

    // 1. Nhập thông tin
    @Override
    public void nhapThongTin(Scanner sc){
        System.out.print("Nhập mã nguyên liệu: ");
        this.maNguyenLieu = sc.nextLine();
        System.out.print("Nhập tên nguyên liệu: ");
        this.tenNguyenLieu = sc.nextLine().toLowerCase();
        System.out.print("Nhập số lượng nguyên liệu: ");
        this.soLuongNguyenLieu = sc.nextDouble(); sc.nextLine();
    }

    // 2. Xuat thong tin
    @Override
    public void xuatThongTin() {
        System.out.println("Mã nguyên liệu: " +this.maNguyenLieu);
        System.out.println("Tên nguyên liệu: " +this.tenNguyenLieu);
        System.out.println("Số lượng: "+this.soLuongNguyenLieu);
    }

    // 3. Sua thong tin
    public void menuThuocTinh(){
        System.out.println("_______BẢNG THUỘC TÍNH_______");
        System.out.println("1/ Mã nguyên liệu");
        System.out.println("2/ Tên nguyên liệu");
        System.out.println("3/ Số lượng nguyên liệu");
        System.out.println("0/ Thoát");
    }
    public void choiceBrain(int choice){
        Scanner sc = new Scanner(System.in);
        if (choice == 1){
            System.out.print("Nhập mã nguyên liệu mới: ");
            this.maNguyenLieu = sc.nextLine();
            System.out.println("Đã thay đổi mã nguyên liệu.");
        }
        else if (choice == 2){
            System.out.print("Nhập tên nguyên liệu mới: ");
            this.tenNguyenLieu = sc.nextLine().toLowerCase();
            System.out.println("Đã thay đổi tên nguyên liệu.");
        }
        else if (choice == 3){
            System.out.print("Nhập số lượng nguyên liệu mới: ");
            this.soLuongNguyenLieu = sc.nextDouble(); sc.nextLine();
            System.out.println("Đã thay đổi số lượng nguyên liệu.");
        }
    }
    public void suaThongTin(){
        int choice;
        Scanner sc = new Scanner(System.in);
        do{
            menuThuocTinh();
            System.out.print("Nhập lựa chọn: "); choice = sc.nextInt(); sc.nextLine();
            choiceBrain(choice);
        }while(choice!=0);
    }

    // Phuong thuc toString
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.maNguyenLieu+","+this.tenNguyenLieu+","+this.soLuongNguyenLieu);
        return sb.toString();
    }

    // Getter
    public String getmaNguyenLieu(){return this.maNguyenLieu;}
    public String gettenNguyenLieu(){return tenNguyenLieu;}
    public double getsoLuongNguyenLieu(){return soLuongNguyenLieu;}

}
