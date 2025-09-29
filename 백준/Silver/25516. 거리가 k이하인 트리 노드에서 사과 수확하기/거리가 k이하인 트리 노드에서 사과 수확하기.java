import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] tree;
    static int[] apple;
    static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 정점의 수
        int k = Integer.parseInt(st.nextToken()); // 한계 거리

        tree = new List[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        apple = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            apple[i] = Integer.parseInt(st.nextToken());
        }

        bfs(k);

        System.out.println(count);
    }

    private static void bfs(int k) {
        boolean[] visited = new boolean[tree.length];
        Deque<Node> deque = new ArrayDeque<>();
        visited[0] = true;
        deque.offer(new Node(0, 0));
        count = apple[0];

        while (!deque.isEmpty()) {
            Node node = deque.poll();
            for (int next : tree[node.id]) {
                if (!visited[next]) {
                    visited[next] = true;
                    if (node.distance + 1 <= k) {
                        deque.offer(new Node(next, node.distance + 1));
                        count += apple[next];
                    }
                }
            }
        }
    }

    static class Node {
        int id;
        int distance;

        Node(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }
    }
}
