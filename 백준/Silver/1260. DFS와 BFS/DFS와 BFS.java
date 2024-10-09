import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] nums;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        nums = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i < n+1; i++) {
            nums[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nums[a].add(b);
            nums[b].add(a);
        }

        for (int i=1; i<n+1; i++ ) {
            Collections.sort(nums[i]);
        }

        StringBuffer sb = new StringBuffer();
        dfs(v, sb);
        System.out.println(sb);
        Arrays.fill(visited, false);
        bfs(v);
    }

    private static void dfs(int v, StringBuffer sb) {
        sb.append(v).append(" ");
        visited[v] = true;
        for (int i : nums[v]) {
            if (!visited[i]) {
                dfs(i, sb);
            }
        }
    }

    private static void bfs(int v) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        StringBuffer sb = new StringBuffer();
        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            sb.append(node).append(" ");
            for (int i : nums[node]) {
                if (!visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
        System.out.println(sb);
    }
}
