import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static int[] dist;
    static boolean[] visited;
    static List<Node>[] adjList;
    static int[] trace;
    static Deque<Integer> stack = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 도시의 개수
        int m = Integer.parseInt(br.readLine()); // 버스의 개수

        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        visited = new boolean[n + 1];
        adjList = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            adjList[i] = new ArrayList<>();
        }
        trace = new int[n + 1];

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());  // 출발점
        int end = Integer.parseInt(st.nextToken());  // 도착점

        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.dest]) {
                continue;
            }

            visited[cur.dest] = true;

            for (Node next : adjList[cur.dest]) {
                if (dist[next.dest] > cur.cost + next.cost) {
                    dist[next.dest] = cur.cost + next.cost;
                    pq.add(new Node(next.dest, dist[next.dest]));

                    trace[next.dest] = cur.dest;
                }
            }
        }

        System.out.println(dist[end]);  // 거리 출력
        findTrace(start, end);
        System.out.println(stack.size());

        for (int s : stack) {
            System.out.print(s + " ");
        }
    }

    static void findTrace(int start, int end) {
        if (end == start) {
            stack.push(start);
            return;
        }

        stack.push(end);
        findTrace(start, trace[end]);
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
