import java.util.*;

class Solution {
    private static List<Integer>[] node;
    private static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        node = new ArrayList[n];
        visited = new boolean[n];
        for (int i=0; i<n; i++) {
            node[i] = new ArrayList<>();
        }
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (i != j && computers[i][j] == 1) {
                    node[i].add(j);
                }
            }
        }
        
        int answer = 0;
        for (int i=0; i<n; i++) {
            if (!visited[i] && node[i].isEmpty()) {
                answer++;
            }
            for (int j : node[i]) {
                if (!visited[j]) {
                    dfs(j);
                    answer++;
                }
            }
        }
        return answer;
    }
    
    private static void dfs(int start) {
        visited[start] = true;
        
        for (int next : node[start]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}