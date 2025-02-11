import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;
    static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        parent = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        while (e-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            edges.add(new Edge(a, b, c));
        }

        edges.sort(Comparator.comparingLong(o -> o.weight));
        long answer = kruskal(v);
        bw.write(String.valueOf(answer));
        bw.flush();

        br.close();
        bw.close();
    }

    static long kruskal(int v) {
        long distance = 0L;
        int edgeCount = 0;

        for (Edge edge : edges) {
            if (find(edge.from) != find(edge.to)) { // 같은 집합이 아니라면
                union(edge.from, edge.to); // 연결하기
                edgeCount++;
                distance += edge.weight;
            }

            if (edgeCount == v - 1) {
                return distance;
            }
        }
        return -1;
    }

    static void union(int a, int b) {
        int p1 = find(a);
        int p2 = find(b);

        parent[p2] = p1;
    }

    static int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }

    static class Edge {
        int from;
        int to;
        long weight;

        public Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
