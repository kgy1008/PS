import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        long[] primes = new long[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            primes[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(primes);

        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.offer(1L);

        long result = 0;
        for (int i = 0; i <= n; i++) {
            result = pq.poll();

            for (long prime : primes) {
                long next = result * prime;
                pq.offer(next);

                if (result % prime == 0) {
                    break;
                }
            }
        }
        System.out.println(result);
    }
}
