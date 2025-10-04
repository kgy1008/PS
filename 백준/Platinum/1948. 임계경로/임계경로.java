import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Node>[] adj;
    static List<Node>[] revAdj;
    static int[] inDegree;
    static int[] revInDegree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 도시의 개수
        int m = Integer.parseInt(br.readLine()); // 도로의 개수

        // 인접 리스트 초기화
        adj = new List[n + 1];
        revAdj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            revAdj[i] = new ArrayList<>();
        }

        inDegree = new int[n + 1];
        revInDegree = new int[n + 1];

        for (int M = 0; M < m; M++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken()); // 출발 도시
            int end = Integer.parseInt(st.nextToken()); // 도착 도시
            int time = Integer.parseInt(st.nextToken()); // 소요 시간

            Node node = new Node(end, time);

            adj[start].add(node);
            inDegree[end]++;

            Node revNode = new Node(start, time);
            revAdj[end].add(revNode);
            revInDegree[start]++;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()); // 출발 도시
        int E = Integer.parseInt(st.nextToken()); // 도착 도시

        int[] maxTime = new int[n + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(S);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Node next : adj[current]) {
                if (maxTime[next.end] < maxTime[current] + next.time) {
                    maxTime[next.end] = maxTime[current] + next.time;
                }

                inDegree[next.end]--;
                if (inDegree[next.end] == 0) {
                    queue.offer(next.end);
                }
            }
        }

        System.out.println(maxTime[E]);

        int[] revMaxTime = new int[n + 1];
        queue = new ArrayDeque<>();
        queue.offer(E);
        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Node next : revAdj[current]) {
                if (revMaxTime[next.end] < revMaxTime[current] + next.time) {
                    revMaxTime[next.end] = revMaxTime[current] + next.time;
                }

                revInDegree[next.end]--;
                if (revInDegree[next.end] == 0) {
                    queue.offer(next.end);
                }
            }
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (Node next : adj[i]) {
                if (maxTime[i] + next.time + revMaxTime[next.end] == maxTime[E]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    static class Node {
        int end;
        int time;

        public Node(int end, int time) {
            this.end = end;
            this.time = time;
        }
    }
}
