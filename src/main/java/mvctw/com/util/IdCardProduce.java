package mvctw.com.util;

import java.util.Random;

public class IdCardProduce {

    static String checkHead = "ABCDEFGHJKLMNPQRSTUVWXYZIO";

    public static void main(String[] args) {
    	Integer sex = 2;
    	generateIdCard(sex);
    }

    public static String generateIdCard(Integer sex) {
        Random r = new Random();
        String s = "";
        int checknum = 0; // 產生前9碼的同時計算產生驗證碼

        // 產生第一個英文字母
        int t = (r.nextInt(26) + 65);
        s += (char) t;
        t = checkHead.indexOf((char) t) + 10;
        checknum += t / 10;
        checknum += t % 10 * 9;

        // 產生第2個數字 (1~2)
        s += Integer.toString(sex);
        checknum += t * 8;

        // 產生後8碼
        for (int i = 2; i < 9; i++) {
            s += Integer.toString(t = r.nextInt(10));
            checknum += t * (9 - i);
        }

        // 完成驗證碼計算
        checknum = (10 - ((checknum) % 10)) % 10;
        s += Integer.toString(checknum);
        System.out.println(s);
		return s;
    }
}
