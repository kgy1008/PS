import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 60000001;

    static List<Edge> edges = new ArrayList<>();
    static long[] dist;  // 거리 배열을 long 타입으로 변경

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());  // 도시의 개수 -> 정점의 개수
        int m = Integer.parseInt(st.nextToken());  // 노선의 개수 -> 간선의 개수

        // 거리 초기화
        dist = new long[n + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.add(new Edge(a, b, c));
        }
        br.close();

        // 벨만포드
        boolean hasCycle = bellmanFord(n, m);

        if (hasCycle) {
            System.out.println("-1");
        } else {
            for (int i = 2; i <= n; i++) {
                if (dist[i] == INF) {
                    System.out.println("-1");
                } else {
                    System.out.println(dist[i]);
                }
            }
        }
    }

    static boolean bellmanFord(int n, int m) {
        for (int i = 0; i < n - 1; i++) { // (정점의 개수 - 1) 만큼 반복
            for (int j = 0; j < m; j++) {  // 간선의 개수만큼 반복
                Edge edge = edges.get(j); // 현재 간선

                // 현재 간선의 출발 정점에서의 거리가 INF가 아닌 경우에만 갱신
                if (dist[edge.v] != INF && dist[edge.w] > dist[edge.v] + edge.cost) {
                    dist[edge.w] = dist[edge.v] + edge.cost;
                }
            }
        }

        // 음수 가중치 확인
        for (int i = 0; i < m; i++) {
            Edge edge = edges.get(i); // 현재 간선

            // 음수 사이클 존재 확인
            if (dist[edge.v] != INF && dist[edge.w] > dist[edge.v] + edge.cost) {
                return true;  // 음수 사이클 존재
            }
        }
        return false;
    }

    static class Edge {
        int v; // 나가는 정점
        int w; // 들어오는 정점
        int cost;

        public Edge(int v, int w, int cost) {
            this.v = v;
            this.w = w;
            this.cost = cost;
        }
    }
}
