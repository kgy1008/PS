import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int INF = 987654321;
    static long[] distance;
    static List<Edge> edges;
    static int N, M, W;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());

        StringBuilder answer = new StringBuilder();

        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            distance = new long[N + 1];
            edges = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                edges.add(new Edge(s, e, t));
                edges.add(new Edge(e, s, t));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                edges.add(new Edge(s, e, -t));
            }

            // 가상의 노드 0 추가
            for (int i = 1; i <= N; i++) {
                edges.add(new Edge(0, i, 0));
            }

            if (bellmanFord()) {
                answer.append("YES\n");
            } else {
                answer.append("NO\n");
            }
        }

        bw.write(answer.toString());
        bw.flush();
    }

    private static boolean bellmanFord() {
        Arrays.fill(distance, INF);
        distance[0] = 0L; // 시작점 거리 0으로

        for (int i = 0; i <= N; i++) {
            for (Edge edge : edges) {
                if (distance[edge.start] == INF) {
                    continue;
                }
                if (distance[edge.end] > distance[edge.start] + edge.time) {
                    distance[edge.end] = distance[edge.start] + edge.time;
                    if (i == N) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static class Edge {
        int start, end, time;

        public Edge(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }
}
