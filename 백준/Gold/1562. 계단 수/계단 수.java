import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    final static long MOD = 1_000_000_000;

    static long[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new long[N + 1][10][1 << 10];

        for (int j = 1; j <= 9; j++) {
            dp[1][j][1 << j] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k < (1 << 10); k++) {
                    if (dp[i - 1][j][k] == 0) {
                        continue;
                    }

                    long count = dp[i - 1][j][k];

                    if (j - 1 >= 0) {
                        int next_digit = j - 1;
                        int next_bit = k | (1 << next_digit);

                        dp[i][next_digit][next_bit] = (dp[i][next_digit][next_bit] + count) % MOD;
                    }

                    if (j + 1 <= 9) {
                        int next_digit = j + 1;
                        int next_bit = k | (1 << next_digit);

                        dp[i][next_digit][next_bit] = (dp[i][next_digit][next_bit] + count) % MOD;
                    }
                }
            }
        }

        long result = 0;
        int finalBit = (1 << 10) - 1;

        for (int j = 0; j <= 9; j++) {
            result = (result + dp[N][j][finalBit]) % MOD;
        }

        System.out.println(result);
    }
}
