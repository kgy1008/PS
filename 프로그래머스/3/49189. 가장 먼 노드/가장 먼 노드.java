import java.util.*;

class Solution {
    static ArrayList<Integer>[] adj;

    public int solution(int n, int[][] edge) {
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edge) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int[] dist = bfs(n);

        int max = Arrays.stream(dist).max().getAsInt();
        int answer = 0;
        for (int d : dist) {
            if (d == max) {
                answer++;
            }
        }
        return answer;
    }

    static int[] bfs(int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1];

        queue.offer(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : adj[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    dist[next] = dist[cur] + 1; 
                    queue.offer(next);
                }
            }
        }
        return dist;
    }
}
