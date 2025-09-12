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

        int start = 1;
        int end = n;

        for (int i = 1; i <= n; i++) {
            if (k >= (n - i)) {
                k -= n - i;
                answer[i - 1] = end--;
            } else {
                answer[i - 1] = start++;
            }
        }

        if (k != 0) {
            System.out.println(-1);
        } else {
            for (int i = 0; i < n; i++) {
                System.out.print(answer[i] + " ");
            }
        }
    }
}
