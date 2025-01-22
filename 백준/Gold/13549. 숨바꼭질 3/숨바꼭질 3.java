import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
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
        Arrays.fill(num, -1);
        int ans = bfs(n, m);
        System.out.println(ans);
    }

    private static int bfs(int start, int target) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        num[start] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == target) {
                return num[cur];
            }

            if (cur * 2 < num.length && num[cur * 2] == -1) {
                queue.addFirst(cur * 2);
                num[cur * 2] = num[cur];
            }

            if (cur - 1 >= 0 && num[cur - 1] == -1) {
                queue.addLast(cur - 1);
                num[cur - 1] = num[cur] + 1;
            }

            if (cur + 1 < num.length && num[cur + 1] == -1) {
                queue.addLast(cur + 1);
                num[cur + 1] = num[cur] + 1;
            }
        }
        return -1;
    }
}
