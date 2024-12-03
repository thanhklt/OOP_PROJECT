import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class LoiDatBan implements INhapXuat {
    Scanner sc = new Scanner(System.in);
    private String maDatBan;
    private int soLuongKhachHang;
    private KhachHang khachHangDaiDien;
    private int soLuongMonAn;
    private ArrayList<String> dsMonAn;
    private double tongTienMonAn;
    private BanAn banAn;
    private Map<String, Double> dsMenu = new HashMap<>();
    private String maNhanVien;

    public LoiDatBan() {
        maDatBan = "";
        soLuongKhachHang = 0;
        khachHangDaiDien = new KhachHang();
        soLuongMonAn = 0;
        dsMonAn = new ArrayList<>();
        banAn = new BanAn();
        maNhanVien = "";
        tongTienMonAn = 0;
    }

    public LoiDatBan(String maDatBan, int soLuongKhachHang, KhachHang khachHangDaiDien, int soLuongMonAn, ArrayList<String> dsMonAn, double tongTienMonAn, BanAn banAn, String maNhanVien) {
        this.maDatBan = maDatBan;
        this.soLuongKhachHang = soLuongKhachHang;
        this.khachHangDaiDien = khachHangDaiDien;
        this.soLuongMonAn = soLuongMonAn;
        this.dsMonAn = dsMonAn;
        this.tongTienMonAn = tongTienMonAn;
        this.banAn = banAn;
        this.maNhanVien = maNhanVien;
    }

    // 1. Hàm nhập thông tin
    @Override
    public void nhapThongTin(Scanner sc) {
        dsMenu.clear();
        capNhatDSMonAn();
        System.out.println("Mời nhập mã đặt bàn: ");
            setMaDatBan(sc.nextLine());

        System.out.println("Mời nhập thông tin khách hàng: ");
        this.khachHangDaiDien.nhapThongTin(sc);
        System.out.println("Mời nhập số lượng khách hàng: ");
        this.soLuongKhachHang = sc.nextInt();
        sc.nextLine();

        System.out.println("Danh sách món ăn:");
        for (Map.Entry<String, Double> entry : dsMenu.entrySet()) {
            System.out.println("Tên món ăn: " + entry.getKey() + " - Giá tiền: " + entry.getValue());
        }
        System.out.println("Mời nhập số lượng món ăn: ");
            int soLuongMonAn = 0;
            try {
                soLuongMonAn = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Lỗi: Kiểu dữ liệu không đúng");
            }
            sc.nextLine();
            this.soLuongMonAn = soLuongMonAn;
            for (int i = 0 ; i < soLuongMonAn ; i++) {
                System.out.println("Mời nhập tên món ăn thứ " + (i+1) + ": ");
                String monAn = sc.nextLine();
                dsMonAn.add(monAn);
            }
        tinhTongTienMonAn();
        banAn.setTinhTrangBan(true);
        maNhanVien = "NV" + banAn.getMaBan().substring(2, 4);
        banAn.setMaNhanVienPhuTrach(maNhanVien);
    }

    // 2. Hàm xuất thông tin
    @Override
    public void xuatThongTin() {
        System.out.println("Mã đặt bàn là: " + getMaDatBan());
        khachHangDaiDien.xuatThongTin();
        System.out.println("Số lượng khách hàng là: " + getSoLuongKhachHang());
        System.out.println("Số lượng món ăn là: " + getSoLuongMonAn());
        System.out.println("Danh sách món ăn lần lượt là: ");
        for (int i = 0 ; i < dsMonAn.size() ; i++) {
            System.out.println(i+1 + "/ " + dsMonAn.get(i));
        }
        System.out.println("Mã bàn ăn là : " + getbanAn().getMaBan());
        System.out.println("Mã nhân viên phụ trách là: " + getMaNhanVien());
        System.out.println("Tổng tiền phải trả là: " + getTongTienMonAn());
    }

    // 3. Hàm thêm món ăn
    public void them() {
        dsMenu.clear();
        capNhatDSMonAn(); // Đưa danh sách Menu

        System.out.println("______________Danh sách món ăn______________");
        for (Map.Entry<String, Double> entry : dsMenu.entrySet()) {
            System.out.println("Tên món ăn: " + entry.getKey() + " - Giá tiền: " + entry.getValue());
        }
        System.out.println("Vui lòng chọn món ăn : ");
        String monAn = sc.nextLine();
        this.dsMonAn.add(monAn);
        System.out.println("Thêm món ăn thành công");
        this.soLuongMonAn++;
        tinhTongTienMonAn();
    }

    // 4. Hàm xóa khách hàng hoặc xóa món ăn
    public void xoa() {
        dsMenu.clear();
        capNhatDSMonAn();
        System.out.println("Mời nhập tên món ăn");
        String tenMonAn = sc.nextLine();
        boolean check = false;

        for (int i = 0 ; i < dsMonAn.size() ; i++) {
            if (tenMonAn.equals(dsMonAn.get(i))) {
                dsMonAn.remove(i);
                check = true;
                break;
            }
        }
        if (check) {
            System.out.println("Xóa món ăn thành công");
            soLuongMonAn--;
            tinhTongTienMonAn();
        }
        else {
            System.out.println("Không tìm thấy món ăn");
        }
    }

    public void sua() {
        int count = 0;
        while(true) {
            if (count == 5) {
                break;
            }

            System.out.println("1/ Sửa thông tin khách hàng.");
            System.out.println("2/ Sửa mã đặt bàn.");
            System.out.println("0/ Thoát");

            int choice = 0;
            try {
                choice = sc.nextInt();
            }
            catch (Exception e) {
                System.out.println("Lỗi: Kiểu dữ liệu không đúng");
            }
            if(choice == 1) {
                System.out.println("Mời nhập mã số khách hàng : ");
                String Ma = sc.nextLine();

            }
            else if(choice == 2) {
                System.out.println("Vui lòng nhập mã mới: ");
                setMaDatBan(sc.nextLine());
            }
            else if (choice == 0) {
                break;
            }
            else {
                count++;
            }
        }
    }

    // 5. Tính tổng tiền món ăn
    public void tinhTongTienMonAn() {
        for (int i = 0; i < dsMonAn.size(); i++) {
            Double price = dsMenu.get(dsMonAn.get(i)); // Retrieve the value from the map
            if (price != null) { // Ensure it is not null
                this.tongTienMonAn += price;
            } else {
                System.out.println("Món ăn " + dsMonAn.get(i) + " không có trong menu.");
            }
        }
    }

    // 6. Cập nhật danh sách món ăn
    public void capNhatDSMonAn() {
        dsMenu.clear(); // Đảm bảo xóa dữ liệu cũ trước khi cập nhật
        String filePath = "./data/ListMonAn.txt";
        File file = new File(filePath);

        try (Scanner readFile = new Scanner(file)) {
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();

                String[] parts = line.split(",");

                if (parts.length >= 3) {
                    String tenMonAn = parts[1].trim(); // Tên món ăn
                    double giaTien = Double.parseDouble(parts[2].trim()); // Giá tiền

                    dsMenu.put(tenMonAn, giaTien);
                }
            }

            System.out.println("Cập nhật danh sách món ăn thành công!");
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file: " + filePath);
        } catch (NumberFormatException e) {
            System.out.println("Lỗi khi chuyển đổi giá trị sang số: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }
    }

    // Getter và Setter
    public void setMaDatBan(String maDatBan) {
        this.maDatBan = maDatBan;
    }

    public void setTongTienMonAn (Double tongTienMonAn) {
        this.tongTienMonAn = tongTienMonAn;
    }

    public void setkhachHang(KhachHang khachHang) {
        this.khachHangDaiDien = khachHang;
    }

    public void setSoLuongMonAn(int soLuongMonAn) {
        this.soLuongMonAn = soLuongMonAn;
    }

    public void setDsMonAn(ArrayList<String> dsMonAn) {
        this.dsMonAn = dsMonAn;
    }

    public void setbanAn(BanAn banAn) {
        this.banAn = banAn;
    }

    public void setMaNhanVien (String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaDatBan() {
        return maDatBan;
    }

    public int getSoLuongKhachHang() {return soLuongKhachHang;}

    public KhachHang khachHang() {
        return khachHangDaiDien;
    }

    public int getSoLuongMonAn() {
        return soLuongMonAn;
    }

    public ArrayList<String> getDsMonAn() {
        return dsMonAn;
    }

    public BanAn getbanAn() {
        return banAn;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public Double getTongTienMonAn() {
        return tongTienMonAn;
    }

    public void setSoLuongKhachHang(int soLuongKhachHang) {
        this.soLuongKhachHang = soLuongKhachHang;
    }
}
