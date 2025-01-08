import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        ArrayList<Node>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        int e = Integer.parseInt(st.nextToken());

        int[] cost = new int[n + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);

        int k = Integer.parseInt(br.readLine());
        cost[k] = 0;

        while (e-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[u].add(new Node(v, w));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        pq.add(new Node(k, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cost[cur.dest] < cur.cost) {
                continue;
            }

            for (Node node : adj[cur.dest]) {
                if (cost[node.dest] > node.cost + cur.cost) {
                    cost[node.dest] = node.cost + cur.cost;
                    pq.add(new Node(node.dest, cost[node.dest]));
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (cost[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(cost[i]);
            }
        }
    }

    private static class Node {
        int dest;
        int cost;

        public Node(final int dest, final int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
}
