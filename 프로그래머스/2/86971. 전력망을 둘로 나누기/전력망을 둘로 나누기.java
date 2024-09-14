import java.util.*;

class Solution {
    ArrayList<Integer>[] nodes;
    boolean[] visited;
    int answer;
    
    public int solution(int n, int[][] wires) {
        answer = n-1;
        visited = new boolean[n+1];
        nodes = new ArrayList[n+1];
        
        for (int i=1; i<n+1; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int[] w : wires) {
            nodes[w[0]].add(w[1]);
            nodes[w[1]].add(w[0]);
        }
        
        dfs(1, n);
        return answer;
    }
    
    private int dfs(int start, int n) {
        visited[start] = true;
        
        int sum = 0;
        for (int node : nodes[start]) {
            if (!visited[node]) {
                int cnt = dfs(node, n);
                answer = Math.min(answer, Math.abs(n - 2*cnt));
                sum += cnt;
            }    
        }
        return sum + 1;
    }
}