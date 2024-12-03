import java.util.Scanner;

public class KhachHang implements INhapXuat{
    Scanner sc = new Scanner(System.in);
    private String maKhachHang;
    private String hoVaTen;

    public KhachHang() {
        maKhachHang = "";
        hoVaTen = "";
    }

    public KhachHang(String maKhachHang, String hoVaTen) {
        this.maKhachHang = maKhachHang;
        this.hoVaTen = hoVaTen;
    }

    public String getmaKhachHang() {
        return maKhachHang;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    @Override
    public void nhapThongTin(Scanner sc) {
        System.out.println("Mời nhập mã số khách hàng : ");
        String Ma = sc.nextLine();

        System.out.println("Mời nhập tên khách hàng : ");
        String HovaTen = sc.nextLine();

        setMaKhachHang(Ma);
        setHoVaTen(HovaTen);
    }

    @Override
    public void xuatThongTin() {
        System.out.println("Mã của khách hàng là: " + getmaKhachHang());
        System.out.println("Họ và tên của khách hàng là: " + getHoVaTen());
    }

    public void suaThongTin() {
        int count = 0;

        while (true) {

            if(count == 5) {
                System.out.println("Lỗi");
                break;
            }

            System.out.println("1/ Sửa đổi mã số khách hàng.");
            System.out.println("2/ Sửa đổi họ và tên khách hàng.");
            System.out.println("0/ Thoát ra.");
            int choice = 0;
            try {
                choice = sc.nextInt();
            }
            catch (Exception e) {
                System.out.println("Lỗi: Kiểu dữ liệu không đúng");
            }
            sc.nextLine();

            if (choice == 1) {
                System.out.println("Mời nhập mã số khách hàng: ");
                String Ma = sc.nextLine();
                setMaKhachHang(Ma);
            }
            else if (choice == 2) {
                System.out.println("Mời nhậ[ họ và tên khách hàng: ");
                String HovaTen = sc.nextLine();
                setHoVaTen(HovaTen);
            }
            else if (choice == 0) {
                break;
            }
            else  {
                count++;
            }
        }
    }
}
