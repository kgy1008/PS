import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            String s = br.readLine();
            int result = isPalindrome(s, 0, s.length() - 1, false);
            sb.append(result).append('\n');
        }
        br.close();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int isPalindrome(String s, int left, int right, boolean removed) {
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                if (removed) {
                    return 2;
                }

                // 유사 팰린드롬 검사
                boolean removeLeft = checkSimilarPalindrome(s, left + 1, right);
                boolean removeRight = checkSimilarPalindrome(s, left, right - 1);

                if (removeLeft || removeRight) {
                    return 1;
                } else {
                    return 2;
                }
            }
        }

        if (removed) {
            return 1; // 유사 팰린드롬
        }
        return 0; // 팰린드롬
    }

    private static boolean checkSimilarPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}
