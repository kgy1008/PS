import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int minPerson;
    static List<Integer>[] graph;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        minPerson = n;
        init(n);

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0;
        dp[node][1] = 1;

        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next);
                dp[node][0] += dp[next][1]; // 현재 노드가 얼리어답터가 아닐 때, 자식 노드는 무조건 얼리어답터여야 함
                dp[node][1] += Math.min(dp[next][0], dp[next][1]); // 현재 노드가 얼리어답터일 때, 자식 노드는 얼리어답터여도 되고 아니어도 됨
            }
        }
    }

    private static void init(int n) {
        graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        dp = new int[n + 1][2]; // [][0]: 해당 노드가 얼리어답터가 아닐 때, [][1]: 해당 노드가 얼리어답터일 때
        visited = new boolean[n + 1];
    }
}
