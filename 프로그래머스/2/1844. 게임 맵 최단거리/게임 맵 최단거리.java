import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        int[][] visited = new int[n][m];
        
        int[] dx = new int[] {1, -1, 0, 0};
        int[] dy = new int[] {0, 0, 1, -1};
        int x = 0; int y = 0;
        int nx = 0; int ny = 0;
        
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {x, y});
        visited[0][0] = 1;  
        
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            x = now[0];
            y = now[1];
            
            for (int i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (maps[nx][ny] != 0 && visited[nx][ny] == 0) {
                    queue.offer(new int[] {nx, ny});
                    visited[nx][ny] = 1;
                    maps[nx][ny] = maps[x][y] + 1;
                }  
            }
        }
        
        if (visited[n - 1][m - 1] == 0) return -1;  
        return maps[n - 1][m - 1];
    }
}
