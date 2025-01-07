import java.util.*;

class Solution {
    private static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (computers[i][j] == 1 && !visited[j]) {
                    dfs(j, computers);
                    answer++;
                }
            }
        }
        return answer;
    }
    
    private static void dfs(int start, int[][] computers) {
        visited[start] = true;
        
        for (int j=0; j<computers.length; j++) {
            if (computers[start][j] == 1) {
                if (!visited[j]) {
                    dfs(j, computers);
                }
            }
        }
    }
}