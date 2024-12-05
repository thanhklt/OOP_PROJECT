import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class DanhSachLoiDatBan implements IThemSuaXoa {
    private ArrayList<LoiDatBan> dsDatBan = new ArrayList<>();
    private double tongDoanhThu = 0;
    Scanner sc = new Scanner(System.in);

    // 1. Thêm lời đặt bàn
    @Override
    public void themThongTin() {
        LoiDatBan loiDatBan = new LoiDatBan();
        loiDatBan.setbanAn(takeDataBanAn());
        loiDatBan.nhapThongTin(sc);
        dsDatBan.add(loiDatBan);
        System.out.println("Thêm lời đặt bàn thành công!");
    }

    // 2. Sửa thông tin lời đặt bàn
    @Override
    public void suaThongTin(String MaSo) {
        boolean check = false;
        for (int i = 0; i < dsDatBan.size(); i++) {
            if (MaSo.equals(dsDatBan.get(i).getMaDatBan())) {
                check = true;
                while (true) {
                    System.out.println("1/ Thêm món ăn.");
                    System.out.println("2/ Xóa món ăn.");
                    System.out.println("3/ Sửa lời đặt bàn.");
                    System.out.println("0/ Thoát.");

                    int choice = sc.nextInt();
                    sc.nextLine();
                    if (choice == 1) {
                        dsDatBan.get(i).them();
                    } else if (choice == 2) {
                        dsDatBan.get(i).xoa();
                    } else if (choice == 3) {
                        dsDatBan.get(i).sua();
                    } else if (choice == 0) {
                        break;
                    } else {
                        System.out.println("Lựa chọn không hợp lệ!");
                    }
                }
                break;
            }
        }
        if (check) {
            System.out.println("Sửa đổi dữ liệu thành công!");
        } else {
            System.out.println("Không tìm thấy lời đặt bàn với mã: " + MaSo);
        }
    }

    // 3. Xóa lời đặt bàn
    @Override
    public void xoaThongTin(String MaSo) {
        boolean check = false;
        for (int i = 0; i < dsDatBan.size(); i++) {
            if (MaSo.equals(dsDatBan.get(i).getMaDatBan())) {
                dsDatBan.remove(i);
                check = true;
                break;
            }
        }
        if (check) {
            System.out.println("Xóa lời đặt bàn thành công!");
        } else {
            System.out.println("Không tìm thấy lời đặt bàn với mã: " + MaSo);
        }
    }

    // 4. Tìm lời đặt bàn
    public LoiDatBan timLoiDatBan(String maDatBan) {
        for (LoiDatBan loi : dsDatBan) {
            if (loi.getMaDatBan().equals(maDatBan)) {
                return loi;
            }
        }
        return null;
    }

    // 5. In danh sách các lời đặt bàn
    public void inDanhSachDatBan() {
        System.out.println("Chi tiết các lời đặt bàn:");
        if (dsDatBan.isEmpty()) {
            System.out.println("Danh sách đặt bàn trống.");
        }
        for (int i = 0; i < dsDatBan.size(); i++) {
            System.out.println("_________Thông tin về lời đặt bàn thứ " + (i + 1) + "_________");
            dsDatBan.get(i).xuatThongTin();
        }
    }

    // 6. In thông tin lời đặt bàn cần tìm
    public void inLoiDatBan(String maDatBan) {
        LoiDatBan loiDatBan = timLoiDatBan(maDatBan);
        if (loiDatBan != null) {
            loiDatBan.xuatThongTin();
        } else {
            System.out.println("Không tìm thấy lời đặt bàn với mã: " + maDatBan);
        }
    }

    // 7. Tính tổng doanh thu
    public void tinhTongDoanhThu() {
        this.tongDoanhThu = 0; // Reset trước khi tính
        for (LoiDatBan loiDatBan : dsDatBan) {
            tongDoanhThu += loiDatBan.getTongTienMonAn();
        }
    }

    // 8. Cập nhật File
    public void capNhatFile() {
        try (FileWriter writer = new FileWriter("./data/ListLoiDatBan.txt")) {
            // Ghi từng lời đặt bàn
            for (LoiDatBan loi : dsDatBan) {
                // Ghi thông tin theo định dạng
                writer.write(loi.getMaDatBan() + ","); // Mã đặt bàn
                writer.write(loi.getbanAn().getMaBan() + ","); // Mã bàn ăn
                writer.write(loi.getSoLuongMonAn() + ","); // Số lượng món ăn
                writer.write(String.join(",", loi.getDsMonAn()) + ","); // Danh sách món ăn
                writer.write(loi.getSoLuongKhachHang() + ","); // Số lượng khách hàng
                writer.write(loi.khachHang().getmaKhachHang() + ",");
                writer.write(loi.khachHang().getHoVaTen() + ","); // Tên khách hàng
                writer.write(loi.getTongTienMonAn() + ","); // Tổng tiền món ăn
                writer.write(loi.getMaNhanVien() + "\n"); // Mã nhân viên
            }

            // Ghi tổng doanh thu ở dòng cuối cùng
            writer.write(String.valueOf(tongDoanhThu));

            System.out.println("Cập nhật file thành công!");
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    // 9. Cập nhật Data
    public void capNhatData(DanhSachBanAn dsBanAn, ArrayList<MonAn> dsMonAn) {
        String filePath = "./data/ListLoiDatBan.txt";
        File file = new File(filePath);

        dsDatBan.clear();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Nếu là dòng tổng doanh thu
                if (!line.contains(",")) {
                    this.tongDoanhThu = Double.parseDouble(line);
                    continue;
                }

                String[] parts = line.split(",");

                // Tạo đối tượng LoiDatBan mới
                LoiDatBan loiDatBan = new LoiDatBan();

                // Set các thuộc tính từ dữ liệu file
                loiDatBan.setMaDatBan(parts[0]); // Mã đặt bàn

                String maBanAn = parts[1]; // Mã bàn ăn
                for (int i = 0 ; i < dsBanAn.getDSBanAn().size() ; i++) {
                    if (maBanAn.equals(dsBanAn.getDSBanAn().get(i).getMaBan())) {
                        loiDatBan.setbanAn(dsBanAn.getDSBanAn().get(i));
                        break;
                    }
                }
                loiDatBan.getbanAn().setTinhTrangBan(true);

                int soLuongMonAn = Integer.parseInt(parts[2]); // Số lượng món ăn
                loiDatBan.setSoLuongMonAn(soLuongMonAn);

                ArrayList<String> dsMonAnTemp = new ArrayList<>();
                for (int i = 0; i < soLuongMonAn; i++) {
                    dsMonAnTemp.add(parts[3 + i]); // Danh sách món ăn
                }
                loiDatBan.setDsMonAn(dsMonAnTemp);

                loiDatBan.setSoLuongKhachHang(Integer.parseInt(parts[3 + soLuongMonAn])); // Số lượng khách hàng

                KhachHang khachHang = new KhachHang(parts[4 + soLuongMonAn], parts[5 + soLuongMonAn]);
                loiDatBan.setkhachHang(khachHang);

                loiDatBan.setTongTienMonAn(Double.parseDouble(parts[6 + soLuongMonAn])); // Tổng tiền món ăn

                loiDatBan.setMaNhanVien(parts[7 + soLuongMonAn]); // Mã nhân viên

                // Thêm lời đặt bàn vào danh sách
                dsDatBan.add(loiDatBan);
            }

            System.out.println("Cập nhật dữ liệu thành công!");
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file: " + filePath);
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }

    //10
    private BanAn banAn;
    public void setDaTaBanAn (BanAn banAn) {
        this.banAn = banAn;
    }
    public BanAn takeDataBanAn (){
        return banAn;
    }

    // Getter và Setter
    DanhSachLoiDatBan() {
        tongDoanhThu = 0;
    }

    DanhSachLoiDatBan(ArrayList<LoiDatBan> dsDatBan, double tongDoanhThu) {
        this.dsDatBan = dsDatBan;
        this.tongDoanhThu = tongDoanhThu;
    }

    public void setDsDatBan(ArrayList<LoiDatBan> dsDatBan) {
        this.dsDatBan = dsDatBan;
    }

    public ArrayList<LoiDatBan> getDsDatBan() {
        return dsDatBan;
    }

    public void setTongDoanhThu(double tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }

    public double getTongDoanhThu() {
        return tongDoanhThu;
    }
}
