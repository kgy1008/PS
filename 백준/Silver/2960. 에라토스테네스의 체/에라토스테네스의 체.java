import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static boolean[] isPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        int answer = solve(k, n);
        System.out.println(answer);

        br.close();
    }

    static int solve(int k, int n) {
        isPrime[0] = isPrime[1] = false;
        int cnt = 0;

        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i; j <= n; j += i) {
                    if (isPrime[j]) {
                        isPrime[j] = false;
                        cnt++;

                        if (cnt == k) {
                            return j;
                        }
                    }
                }
            }
        }
        return 1;
    }
}
