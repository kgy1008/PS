class Solution {
    int answer = 0;
    boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        backtrack(k, dungeons, 0);
        return answer;
    }
    
    private void backtrack(int k, int[][]dungeons, int cnt) {
        for (int i=0; i<dungeons.length; i++) {
            if (k>= dungeons[i][0] && !visited[i]) {
                visited[i] = true;
                backtrack(k-dungeons[i][1], dungeons, cnt+1);
                answer = Math.max(answer,cnt+1);
                visited[i] = false;
            }
        }
    }
}