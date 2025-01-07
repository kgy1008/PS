import java.util.*;

class Solution {
    private static int[] dx = {1,-1,0,0};
    private static int[] dy = {0,0,1,-1};
    private static int n = 0;
    private static int m = 0;
    
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        
        bfs(0,0,maps);
        
        return maps[n-1][m-1] == 1 ? -1 : maps[n-1][m-1];
    }
    
    private static void bfs(int x, int y, int[][] maps) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{x,y});
        
        while (!deque.isEmpty()) {
            int[] current = deque.poll();
            int cx = current[0];
            int cy = current[1];
            
            for (int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if (nx >= n || nx < 0 || ny >= m || ny < 0) {
                    continue;
                }
                if (maps[nx][ny] == 1 && !(nx == 0 && ny == 0)) {
                    maps[nx][ny] = maps[cx][cy] + 1;
                    deque.offer(new int[]{nx, ny});
                }
            }
        }
        
    }
}