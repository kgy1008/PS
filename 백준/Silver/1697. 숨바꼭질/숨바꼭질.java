import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        num = new int[100001];
        int ans = bfs(n, m);
        System.out.println(ans);
    }

    private static int bfs(int start, int target) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        num[start] = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == target) {
                return num[cur] - 1;
            }

            int[] nextPositions = {cur - 1, cur + 1, cur * 2};
            for (int next : nextPositions) {
                if (next >= 0 && next < num.length && num[next] == 0) {
                    queue.add(next);
                    num[next] = num[cur] + 1;
                }
            }
        }
        return -1;
    }
}
