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
    static boolean[] visited;
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
        visited = new boolean[N];

        solve(M, new ArrayList<>());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void solve(int m, List<Integer> answer) {
        if (m == 0) {
            for (int num : answer) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                answer.add(nums[i]);
                solve(m - 1, answer);
                visited[i] = false;
                answer.remove(answer.size() - 1);
            }
        }
    }
}
