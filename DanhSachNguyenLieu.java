import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class DanhSachNguyenLieu implements IThemSuaXoa{
    private ArrayList<NguyenLieu> dsNguyenLieu;
    private static String URL = "./data/ListNguyenLieu.txt";

    public DanhSachNguyenLieu(){
        this.dsNguyenLieu = new ArrayList<>();
        this.docFile();
    }

    public DanhSachNguyenLieu(ArrayList<NguyenLieu> dsNL){
        this.dsNguyenLieu = dsNL;
    }

    // 1. Them nguyen lieu
    @Override
    public void themThongTin(){
        Scanner sc = new Scanner(System.in);
        NguyenLieu nlMoi = new NguyenLieu();
        nlMoi.nhapThongTin(sc);
        dsNguyenLieu.add(nlMoi);
    }

    // 2. Tim nguyen lieu theo ma hoac theo ten
    public NguyenLieu timNguyenLieuTheoMa(String maNguyenLieu){
        for (NguyenLieu nl: dsNguyenLieu){
            if (nl.getmaNguyenLieu().equals(maNguyenLieu) || nl.gettenNguyenLieu().equals(maNguyenLieu.toLowerCase())){
                return nl;
            }
        }
        return null;
    }


    // 3. Xoa nguyen lieu theo ma
    @Override
    public void xoaThongTin(String maNguyenLieu){
        NguyenLieu nl = this.timNguyenLieuTheoMa(maNguyenLieu);
        if (nl!=null){
            this.dsNguyenLieu.remove(nl);
            System.out.println("Đã xóa thành công nguyên liệu.");
        }
        else System.out.println("Lỗi: Không tìm thấy nguyên liệu");
    }

    // 4. Sua thong tin
    @Override
    public void suaThongTin(String maNguyenLieu){
        NguyenLieu nl = this.timNguyenLieuTheoMa(maNguyenLieu);
        if (nl!=null){
            nl.suaThongTin();
        }
        else{
            System.out.println("Lỗi: Không tìm thấy nguyên liệu");
        }
    }

    // 5. Xuat danh sach nguyen lieu
    public void xuatDanhSachNguyenLieu(){
        int i = 1;
        for (NguyenLieu nl: dsNguyenLieu){
            System.out.println("_________NGUYÊN LIỆU THỨ " +i+"________");
            nl.xuatThongTin();
            System.out.println("_______________________________________");
            i++;
        }
    }

//    // 6. Kiem tra con du nguyen lieu cho mon an
//    public boolean kiemTraConDuNguyenLieuChoMonAn(MonAn a){
//        Map<String,Double> nguyenLieu = a.getnguyenLieuMonAn();
//        for (String key: nguyenLieu.keySet()){
//            for (NguyenLieu nl: dsNguyenLieu){
//                if (nl.getmaNguyenLieu().equals(key)){
//                    // Nếu ko đủ nguyên liệu ngay lập tức return false
//                    if (nl.getsoLuongNguyenLieu() < nguyenLieu.get(key)){
//                        return false;
//                    }
//                }
//            }
//        }
//        return true;
//    }

    // 7. Doc file
    public boolean docFile(){
        try(Scanner sc = new Scanner(new FileReader(URL))){
            this.dsNguyenLieu.clear();
            String[] data = new String[1001];
            int n = 0;
            while(sc.hasNextLine()){
                data[n] = sc.nextLine();
                n++;
            }
            for (int i = 0; i<n;i++){
                String[] dataThanhPhan = data[i].split(",");
                if (dataThanhPhan.length != 3){
                    System.out.println("Lỗi: Số lượng input không đủ");
                }
                else{
                    String maNL = dataThanhPhan[0];
                    String tenNL = dataThanhPhan[1];
                    double soLuongNL = Double.parseDouble(dataThanhPhan[2]);
                    NguyenLieu nlMoi = new NguyenLieu(maNL, tenNL, soLuongNL);
                    dsNguyenLieu.add(nlMoi);
                }
            }
            return true;
        }
        catch(Exception e){
            System.out.println("Lỗi: Vui lòng kiểm tra file nguyên liệu.");
            return false;
        }
    }

    // 8. Xuat ra file
    public boolean xuatRaFile(){
        try(PrintWriter pw = new PrintWriter(new FileWriter(URL))){
            for (NguyenLieu nl: dsNguyenLieu){
                pw.println(nl);
            }
            return true;
        }
        catch(Exception e){
            System.out.println("Lỗi: Vui lòng kiểm tra lại file nguyên liệu.");
            return false;
        }
    }

    // Getter
    public ArrayList<NguyenLieu> getDsNguyenLieu(){ return this.dsNguyenLieu; }
}



