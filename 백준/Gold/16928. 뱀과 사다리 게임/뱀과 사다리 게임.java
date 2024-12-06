import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.put(a, b);
        }

        int[] visited = new int[101];
        Arrays.fill(visited, -1);
        visited[1] = 0;

        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(1);

        while (!queue.isEmpty()) {
            int current = queue.pollFirst();

            for (int dice = 1; dice <= 6; dice++) {
                int next = current + dice;

                if (next > 100) continue;

                if (map.containsKey(next)) {
                    next = map.get(next);
                }

                if (visited[next] == -1) {
                    visited[next] = visited[current] + 1;
                    queue.add(next);
                }
            }
        }
        System.out.println(visited[100]);
    }
}
