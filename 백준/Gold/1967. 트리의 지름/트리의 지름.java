import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Node>[] adjList;
    static int n;
    static boolean[] visited;
    static int maxDist = 0;
    static int farthestNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        adjList = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {  
            adjList[i] = new ArrayList<>();
        }


        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[from].add(new Node(to, cost));
            adjList[to].add(new Node(from, cost));
        }

        // 가장 먼 노드 찾기
        visited = new boolean[n + 1];
        dfs(1, 0);

        // 2. 찾은 노드에서 다시 DFS 수행
        visited = new boolean[n + 1];
        maxDist = 0;
        dfs(farthestNode, 0);

        System.out.println(maxDist);
    }

    static void dfs(int node, int cost) {
        if (cost > maxDist) {
            maxDist = cost;
            farthestNode = node;
        }

        visited[node] = true;

        for (Node next : adjList[node]) {
            if (!visited[next.dest]) {
                dfs(next.dest, cost + next.cost);
            }
        }
    }

    static class Node {
        int dest, cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
}
