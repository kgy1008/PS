class Solution {
    int answer = 0;
    boolean[] visited;
    
    
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        
        backtracking(k,0,dungeons);
        return answer;
    }
    
    private void backtracking(int k, int cnt, int[][] dungeons) {
        for (int i=0; i<dungeons.length; i++) {
            if (!visited[i] && k>= dungeons[i][0]) {
                visited[i] = true;
                backtracking(k-dungeons[i][1], cnt+1, dungeons);
                answer = Math.max(answer, cnt+1);
                visited[i] = false;
            }
        }
    }
}