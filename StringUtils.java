public class StringUtils{
    public static String chuanHoaTen(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        String[] arr = str.toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word: arr){
            sb.append(Character.toUpperCase(word.charAt(0)));
            sb.append(word.substring(1));
            sb.append(" ");
        }
        return sb.toString().trim();
    }
    public static String layHaiTruCaiDauCuaMa(String ma){
        return ma.substring(0,2);
    }
    public static String[] tachThanhCacPhanBoiDauPhay(String data){
        return data.split(",");
    }
}
