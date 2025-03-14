import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] nums;
    static int[] cal;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 수의 개수
        nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        cal = new int[4]; // +, -, *, /
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            cal[i] = Integer.parseInt(st.nextToken());  // 연산자의 개수
        }

        combi(n - 1, nums[0], 1);
        System.out.println(max);
        System.out.println(min);
    }

    private static void combi(int n, int sum, int idx) {
        if (n == 0) {
            min = Math.min(sum, min);
            max = Math.max(sum, max);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (cal[i] > 0) {
                cal[i]--;
                if (i == 0) { // 덧셈
                    combi(n - 1, sum + nums[idx], idx + 1);
                } else if (i == 1) { // 뺄셈
                    combi(n - 1, sum - nums[idx], idx + 1);
                } else if (i == 2) { // 곱셈
                    combi(n - 1, sum * nums[idx], idx + 1);
                } else { // 나눗셈
                    combi(n - 1, sum / nums[idx], idx + 1);
                }
                cal[i]++;
            }
        }
    }
}
