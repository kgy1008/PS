import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] prefix = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            prefix[i] = prefix[i - 1] + Integer.parseInt(br.readLine());
        }

        int answer = Integer.MIN_VALUE;
        int min = 0;

        for (int i = K; i <= N; i++) {
            min = Math.min(min, prefix[i - K]);
            int cur = prefix[i] - min;

            answer = Math.max(answer, cur);
        }

        System.out.println(Math.max(answer, 0));
    }
}
