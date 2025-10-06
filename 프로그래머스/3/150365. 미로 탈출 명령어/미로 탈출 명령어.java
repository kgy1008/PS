import java.util.*;

class Solution {
    private static int n, m, r, c;
    private static String answer = "";
    private static int[] dx = {1, 0, 0, -1}; 
    private static int[] dy = {0, -1, 1, 0}; 
    private static char[] dir = {'d', 'l', 'r', 'u'};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.r = r;
        this.c = c;
        
        int dist = Math.abs(x-r) + Math.abs(y-c);
        if (dist > k) {
            return "impossible";
        }
        
        dfs(x, y, k, "");
        
        return answer.isEmpty() ? "impossible" : answer;
    }
    
    private void dfs(int x, int y, int k, String path) {
        if (!answer.isEmpty()) {
            return;
        }
        
        if (k == 0) {
            if (x == r && y == c) {
                answer = path;
            }
            return;
        }
        
        int dist = Math.abs(x-r) + Math.abs(y-c);
        if (dist > k || (k-dist) % 2 != 0) {
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx < 1 || nx > n || ny < 1 || ny > m) {
                continue;
            }
            
            dfs(nx, ny, k - 1, path + dir[i]);
            if (!answer.isEmpty()) {
                return;
            }
        }
    }
}