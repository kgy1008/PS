import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] nums;
    static int answer = 0;
    static int s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            solve(0, i, 0);
        }

        System.out.println(answer);
    }

    private static void solve(int idx, int n, int sum) {
        if (n == 0) {
            if (sum == s) {
                answer++;
            }
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            solve(i + 1, n - 1, sum + nums[i]);
        }
    }
}
