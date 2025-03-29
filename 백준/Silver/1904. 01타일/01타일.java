import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dp = new int[1_000_001];

        int N = Integer.parseInt(br.readLine());
        dp[1] = 1; // 1
        dp[2] = 2; // 00, 11

        for (int i = 3; i <= 1_000_000; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
        }

        System.out.println(dp[N]);
    }
}
