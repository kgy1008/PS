import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 포도주의 양

        int[] wine = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        if (n == 1) {
            System.out.println(wine[1]);
            return;
        }

        int[] dp = new int[n + 1];
        dp[1] = wine[1];
        dp[2] = wine[1] + wine[2];
        for (int i = 3; i < n + 1; i++) {
            dp[i] = Math.max(dp[i - 3] + wine[i - 1], dp[i - 2]) + wine[i];
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }

        System.out.println(dp[n]);
    }
}
