import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final StringBuilder sb = new StringBuilder();
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            N = Integer.parseInt(br.readLine());
            solve(1, "" + 1);
            sb.append("\n");
        }
        br.close();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void solve(int num, String formula) {
        if (num == N) {
            if (isZero(formula)) {
                sb.append(formula).append("\n");
            }
            return;
        }

        int next = num + 1;

        solve(next, formula + " " + next);
        solve(next, formula + "+" + next);
        solve(next, formula + "-" + next);
    }

    static boolean isZero(String str) {
        String target = str.replaceAll(" ", "");
        StringTokenizer st = new StringTokenizer(target, "+-", true);

        int sum = Integer.parseInt(st.nextToken());

        while (st.hasMoreTokens()) {
            String operator = st.nextToken();
            String number = st.nextToken();

            int num = Integer.parseInt(number);

            if (operator.equals("+")) {
                sum += num;
            } else {
                sum -= num;
            }
        }

        return sum == 0;
    }
}
