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
        boolean[] visited = new boolean[adjList.length];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int current = node.dest;

            if (visited[current]) {
                continue;
            }
            visited[current] = true;

            for (Node next : adjList[current]) {
                if (!visited[next.dest] && distance[next.dest] > distance[current] + next.cost) {
                    distance[next.dest] = distance[current] + next.cost;
                    queue.offer(new Node(next.dest, distance[next.dest]));
                }
            }
        }

        return distance[end];
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
