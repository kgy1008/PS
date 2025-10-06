import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static final int LOG = 17;

    static int N, M;
    static boolean[] visited;
    static ArrayList<Node>[] adjList;
    static int[] depth;
    static int[] distance;
    static int[][] parent;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 노드의 개수
        visited = new boolean[N + 1];
        depth = new int[N + 1];
        distance = new int[N + 1];
        parent = new int[LOG + 1][N + 1];

        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int n = 0; n < N - 1; n++) {
            st = new StringTokenizer(br.readLine());
            int d1 = Integer.parseInt(st.nextToken());
            int d2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjList[d1].add(new Node(d2, w));
            adjList[d2].add(new Node(d1, w));
        }

        bfs(1);

        findAncestors();

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int ancestor = lca(a, b);
            int answer = distance[a] + distance[b] - 2 * distance[ancestor];
            sb.append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void bfs(int root) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(root);
        depth[root] = 0;
        distance[root] = 0;
        visited[root] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            for (Node nextNode : adjList[currentNode]) {
                if (!visited[nextNode.dest]) {
                    visited[nextNode.dest] = true;

                    parent[0][nextNode.dest] = currentNode;
                    depth[nextNode.dest] = depth[currentNode] + 1;
                    distance[nextNode.dest] = distance[currentNode] + nextNode.weight;
                    queue.add(nextNode.dest);
                }
            }
        }
    }

    static void findAncestors() {
        for (int k = 1; k <= LOG; k++) {
            for (int v = 1; v <= N; v++) {
                parent[k][v] = parent[k - 1][parent[k - 1][v]];
            }
        }
    }

    static int lca(int a, int b) {
        if (depth[a] > depth[b]) {
            int temp = b;
            b = a;
            a = temp;
        }

        for (int k = LOG; k >= 0; k--) {
            if (depth[b] - depth[a] >= (1 << k)) {
                b = parent[k][b];
            }
        }

        if (a == b) {
            return a;
        }

        for (int k = LOG; k >= 0; k--) {
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }

        return parent[0][a];
    }

    static class Node {
        int dest;
        int weight;

        public Node(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }
}
