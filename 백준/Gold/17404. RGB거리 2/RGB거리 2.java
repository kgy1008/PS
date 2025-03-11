import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] color = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            color[i][0] = r;
            color[i][1] = g;
            color[i][2] = b;
        }

        int answer = 1_000_001;

        // 첫번째 집 빨간색 선택
        int[] dp = new int[3];
        Arrays.fill(dp, 1_000_001);
        dp[0] = color[0][0];
        for (int i = 1; i < n; i++) {
            int red = Math.min(dp[1], dp[2]) + color[i][0];
            int green = Math.min(dp[0], dp[2]) + color[i][1];
            int blue = Math.min(dp[0], dp[1]) + color[i][2];

            dp[0] = red;
            dp[1] = green;
            dp[2] = blue;

            if (i == n - 1) { // 마지막 집
                answer = Math.min(Math.min(answer, dp[1]), dp[2]);
            }
        }

        // 첫번째 집 초록색 선택
        dp = new int[3];
        Arrays.fill(dp, 1_000_001);
        dp[1] = color[0][1];
        for (int i = 1; i < n; i++) {
            int red = Math.min(dp[1], dp[2]) + color[i][0];
            int green = Math.min(dp[0], dp[2]) + color[i][1];
            int blue = Math.min(dp[0], dp[1]) + color[i][2];

            dp[0] = red;
            dp[1] = green;
            dp[2] = blue;

            if (i == n - 1) { // 마지막 집
                answer = Math.min(Math.min(answer, dp[0]), dp[2]);
            }
        }

        // 첫번째 집 파란색 선택
        dp = new int[3];
        Arrays.fill(dp, 1_000_001);
        dp[2] = color[0][2];
        for (int i = 1; i < n; i++) {
            int red = Math.min(dp[1], dp[2]) + color[i][0];
            int green = Math.min(dp[0], dp[2]) + color[i][1];
            int blue = Math.min(dp[0], dp[1]) + color[i][2];

            dp[0] = red;
            dp[1] = green;
            dp[2] = blue;

            if (i == n - 1) { // 마지막 집
                answer = Math.min(Math.min(answer, dp[0]), dp[1]);
            }
        }

        System.out.println(answer);
    }
}
