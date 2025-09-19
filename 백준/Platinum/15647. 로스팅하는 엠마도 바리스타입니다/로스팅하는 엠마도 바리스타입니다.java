import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<Node>[] adj;
    static long[] dpDown;
    static long[] dpUp;
    static int[] size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            adj[u].add(new Node(v, d));
            adj[v].add(new Node(u, d));
        }

        dpDown = new long[N + 1];
        dpUp = new long[N + 1];
        size = new int[N + 1];

        dfsDown(1, 0);
        dfsUp(1, 0);

        for (int i = 1; i <= N; i++) {
            System.out.println(dpDown[i] + dpUp[i]);
        }
    }

    private static void dfsDown(int curr, int parent) {
        size[curr] = 1;
        dpDown[curr] = 0;

        for (Node next : adj[curr]) {
            if (next.to != parent) {
                dfsDown(next.to, curr);
                size[curr] += size[next.to];
                dpDown[curr] += dpDown[next.to] + (long) size[next.to] * next.weight;
            }
        }
    }

    private static void dfsUp(int curr, int parent) {
        for (Node next : adj[curr]) {
            if (next.to != parent) {
                long restSize = N - size[next.to];
                long restSum = dpDown[curr] - (dpDown[next.to] + (long) size[next.to] * next.weight);

                dpUp[next.to] = dpUp[curr] + restSum + restSize * next.weight;
                dfsUp(next.to, curr);
            }
        }
    }

    static class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
