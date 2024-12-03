import java.util.InputMismatchException;
import java.util.Scanner;

public class Date {
    private int ngay,thang,nam;

    public Date(){};

    public Date(int ngay, int thang, int nam){
        this.ngay = ngay;
        this.thang = thang;
        this.nam = nam;
    }

    @Override
    public String toString(){
        return String.format("ngày %d, tháng %d, năm %d\n",this.ngay, this.thang, this.nam);
    }

    public void nhap(Scanner sc) {
        boolean valid = false;
        while (!valid) {
            try {
                // Nhập ngày
                System.out.print("Nhập ngày: ");
                this.ngay = sc.nextInt();

                // Nhập tháng
                System.out.print("Nhập tháng: ");
                this.thang = sc.nextInt();
                if (thang < 1 || thang > 12) {
                    throw new InvalidIdException("Tháng không hợp lệ");
                }

                // Kiểm tra ngày hợp lệ cho tháng
                if ((thang==1||thang ==3||thang==5||thang ==7 || thang==8||thang==10||thang==12) && (ngay > 31 || ngay < 1)) {
                    throw new InvalidIdException("Ngày không hợp lệ");
                } else if ((thang == 4 || thang == 6 || thang == 9 || thang == 11) && (ngay > 30 || ngay < 1)) {
                    throw new InvalidIdException("Ngày không hợp lệ");
                } else if (thang == 2 && (ngay > 29 || ngay < 1)) {
                    throw new InvalidIdException("Ngày không hợp lệ");
                }

                // Nhập năm
                System.out.print("Nhập năm: ");
                this.nam = sc.nextInt();

                // Nếu tất cả hợp lệ, thoát khỏi vòng lặp
                valid = true;

            } catch (InputMismatchException e) {
                System.out.println("Lỗi: input không hợp lệ. Vui lòng nhập lại.");
                sc.nextLine();
            } catch (InvalidIdException e) {
            System.out.println("Lỗi: " + e.getMessage() + ". Vui lòng nhập lại.");
            sc.nextLine();
        }
        }
    }

    // 2. Kiểm tra Date a có trước Date b không
    public static boolean kiemTraDateATruocDateB(Date a, Date b){
        if (a.getNam() > b.getNam())
            return false;
        else if (a.getNam() == b.getNam()){
            if (a.getThang() > b.getThang()) return false;
            else if (a.getThang() == b.getThang()){
                // Không chấp nhận cùng ngày
                if( a.getNgay() >= b.getNgay()) return false;
            }
        }
        return true;
    }

    // Getter
    public int getNgay() {
        return ngay;
    }

    public int getThang() {
        return thang;
    }

    public int getNam() {
        return nam;
    }

}
