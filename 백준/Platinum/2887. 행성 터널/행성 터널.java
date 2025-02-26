import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;
    static List<Edge> edges = new ArrayList<>();
    static long distance = 0L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 행성 개수
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        List<int[]> xList = new ArrayList<>();
        List<int[]> yList = new ArrayList<>();
        List<int[]> zList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            xList.add(new int[]{x, i});
            yList.add(new int[]{y, i});
            zList.add(new int[]{z, i});
        }

        xList.sort((Comparator.comparingInt(o -> o[0])));
        yList.sort((Comparator.comparingInt(o -> o[0])));
        zList.sort((Comparator.comparingInt(o -> o[0])));

        for (int i = 0; i < n - 1; i++) {
            edges.add(new Edge(xList.get(i)[1], xList.get(i + 1)[1], xList.get(i + 1)[0] - xList.get(i)[0]));
            edges.add(new Edge(yList.get(i)[1], yList.get(i + 1)[1], yList.get(i + 1)[0] - yList.get(i)[0]));
            edges.add(new Edge(zList.get(i)[1], zList.get(i + 1)[1], zList.get(i + 1)[0] - zList.get(i)[0]));
        }

        edges.sort(Comparator.comparingLong(o -> o.cost));
        kruskal();

        System.out.println(distance);
    }

    static void kruskal() {
        for (Edge edge : edges) {
            if (find(edge.to) != find(edge.from)) {
                union(edge.to, edge.from);
                distance += edge.cost;
            }
        }
    }

    static void union(int a, int b) {
        int p1 = find(a);
        int p2 = find(b);

        parent[p2] = p1;
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static class Edge {
        int from;
        int to;
        long cost;

        public Edge(final int from, final int to, final long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
