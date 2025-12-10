package com.example.javafx_app.convert;

/**
 * Đổi sang tiếng Việt không dấu thôi:)
 */
public class ConvertToLatinString {
    public static String convert(String string){
        StringBuilder stringBuilder = new StringBuilder(string);
        for(int i = 0; i < stringBuilder.length(); i++){
            switch (stringBuilder.charAt(i)){
                case 'à','á','ả','ã','ạ','ă','ằ','ắ','ẳ','ẵ','ặ','â','ầ','ấ','ẩ','ẫ','ậ':
                    stringBuilder.setCharAt(i, 'a');
                    break;
                case 'è','é','ẻ','ẽ','ẹ','ê','ề','ế','ể','ễ','ệ':
                    stringBuilder.setCharAt(i, 'e');
                    break;
                case 'ì','í','ỉ','ĩ','ị':
                    stringBuilder.setCharAt(i, 'i');
                    break;
                case 'ò','ó','ỏ','õ','ọ','ô','ồ','ố','ổ','ỗ','ộ','ơ','ờ','ở','ỡ','ợ':
                    stringBuilder.setCharAt(i, 'o');
                    break;
                case 'ù','ú','ủ','ũ','ụ','ư','ừ','ứ','ử','ữ','ự':
                    stringBuilder.setCharAt(i, 'u');
                    break;
                case 'ỳ','ý','ỷ','ỹ','ỵ':
                    stringBuilder.setCharAt(i, 'y');
                    break;
                default:
                    break;
            }
        }
        return stringBuilder.toString();
    }
}
