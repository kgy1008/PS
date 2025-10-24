import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] adj;
    static int[] count;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 정점의 수
        int r = Integer.parseInt(st.nextToken()); // 루트의 번호
        int q = Integer.parseInt(st.nextToken()); // 쿼리의 수

        adj = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        count = new int[n + 1];
        visited = new boolean[n + 1];
        dfs(r);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(count[num]).append("\n");
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int dfs(int root) {
        int cnt = 0;

        visited[root] = true;

        for (int next : adj[root]) {
            if (!visited[next]) {
                cnt += dfs(next);
            }
            count[root] = cnt + 1;
        }

        return cnt + 1;
    }
}
