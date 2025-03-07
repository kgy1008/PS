import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 실행 중인 앱 개수
        int M = Integer.parseInt(st.nextToken());  // 필요한 메모리 크기

        int[] memory = new int[N];
        int[] cost = new int[N];
        int sumCost = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            sumCost += cost[i];  // 비용의 최댓값 계산
        }

        int[] dp = new int[sumCost + 1];
        Arrays.fill(dp, 0);

        int answer = sumCost;
        for (int i = 0; i < N; i++) {
            int m = memory[i]; // 앱이 확보하는 메모리
            int c = cost[i];   // 앱을 종료할 때 드는 비용

            // 뒤에서부터 갱신하여 중복 사용 방지
            for (int j = sumCost; j >= c; j--) {
                dp[j] = Math.max(dp[j], dp[j - c] + m);
                if (dp[j] >= M) {
                    answer = Math.min(answer, j);
                }
            }
        }

        System.out.println(answer);
    }
}
