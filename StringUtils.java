public class StringUtils{
    public static String capitalize(String str) {
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
}
