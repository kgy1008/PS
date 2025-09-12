import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        int[] answer = new int[n];

        int max = n; // 될 수 있는 최대값
        int min = 1; // 될 수 있는 최소값

        for (int i = 0; i < n; i++) {
            if ((n - i - 1) <= k) {
                answer[i] = max--;
                k -= (n - i - 1);
            } else {
                answer[i] = min++;
            }
        }

        if (k == 0) {
            for (int i = 0; i < n; i++) {
                System.out.print(answer[i] + " ");
            }
        } else {
            System.out.println(-1);
        }
    }
}
