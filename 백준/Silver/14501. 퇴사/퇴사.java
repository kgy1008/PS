import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[] time = new int[n]; // 소요 기간
        int[] money = new int[n]; // 금액

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            money[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n + 1]; // dp[i] : i번째 날까지 진행했을 때 얻을 수 있는 최대 금액

        for (int i = 0; i < n; i++) {
            if (i + time[i] <= n) {  // 범위에 벗어나지 않는다면
                // 종료일(i+time[i])에 얻을 수 있는 최대 금액을 기록
                dp[i + time[i]] = Math.max(dp[i + time[i]], dp[i] + money[i]);
            }
            // 상담을 진행하지 않는 경우 -> 현재 날짜까지의 최댓값을 다음 날짜로 넘겨줌
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }
        System.out.println(dp[n]);
    }
}
