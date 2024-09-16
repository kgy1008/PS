import java.util.*;

class Solution {
    private static final int[] dx = {0,0,-1,1};  
    private static final int[] dy = {1,-1,0,0};  
    
    private static class Point {
        int x;
        int y;
        int cost;  
        int direction;   // 이전에 이동한 방향 (0: 가로, 1: 세로)
        
        public Point(int x, int y, int cost, int direction) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.direction = direction;
        }
    }

    public int solution(int[][] board) {
        int n = board.length;
        int[][][] money = new int[n][n][2];
        
        for (int i = 0; i < n; i++) {
            for (int j=0; j<n; j++) {
                Arrays.fill(money[i][j], Integer.MAX_VALUE);
            }
        }

        ArrayDeque<Point> queue = new ArrayDeque<>();
        
        queue.offer(new Point(0, 0, 0, -1));  // start (-1)
        money[0][0][0] = 0;
        money[0][0][1] = 0;
        
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == 1) continue;
                
                int newDirection = (i < 2) ? 0 : 1;
                
                int newCost = now.cost + (now.direction == -1 || now.direction == newDirection ? 100 : 600);
                
                if (money[nx][ny][newDirection] >= newCost) {
                    money[nx][ny][newDirection] = newCost;
                    queue.offer(new Point(nx, ny, newCost, newDirection));
                }
            }
        }
        
        return Math.min(money[n - 1][n - 1][0], money[n - 1][n - 1][1]) ;
    }
}
