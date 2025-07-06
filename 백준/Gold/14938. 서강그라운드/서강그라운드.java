import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static List<Node>[] adjList;
    private static int[] item;
    private static int answer = 0;
    private static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 지역의 개수
        int m = Integer.parseInt(st.nextToken()); // 수색 범위
        int r = Integer.parseInt(st.nextToken()); // 길의 개수

        adjList = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        item = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 시작점
            int b = Integer.parseInt(st.nextToken()); // 끝점
            int l = Integer.parseInt(st.nextToken()); // 길이

            adjList[a].add(new Node(b, l));
            adjList[b].add(new Node(a, l));
        }

        for (int i = 1; i <= n; i++) {
            distance = new int[n + 1];
            Arrays.fill(distance, 987654321);
            distance[i] = 0;
            solve(i, n);

            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (distance[j] <= m) {
                    count += item[j];
                }
            }
            answer = Math.max(count, answer);
        }

        System.out.println(answer);
    }

    private static void solve(int start, int n) {
        boolean[] visited = new boolean[n + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.distance, o2.distance));
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (visited[now.dest]) {
                continue;
            }

            visited[now.dest] = true;

            for (Node next : adjList[now.dest]) {
                if (distance[next.dest] > now.distance + next.distance) {
                    distance[next.dest] = now.distance + next.distance;
                    pq.add(new Node(next.dest, distance[next.dest]));
                }
            }
        }
    }

    private static class Node {
        int dest;
        int distance;

        Node(int dest, int distance) {
            this.dest = dest;
            this.distance = distance;
        }
    }
}
