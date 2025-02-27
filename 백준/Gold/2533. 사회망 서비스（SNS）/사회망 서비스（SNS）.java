import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] adjList;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        adjList = new ArrayList[n + 1];
        dp = new int[n + 1][2];  // [][0] = 얼리 어답터 X, [][1] = 얼리 어답터 O
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[a].add(b);
            adjList[b].add(a);
        }

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0;  // 이 노드가 얼리 어답터가 아닐 때
        dp[node][1] = 1;  // 이 노드가 얼리 어답터일 때

        for (int child : adjList[node]) {
            if (!visited[child]) {
                dfs(child);
                dp[node][0] += dp[child][1];  // 내가 얼리 어답터 X -> 자식은 얼리 어답터 O
                dp[node][1] += Math.min(dp[child][0], dp[child][1]);  // 내가 얼리 어답터 O -> 자식은 아무거나 가능
            }
        }
    }
}
