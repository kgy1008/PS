import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    static boolean[] isPrime = new boolean[1000001];
    static Set<Integer> primeSet = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Arrays.fill(isPrime, true);
        getPrimes();

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            String answer = solve(n);
            bw.write(answer);
        }

        br.close();
        bw.flush();
        bw.close();
    }

    private static String solve(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(n).append(" = ");
        for (int num : primeSet) {
            if (primeSet.contains(n - num)) {
                sb.append(num).append(" + ").append(n - num);
                break;
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    private static void getPrimes() {
        for (int i = 2; i < Math.sqrt(1000001) + 1; i++) {
            if (isPrime[i]) {
                for (int j = 2; i * j < 1000001; j++) {
                    isPrime[i * j] = false;
                }
            }
        }

        for (int i = 2; i < 1000001; i++) {
            if (isPrime[i]) {
                primeSet.add(i);
            }
        }
    }
}
