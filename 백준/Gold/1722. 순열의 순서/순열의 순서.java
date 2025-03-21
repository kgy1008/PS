import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] factorial;
    static boolean[] visited;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        nums = new int[n];
        visited = new boolean[n + 1];
        factorial = new long[n + 1];
        
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        st = new StringTokenizer(br.readLine());
        int q = Integer.parseInt(st.nextToken());

        if (q == 1) {
            // k번째 순열 찾기
            long k = Long.parseLong(st.nextToken());
            findKthPermutation(n, k);
        } else {
            // 주어진 순열의 순서 찾기
            int[] target = new int[n];
            for (int i = 0; i < n; i++) {
                target[i] = Integer.parseInt(st.nextToken());
            }
            findRankOfPermutation(n, target);
        }
    }

    private static void findKthPermutation(int n, long k) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                if (!visited[j]) {
                    if (factorial[n - i - 1] < k) {
                        k -= factorial[n - i - 1];
                    } else {
                        result.append(j).append(" ");
                        visited[j] = true;
                        break;
                    }
                }
            }
        }
        System.out.println(result.toString().trim());
    }

    private static void findRankOfPermutation(int n, int[] target) {
        long rank = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < target[i]; j++) {
                if (!visited[j]) {
                    rank += factorial[n - i - 1];
                }
            }
            visited[target[i]] = true;
        }
        System.out.println(rank);
    }
}
