import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<Node>[] adj;
    static long[] dp_down;
    static long[] dp_up;
    static int[] size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            adj[u].add(new Node(v, d));
            adj[v].add(new Node(u, d));
        }

        dp_down = new long[N + 1];
        dp_up = new long[N + 1];
        size = new int[N + 1];

        // 첫 번째 DFS: 자식 서브트리 합계 계산
        dfs1(1, 0);

        // 두 번째 DFS: 부모 서브트리 합계 계산
        dfs2(1, 0);

        for (int i = 1; i <= N; i++) {
            System.out.println(dp_down[i] + dp_up[i]);
        }
    }

    // Post-order Traversal
    static void dfs1(int current, int parent) {
        size[current] = 1;
        dp_down[current] = 0;

        for (Node node : adj[current]) {
            if (node.to != parent) {
                dfs1(node.to, current);
                size[current] += size[node.to];
                dp_down[current] += dp_down[node.to] + (long) size[node.to] * node.weight;
            }
        }
    }
    
    static void dfs2(int current, int parent) {
        for (Node node : adj[current]) {
            if (node.to != parent) {
                // 부모 서브트리에서 자식 서브트리를 제외한 나머지 부분의 합
                long rest_sum = dp_down[current] - (dp_down[node.to] + (long) size[node.to] * node.weight);
                // 부모 서브트리에서 자식 서브트리를 제외한 나머지 정점의 수
                long rest_size = N - size[node.to];

                dp_up[node.to] = dp_up[current] + rest_sum + rest_size * node.weight;
                dfs2(node.to, current);
            }
        }
    }

    static class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
