import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class DanhSachMonAn implements IThemSuaXoa {
    private ArrayList<MonAn> dsMonAn; // Danh sách món ăn
    private static String URL = "./data/ListMonAn.txt";

    // Constructor mặc định
    public DanhSachMonAn() {
        dsMonAn = new ArrayList<>();
        this.docFile();
    }

    // Constructor nhận danh sách món ăn
    public DanhSachMonAn(ArrayList<MonAn> dsMenu) {
        this.dsMonAn = dsMenu;
    }

    // 1. Thêm món ăn mới vào danh sách
    @Override
    public void themThongTin() {
        Scanner sc = new Scanner(System.in);
        MonAn monAn = new MonAn();
        monAn.nhapThongTin(sc); // Sử dụng phương thức nhập từ class MonAn
        dsMonAn.add(monAn);
        System.out.println("Đã thêm món ăn thành công.");
    }

    // 2. Tìm món ăn theo mã
    public MonAn timMonAnTheoMa(String maMon) {
        for (MonAn ma : dsMonAn) {
            if (ma.getmaMonAn().equals(maMon)) {
                return ma;
            }
        }
        return null;
    }

    // 3. Xóa món ăn theo mã
    @Override
    public void xoaThongTin(String maMon) {
        MonAn ma = this.timMonAnTheoMa(maMon);
        if (ma != null) {
            dsMonAn.remove(ma);
            System.out.println("Đã xóa món ân thành công.");
        } else {
            System.out.println("Lỗi: Không tìm thấy món ăn.");
        }
    }

    // 4. Sửa thuộc tính món ăn
    @Override
    public void suaThongTin(String maBan){
        MonAn ma = this.timMonAnTheoMa(maBan);
        if (ma!=null){
            ma.suaThongTin();
        }
        else{
            System.out.println("Lỗi: Không tìm thấy món ăn.");
        }
    }

    // 5. Xuất danh sách món ăn
    public void xuatDanhSachMonAn() {
        int i = 1;
        for (MonAn ma : dsMonAn) {
            if (ma != null) {
                System.out.println("____________MÓN ĂN THỨ " + i + "__________");
                ma.xuatThongTin();
                i++;
            }
        }
    }

    // 6. Doc file
    public boolean docFile() {
        dsMonAn.clear();
        File input = new File(URL);
        try {
            if (!input.exists()) {
                input.createNewFile();
            }
            Scanner sc = new Scanner(input);
            String[] data = new String[1001];
            int n = 0;
            while (sc.hasNextLine()) {
                data[n] = sc.nextLine();
                n++;
            }
            for (int i = 0; i < n; i++) {
                MonAn monAnMoi = null;
                String[] dataThanhPhan = data[i].split(",");
                String maMonAn = dataThanhPhan[0];
                String tenMonAn = dataThanhPhan[1];
                double giaMonAn = Double.parseDouble(dataThanhPhan[2]);
                ArrayList<Map<String, Double>> dsNguyenLieu = new ArrayList<>();
                int soLuongNguuyenLieu = Integer.parseInt(dataThanhPhan[3]);
                Map<String, Double> nguyenlieu = new HashMap<>();
                for (int j = 0; j < soLuongNguuyenLieu * 2; j+=2) { // Vì có 1 cặp nguyên liệu
                    String tenNguyenLieu = dataThanhPhan[4 + j];
                    Double giaNguyenLieu = Double.parseDouble(dataThanhPhan[4 + j + 1]);
                    nguyenlieu.put(tenNguyenLieu, giaNguyenLieu);
                }

                monAnMoi = new MonAn(maMonAn, tenMonAn, giaMonAn, nguyenlieu);
                dsMonAn.add(monAnMoi);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi: Vui lòng kiểm tra lại file món ăn.");
            return false;
        }
    }

    // 6. ghi ra file
    public void ghiRaFile(){
        File output = new File(URL);
        try(PrintWriter pw = new PrintWriter(output)){
            for (MonAn ma: dsMonAn){
                if(ma!=null){
                    pw.println(ma);
                }
            }
        }
        catch(Exception e){
            System.out.println("Lỗi: Vui lòng kiểm tra lại file.");
        }
    }


    // Getter
    public ArrayList<MonAn> getDsMonAn(){ return this.dsMonAn; }
}
