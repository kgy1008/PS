import java.util.*;

class Solution {
    private static int[][] cost;
    private static int[] dx = {1,-1,0,0};
    private static int[] dy = {0,0,1,-1};
    
    public int solution(String[] maps) {
        cost = new int[maps.length][maps[0].length()];
        int[] start = new int[2];
        int[] lever = new int[2];
        int[] exit = new int[2];
        
        for (int i=0; i<maps.length; i++) {
            String tmp = maps[i];
            for (int j=0; j<tmp.length(); j++) {
                if (tmp.charAt(j) == 'S') {
                    start[0] = i;
                    start[1] = j;
                }
                else if (tmp.charAt(j) == 'L') {
                    lever[0] = i;
                    lever[1] = j;
                }
                else if (tmp.charAt(j) == 'E') {
                    exit[0] = i;
                    exit[1] = j;
                }
            }
        }
        
        int r1 = bfs(start[0], start[1], maps, lever);
        for (int i=0; i<cost.length; i++) {
            for (int j=0; j<cost[0].length; j++) {
                cost[i][j] = 0;
            }
        }
        int r2 = bfs(lever[0], lever[1], maps, exit);
        if (r1 == 0 || r2 == 0) {
            return -1;
        } 
        return r1+r2;
    }
    
    private static int bfs(int x, int y, String[] maps, int[] target) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i=0; i<4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                
                if (nx<0 || nx >= cost.length || ny<0 || ny >= cost[0].length) {
                    continue;
                }
                
                if (maps[nx].charAt(ny) != 'X' && cost[nx][ny] == 0) {
                    cost[nx][ny] = cost[current[0]][current[1]] + 1;
                    if (nx == target[0] && ny == target[1]) {
                        return cost[nx][ny];
                    }
                    queue.offer(new int[]{nx,ny});
                }
            }
        }
        return cost[target[0]][target[1]];
    }
}