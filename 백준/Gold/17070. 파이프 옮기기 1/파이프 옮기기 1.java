import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] board = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[n][n][3]; // 0:가로, 1:세로, 2:대각선

        dp[0][1][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 2; j < n; j++) {
                if (board[i][j] == 0) {
                    dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];
                }
                if (i >= 1 && board[i][j] == 0) {
                    dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];
                }
                if (i >= 1 && board[i][j] == 0 && board[i - 1][j] == 0 && board[i][j - 1] == 0) {
                    dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                }
            }
        }

        int answer = dp[n - 1][n - 1][0] + dp[n - 1][n - 1][1] + dp[n - 1][n - 1][2];
        System.out.println(answer);
    }
}
