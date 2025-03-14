import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] adjList;
    static boolean[] visited;
    static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 도시의 개수
        int M = Integer.parseInt(st.nextToken());  // 도로의 개수
        int K = Integer.parseInt(st.nextToken());  // 최단 거리
        int X = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
        }

        bfs(X, K);

        Collections.sort(answer);
        if (answer.isEmpty()) {
            System.out.println(-1);
            return;
        }
        
        for (int node : answer) {
            System.out.println(node);
        }
    }

    private static void bfs(int X, int k) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(X, 0));
        visited[X] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.distance == k) {
                answer.add(cur.dest);
            }

            for (int next : adjList[cur.dest]) {
                if (!visited[next]) {
                    queue.add(new Node(next, cur.distance + 1));
                    visited[next] = true;
                }
            }
        }
    }

    static class Node {
        int dest;
        int distance;

        public Node(final int dest, final int distance) {
            this.dest = dest;
            this.distance = distance;
        }
    }
}
