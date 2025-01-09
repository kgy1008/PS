import java.util.*;

class Solution {
    private static int count = 0; 
    private static boolean[] visited;
    private static List<Integer>[] adj;
    
    public int solution(int n, int[][] wires) {
        visited = new boolean[n+1];
        adj = new ArrayList[n+1];
        for (int i=1; i<n+1; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] wire : wires) {
            adj[wire[0]].add(wire[1]);
            adj[wire[1]].add(wire[0]);
        }
        
        int answer = n;
        for (int[] wire : wires) {
            dfs(1, wire);
            int tmp = Math.abs(n - 2*count);
            answer = Math.min(tmp, answer);
            count = 0;
            Arrays.fill(visited, false);
        }
        
        return answer;
    }
    
    private static void dfs(int start, int[] wire) {
        visited[start] = true;
        count++;
        
        for (int next : adj[start]) {
            if (!visited[next]) {
                if (start == wire[0] && next == wire[1]) {
                    continue;
                }
                if (start == wire[1] && next == wire[0]) {
                    continue;
                }
                dfs(next, wire);
            }
        }
    }
}