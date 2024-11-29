import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int lcm = lcm(m, n);
            int answer = -1;

            for (int k = x; k <= lcm; k += m) {  // x를 기준으로 탐색
                if (((k - 1) % n) + 1 == y) {
                    answer = k;
                    break;
                }
            }

            System.out.println(answer);
        }
    }

    private static int gcd(int a, int b) { // 최대 공약수
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private static int lcm(int a, int b) {  // 최소 공배수
        return a * b / gcd(a, b);
    }
}
