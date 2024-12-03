import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class QuanLyCuaHang {
    private static double doanhThu=0;
    private static String doanhThuURL = "./data/doanhthu.txt";
    private static String matKhau ="";
    private static String matKhauURL = "./data/password.txt";
    private static String tenCuaHang = "Vui cười lên";
    private static String diaChiCuaHang = "273 An Dương Vương – Phường 3 – Quận 5";
    private static DanhSachNhanVien dsNhanVien = new DanhSachNhanVien();
    private static DanhSachBanAn dsBanAn = new DanhSachBanAn();
    private static DanhSachMonAn dsMonAn = new DanhSachMonAn();
    private static DanhSachNguyenLieu dsNguyenLieu = new DanhSachNguyenLieu();
    private static DanhSachLoiDatBan dsLoiDatBan = new DanhSachLoiDatBan();


    public static void menuChucNangChinh(){
        System.out.println("_____________CỬA HÀNG: " +tenCuaHang + "_____________");
        System.out.println("_____________ĐỊA CHỈ: " +diaChiCuaHang);
        System.out.println("_____________MENU CHỨC NĂNG CHÍNH_____________");
        System.out.println("1/ Quản lý nhân viên");
        System.out.println("2/ Quản lý lời đặt bàn");
        System.out.println("3/ Quản lý bàn ăn");
        System.out.println("4/ Quản lý menu");
        System.out.println("5/ Quản lý nguyên liệu");
        System.out.println("6/ Đổi tên cửa hàng");
        System.out.println("7/ Đổi địa chỉ cửa hàng");
        System.out.println("8/ Tính doanh thu cửa hàng");
        System.out.println("9/ Đổi mật khẩu");
        System.out.println("10/ Xem mật khẩu");
        System.out.println("0/ Thoát");
    }

    public static void doiTenCuaHang(Scanner sc){
        System.out.print("Nhập tên cửa hàng mới: ");
        tenCuaHang = sc.nextLine();
    }
    public static void doiDiaChiCuaHang(Scanner sc){
        System.out.print("Nhập địa chỉ cửa hàng mới: ");
        diaChiCuaHang = sc.nextLine();
    }

    public static void docFileDoanhThu(){
        try(Scanner sc = new Scanner(new FileReader(doanhThuURL))){
            if(sc.hasNextLine()){
                doanhThu = Double.parseDouble(sc.nextLine());
            }
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Lỗi: Vui lòng kiểm tra lại file doanh thu.");
        }
    }
    public static void ghiFileDoanhThu(){
        try(PrintWriter pw = new PrintWriter(new FileWriter(doanhThuURL))){
            pw.println(doanhThu);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Lỗi: Vui lòng kiểm tra lại file doanh thu.");
        }
    }
//    public static void xuatDoanhThu() {
//        System.out.println("Doanh thu cửa hàng hiện tại: " +doanhThu);
//    }
    public static void doiMatKhau(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mật khẩu mới: ");
        matKhau = sc.nextLine();
        try(PrintWriter pw = new PrintWriter(new FileWriter(matKhauURL))){
            pw.println(matKhau);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Lỗi: Vui lòng kiểm tra file mật khẩu");
        }
    }
    /// NHÂN VIÊN
    // Menu
    public static void menuChucNangNhanVien(){
        System.out.println("____________QUẢN LÝ NHÂN VIÊN____________");
        System.out.println("1/ Thêm nhân viên");
        System.out.println("2/ Tìm nhân viên theo mã");
        System.out.println("3/ Sửa thông tin nhân viên");
        System.out.println("4/ Xóa thông tin nhân viên");
        System.out.println("5/ Xuất danh sách nhân viên");
        System.out.println("6/ Kiểm tra nhân viên sắp hết hợp đồng");
        System.out.println("7/ Lọc danh sách nhân viên theo loại");
        System.out.println("8/ Tính lương nhân viên theo mã");
        System.out.println("9/ Xuất bẳng lương nhân viên");
        System.out.println("10/ Quyết toán bằng lương ( Lưu ý: sau khi quyết toán, giờ công reset về 0)");
        System.out.println("11/ In ra số lượng mỗi loại nhân viên");
        System.out.println("12/ Đọc file");
        System.out.println("13/ Cập nhật lại file");
        System.out.println("0/ Thoát");
    }

    // Choice brain NV
    public static void nhanVienChoiceBrain(int choice){
        if (choice == 1){
            dsNhanVien.themThongTin();
        }
        else if(choice == 2){
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập mã nhân viên muốn tìm: ");
            String maNV = sc.nextLine();
            NhanVien a = dsNhanVien.timNVTheoMaSo(maNV);
            a.xuatThongTin();
        }
        else if (choice == 3){
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập mã nhân viên muốn sửa: ");
            String maNV = sc.nextLine();
            dsNhanVien.suaThongTin(maNV);
        }
        else if ( choice == 4){
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập mã nhân viên muốn xóa: ");
            String maNV = sc.nextLine();
            dsNhanVien.xoaThongTin(maNV);
        }
        else if (choice == 5){
            dsNhanVien.inDanhSachNhanVien();
        }
        else if (choice == 6){
            dsNhanVien.kiemTraDanhSachNhanVienSapHetHopDong();
        }
        else if (choice == 7){
            dsNhanVien.locDanhSachTheoLoai();
        }
        else if (choice == 8){
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập mã nhân viên muốn tính lương: ");
            String maNV = sc.nextLine();
            dsNhanVien.tinhLuongNhanVienTheoMa(maNV);
        }
        else if (choice == 9){
            dsNhanVien.xuatBangLuongNhanVien();
        }
        else if (choice == 10){
            Scanner sc = new Scanner(System.in);
            System.out.print("Lưu ý: việc quyết toán bảng lương sẽ xuất ra danh danh sách nhưng đồng thời reset giờ công của toàn bộ nhân viên");
            System.out.print("Bạn có muốn tiếp tục (yes/no): ");
            String ans = sc.nextLine().toLowerCase();
            // Cho phep ca 3 input
            if (ans.equals("yes") || ans.equals("1") || ans.equals("co"));
                dsNhanVien.quyetToanBangLuong();
        }
        else if (choice == 11){
            dsNhanVien.inSoLuongMoiLoaiNV();
        }
        else if (choice == 12){
            dsNhanVien.docFile();
        }
        else if (choice == 13){
            dsNhanVien.capNhatLaiFile();
        }

    }

    /// BÀN ĂN
    // menu chức năng bàn
    public static void menuChucNangBanAn(){
        System.out.println("____________QUẢN LÝ BÀN ĂN____________");
        System.out.println("1/ Thêm bàn ăn ( Lưu ý: Chỉ thêm được khi chưa đạt số lượng bàn ăn tối đa)");
        System.out.println("2/ Kiểm tra số lượng bàn ăn mỗi loại");
        System.out.println("3/ Tìm bàn ăn theo mã");
        System.out.println("4/ Xóa bàn ăn theo mã");
        System.out.println("5/ Sửa thông tin bàn ăn theo mã");
        System.out.println("6/ Xuất danh sách bàn ăn");
        System.out.println("7/ Thay đổi số lượng bàn ăn tối đa");
        System.out.println("8/ Đọc file");
        System.out.println("9/ Xuất ra file");
        System.out.println("0/ Thoát");
    }
    // Choice brain ban an
    public static void banAnChoiceBrain(int choice){
        if (choice == 1){
            dsBanAn.themThongTin();
        }
        else if (choice == 2){
            dsBanAn.kiemTraSoLuongBanAnMoiLoai();
        }
        else if (choice == 3){
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("Nhập mã số bàn ăn muốn tìm: ");
                String maBan = sc.nextLine();
                BanAn a = dsBanAn.timBanAnTheoMa(maBan);
                if (a!=null){
                    a.xuatThongTin();
                }
                else{
                    System.out.println("Lỗi: Không tìm thấy mã bàn ăn.");
                }
            }catch(Exception e){
                System.out.println("Lỗi: Kiểu dữ liệu không đúng.");
            }
        }
        else if (choice == 4){
            try{
                Scanner sc = new Scanner(System.in);
                System.out.print("Nhập mã bàn ăn muốn xóa: ");
                String maBan = sc.nextLine();
                BanAn a = dsBanAn.timBanAnTheoMa(maBan);
                if (a!=null){
                    dsBanAn.getDSBanAn().remove(a);
                    System.out.println("Bàn ăn có mã " + maBan + " đã được xóa." );
                }
                else{
                    System.out.println("Lỗi: Không tìm thấy mã bàn ăn.");
                }
            }
            catch(Exception e){
                System.out.println("Lỗi: Kiểu dữ liệu không đúng.");
            }
        }
        else if (choice == 5){
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập mã bàn muốn sửa: ");
            String maBan = sc.nextLine();
            BanAn a = dsBanAn.timBanAnTheoMa(maBan);
            if (a!=null)
                a.suaThongTin(sc, dsNhanVien);
            else
                System.out.println("Lỗi: Không tìm thây mã bàn");
        }
        else if (choice == 6){
            dsBanAn.xuatDanhSachBanAn();
        }
        else if (choice == 7){
            DanhSachBanAn.tangSoLuongBanAnToiDa();
        }
        else if (choice == 8){
            dsBanAn.docFile();
            System.out.println("Đọc file thành công.");
        }
        else if (choice == 9){
            dsBanAn.ghiRaFile();
            System.out.println("Cập nhật file thành cồng.");
        }
    }

    /// MÓN ĂN
    // menu chuc nang mon an
    public static void menuChucNangMonAn(){
        System.out.println("____________QUẢN LÝ MÓN ĂN____________");
        System.out.println("1/ Thêm món ăn.");
        System.out.println("2/ Tìm món ăn theo mã.");
        System.out.println("3/ Xóa món ăn theo mã.");
        System.out.println("4/ Sửa thuộc tính món ăn.");
        System.out.println("5/ Xuất danh sách món ăn.");
        System.out.println("6/ Đọc file.");
        System.out.println("7/ Xuất ra file.");
        System.out.println("0/ Thoát");
    }

    // Mon an choice brain
    public static void monAnChoiceBrain(int choice){
        if (choice == 1){
            dsMonAn.themThongTin();
        }
        else if (choice == 2){
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập mã món ăn muốn tìm: ");
            String maMon = sc.nextLine();
            MonAn ma = dsMonAn.timMonAnTheoMa(maMon);
            if (ma!=null){
                ma.xuatThongTin();
            }
            else{
                System.out.println("Lỗi: Không tìm thấy món ăn.");
            }
        }
        else if (choice == 3){
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập mã món ăn muốn xóa: ");
            String maMon = sc.nextLine();
            MonAn ma = dsMonAn.timMonAnTheoMa(maMon);
            if (ma!=null){
                dsMonAn.getDsMonAn().remove(ma);
                System.out.println("Món ăn đã được xóa.");
            }
            else{
                System.out.println("Lỗi: Không tìm thấy món ăn.");
            }
        }
        else if (choice == 4){
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập mã món ăn muốn sửa: ");
            String maMon = sc.nextLine();
            MonAn ma = dsMonAn.timMonAnTheoMa(maMon);
            if (ma!=null) {
                ma.suaThongTin();
            }
            else{
                System.out.println("Lỗi: Không tìm thấy món ăn.");
            }
        }
        else if (choice == 5){
            dsMonAn.xuatDanhSachMonAn();
        }
        else if (choice == 6){
            boolean isSuccess = dsMonAn.docFile();
            if(isSuccess)
                System.out.println("Đọc file thành công.");
        }
        else if (choice == 7){
            dsMonAn.ghiRaFile();
            System.out.println("Ghi file thành công.");
        }
    }

    /// NGUYÊN LIỆU
    // menu chuc nang nguyen lieu
    public static void menuChucNangNguyenLieu(){
        System.out.println("____________QUẢN LÝ NGUYÊN LIỆU____________");
        System.out.println("1/ Thêm nguyên liệu");
        System.out.println("2/ Tìm nguyên liệu theo mã hoặc tên.");
        System.out.println("3/ Xóa nguyên liệu theo mã hoặc tên.");
        System.out.println("4/ Sửa thuộc tính nguyên liệu.");
        System.out.println("5/ Xuất danh sách nguyên liệu.");
        System.out.println("6/ Đọc file.");
        System.out.println("7/ Xuất ra file.");
        System.out.println("0/ Thoát");
    }

    // Nguyen lieu choice brain
    public static void nguyenLieuChoiceBrain(int choice){
        if (choice == 1){
            dsNguyenLieu.themThongTin();
        }
        else if (choice == 2){
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập mã Nguey hoặc tên món ăn muốn tìm: ");
            String maNL = sc.nextLine();
            NguyenLieu a = dsNguyenLieu.timNguyenLieuTheoMa(maNL);
            if ( a!= null){
                a.xuatThongTin();
            }
            else{
                System.out.println("Lỗi: Không tìm thấy nguyên liệu.");
            }
        }
        else if (choice == 3){
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập mã món ăn hoặc tên món ăn muốn xóa: ");
            String maNL = sc.nextLine();
            dsNguyenLieu.xoaThongTin(maNL);
        }
        else if (choice == 4){
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập mã món ăn hoặc tên món ăn muốn sửa: ");
            String maNL = sc.nextLine();
            dsNguyenLieu.suaThongTin(maNL);
        }
        else if (choice == 5){
            dsNguyenLieu.xuatDanhSachNguyenLieu();
        }
        else if (choice == 6){
            if(dsNguyenLieu.docFile()){
                System.out.println("Đọc file thành công.");
            }
        }
        else if (choice == 7){
            if(dsNguyenLieu.xuatRaFile()){
                System.out.println("Ghi file thành công.");
            }
        }
    }

    /// Loi dat ban
    public static void menuChucNangLoiDatBan() {
        System.out.println("____________QUẢN LÝ LỜI ĐẶT BÀN____________");
        System.out.println("1/ Thêm lời đặt bàn");
        System.out.println("2/ Sửa lời đặt bàn");
        System.out.println("3/ Xóa lời đặt bàn");
        System.out.println("4/ Tìm lời đặt bàn");
        System.out.println("5/ In ra tổng doanh thu");
        System.out.println("6/ In ra thông tin các lời đặt bàn");
        System.out.println("7/ Đọc File");
        System.out.println("8/ Cập nhật lại File");
        System.out.println("0/ Thoát");
    }
    /// Loi dat ban
    public static void danhSachDatBanChoiceBrain(int choice) {
        if (choice == 1) {
            ArrayList<LoiDatBan> dsDatBan = dsLoiDatBan.getDsDatBan();
            dsLoiDatBan.setDaTaBanAn(dsBanAn.chonBanAnRanh());
            dsLoiDatBan.themThongTin();

            for (int i = 0 ; i < dsDatBan.size() ; i++) {
                if (dsDatBan.get(i).getMaNhanVien().equals("")) {
                    dsDatBan.get(i).setMaNhanVien(dsNhanVien.chonNgauNhienPhucVuConRanh().getMaNhanVien());
                }
            }
        }
        else if(choice == 2) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Mời nhập mã số lời đặt bàn: ");
            String Ma = sc.nextLine();
            dsLoiDatBan.suaThongTin(Ma);
        }
        else if(choice == 3) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Mời nhập mã số lời đặt bàn: ");
            String Ma = sc.nextLine();
            dsLoiDatBan.xoaThongTin(Ma);
        }
        else if(choice == 4) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Mời nhập mã lời đặt bàn cần tìm: ");
            String maLoiDatBan = sc.nextLine();
            sc.nextLine();
            if (dsLoiDatBan.timLoiDatBan(maLoiDatBan) != null) {
                dsLoiDatBan.inLoiDatBan(maLoiDatBan);
            }
            else {
                System.out.println("Không tìm thấy lời đặt bàn!");
            }
        }
        else if(choice == 5) {
            dsLoiDatBan.tinhTongDoanhThu();
            System.out.println(dsLoiDatBan.getTongDoanhThu());
        }
        else if (choice == 6) {
            dsLoiDatBan.inDanhSachDatBan();
        }
        else if(choice == 7) {
            dsLoiDatBan.capNhatData(dsBanAn, dsMonAn.getDsMonAn());
        }
        else if(choice == 8) {
            if (dsLoiDatBan.capNhatFile()){
                System.out.println("Cập nhật file thành công.");
            }
        }
    }

    public static void layMatKhauTuFile(){
        try(Scanner sc = new Scanner(new FileReader(matKhauURL))){
            matKhau = "";
            if (sc.hasNextLine()){
                matKhau = sc.nextLine();
            }
            else throw new IOException();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Lỗi: Vui lòng kiểm tra file mật khẩu.");
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        docFileDoanhThu(); // Lay doanh thu
        layMatKhauTuFile(); // Lay mat khau
        int attemps = 0;
        String idManager;
        System.out.print("Nhập mật khẩu/mã quản lý: ");
        while (attemps < 5) {
            try {
                idManager = sc.nextLine();
                if (!dsNhanVien.kiemTraNhanVienCoPhaiQuanLy(idManager) && !idManager.equals(matKhau)) {
                    throw new InvalidIdException("Mật khẩu hoặc mã số quản lý không tồn tại");
                }
                break;
            } catch (Exception e) {
                attemps++;
                System.out.println(e.getMessage());
                if (attemps < 5) {
                    System.out.printf("Bạn còn %d lần nhập. Vui lòng nhập lại: ",5-attemps);
                } else {
                    System.out.println("Bạn đã nhập sai quá 5 lần. Chương trình sẽ tự động thoát.");
                    System.exit(0); // Dùng để thoát chương trình
                }
            }
        }

        // Viết code chính
        int choice;
        do{
            menuChucNangChinh();
            System.out.print("Nhập lựa chọn: "); choice = sc.nextInt();
            sc.nextLine();
            if(choice == 1){
                int nhanVienChoice;
                do {
                    menuChucNangNhanVien();
                    System.out.print("Nhập lựa chọn: ");
                    nhanVienChoice = sc.nextInt();
                    sc.nextLine();
                    nhanVienChoiceBrain(nhanVienChoice);
                }while (nhanVienChoice != 0);
            }
            else if (choice == 2) {
                int loiDatBanChoice;
                do {
                    menuChucNangLoiDatBan();
                    System.out.print("Nhập lựa chọn: ");
                    loiDatBanChoice = sc.nextInt();
                    sc.nextLine();
                    danhSachDatBanChoiceBrain(loiDatBanChoice);
                } while (loiDatBanChoice != 0);
            }
            else if (choice == 3){
                int banAnChoice;
                do{
                    menuChucNangBanAn();
                    System.out.print("Nhập lựa chọn: ");
                    banAnChoice = sc.nextInt();
                    sc.nextLine();
                    banAnChoiceBrain(banAnChoice);
                }while(banAnChoice!=0);
            }
            else if (choice == 4){
                int monAnChoice;
                do{
                    menuChucNangMonAn();
                    System.out.print("Nhập lựa chọn: ");
                    monAnChoice = sc.nextInt();sc.nextLine();
                    monAnChoiceBrain(monAnChoice);
                }while(monAnChoice!=0);
            }
            else if (choice == 5){
                int nguyenLieuChoice;
                do{
                    menuChucNangNguyenLieu();
                    System.out.print("Nhập lựa chọn: ");
                    nguyenLieuChoice = sc.nextInt(); sc.nextLine();
                    nguyenLieuChoiceBrain(nguyenLieuChoice);
                }while(nguyenLieuChoice!=0);
            }
            else if (choice == 6){
                doiTenCuaHang(sc);
            }
            else if (choice == 7){
                doiDiaChiCuaHang(sc);
            }
            else if (choice == 8){
                for (LoiDatBan loiDatBan: dsLoiDatBan.getDsDatBan()){
                    doanhThu += loiDatBan.getTongTienMonAn();
                }
                System.out.println("Doanh thu hiện tại: " + doanhThu);
                ghiFileDoanhThu();
            }
            else if (choice == 9){
                doiMatKhau();
            }
            else if (choice==10){
                System.out.println("Mật khẩu hiện tại là: " + matKhau);
            }
        }while(choice != 0);
    }
}
