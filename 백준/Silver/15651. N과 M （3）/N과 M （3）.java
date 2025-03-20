import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] nums;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        br.close();

        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = i + 1;
        }

        solve(M, new ArrayList<>());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void solve(int M, List<Integer> answer) {
        if (M == 0) {
            for (int a : answer) {
                sb.append(a).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            answer.add(nums[i]);
            solve(M - 1, answer);
            answer.remove(answer.size() - 1);
        }
    }
}
