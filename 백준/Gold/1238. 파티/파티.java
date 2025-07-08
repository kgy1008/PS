import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = 987_654_321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 학생 수 (마을 수)
        int m = Integer.parseInt(st.nextToken()); // 도로 수
        int x = Integer.parseInt(st.nextToken()); // 파티 장소

        List<Node>[] adjList = new ArrayList[n + 1];
        List<Node>[] reverseAdjList = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
            reverseAdjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            adjList[a].add(new Node(b, t));
            reverseAdjList[b].add(new Node(a, t)); 
        }
        
        int[] distFromX = dijkstra(x, adjList, n);
        int[] distToX = dijkstra(x, reverseAdjList, n);

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (i != x) {
                answer = Math.max(answer, distToX[i] + distFromX[i]);
            }
        }

        System.out.println(answer);
    }

    private static int[] dijkstra(int start, List<Node>[] graph, int n) {
        int[] distance = new int[n + 1];
        Arrays.fill(distance, INF);
        boolean[] visited = new boolean[n + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));

        distance[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (visited[current.dest]) {
                continue;
            }

            visited[current.dest] = true;

            for (Node next : graph[current.dest]) {
                if (distance[next.dest] > distance[current.dest] + next.cost) {
                    distance[next.dest] = distance[current.dest] + next.cost;
                    pq.add(new Node(next.dest, distance[next.dest]));
                }
            }
        }

        return distance;
    }

    private static class Node {
        int dest;
        int cost;

        Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
}
