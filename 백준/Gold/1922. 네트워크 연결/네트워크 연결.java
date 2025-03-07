import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static PriorityQueue<Edge> edges = new PriorityQueue<>(
            (o1, o2) -> Integer.compare(o1.cost, o2.cost)); // 비용 기준 오름차순 정렬 (최소 힙)
    static int N;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
        int M = Integer.parseInt(br.readLine()); // 연결할 수 있는 선의 수

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 3; i <= M + 2; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.offer(new Edge(a, b, c));
        }

        int answer = solve();
        System.out.println(answer);
    }

    static int solve() {
        int edgeCnt = 0;
        int minCost = 0;

        while (!edges.isEmpty()) {
            Edge edge = edges.poll();
            if (find(edge.from) != find(edge.to)) { // 연결되어 있지 않다면
                union(edge.from, edge.to); // 연결
                edgeCnt++;
                minCost += edge.cost;
            }

            if (edgeCnt == N - 1) { // 필요한 모든 간선 연결됨
                break;
            }
        }
        return minCost;
    }

    static void union(int a, int b) {
        int p1 = find(a);
        int p2 = find(b);

        parent[p2] = p1;
    }

    static int find(int n) {
        if (parent[n] == n) {
            return n;
        }
        return parent[n] = find(parent[n]);
    }

    static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
