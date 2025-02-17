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
            int n = Integer.parseInt(br.readLine());

            int[][] sticker = new int[2][n];

            for (int j = 0; j < 2; j++) {
                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < n; i++) {
                    sticker[j][i] = Integer.parseInt(st.nextToken());
                }
            }

            if (n == 1) {
                System.out.println(Math.max(sticker[0][0], sticker[1][0]));
                continue;
            }

            int[][] dp = new int[2][n];
            dp[0][0] = sticker[0][0];
            dp[1][0] = sticker[1][0];
            dp[0][1] = sticker[0][1] + sticker[1][0];
            dp[1][1] = sticker[1][1] + sticker[0][0];

            for (int i = 2; i < n; i++) {
                dp[1][i] = Math.max(dp[0][i - 2], dp[0][i - 1]) + sticker[1][i];
                dp[0][i] = Math.max(dp[1][i - 2], dp[1][i - 1]) + sticker[0][i];
            }

            System.out.println(Math.max(dp[0][n - 1], dp[1][n - 1]));
        }

    }
}
