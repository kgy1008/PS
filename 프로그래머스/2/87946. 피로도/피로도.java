class Solution {
    static int n;
    static int[][] DUNGEONS;
    static boolean[] visited;
    static int answer = 0;
    
    public int solution(int k, int[][] dungeons) {
        n = dungeons.length;
        visited = new boolean[n];
        DUNGEONS = dungeons;
        
        solve(k,0);
        return answer;
    }
    
    private static void solve(int k, int count) {
        for (int i=0; i<n; i++) {
            if (!visited[i] && k >= DUNGEONS[i][0]) {
                visited[i] = true;
                solve(k-DUNGEONS[i][1], count+1);
                visited[i] = false;
            }
        }
        
        answer = Math.max(answer, count);
    }
}