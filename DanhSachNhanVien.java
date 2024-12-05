import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class DanhSachNhanVien implements IThemSuaXoa{
    private ArrayList<NhanVien> dsNhanVien;
    private final String URL = "./data/ListNhanVien.txt";

    public DanhSachNhanVien() {
        dsNhanVien = new ArrayList<>();
        this.docFile();
    }

    public DanhSachNhanVien(ArrayList<NhanVien> dsnv) {
        this.dsNhanVien = dsnv;
    }

    // 1. Them nhan vien
    public void menuLoaiNhanVien(){
        System.out.println("____________LOẠI NHÂN VIÊN____________");
        System.out.println("1. Đầu bếp");
        System.out.println("2. Quản lý");
        System.out.println("3. Lao công");
        System.out.println("4. Tiếp tân");
        System.out.println("5. Phục vụ");
        System.out.println("0. Thoát");
        System.out.println("__________________________________");
    }
    @Override
    public void themThongTin() {
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            menuLoaiNhanVien();
            System.out.print("Chọn loại nhân viên muốn thêm (1-5): ");
            choice = sc.nextInt(); sc.nextLine();
            NhanVien nvMoi = null;
            if(choice==1){
                nvMoi = new DauBep();
                nvMoi.nhapThongTin(sc);
                dsNhanVien.add(nvMoi);
            }
            else if(choice==2){
                 nvMoi = new QuanLy();
                 nvMoi.nhapThongTin(sc);
                dsNhanVien.add(nvMoi);
            }
            else if(choice==3){
                nvMoi =  new LaoCong();
                nvMoi.nhapThongTin(sc);
                dsNhanVien.add(nvMoi);
            }
            else if(choice==4){
                nvMoi = new TiepTan();
                nvMoi.nhapThongTin(sc);
                dsNhanVien.add(nvMoi);
            }
            else if(choice==5){
                nvMoi = new PhucVu();
                nvMoi.nhapThongTin(sc);
                dsNhanVien.add(nvMoi);
            }
        }while(choice!=0);
    }

    // 2. Tim nhan vien theo ma so
    public NhanVien timNVTheoMaSo(String maSo){
        for(NhanVien nv: dsNhanVien){
            if(nv.getMaNhanVien().equals(maSo)){
                return nv;
            }
        }
        return null;
    }

    // 3. Sua thong tin nhan vien
//    public boolean isMaNhanVienUnique(String newMaNhanVien, NhanVien currentNhanVien) {
//        for (NhanVien nv : dsNhanVien) {
//            // Skip the current employee being edited
//            if (nv != currentNhanVien && nv.getMaNhanVien().equals(newMaNhanVien)) {
//                return false; // Duplicate found
//            }
//        }
//        return true; // No duplicates
//    }
    @Override
    public void suaThongTin(String maSo){
        Scanner sc = new Scanner(System.in);
        try {
            NhanVien a = timNVTheoMaSo(maSo);
            if (a != null) {
                a.suaThongTin(sc);
            } else {
                System.out.println("Lỗi: Không tìm thấy nhân viên");
            }
        }finally {
            sc.close();
        }
    }

    // 4. Xoa nhan vien theo ma
    public void xoaThongTin(String maSo){
        NhanVien a = timNVTheoMaSo(maSo);
        if (a!=null){
            dsNhanVien.remove(a);
            System.out.println("Nhân viên đã được xóa!");
        }
    }

    // 5. In danh sach sach nhan vien
    public void inDanhSachNhanVien() {
        int n = dsNhanVien.size();
        int i = 1;
        for (NhanVien nv : dsNhanVien) {
            if (nv != null) {
                System.out.println("______________THONG TIN NHAN VIEN THU " + i + "_______________");
                nv.xuatThongTin();
                i++;
            }
        }
    }

    // 6. Kiểm tra nhân viên sắp hết hạn hợp đồng
    public void kiemTraDanhSachNhanVienSapHetHopDong(){
        // Lấy năm thực
        LocalDate curDate = LocalDate.now();
        int curYear = curDate.getYear();
        int n = 0;
        System.out.println("Những nhân viên sắp hết hạn hợp đồng");
        for(NhanVien nv: dsNhanVien){
            if (Math.abs(nv.getNgayHetHopDong().getNam()-curYear) <= 1){
                n++;
                System.out.print(n+ "/ Nhân viên: " +nv.getMaNhanVien() + " - " + nv.getHoVaTen()+". Thời hạn: " + nv.getNgayHetHopDong());
            }
        }
        if(n==0){
            System.out.println("Không có nhân viên nào");
        }
    }

    // 7. Loc danh sach theo loai
    public void locDanhSachTheoLoai(){
        int choice;
        Scanner sc = new Scanner(System.in);
        do{
            menuLoaiNhanVien();
            System.out.print("Lựa chọn: "); choice = sc.nextInt();
            if(choice == 1){
                System.out.println("Có tất cả " + DauBep.xuatSoLuongDauBep() + " đầu bếp:");
                int i = 1;
                for (NhanVien nv: dsNhanVien){
                    if (nv instanceof DauBep){
                        System.out.println(i+"/");
                        nv.xuatThongTin();
                        i++;
                    }
                }
            }
            else if (choice == 2){
                System.out.println("Có tất cả " + QuanLy.xuatSoLuongQuanLy() + " quản lý:");
                int i = 1;
                for (NhanVien nv: dsNhanVien){
                    if (nv instanceof QuanLy){
                        System.out.println(i+"/");
                        nv.xuatThongTin();
                        i++;
                    }
                }
            }
            else if (choice == 3){
                System.out.println("Có tất cả " + LaoCong.xuatSoLuongLaoCong() + " lao công:");
                int i = 1;
                for (NhanVien nv:dsNhanVien){
                    if (nv instanceof LaoCong){
                        System.out.println(i+"/");
                        nv.xuatThongTin();
                        i++;
                    }
                }
            }
            else if(choice == 4){
                System.out.println("Có tất cả " + TiepTan.xuatSoLuongTiepTan() + " tiếp tân:");
                int i = 1;
                for (NhanVien nv: dsNhanVien){
                    if (nv instanceof TiepTan) {
                        System.out.println(i+"/");
                        nv.xuatThongTin();
                        i++;
                    }
                }
            }
            else if(choice == 5){
                System.out.println("Có tất cả " + PhucVu.xuatSoLuongPhucVu() + " phục vụ:");
                int i = 1;
                for (NhanVien nv: dsNhanVien){
                    if (nv instanceof  PhucVu){
                        System.out.println(i+"/");
                        nv.xuatThongTin();
                        i++;
                    }
                }
            }
        }
        while(choice!=0);
    }

    // 8. Tính lương nhân viên theo mã
    public double tinhLuongNhanVienTheoMa(String maSo){
        NhanVien a = timNVTheoMaSo(maSo);
        if (a!=null){
            return a.getGioCong() * a.getLuongCoBan();
        }
        return 0;
    }

    // 9. Xuất bảng lương nhân viên
    public void xuatBangLuongNhanVien(){
        DecimalFormat df = new DecimalFormat("#.00");
        int i = 1;
        for (NhanVien nv: dsNhanVien){
            System.out.println(i + "/");
            double salary = nv.getGioCong() * nv.getLuongCoBan();
            String formattedSalary = df.format(salary);
            System.out.println("Nhân viên: " + nv.getMaNhanVien()+ " - " + StringUtils.chuanHoaTen(nv.getHoVaTen())+ ". Lương: $" + formattedSalary);
            i++;
        }
    }

    // 10. Quyet toan bang luong
    public void quyetToanBangLuong(){
        String url = "./data/BangLuongNhanVien.txt";
        DecimalFormat df = new DecimalFormat("#.00");
        File bangLuong = new File(url);
        try { // Ngoai le file chua tao
            if (!bangLuong.exists() && !bangLuong.createNewFile()) {
                throw new IOException("Lỗi: File chưa được tạo");
            }

            try (PrintWriter pw = new PrintWriter(new FileWriter(bangLuong))) {
                for (NhanVien nv : dsNhanVien) {
                    double salary = nv.getGioCong() * nv.getLuongCoBan();
                    String formattedSalary = df.format(salary);
                    pw.println("Nhân viên: " + nv.getMaNhanVien() + " - " + StringUtils.chuanHoaTen(nv.getHoVaTen()) + ". Lương: $" + formattedSalary);
                    nv.resetGioCong();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 11. Doc file
    public void docFile() {
        try {
            this.dsNhanVien.clear();
            File input = new File(this.URL);
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
                NhanVien nvMoi = null;
                String[] dataThanhPhan = data[i].split(",");
//                System.out.println("So luong data thanh phan" + dataThanhPhan.length);
                String maNhanVien = dataThanhPhan[0];
                String loaiNhanVien = StringUtils.layHaiChuCaiDauCuaMa(maNhanVien);
                String tenNhanVien = dataThanhPhan[1];
                String gioiTinh = dataThanhPhan[2];
                // Ngay sinh
                int ngaySinh = Integer.parseInt(dataThanhPhan[3]);
                int thangSinh = Integer.parseInt(dataThanhPhan[4]);
                int namSinh = Integer.parseInt(dataThanhPhan[5]);

                // Ngay ki hop dong
                int ngayKiHopDong = Integer.parseInt(dataThanhPhan[6]);
                int thangKiHopDong = Integer.parseInt(dataThanhPhan[7]);
                int namKiHopDong = Integer.parseInt(dataThanhPhan[8]);

                // Ngay het hop dong,
                int ngayHetHopDong = Integer.parseInt(dataThanhPhan[9]);
                int thangHetHopDong = Integer.parseInt(dataThanhPhan[10]);
                int namHetHopDong = Integer.parseInt(dataThanhPhan[11]);

                double gioCong = Double.parseDouble(dataThanhPhan[12]);
                if (loaiNhanVien.equals("QL")) {
                    nvMoi = new QuanLy(maNhanVien, tenNhanVien, gioiTinh, ngaySinh, thangSinh, namSinh,
                            ngayKiHopDong, thangKiHopDong, namKiHopDong,
                            ngayHetHopDong, thangHetHopDong, namHetHopDong, gioCong);
                } else if (loaiNhanVien.equals("LC")) {
                    nvMoi = new LaoCong(maNhanVien, tenNhanVien, gioiTinh, ngaySinh, thangSinh, namSinh,
                            ngayKiHopDong, thangKiHopDong, namKiHopDong,
                            ngayHetHopDong, thangHetHopDong, namHetHopDong, gioCong);
                } else if (loaiNhanVien.equals("DB")) {
                    String capBac = dataThanhPhan[13];
                    nvMoi = new DauBep(maNhanVien, tenNhanVien, gioiTinh, ngaySinh, thangSinh, namSinh,
                            ngayKiHopDong, thangKiHopDong, namKiHopDong,
                            ngayHetHopDong, thangHetHopDong, namHetHopDong, gioCong, capBac);
                } else if (loaiNhanVien.equals("TT")) {
                    nvMoi = new TiepTan(maNhanVien, tenNhanVien, gioiTinh, ngaySinh, thangSinh, namSinh,
                            ngayKiHopDong, thangKiHopDong, namKiHopDong,
                            ngayHetHopDong, thangHetHopDong, namHetHopDong, gioCong);
                } else if (loaiNhanVien.equals("PV")) {
                    ArrayList<String> dsBan = new ArrayList<>();
                    String banPhuTrach1 = null;
                    if (dataThanhPhan.length == 14) {
                        banPhuTrach1 = dataThanhPhan[13];
                        dsBan.add(banPhuTrach1);
                    }
                    nvMoi = new PhucVu(maNhanVien, tenNhanVien, gioiTinh, ngaySinh, thangSinh, namSinh,
                            ngayKiHopDong, thangKiHopDong, namKiHopDong,
                            ngayHetHopDong, thangHetHopDong, namHetHopDong, gioCong);
                }
                this.dsNhanVien.add(nvMoi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 12. Cap nhat lai file
    public void capNhatLaiFile(){
        try{
            File output = new File(this.URL);
            if (!output.exists()) {
                output.createNewFile();
            }
            PrintWriter pw = new PrintWriter(output);
            for (NhanVien nv : dsNhanVien) {
                if (nv!=null)
                    pw.println(nv);
            }
            System.out.println("File đã được cập nhật.");
            pw.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    // 13. Kiem tra nhan vien co phai quan ly khong
    public boolean kiemTraNhanVienCoPhaiQuanLy(String ma){
        for (NhanVien nv: dsNhanVien){
            if (nv.getMaNhanVien().equals(ma)){
                return true;
            }
        }
        return false;
    }

    // 14. Chon ngau nhien phuc vu con ranh
    public NhanVien chonNgauNhienPhucVuConRanh(){
        if (dsNhanVien == null || dsNhanVien.isEmpty()) {
            return null;
        }
        for (NhanVien nv: dsNhanVien){
            if (nv instanceof PhucVu && ((PhucVu) nv).getDSBanPhuTrach().size() < 2){
                return nv;
            }
        }
        return null;
    }

    // 15. In ra so luong moi loai nhan vien
    public void inSoLuongMoiLoaiNV(){
        System.out.println("Số quản lý:  " + QuanLy.xuatSoLuongQuanLy() + " nhân viên.");
        System.out.println("Số đầu bếp:  " + DauBep.xuatSoLuongDauBep() + " nhân viên.");
        System.out.println("Số lao công:  " + LaoCong.xuatSoLuongLaoCong() + " nhân viên.");
        System.out.println("Số tiếp tân:  " + TiepTan.xuatSoLuongTiepTan() + " nhân viên.");
        System.out.println("Số phục vụ:  " + PhucVu.xuatSoLuongPhucVu() + " nhân viên.");
    }

    public ArrayList<NhanVien> getDS() {
        return dsNhanVien;
    }

}
