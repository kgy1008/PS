import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] adjList;
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        visited = new boolean[n + 1];
        adjList = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new LinkedList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        dfs(1);

        for (int i = 2; i <= n; i++) {
            System.out.println(parent[i]);
        }
    }

    private static void dfs(int node) {
        visited[node] = true;

        for (int next : adjList[node]) {
            if (!visited[next]) {
                parent[next] = node;
                visited[next] = true;
                dfs(next);
            }
        }
    }
}
