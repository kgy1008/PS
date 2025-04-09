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
    private static final int INF = 1_000_000_000;
    private static List<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine()); // 도시의 개수
        int m = Integer.parseInt(br.readLine()); // 버스의 개수

        adjList = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList[from].add(new Node(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int answer = solve(start, end);
        System.out.println(answer);

    }

    private static int solve(int start, int end) {
        int[] distance = new int[adjList.length];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (distance[node.dist] < node.cost) {
                continue;
            }

            for (Node next : adjList[node.dist]) {
                if (distance[next.dist] > node.cost + next.cost) {
                    distance[next.dist] = node.cost + next.cost;
                    queue.offer(new Node(next.dist, distance[next.dist]));
                }
            }
        }

        return distance[end];
    }

    private static class Node {
        int dist;
        int cost;

        public Node(final int dist, final int cost) {
            this.dist = dist;
            this.cost = cost;
        }
    }
}
