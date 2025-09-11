import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][][] dp = new int[100_001][5][5]; // (Turn, Left, Right)
        init(dp);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int turn = 1;
        while (true) {
            int n = Integer.parseInt(st.nextToken());

            if (n == 0) {
                break;
            }

            // 오른발을 움직임
            for (int i = 0; i < 5; i++) { // 왼발의 위치
                if (i == n) { // 왼발이 위치한 곳으로 이동 불가능
                    continue;
                }

                for (int j = 0; j < 5; j++) { // 직전의 오른발 위치
                    if (j == 0) {
                        dp[turn][i][n] = Math.min(dp[turn][i][n], dp[turn - 1][i][j] + 2);
                    } else if (j == n) { // 같은 곳
                        dp[turn][i][n] = Math.min(dp[turn][i][n], dp[turn - 1][i][j] + 1);
                    } else if (n % 2 == j % 2) { // 반대편
                        dp[turn][i][n] = Math.min(dp[turn][i][n], dp[turn - 1][i][j] + 4);
                    } else { // 인접
                        dp[turn][i][n] = Math.min(dp[turn][i][n], dp[turn - 1][i][j] + 3);
                    }
                }
            }

            // 왼발을 움직임
            for (int i = 0; i < 5; i++) { // 오른발의 위치
                if (i == n) { // 오른발이 위치한 곳으로 이동 불가능
                    continue;
                }

                for (int j = 0; j < 5; j++) { // 직전의 왼발 위치
                    if (j == 0) {
                        dp[turn][n][i] = Math.min(dp[turn][n][i], dp[turn - 1][j][i] + 2);
                    } else if (j == n) { // 같은 곳
                        dp[turn][n][i] = Math.min(dp[turn][n][i], dp[turn - 1][j][i] + 1);
                    } else if (n % 2 == j % 2) { // 반대편
                        dp[turn][n][i] = Math.min(dp[turn][n][i], dp[turn - 1][j][i] + 4);
                    } else { // 인접
                        dp[turn][n][i] = Math.min(dp[turn][n][i], dp[turn - 1][j][i] + 3);
                    }
                }
            }
            turn++;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                min = Math.min(min, dp[turn - 1][i][j]);
            }
        }
        System.out.println(min);
    }

    private static void init(int[][][] dp) {
        for (int i = 0; i < 100_001; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    dp[i][j][k] = 987654321;
                }
            }
        }
        dp[0][0][0] = 0;
    }
}
