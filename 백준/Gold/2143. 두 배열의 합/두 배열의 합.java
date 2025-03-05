import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        HashMap<Integer, Integer> sumA = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int sum = A[i];
            sumA.put(sum, sumA.getOrDefault(sum, 0) + 1);
            for (int j = i + 1; j < n; j++) {
                sum += A[j];
                sumA.put(sum, sumA.getOrDefault(sum, 0) + 1);
            }
        }

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        HashMap<Integer, Integer> sumB = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int sum = B[i];
            sumB.put(sum, sumB.getOrDefault(sum, 0) + 1);
            for (int j = i + 1; j < m; j++) {
                sum += B[j];
                sumB.put(sum, sumB.getOrDefault(sum, 0) + 1);
            }
        }

        long answer = 0L;
        for (int key : sumA.keySet()) {
            if (sumB.containsKey(t - key)) {
                answer += (long) sumB.get(t - key) * sumA.get(key);
            }
        }
        System.out.println(answer);
    }
}
