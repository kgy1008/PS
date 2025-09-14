import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Integer>[] adj;
    static boolean[] visited;
    static int[] parent;
    static boolean[] isCycle;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        // 1. 순환선 찾기 (DFS)
        visited = new boolean[N + 1];
        parent = new int[N + 1];
        isCycle = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (dfs(i, 0)) {
                break;
            }
            Arrays.fill(visited, false);
            Arrays.fill(parent, 0);
        }

        // 2. 거리 계산 (BFS)
        dist = new int[N + 1];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (isCycle[i]) {
                q.add(i);
                dist[i] = 0;
            }
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj[u]) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.add(v);
                }
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(dist[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    private static boolean dfs(int u, int p) {
        visited[u] = true;
        parent[u] = p;

        for (int v : adj[u]) {
            if (v == p) {
                continue;
            }
            if (visited[v]) {
                int cur = u;
                while (cur != v) {
                    isCycle[cur] = true;
                    cur = parent[cur];
                }
                isCycle[v] = true;
                return true;
            } else {
                if (dfs(v, u)) {
                    return true;
                }
            }
        }
        return false;
    }
}
