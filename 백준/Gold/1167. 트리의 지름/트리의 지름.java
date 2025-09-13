import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Node>[] adjList;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int v = Integer.parseInt(br.readLine()); // 정점의 개수
        adjList = new List[v + 1];
        for (int i = 0; i <= v; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < v; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());

            while (true) {
                int end = Integer.parseInt(st.nextToken());
                if (end == -1) {
                    break;
                }
                int cost = Integer.parseInt(st.nextToken());

                adjList[start].add(new Node(end, cost));
            }
        }

        visited = new boolean[v + 1];
        dist = new int[v + 1];
        dfs(1);

        int startNode = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= v; i++) {
            if (dist[i] == Math.max(max, dist[i])) {
                max = dist[i];
                startNode = i;
            }
        }

        visited = new boolean[v + 1];
        dist = new int[v + 1];
        dfs(startNode);
        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= v; i++) {
            if (dist[i] == Math.max(answer, dist[i])) {
                answer = dist[i];
            }
        }
        System.out.println(answer);
    }

    private static void dfs(int start) {
        visited[start] = true;

        for (Node next : adjList[start]) {
            if (!visited[next.dest]) {
                visited[next.dest] = true;
                dist[next.dest] = dist[start] + next.cost;
                dfs(next.dest);
            }
        }
    }

    static class Node {
        int dest;
        int cost;

        public Node(final int dest, final int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
}
