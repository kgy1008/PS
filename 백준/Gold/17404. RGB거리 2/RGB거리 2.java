import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] cost = new int[n][3];
        int[][] dp = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());  // R
            cost[i][1] = Integer.parseInt(st.nextToken());  // G
            cost[i][2] = Integer.parseInt(st.nextToken());  // B
        }

        int answer = Integer.MAX_VALUE;

        // 첫번째 집 -> R
        dp[0][0] = cost[0][0];
        dp[0][1] = 1001;  // G 불가능
        dp[0][2] = 1001;  // B 불가능
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
        }
        answer = Math.min(answer, Math.min(dp[n - 1][1], dp[n - 1][2]));  // 마지막 집은 초록 또는 파랑

        // 첫번째 집 -> G
        dp[0][0] = 1001;  // R 불가능
        dp[0][1] = cost[0][1];
        dp[0][2] = 1001;  // B 불가능
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
        }
        answer = Math.min(answer, Math.min(dp[n - 1][0], dp[n - 1][2]));  // 마지막 집은 빨강 또는 파랑

        // 첫번째 집 -> B
        dp[0][0] = 1001;  // R 불가능
        dp[0][1] = 1001;  // G 불가능
        dp[0][2] = cost[0][2];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
        }
        answer = Math.min(answer, Math.min(dp[n - 1][0], dp[n - 1][1]));  // 마지막 집은 빨강 또는 초록

        // 결과 출력
        System.out.println(answer);
    }
}
