import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static List<Node>[] adjList;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        adjList = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        dist = new int[n + 1];

        while (e-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[a].add(new Node(b, c));
            adjList[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int answer = Integer.MAX_VALUE;
        int c1 = dijkstra(1, v1);
        int c2 = dijkstra(v1, v2);
        int c3 = dijkstra(v2, n);

        if (c1 != Integer.MAX_VALUE && c2 != Integer.MAX_VALUE && c3 != Integer.MAX_VALUE) {
            answer = c1 + c2 + c3;
        }

        c1 = dijkstra(1, v2);
        c2 = dijkstra(v2, v1);
        c3 = dijkstra(v1, n);

        if (c1 != Integer.MAX_VALUE && c2 != Integer.MAX_VALUE && c3 != Integer.MAX_VALUE) {
            answer = Math.min(c1 + c2 + c3, answer);
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static int dijkstra(int start, int end) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.cost > dist[cur.dest]) {
                continue;
            }

            for (Node next : adjList[cur.dest]) {
                if (next.cost + dist[cur.dest] < dist[next.dest]) {
                    dist[next.dest] = dist[cur.dest] + next.cost;
                    pq.offer(new Node(next.dest, dist[next.dest]));
                }
            }
        }
        return dist[end];
    }

    static class Node {
        int dest;
        int cost;

        Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
}
