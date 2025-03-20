import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static Deque<Edge> queue;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 도시의 수
            int M = Integer.parseInt(st.nextToken()); // 비행기의 종류

            parent = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }
            queue = new ArrayDeque<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                queue.add(new Edge(from, to));
            }

            int answer = solve(N);
            sb.append(answer).append("\n");
        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int solve(int n) {
        int EdgeCount = 0;

        while (!queue.isEmpty()) {
            Edge cur = queue.poll();

            if (find(cur.from) != find(cur.to)) { // 연결되지 않았다면
                union(cur.from, cur.to);
                EdgeCount++;
            }

            if (EdgeCount == n - 1) {
                break;
            }
        }

        return EdgeCount;
    }

    private static void union(int a, int b) {
        int p1 = find(a);
        int p2 = find(b);
        parent[p2] = p1;
    }

    private static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    static class Edge {
        int from;
        int to;

        public Edge(final int from, final int to) {
            this.from = from;
            this.to = to;
        }
    }
}
