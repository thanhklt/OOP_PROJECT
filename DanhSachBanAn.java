import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DanhSachBanAn implements IThemSuaXoa{
    private static int SOLUONGBANANTOIDA = 20;
    private ArrayList<BanAn> dsBanAn;
    private static String URL="./data/ListBanAn.txt";

    public DanhSachBanAn() {
        dsBanAn = new ArrayList<>();
        this.docFile();
    }

    public DanhSachBanAn(ArrayList<BanAn> dsBanAn) {
        this.dsBanAn = dsBanAn;
    }

    // 1. Them ban an
    public boolean kiemTraSoLuongBanAnDaDayChua(){ // true: nếu đẩy
        return dsBanAn.size() >= SOLUONGBANANTOIDA;
    }
    @Override
    public void themThongTin() {
        if (kiemTraSoLuongBanAnDaDayChua()){
            System.out.println("Lỗi: Số lượng bàn ăn đã đầy.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        BanAn banMoi = new BanAn();
        banMoi.nhapThongTin(sc);
        dsBanAn.add(banMoi);
    }
    // 2. Kiem tra so luong ban an moi loai
    public int soLuongBanAnRanh(){
        int banRanh = 0;
        for (BanAn ba: dsBanAn){
            if (!ba.getTinhTrangBan())
                banRanh++;
        }
        return banRanh;
    }
    public int soLuongBanAnRoi(){
        int banRoi = 0;
        for (BanAn ba: dsBanAn){
            if (ba.getTinhTrangBan())
                banRoi++;
        }
        return banRoi;
    }
    public int soLuongBanAnHienTai(){
        return this.dsBanAn.size();
    }
    public void kiemTraSoLuongBanAnMoiLoai(){
        System.out.println("Số lượng bàn ăn nhà hàng hiện có: " + soLuongBanAnHienTai());
        System.out.println("Số lượng bàn ăn đang rỗi: " + soLuongBanAnRanh());
        System.out.println("Số lượng bàn ăn đang bận: " + soLuongBanAnRoi());
    }

    // 3. Tim ban an theo ma
    public BanAn timBanAnTheoMa(String maBan) {
        for (BanAn ba : dsBanAn) {
            if (ba.getMaBan().equals(maBan)) {
                return ba;
            }
        }
        return null;
    }

    // 4. Xoa ban an theo ma
    @Override
    public void xoaThongTin(String maBan) {
        BanAn ba = timBanAnTheoMa(maBan);
        if (ba != null) {
            dsBanAn.remove(ba);
            System.out.println("Bàn ăn " + maBan + " đã được xóa.");
        } else {
            System.out.println("Lỗi: Không tìm thấy bàn ăn.");
        }
    }

    // 5. Sua thong tin ban an
    @Override
    public void suaThongTin(String maBan){} // Hàm để cho vui =))) ( để Overloading)

    public void suaThongTin(String maBan, DanhSachNhanVien dsNV) {
        BanAn ba = timBanAnTheoMa(maBan);
        Scanner sc = new Scanner(System.in);
        if (ba != null) {
            ba.suaThongTin(sc, dsNV);
        } else {
            System.out.println("Không tìm thấy bàn ăn.");
        }
    }

    // 6. Xuất danh sách bàn ăn
    public void xuatDanhSachBanAn(){
        int i = 1;
        for (BanAn ba: this.dsBanAn){
            System.out.println("_____________BÀN ĂN THỨ: " + i + "______________");
            ba.xuatThongTin();
            i++;
        }
    }

    // 7. Tăng số lượng bàn ăn tối đa
    public static void tangSoLuongBanAnToiDa(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập số lượng bàn ăn tối đa mới (Lưu ý: số lượng mới không được nhỏ hơn số lượng cũ): ");
            int soLuongMoi = sc.nextInt();
            if (SOLUONGBANANTOIDA > soLuongMoi ){
                throw new IOException("Lỗi: Số lượng bàn ăn mới bé hơn số lượng bàn ăn đã có");
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            System.out.println("Lỗi: Kiểu dữ liệu không hợp lệ.");
        }
    }

    // 8. Chọn bàn ăn rảnh
    public BanAn chonBanAnRanh(){
        for (BanAn ba: dsBanAn){
            if (!ba.getTinhTrangBan()){
                return ba;
            }
        }
        return null;
    }
    // 9. Đọc file
    public void docFile(){
        try{
            this.dsBanAn.clear(); // Đảm bảo list trống trước khi đọc
            File input = new File(URL);
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
                String[] dataThanhPhan = data[i].split(",");
                if (dataThanhPhan.length < 2) {
                    System.out.println("Lỗi: Không đủ thông tin tại dòng " + (i + 1));
                    continue;
                }

                String maBan = dataThanhPhan[0];
                boolean tinhTrangBan = dataThanhPhan[1].equals("1");
                BanAn banAnMoi;
                if (tinhTrangBan) {
                    if (dataThanhPhan.length < 3) {
                        System.out.println("Lỗi: Không đủ thông tin nhân viên tại dòng " + (i + 1));
                        continue;
                    }
                    String maNV = dataThanhPhan[2];
                    banAnMoi = new BanAn(maBan, tinhTrangBan, maNV);
                    this.dsBanAn.add(banAnMoi);
                } else {
                    banAnMoi = new BanAn(maBan, tinhTrangBan);
                    this.dsBanAn.add(banAnMoi);
                }
            }
        }catch(Exception e){
            System.out.println("Lỗi: Vui lòng kiểm tra lại file.");
        }
    }

    // 10. Ghi ra file
    public void ghiRaFile(){
        File output = new File(URL);
        try (PrintWriter pw = new PrintWriter(output)) { // Using try-with-resources
            for (BanAn ba : dsBanAn) {
                if (ba != null) {
                    pw.println(ba);
                }
            }
        }
        catch (Exception e) {
            System.out.println("Lỗi: Vui lòng xem lại file.");
        }
    }


    // Getter
    public ArrayList<BanAn> getDSBanAn(){
        return this.dsBanAn;
    }

}
