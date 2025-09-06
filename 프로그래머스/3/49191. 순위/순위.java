import java.util.*;

class Solution {
    static List<Integer>[] adjList;
    static List<Integer>[] reverseAdjList;
    
    public int solution(int n, int[][] results) {
        init(n);
        
        for (int[] result : results) {
            int from = result[0];
            int to = result[1];
            adjList[to].add(from);
            reverseAdjList[from].add(to);
        }
        
        int answer = 0;
        for (int i=1; i<=n; i++) {
            boolean[] visited = new boolean[n+1];
            int cntUpper = countUpper(i, visited) - 1;
            
            Arrays.fill(visited, false);
            int cntLower = countLower(i, visited) - 1;
            
            int totalCnt = cntUpper + cntLower;
            if (totalCnt == n-1) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private static int countUpper(int start, boolean[] visited) {
        visited[start] = true;
        int cnt = 1;
        
        for (int next : adjList[start]) {
            if (!visited[next]) {
                visited[next] = true;
                cnt += countUpper(next, visited);
            }
        }
        return cnt;
    }
    
    private static int countLower(int start, boolean[] visited) {
        visited[start] = true;
        int cnt = 1;
        
        for (int next : reverseAdjList[start]) {
            if (!visited[next]) {
                visited[next] = true;
                cnt += countLower(next, visited);
            }
        }
        return cnt;
    }
    
    private static void init(int n) {
        adjList = new List[n+1];
        reverseAdjList = new List[n+1];
        
        for (int i=0; i<=n; i++) {
            adjList[i] = new ArrayList<>();
            reverseAdjList[i] = new ArrayList<>();
        }
    }
}