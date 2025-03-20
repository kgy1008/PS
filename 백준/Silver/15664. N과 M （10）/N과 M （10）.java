import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] nums;
    static Set<String> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        Arrays.sort(nums);

        solve(0, M, "");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void solve(int idx, int m, String s) {
        if (m == 0) {
            s = s.trim();
            if (!set.contains(s)) {
                set.add(s);
                sb.append(s).append("\n");
            }
            return;
        }

        for (int i = idx; i < N; i++) {
            solve(i + 1, m - 1, s + nums[i] + " ");
        }
    }
}
