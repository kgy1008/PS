import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] computers;
    private static long totalComputers = 0L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        computers = new int[n][n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                computers[i][j] = Integer.parseInt(st.nextToken());
                totalComputers += computers[i][j];
                if (computers[i][j] > max) {
                    max = computers[i][j];
                }
            }
        }

        int answer = solve(0, max, n);
        System.out.println(answer);
    }

    private static int solve(int left, int right, int n) {
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            long count = 0L;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    count += Math.min(computers[i][j], mid);
                }
            }

            if (count >= (totalComputers + 1) / 2) { // 절반 이상 켜짐
                answer = mid; // 현재 mid가 가능한 최대값
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }
}
