import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 지방의 수

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] budgets = new int[N]; // 각 지방의 예산 요청
        for (int i = 0; i < N; i++) {
            budgets[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(budgets);

        int M = Integer.parseInt(br.readLine()); // 총 예산

        int low = 0;
        int high = budgets[N - 1];
        int answer = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            long total = 0;

            for (int budget : budgets) {
                total += Math.min(budget, mid);
            }

            if (total <= M) {
                answer = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(answer);
    }
}
