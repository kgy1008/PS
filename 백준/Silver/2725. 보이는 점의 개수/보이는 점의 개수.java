import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int c = Integer.parseInt(br.readLine());

        int[] dp = new int[1001];
        dp[1] = 3;
        dp[2] = 5;
        for (int i = 3; i <= 1000; i++) {
            dp[i] = dp[i - 1] + 2 * (count(i));
        }

        while (c-- > 0) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dp[n]);
        }
    }

    private static int count(final int n) {
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (gcd(n, i) == 1) {
                count++;
            }
        }
        return count;
    }

    private static int gcd(int i, int j) {
        if (j == 0) {
            return i;
        }
        return gcd(j, i % j);
    }
}
