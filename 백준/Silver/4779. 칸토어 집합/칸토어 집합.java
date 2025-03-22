import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while ((input = br.readLine()) != null && !input.isEmpty()) {
            int t = Integer.parseInt(input);
            int num = (int) Math.pow(3, t);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < num; i++) {
                sb.append('-');
            }
            String target = sb.toString();
            String answer = solve(target);
            System.out.println(answer);
        }
    }

    private static String solve(String target) {
        if (target.length() == 1) {
            return target;
        }
        int len = target.length();
        int start = len / 3;
        int end = len - (len / 3);

        String left = target.substring(start, end);
        String blank = " ".repeat(len / 3);
        String right = target.substring(end, len);

        String l = solve(left);
        String r = solve(right);
        return l + blank + r;
    }
}
