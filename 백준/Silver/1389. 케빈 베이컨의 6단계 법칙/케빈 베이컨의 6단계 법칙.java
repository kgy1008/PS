import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static ArrayList<Integer>[] person;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        person = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            person[i] = new ArrayList<>();
        }

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            person[a].add(b);
            person[b].add(a);
        }

        int min = Integer.MAX_VALUE;
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            int tmp = 0;
            for (int j = 1; j <= n; j++) {
                tmp += bfs(i, j, n);
            }
            if (tmp < min) {
                min = tmp;
                answer = i;
            }
        }
        System.out.println(answer);
    }

    private static int bfs(int start, int target, int n) {
        if (start == target) {
            return 0;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        queue.add(start);
        visited[start] = true;

        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                int cur = queue.pollFirst();
                for (int neighbor : person[cur]) {
                    if (!visited[neighbor]) {
                        if (neighbor == target) {
                            return level;
                        }
                        queue.add(neighbor);
                        visited[neighbor] = true;
                    }
                }
            }
        }
        throw new IllegalArgumentException("No path found");
    }
}
