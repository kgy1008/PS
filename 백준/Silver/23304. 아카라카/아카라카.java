import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        if (isAkaraka(str)) {
            System.out.println("AKARAKA");
        } else {
            System.out.println("IPSELENTI");
        }
    }

    public static boolean isAkaraka(String s) {
        int len = s.length();

        if (len <= 1) {
            return true;
        }

        if (!isPalindrome(s)) {
            return false;
        }

        int halfLen = len / 2;
        String up = s.substring(0, halfLen);
        String down = s.substring(len - halfLen);

        return isAkaraka(up) && isAkaraka(down);
    }

    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
