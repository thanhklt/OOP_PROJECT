import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class MonAn implements INhapXuat {
    private String maMonAn;
    private String tenMonAn;
    private Map<String, Double> nguyenLieuMonAn;
    private double giaMonAn;

    public MonAn() {
        maMonAn = "";
        tenMonAn = "";
        nguyenLieuMonAn = new HashMap<>();
        giaMonAn = 0;
    }

    public MonAn(String maMonAn, String tenMonAn, double giaMonAn, Map<String, Double> nguyenLieuMonAn) {
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.nguyenLieuMonAn = nguyenLieuMonAn;
        this.giaMonAn = giaMonAn;
    }

    // 1. Them mon an
    @Override
    public void nhapThongTin(Scanner sc) {
        System.out.print("Nhập mã món ăn: ");
        maMonAn = sc.nextLine();
        System.out.print("Nhập tên món ăn: ");
        tenMonAn = sc.nextLine();
        System.out.print("Nhập giá tiền: $");
        this.giaMonAn = sc.nextDouble();
        sc.nextLine();
        System.out.print("Nhập số lượng nguyên liệu cần thiết: ");
        int n=0;
        try {
            n = sc.nextInt();
        }
        catch(Exception e){
            System.out.println("Lỗi: Sai kiểu dữ liệu.");
        }
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("___________NGUYÊN LIỆU " + (i + 1) + "_____________");
            System.out.print("Nhập tên nguyên liệu thứ " + (i + 1) + ": ");
            String nguyenLieu = sc.nextLine();
            System.out.print("Nhập số lượng: ");
            Double soLuong = sc.nextDouble();
            sc.nextLine();
            this.nguyenLieuMonAn.put(nguyenLieu, soLuong);
        }
    }

    // 2. Xuat thong tin
    public void xuatThongTin() {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("Mã món ăn: " + maMonAn);
        System.out.println("Tên món ăn: " + tenMonAn);
        String giaMonAnDaFormat = df.format(giaMonAn);
        System.out.println("Giá món ăn: $" + giaMonAnDaFormat);
        int i = 1;
        System.out.println("Danh sách nguyên liệu:");
        for (String key : this.nguyenLieuMonAn.keySet()) {
            System.out.println("Nguyên liêu thứ " + i);
            System.out.println(key + ": " + this.nguyenLieuMonAn.get(key));
            i++;
        }
    }

    // 3. Sua thong tin
    public void menuThuocTinh(){
        System.out.println("-------Bảng thuộc tính--------");
            System.out.println("1/ Mã món ăn");
            System.out.println("2/ Tên món ăn");
            System.out.println("3/ Giá món ăn");
            System.out.println("4/ Nguyên liệu món ăn");
            System.out.println("0/ Thoát");
    }
    public void choiceBrain(int choice){
        Scanner sc = new Scanner(System.in);
        if (choice == 1){
            System.out.print("Nhập mã món ăn mới: "); this.maMonAn = sc.nextLine();
            this.maMonAn = maMonAn;
        }
        else if (choice == 2){
            System.out.print("Nhập tên món ăn mới: "); this.tenMonAn = sc.nextLine();
        }
        else if (choice == 3){
            System.out.print("Nhập giá món ăn mới: "); this.giaMonAn = sc.nextDouble(); sc.nextLine();
        }
        else if (choice ==4){
            nguyenLieuMonAn.clear();
            System.out.print("Nhập số lượng nguyên liệu cần thiết: ");
            int n=0;
            try {
                n = sc.nextInt();
            }
            catch(Exception e){
                System.out.println("Lỗi: Sai kiểu dữ liệu.");
            }
            sc.nextLine();
            for (int i = 0; i < n; i++) {
                System.out.println("___________NGUYÊN LIỆU " + (i + 1) + "_____________");
                System.out.print("Nhập tên nguyên liệu thứ " + (i + 1) + ": ");
                String nguyenLieu = sc.nextLine();
                System.out.print("Nhập số lượng: ");
                Double soLuong = sc.nextDouble();
                sc.nextLine();
                this.nguyenLieuMonAn.put(nguyenLieu, soLuong);
            }
        }
    }
    public void suaThongTin(){
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do{
            menuThuocTinh();
            System.out.print("Lựa chọn: "); choice = sc.nextInt(); sc.nextLine();
            this.choiceBrain(choice);
        }while(choice!=0);
    }

    // Tra ve so luong nguyen lieu cua mon an
    public int inSoLuongNguyenLieu(){
        return this.nguyenLieuMonAn.size();
    }

    // Phuong thuc toString
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.maMonAn+","+this.tenMonAn+","+this.giaMonAn+","+this.inSoLuongNguyenLieu()+",");
        for (String key: this.nguyenLieuMonAn.keySet()){
            sb.append(key+","+this.nguyenLieuMonAn.get(key)+",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // Getter
    public String getmaMonAn(){return maMonAn;}
    public String gettenMonAn(){return tenMonAn;}
    public Map<String,Double> getnguyenLieuMonAn(){return nguyenLieuMonAn;}
    public double getgiaMonAn(){return giaMonAn;}
}
