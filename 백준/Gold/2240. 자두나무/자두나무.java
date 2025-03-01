import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[] trees = new int[t];

        for (int i = 0; i < t; i++) {
            trees[i] = Integer.parseInt(br.readLine());
        }

        int[][][] dp = new int[w + 1][t + 1][3];

        for (int i = 1; i <= t; i++) {
            int tree = trees[i - 1];
            for (int m = 0; m <= w; m++) {
                if (m == 0) {
                    if (tree == 1) {
                        dp[0][i][1] = dp[0][i - 1][1] + 1;
                    }
                } else {
                    if (tree == 1) {
                        dp[m][i][1] = Math.max(dp[m][i - 1][1], dp[m - 1][i - 1][2]) + 1;
                        dp[m][i][2] = Math.max(dp[m][i - 1][2], dp[m - 1][i - 1][1]);
                    } else {
                        dp[m][i][1] = Math.max(dp[m][i - 1][1], dp[m - 1][i - 1][2]);
                        dp[m][i][2] = Math.max(dp[m][i - 1][2], dp[m - 1][i - 1][1]) + 1;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i <= w; i++) {
            answer = Math.max(answer, Math.max(dp[i][t][1], dp[i][t][2]));
        }
        System.out.println(answer);
    }
}
