import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static String s;
    static String t;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        t = br.readLine();

        solve(t);

        System.out.println(answer);
    }

    static void solve(String str) {
        if (answer == 1) {
            return;
        }

        if (str.length() == s.length()) {
            if (s.equals(str)) {
                answer = 1;
            }
            return;
        }

        if (str.charAt(str.length() - 1) == 'A') {
            solve(str.substring(0, str.length() - 1));
        } else {
            String target = str.substring(0, str.length() - 1);
            StringBuilder sb = new StringBuilder(target);
            solve(sb.reverse().toString());
        }
    }
}
