import java.util.*;

class Solution {
    private static final int INF = Integer.MAX_VALUE;
    private static final int[] dx = {0, 1, 0, -1}; 
    private static final int[] dy = {1, 0, -1, 0};
    
    public int solution(int[][] board) {
        int n = board.length;
        int[][][] cost = new int[n][n][4]; 
        
        for (int[][] arr : cost) {
            for (int[] row : arr) {
                Arrays.fill(row, INF);
            }
        }
        
        Deque<int[]> queue = new ArrayDeque<>();
        
        for (int i = 0; i < 4; i++) {
            queue.offer(new int[] {0, 0, i, 0});  // x,y,direction,cost
            cost[0][0][i] = 0; // 방향에 따른 비용 초기화
        }
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int dir = cur[2];
            int curCost = cur[3];
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nextCost = curCost + (dir % 2 == i % 2 ? 100 : 600); // 같은 방향: 100, 회전: 600
                
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == 1) {
                    continue;
                }
                
                if (nextCost < cost[nx][ny][i]) {
                    cost[nx][ny][i] = nextCost;
                    queue.offer(new int[] {nx, ny, i, nextCost});
                }
            }
        }
        
        int answer = INF;
        for (int i = 0; i < 4; i++) {
            answer = Math.min(answer, cost[n - 1][n - 1][i]);
        }
        return answer;
    }
}
