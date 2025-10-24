import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static String s;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        String t = br.readLine();

        solve(t);

        System.out.println(answer);
    }

    private static void solve(String str) {
        if (answer == 1) {
            return;
        }

        if (str.length() == s.length()) {
            if (s.equals(str)) {
                answer = 1;
            }
            return;
        }

        StringBuilder sb = new StringBuilder(str);
        String reversed = sb.reverse().toString();

        if (reversed.charAt(reversed.length() - 1) == 'B') {
            solve(reversed.substring(0, reversed.length() - 1));
        }

        if (str.charAt(str.length() - 1) == 'A') {
            solve(str.substring(0, str.length() - 1));
        }
    }
}
